package com.datasection.facebook.thread.crawler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.datasection.facebook.be.dao.ActionDao;
import com.datasection.facebook.be.dao.CommentDao;
import com.datasection.facebook.be.dao.PostDao;
import com.datasection.facebook.be.dao.UserDao;
import com.datasection.facebook.be.entities.ActionInfo;
import com.datasection.facebook.be.entities.ActionObservation;
import com.datasection.facebook.be.entities.CommentDetail;
import com.datasection.facebook.be.entities.PostDetail;
import com.datasection.facebook.be.entities.UserDetail;
import com.datasection.facebook.utils.Constant;
import com.datasection.facebook.utils.FacebookUtil;
import com.datasection.facebook.utils.StringUtil;
import com.restfb.Connection;
import com.restfb.Parameter;
import com.restfb.exception.FacebookNetworkException;
import com.restfb.types.Comment;
import com.restfb.types.Post;

public class CrawlerCommentOnPost extends Thread {

	static Logger logger = Logger.getLogger(CrawlerCommentOnPost.class);
	
	private PostDetail post;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PostDao postDao;
	@Autowired
	private ActionDao actionDao;

	public CrawlerCommentOnPost(PostDetail post, CommentDao commentDao, UserDao userDao, PostDao postDao, ActionDao actionDao) {
		this.post = post;
		this.commentDao = commentDao;
		this.userDao = userDao;
		this.postDao = postDao;
		this.actionDao = actionDao;
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

	public void getCommentsAndUsersOnPost() throws Exception {

		List<CommentDetail> commentDetails = new ArrayList<CommentDetail>();
		List<UserDetail> userDetails = new ArrayList<UserDetail>();
		Connection<Comment> allComments = FacebookUtil.getClient().fetchConnection(post.getPostId() + "/comments",
				Comment.class, Parameter.with("limit", "250"),
				Parameter.with("fields", "id,message,from,created_time,like_count"),
				Parameter.with("filter", "stream"));
		for (List<Comment> postcomments : allComments) {
			for (Comment comment : postcomments) {
				try {
					CommentDetail commentDetail = new CommentDetail(0, String.valueOf(comment.getId()),
							comment.getLikeCount(), comment.getMessage(), StringUtil.getStringIgnoreAccent(comment.getMessage()), comment.getFrom().getId(),
							comment.getFrom().getName(), comment.getCreatedTime().getTime(),
							commentIdToUrl(post.getPostId(), comment.getId()), post.getId(),
							CommentDetail.DEFAULT_COMMENT_STATUS);
					commentDetails.add(commentDetail);
					userDetails.add(new UserDetail(0,comment.getFrom().getId(), comment.getFrom().getName(), post.getId(),
							Constant.USER_STATUS_DEFAULT, comment.getCreatedTime().getTime()));
				} catch (Exception e) {
					logger.error("getCommentsAndUsersOnPost : " + post.getId(), e);
				}
			}
		}

		post.addComments(commentDetails);
		post.addUsers(userDetails);
	}

	@Override
	public void run() {

		boolean isRunning = true;

		while (isRunning) {
			try {
				//System.out.println(System.currentTimeMillis());
				getCommentsAndUsersOnPost();
				updatePostAndAction(post.getPostId(), post.getManagerId());
				insertUsers();
				insertComments();
	
				PostDetail postDetail = postDao.queryPost(post.getPostId(), post.getManagerId());
				
				if(postDetail.getUpdate_util() < (new Date()).getTime()){
					isRunning = false;
					//postDao.updateStatus(post.getId(), Constant.POST_IS_EXPIRED);
				}
				sleep(1);
	
			} catch (FacebookNetworkException e) {
				logger.error("run : " + post.getId(), e);
				sleep(1);
			} catch (NullPointerException e1) {
				isRunning = false;
				logger.error("run : " + post.getId(), e1);
			} catch (Exception e2) {
				logger.error("run : " + post.getId(), e2);
				isRunning = false;
				/*postDao.updateStatus(post.getId(), Constant.POST_IS_EXPIRED);*/
			}
		}
	}

	private void sleep(int seconds) {
		try {
			Thread.sleep(1000L * seconds);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private void updatePostAndAction(String id, String userId) throws Exception {
		Post post1 = FacebookUtil.getClient().fetchObject(id, Post.class,
				Parameter.with("fields", "shares.sumary(true),likes.summary(true),comments.summary(true)"));

		ActionInfo actionInfo = actionDao.queryActionOfPost(post, userId);
		if (actionInfo == null)
			actionInfo = new ActionInfo();

		long like_variation = post1.getLikesCount() - post.getLike_count();
		long comment_variation = post1.getCommentsCount() - post.getComment_count();
		long share_variation = post1.getSharesCount() - post.getShare_count();
		
		post.setComment_count(post1.getCommentsCount());
		post.setLike_count(post1.getLikesCount());
		post.setShare_count(post1.getSharesCount());
		post.setUpdate_time(System.currentTimeMillis());
		
		postDao.updatePost(post);
		
		actionInfo.setComment_count(comment_variation);
		actionInfo.setLike_count(like_variation);
		actionInfo.setShare_count(share_variation);
		actionInfo.getStorage()
				.add(new ActionObservation(like_variation, comment_variation, share_variation, post.getUpdate_time()));

		boolean flag = actionDao.updateActionOfPost(actionInfo);
	}

	private void insertComments() throws Exception {
		commentDao.addListComments(post.getCommentInsertToDatabase());
	}

	private void insertUsers() throws Exception {
		userDao.addListUser(post.getUserInsertToDatabase());
	}

}
