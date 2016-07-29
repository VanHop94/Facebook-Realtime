package com.datasection.facebook.mvc.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datasection.facebook.be.dao.ActionDao;
import com.datasection.facebook.be.dao.CommentDao;
import com.datasection.facebook.be.dao.PostDao;
import com.datasection.facebook.be.entities.ActionInfo;
import com.datasection.facebook.be.entities.ActionObservation;
import com.datasection.facebook.be.entities.CommentDetail;
import com.datasection.facebook.be.entities.HumanDetail;
import com.datasection.facebook.be.entities.ListFixedSize;
import com.datasection.facebook.be.entities.PostDetail;
import com.datasection.facebook.be.entities.ResultInfo;
import com.datasection.facebook.be.entities.UserInfo;
import com.datasection.facebook.utils.Constant;
import com.datasection.facebook.utils.DuplicationException;
import com.datasection.facebook.utils.FacebookUtil;
import com.datasection.facebook.utils.NotFoundPostException;
import com.datasection.facebook.utils.UrlException;
import com.datasection.web.mvc.config.ArgsConstant;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.json.JsonObject;
import com.restfb.types.Page;
import com.restfb.types.Post;

import net.arnx.jsonic.JSON;

@Controller
public class PostsController {

	final static Logger logger = Logger.getLogger(PostsController.class);

	@Autowired
	private PostDao postDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private ActionDao actionDao;
	@Autowired
	private ArgsConstant argsConstant;

	@RequestMapping("post/all")
	public @ResponseBody List<PostDetail> all_posts(HttpSession session) {
		List<PostDetail> listPosts = postDao.queryAllPost((String) session.getAttribute("user_id"));
		return listPosts;
	}

	@RequestMapping(value = "post/crawler", method = RequestMethod.POST)
	public @ResponseBody String add_post(HttpServletRequest request, HttpServletResponse reponse, HttpSession session) {

		// if ((Integer) request.getSession().getAttribute("rule") == 0) {
		// model.addObject("error", "You dont have acceptable permission");
		// return model;
		// }

		// ModelAndView model = new
		// ModelAndView("redirect:../view/post/management");
		JsonObject result = new JsonObject();

		String url = request.getParameter("url");
		long update_util = Long.parseLong(request.getParameter("update_util"));
		logger.info(session.getAttribute("user_id") + "\t" + url);

		if (postDao.getAllPostIsActive((String) session.getAttribute("user_id")) >= argsConstant.getMaxThread()) {
			result.put("message",
					"Số lượng bài theo dõi vượt quá giới hạn cho phép (" + argsConstant.getMaxThread() + " bài)");
			logger.error("Số lượng bài theo dõi vượt quá giới hạn cho phép (" + argsConstant.getMaxThread() + " bài)");
			result.put("status", 0);

			return result.toString();
		}

		if (update_util > ((new Date()).getTime() + argsConstant.getMaxFollowingTime())) {
			result.put("message", "Thời gian theo dõi bài viết tối đa là 1h!");
			logger.error("Thời gian theo dõi bài viết tối đa là 1h!");
			result.put("status", 0);

			return result.toString();
		}

		Post post = null;
		try {
			post = getPost(url, (String) session.getAttribute("user_id"));
		} catch (DuplicationException e) {
			result.put("message", "Bài đăng đã có trong danh sách hoặc không tồn tại trên facebook!");
			result.put("status", 0);
			return result.toString();
		} catch (UrlException e){
			result.put("message", "Địa chỉ đường dẫn không chính xác!");
			result.put("status", 0);
			return result.toString();
		} catch (NotFoundPostException e) {
			result.put("message", "Bài viết không tồn tại");
			result.put("status", 0);
			return result.toString();
		}

		HumanDetail human = new HumanDetail();
		human.setUsername((String) session.getAttribute("user_id"));

		PostDetail p = new PostDetail(0, post.getId(), FacebookUtil.getPageID(url), post.getMessage(), update_util,
				post.getLikesCount(), post.getCommentsCount(), post.getSharesCount(), System.currentTimeMillis(),
				post.getPicture(), post.getCreatedTime().getTime(), human, Constant.POST_IS_UNREAD, System.currentTimeMillis(),(String) session.getAttribute("user_id"));
		boolean checkPost = postDao.addPost(p);
		if (!checkPost) {
			result.put("message", "Không thể thêm post này vào hệ thống. Có thể đã tồn tại rồi!");
			result.put("status", 0);
			return result.toString();
		}

		ListFixedSize<ActionObservation> listFixedSize = new ListFixedSize<ActionObservation>();
		listFixedSize.add(new ActionObservation(0, 0, 0, p.getUpdate_time()));
		
		PostDetail post1 = postDao.queryPost(post.getId(), human.getUsername());
		
		//actionDao.updateActionOfPost(new ActionInfo(1,post1.getId(), p.getUpdate_time(),post.getLikesCount(), post.getCommentsCount(), post.getSharesCount(), listFixedSize, 0));
		actionDao.updateActionOfPost(new ActionInfo(1,post1.getId(), p.getUpdate_time(),0, 0, 0, listFixedSize, 0));
		
		result.put("message", "Đã cho bài vào danh sách quản lí");
		result.put("status", 1);
		
		
		return result.toString();
	}

