package com.datasection.facebook.be.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

import com.datasection.facebook.be.dao.AutoWireJdbcDaoSupport;
import com.datasection.facebook.be.dao.PostDao;
import com.datasection.facebook.be.entities.HumanDetail;
import com.datasection.facebook.be.entities.PostDetail;

public class PostDaoImpl extends AutoWireJdbcDaoSupport implements PostDao {

	static Logger logger = Logger.getLogger(PostDaoImpl.class);

	@Override
	public boolean addListPosts(List<PostDetail> list) {
		return false;
	}

	@Override
	public boolean addPost(PostDetail post) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isSuccess = true;
		try {
			conn = dataSource.getConnection();
			String sql = "insert into post value(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, 0);
			ps.setString(2, post.getPost_id());
			ps.setString(3, post.getPageId());
			ps.setString(4, post.getMessage());
			ps.setLong(5, post.getUpdate_util());
			ps.setString(6, String.valueOf(post.getLike_count()));
			ps.setString(7, String.valueOf(post.getShare_count()));
			ps.setString(8, String.valueOf(post.getComment_count()));
			ps.setLong(9, post.getUpdate_time());
			ps.setString(10, post.getPicture());
			ps.setLong(11, post.getCreated_time());
			ps.setString(12, post.getHuman().getUsername());
			ps.setLong(13, post.getBegin_time());
			return (ps.executeUpdate() > 0) ? true : false;

		} catch (Exception e) {
			logger.error("addPost: cannot add post has id " + post.getPost_id(), e);
			isSuccess = false;
		} finally {
			
			if(isSuccess)
				logger.info("A post is add by user id " + post.getManagerId());
			
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
		return false;
	}

	@Override
	public PostDetail queryPost(String id, String userId) {
		Connection conn = null;
		PreparedStatement smt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "Select * from post where post.post_id = ? and post.manager_id = ?";
			smt = conn.prepareStatement(sql);

			smt.setString(1, id);
			smt.setString(2, userId);

			rs = smt.executeQuery();
			if (rs.next()) {
				long id1 = rs.getLong("id");
				String postId = rs.getString("post_id");
				String pageid = rs.getString("page_id");
				String message = rs.getString("message");
				long update_until = rs.getLong("update_util");
				long like_count = rs.getLong("like_count");
				long share_count = rs.getLong("share_count");
				long comment_count = rs.getLong("comment_count");
				long time_update = rs.getLong("update_time");
				String picture = rs.getString("picture");
				long created_time = rs.getLong("created_time");
				String user_name = rs.getString("manager_id");
				//int user_rule = rs.getInt("manager.rule");
				int status = (update_until > (new Date()).getTime()) ? 1 : 2;
				long begin_time = rs.getLong("begin_time");
				String user_id = rs.getString("manager_id");
				PostDetail post = new PostDetail(id1, postId, pageid, message, update_until, like_count, comment_count,
						share_count, time_update, picture, created_time, new HumanDetail(user_name, "", 1),
						status, begin_time, user_id);

				return post;
			}
		} catch (Exception e) {
			logger.error("queryPost: " + id, e);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(smt);
			DbUtils.closeQuietly(conn);
		}
		return null;
	}

	@Override
	public List<PostDetail> queryAllPost(String user_id1) {
		List<PostDetail> list = new ArrayList<PostDetail>();
		Connection conn = null;
		PreparedStatement smt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "Select * from post innner join manager on manager_id = manager.username where manager_id = ? order by begin_time desc";
			smt = conn.prepareStatement(sql);
			smt.setString(1, user_id1);
			rs = smt.executeQuery();
			while (rs.next()) {
				long id1 = rs.getLong("id");
				String postId = rs.getString("post_id");
				String pageid = rs.getString("page_id");
				String message = rs.getString("message");
				long update_until = rs.getLong("update_util");
				long like_count = rs.getLong("like_count");
				long share_count = rs.getLong("share_count");
				long comment_count = rs.getLong("comment_count");
				long time_update = rs.getLong("update_time");
				String picture = rs.getString("picture");
				long created_time = rs.getLong("created_time");
				int status = (update_until > (new Date()).getTime()) ? 1 : 2;
				String user_id = rs.getString("manager_id");
				String user_name = rs.getString("manager.username");
				int user_rule = rs.getInt("manager.rule");
				long begin_time = rs.getLong("begin_time");
				PostDetail post = new PostDetail(id1, postId, pageid, message, update_until, like_count, share_count,
						comment_count, time_update, picture, created_time, new HumanDetail(user_name, "", user_rule),
						status, begin_time, user_id);
				list.add(post);
			}
		} catch (Exception e) {
			logger.error("queryAllPost: " + user_id1, e);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(smt);
			DbUtils.closeQuietly(conn);
		}

		return list;
	}

	@Override
	public void deletePost(long id, String userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "delete from post where id = ? and manager_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.setString(2, userId);
			ps.executeUpdate();
			
			logger.info("a post is delete by " + userId);

		} catch (Exception e) {
			logger.error("deletePost: " + id + " by " + userId);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public int getAllPostIsActive(String user_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select * from post where update_util > ? and manager_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, (new Date()).getTime());
			ps.setString(2, user_id);
			rs = ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
			}

			return count;
		} catch (Exception e) {
			logger.error("getAllPostIsActive: " + user_id);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
		return 0;
	}

	@Override
	public boolean updatePost(PostDetail post) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTimeUtil(String postId, long date_time) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTimeUpdate(String postId, long date_time) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PostDetail queryPost(long id, String userId) {
		Connection conn = null;
		PreparedStatement smt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "Select * from post where post.id = ? and post.manager_id = ?";
			smt = conn.prepareStatement(sql);

			smt.setLong(1, id);
			smt.setString(2, userId);

			rs = smt.executeQuery();
			if (rs.next()) {
				long id1 = rs.getLong("id");
				String postId = rs.getString("post_id");
				String pageid = rs.getString("page_id");
				String message = rs.getString("message");
				long update_until = rs.getLong("update_util");
				long like_count = rs.getLong("like_count");
				long share_count = rs.getLong("share_count");
				long comment_count = rs.getLong("comment_count");
				long time_update = rs.getLong("update_time");
				String picture = rs.getString("picture");
				long created_time = rs.getLong("created_time");
				String user_name = rs.getString("manager_id");
				//int user_rule = rs.getInt("manager.rule");
				int status = (update_until > (new Date()).getTime()) ? 1 : 2;
				long begin_time = rs.getLong("begin_time");
				String user_id = rs.getString("manager_id");
				PostDetail post = new PostDetail(id1, postId, pageid, message, update_until, like_count, comment_count,
						share_count, time_update, picture, created_time, new HumanDetail(user_name, "", 1),
						status, begin_time, user_id);

				return post;
			}
		} catch (Exception e) {
			logger.error("queryPost: " + id, e);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(smt);
			DbUtils.closeQuietly(conn);
		}
		return null;
	}

	

}
