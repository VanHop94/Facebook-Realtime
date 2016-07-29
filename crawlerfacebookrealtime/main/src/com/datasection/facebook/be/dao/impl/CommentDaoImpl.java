package com.datasection.facebook.be.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.datasection.facebook.be.dao.AutoWireJdbcDaoSupport;
import com.datasection.facebook.be.dao.CommentDao;
import com.datasection.facebook.be.entities.CommentDetail;
import com.datasection.web.mvc.config.ArgsConstant;

public class CommentDaoImpl extends AutoWireJdbcDaoSupport implements CommentDao {

	static Logger logger = Logger.getLogger(CommentDaoImpl.class);
	//@Autowired
	//private ArgsConstant argsConstant;
	
	/*@Override
	public boolean addComment(CommentDetail comment) {
		Connection conn = null;
		PreparedStatement ps = null;
		// ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "insert into comment value(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, comment.getId());
			ps.setString(2, comment.getPost_id());
			ps.setString(3, comment.getFrom_id());
			ps.setString(4, String.valueOf(comment.getCreate_time()));
			ps.setString(5, comment.getContent());
			ps.setInt(6, comment.getStatus());
			ps.setLong(7, comment.getLike_count());

			return (ps.executeUpdate() > 0);
		} catch (Exception e) {
			logger.error("addComment", e);
		} finally {
			// DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
		return false;
	}
*/
	@Override
	public boolean addListComments(List<CommentDetail> listComments) {

		if (listComments.size() == 0)
			return true;

		Connection conn = null;
		PreparedStatement ps = null;
		// ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			String sql = "insert into comment value(?,?,?,?,?,?,?,?,?) on duplicate key update like_count = ?";
			ps = conn.prepareStatement(sql);
			try {
				for (CommentDetail comment : listComments) {
					ps.setLong(1, 0);
					ps.setString(2, comment.getComment_id());
					ps.setLong(3, comment.getPost_id());
					ps.setString(4, comment.getFrom_id());
					ps.setString(5, String.valueOf(comment.getCreate_time()));
					ps.setString(6, comment.getContent());
					ps.setString(7, comment.getContent_2());
					ps.setInt(8, comment.getStatus());
					ps.setLong(9, comment.getLike_count());
					ps.setLong(10, comment.getLike_count());
					ps.addBatch();

				}
				ps.executeBatch();
			} catch (SQLException e) {
				//logger.error("addListComments", e);
			}

		} catch (SQLException e1) {
			logger.error("addListComments",e1);
			return false;
		} finally {
			// DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
		return true;
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

	@Override
	public boolean addComment(CommentDetail comment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CommentDetail> queryListComments(String idPost, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDetail queryComment(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateComments(String[] ids, int status, String post_id, String managerId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCommentsOfUser(String[] user_ids, String post_id, int status, String managerId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateAllCommentsOnPost(String post_id, int status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CommentDetail> queryListCommentsIsView(String idPost, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public List<CommentDetail> queryListComments(String idPost, String userId) {
		List<CommentDetail> list = new ArrayList<CommentDetail>();
		Connection conn = null;
		PreparedStatement smt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "Select comment.*,user.name from comment inner join user on comment.user_id = user.id_user and comment.post_id = user.id_post inner join post on comment.post_id = post.id where comment.post_id = ? and user.status = 1 and post.user_id = ? order by comment.datetime desc limit ?";
			smt = conn.prepareStatement(sql);
			smt.setString(1, idPost);
			smt.setString(2, userId);
			smt.setInt(3, argsConstant.getMaxCommentIsViewed());;
			rs = smt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String postid = rs.getString("post_id");
				String userid = rs.getString("user_id");
				long datetime = Long.parseLong(rs.getString("datetime"));
				String content = rs.getString("content");
				String username = rs.getString("name");
				int status = rs.getInt("status");
				long like_count = rs.getLong("like_count");
				CommentDetail comment = new CommentDetail(id, like_count, content, userid, username, datetime,
						commentIdToUrl(postid, id), postid, status);
				list.add(comment);
			}
		} catch (Exception e) {
			logger.error("queryListComments", e);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(smt);
			DbUtils.closeQuietly(conn);
		}
		return list;
	}*/

	/*@Override
	public CommentDetail queryComment(String id) {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*@Override
	public boolean updateComments(String[] ids, int status, String post_id, String managerId) {

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			String sql = "update comment inner join post on comment.post_id = post.id set comment.status = ? where comment.id = ? and post.user_id = ?";
			ps = conn.prepareStatement(sql);
			for (String id : ids) {
				ps.setInt(1, status);
				ps.setString(2, id);
				ps.setString(3, managerId);
				ps.addBatch();
			}
			return (ps.executeBatch().length == ids.length) ? true : false;

		} catch (Exception e) {
			logger.error("updateComments", e);
		} finally {
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
		return false;
	}*/

	/*@Override
	public List<CommentDetail> queryListCommentsIsView(String idPost, String userId) {
		List<CommentDetail> list = new ArrayList<CommentDetail>();
		Connection conn = null;
		PreparedStatement smt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "Select comment.*,user.name from comment inner join user on comment.user_id = user.id_user and comment.post_id = user.id_post inner join post on comment.post_id = post.id where comment.post_id = ? and comment.status = 1 and post.user_id = ? order by comment.datetime desc limit ?";
			smt = conn.prepareStatement(sql);
			smt.setString(1, idPost);
			smt.setString(2, userId);
			smt.setInt(3, argsConstant.getMaxCommentIsViewed());
			rs = smt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String postid = rs.getString("post_id");
				String userid = rs.getString("user_id");
				long datetime = Long.parseLong(rs.getString("datetime"));
				String content = rs.getString("content");
				String username = rs.getString("name");
				int status = rs.getInt("status");
				Long like_count = rs.getLong("like_count");
				CommentDetail comment = new CommentDetail(id, like_count, content, userid, username, datetime, "",
						postid, status);
				list.add(comment);
			}
		} catch (Exception e) {
			logger.error("queryListCommentsIsView", e);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(smt);
			DbUtils.closeQuietly(conn);
		}
		return list;
	}*/

	/*@Override
	public boolean updateCommentsOfUser(String[] user_ids, String post_id, int status, String managerId) {

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			String sql = "update comment inner join post on comment.post_id = post.id set status = ? where comment.user_id = ? and post_id = ? and post.user_id = ?";
			ps = conn.prepareStatement(sql);
			for (String id : user_ids) {
				ps.setInt(1, status);
				ps.setString(2, id);
				ps.setString(3, post_id);
				ps.setString(4, managerId);
				ps.addBatch();
			}

			return (ps.executeBatch().length == user_ids.length) ? true : false;
		} catch (Exception e) {
			logger.error("updateCommentsOfUser", e);
		} finally {
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
		return false;
	}

	@Override
	public void updateAllCommentsOnPost(String post_id, int status) {

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			String sql = "update comment set status = ? where post_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setString(2, post_id);

			ps.executeUpdate();
		} catch (Exception e) {
			logger.error("updateAllCommentsOnPost", e);
		} finally {
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}

	}*/

}