	@RequestMapping(value = "post/{idPost}/delete", method = RequestMethod.POST)
	public void delete_post(@PathVariable("idPost") long idPost, HttpSession session) {
		logger.info(session.getAttribute("user_id") + "\t" + idPost);
		postDao.deletePost(idPost, (String) session.getAttribute("user_id"));
		
	}

	@RequestMapping("post/{idPost}/query")
	public @ResponseBody PostDetail queryPost(@PathVariable("idPost") long idPost, HttpSession session) {
		logger.info(session.getAttribute("user_id") + "\t" + idPost);
		return postDao.queryPost(idPost, (String) session.getAttribute("user_id"));
	}

	private Post getPost(String url, String user_id) throws UrlException, DuplicationException, NotFoundPostException {

		String postId = FacebookUtil.getPostID(url);
		
		if(postId == null)
			throw new UrlException();
		
		FacebookClient facebookClient = FacebookUtil.getClient();
		String pageId = facebookClient.fetchObject(FacebookUtil.getPageID(url), Page.class).getId();
		Post post = null;
		try{
			post = facebookClient.fetchObject(pageId + "_" + postId, Post.class, Parameter.with("fields",
					"id,message,created_time,shares.sumary(true),likes.summary(true),comments.summary(true),picture"));
			PostDetail postDetail = postDao.queryPost(post.getId(), user_id);
			if (postDetail != null) {
				throw new DuplicationException();
			}
		} catch(Exception e){
			throw new NotFoundPostException();
		}

		return post;
	}

	public String commentIdToUrl(final String facebookPostId, final String facebookCommentId) {
		String postPart[] = facebookPostId.split("_");
		String cmId = facebookCommentId;
		if (cmId.contains("_")) {
			cmId = cmId.split("_")[1];
		}
		return "https://www.facebook.com/permalink.php?story_fbid=" + postPart[1] + "&id=" + postPart[0]
				+ "&comment_id=" + cmId;
	}

	@RequestMapping(value = "post/{id}/graph_realtime/data", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json;charset=utf-8")
	public @ResponseBody String graph_realtime(@PathVariable("id") long id, HttpServletRequest request,
			HttpSession session) {
		int request_times = Integer.parseInt(request.getParameter("request_times"));
		PostDetail postDetail = postDao.queryPost(id, (String) session.getAttribute("user_id"));
		try {
			if (request_times > 0) {
				if (postDetail.getUpdate_util() < (new Date()).getTime()) {
					return String.valueOf(Constant.POST_IS_EXPIRED);
				}
				request_times++;
			} else {
				request_times++;
			}
		} catch (Exception e) {
			logger.error("There is no post to read", e);
			return String.valueOf(Constant.POST_IS_DELETED);
		}

		ActionInfo actionInfo = actionDao.queryActionOfPost(postDetail, (String) session.getAttribute("user_id"));
		Integer actId = actionInfo.getUpdate_count();
		if (actId.equals(session.getAttribute("last_data"))) {
			actionInfo.setComment_count(-1);
			actionInfo.setLike_count(-1);
			actionInfo.setShare_count(-1);
		} else {
			session.setAttribute("last_data", actId);
		}
		actionInfo.setRequestTimes(request_times);
		/*----------------------Top Users--------------------------*/
		// List<UserDetail> listUsers = userDao.queryListUser(id);
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		List<CommentDetail> listCommentsOnPost = commentDao.queryListCommentsIsView(id,
				(String) session.getAttribute("user_id"));
		HashMap<String, Long> topUsersID = new HashMap<String, Long>();
		HashMap<String, String> topUsersName = new HashMap<String, String>();
		for (CommentDetail commentDetail : listCommentsOnPost) {
			commentDetail.setUrl(commentIdToUrl(postDetail.getPost_id(), commentDetail.getComment_id()));
			if (topUsersID.containsKey(commentDetail.getFrom_id())) {
				Long like = topUsersID.get(commentDetail.getFrom_id());
				topUsersID.put(commentDetail.getFrom_id(), like + commentDetail.getLike_count());
			} else {
				topUsersID.put(commentDetail.getFrom_id(), commentDetail.getLike_count());
				topUsersName.put(commentDetail.getFrom_id(), commentDetail.getFrom_name());
			}
		}

		for (String idUser : topUsersID.keySet()) {
			userInfos.add(new UserInfo(idUser, topUsersName.get(idUser), topUsersID.get(idUser)));
		}

		Collections.sort(userInfos, new Comparator<UserInfo>() {
			@Override
			public int compare(UserInfo o1, UserInfo o2) {
				if (o1.getNumCmt() < o2.getNumCmt()) {
					return 1;
				} else {
					if (o1.getNumCmt() == o2.getNumCmt()) {
						return 0;
					} else {
						return -1;
					}
				}
			}
		});

		/*---------------------ListComments------------------------*/
		// List<CommentDetail> listComments =
		// commentDao.queryListCommentsIsView(id);

		Collections.sort(listCommentsOnPost, new Comparator<CommentDetail>() {

			@Override
			public int compare(CommentDetail cmt1, CommentDetail cmt2) {
				if (cmt1.getCreate_time() < cmt2.getCreate_time()) {
					return 1;
				} else {
					if (cmt1.getCreate_time() == cmt2.getCreate_time()) {
						return 0;
					} else {
						return -1;
					}
				}
			}

		});

		userInfos = userInfos.subList(0, (argsConstant.getMaxUserIsViewed() < userInfos.size())
				? argsConstant.getMaxUserIsViewed() : userInfos.size());

		listCommentsOnPost = listCommentsOnPost.subList(0,
				(argsConstant.getMaxCommentIsViewed() < listCommentsOnPost.size())
						? argsConstant.getMaxCommentIsViewed() : listCommentsOnPost.size());

		ResultInfo resultInfo = new ResultInfo(postDetail, actionInfo, userInfos, listCommentsOnPost);
		return JSON.encode(resultInfo, true);
	}

	/*@RequestMapping(value = "post/{idPost}/update", method = RequestMethod.POST)
	public @ResponseBody boolean update_post(@PathVariable("idPost") String idPost, HttpServletRequest request,
			HttpSession session) {

		long update_util = Long.parseLong(request.getParameter("update_util"));

		if (update_util > (postDao.queryPost(idPost, (String) session.getAttribute("user_id")).getBegin_time()
				+ argsConstant.getMaxFollowingTime()))
			return false;
		return postDao.updateTimeUtil(idPost, update_util);

	}*/
}
