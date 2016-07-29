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
import com.datasection.facebook.utils.Constant;

public class PostDaoImpl extends AutoWireJdbcDaoSupport implements PostDao {

	static Logger logger = Logger.getLogger(PostDaoImpl.class);

	/*@Override
	public boolean addListPosts(List<PostDetail> list) {
		return false;
	}*/

	/*@Override
	public boolean addPost(PostDetail post) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "insert into post value(?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, post.getId());
			ps.setString(2, post.getPageId());
			ps.setString(3, post.getMessage());
			ps.setLong(4, post.getUpdate_util());
			ps.setString(5, String.valueOf(post.getLike_count()));
			ps.setString(6, String.valueOf(post.getShare_count()));
			ps.setString(7, String.valueOf(post.getComment_count()));
			ps.setLong(8, post.getUpdate_time());
			ps.setString(9, post.getPicture());
			ps.setLong(10, post.getCreated_time());
			ps.setString(11, post.getHuman().getUsername());
			ps.setLong(12, post.getBegin_time());
			return (ps.executeUpdate() > 0) ? true : false;

		} catch (Exception e) {
			logger.error("addPost: " + post.getId());
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
		return false;
	}*/

	/*@Override
	public PostDetail queryPost(String id, String userId) {
		Connection conn = null;
		PreparedStatement smt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "Select * from post inner join manager on post.user_id = manager.username where post.id = ? and post.user_id = ?";
			smt = conn.prepareStatement(sql);

			smt.setString(1, id);
			smt.setString(2, userId);

			rs = smt.executeQuery();
			if (rs.next()) {
				String id1 = rs.getString("post.id");
				String pageid = rs.getString("page_id");
				String message = rs.getString("message");
				long update_until = rs.getLong("update_util");
				long like_count = rs.getLong("like_count");
				long share_count = rs.getLong("share_count");
				long comment_count = rs.getLong("comment_count");
				long time_update = rs.getLong("update_time");
				String picture = rs.getString("picture");
				long created_time = rs.getLong("created_time");
				String user_name = rs.getString("manager.username");
				int user_rule = rs.getInt("manager.rule");
				int status = (update_until > (new Date()).getTime()) ? 1 : 2;
				long begin_time = rs.getLong("begin_time");
				String user_id = rs.getString("user_id");
				PostDetail post = new PostDetail(id1, pageid, message, update_until, like_count, comment_count,
						share_count, time_update, picture, created_time, new HumanDetail(user_name, "", user_rule),
						status, begin_time, user_id);

				return post;
			}
		} catch (Exception e) {
			logger.error("queryPost: " + id);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(smt);
			DbUtils.closeQuietly(conn);
		}
		return null;
	}*/

	/*@Override
	public List<PostDetail> queryAllPost(String user_id1) {
		List<PostDetail> list = new ArrayList<PostDetail>();
		Connection conn = null;
		PreparedStatement smt = null;
		ResultSet rs = null;
		System.out.println(user_id1);
		try {
			conn = dataSource.getConnection();
			String sql = "Select * from post innner join manager on user_id = manager.username where user_id = ? order by begin_time desc";
			smt = conn.prepareStatement(sql);
			smt.setString(1, user_id1);
			rs = smt.executeQuery();
			while (rs.next()) {
				String id1 = rs.getString("id");
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
				String user_id = rs.getString("user_id");
				String user_name = rs.getString("manager.username");
				int user_rule = rs.getInt("manager.rule");
				long begin_time = rs.getLong("begin_time");
				PostDetail post = new PostDetail(id1, pageid, message, update_until, like_count, share_count,
						comment_count, time_update, picture, created_time, new HumanDetail(user_name, "", user_rule),
						status, begin_time, user_id);
				list.add(post);
			}
		} catch (Exception e) {
			logger.error("queryAllPost: " + user_id1);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(smt);
			DbUtils.closeQuietly(conn);
		}

		return list;
	}*/

	@Override
	public boolean updatePost(PostDetail post) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "update post set update_time = ? , like_count = ? , share_count = ? , comment_count = ? where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, post.getUpdate_time());
			ps.setLong(2, post.getLike_count());
			ps.setLong(3, post.getShare_count());
			ps.setLong(4, post.getComment_count());
			ps.setLong(5, post.getId());

			return (ps.executeUpdate() > 0);

		} catch (Exception e) {
			logger.error("updatePost: " + post.getId());
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}

		return false;
	}

	/*@Override
	public boolean updateTimeUpdate(String postId, long date_time) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "update post set update_time = ? where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, date_time);
			ps.setString(2, postId);

			return (ps.executeUpdate() > 0);

		} catch (Exception e) {
			logger.error("updateTimeUpdate: " + postId);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
		return false;
	}*/

	/*@Override
	public boolean updateTimeUtil(String postId, long date_time) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "update post set update_util = ? where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, date_time);
			ps.setString(2, postId);

			return (ps.executeUpdate() > 0);

		} catch (Exception e) {
			logger.error("updateTimeUtil: " + postId);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
		return false;

	}*/

	/*@Override
	public void deletePost(String postId, String userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "delete from post where id = ? and user_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, postId);
			ps.setString(2, userId);
			ps.executeUpdate();

		} catch (Exception e) {
			logger.error("deletePost: " + postId + " by " + userId);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
	}
*/
	/*@Override
	public int getAllPostIsActive(String user_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select * from post where update_util > ? and user_id = ?";
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
	}*/

	/*@Override
	public List<PostDetail> queryPostIsUnread() {
		
		List<PostDetail> listPostUnread = new ArrayList<PostDetail>();
		
		Connection conn = null;
		PreparedStatement smt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "Select * from post where status = ?";
			smt = conn.prepareStatement(sql);
			smt.setInt(1, Constant.POST_IS_UNREAD);
			rs = smt.executeQuery();
			while (rs.next()) {
				String id1 = rs.getString("id");
				String pageid = rs.getString("page_id");
				String message = rs.getString("message");
				long update_until = rs.getLong("update_util");
				long like_count = rs.getLong("like_count");
				long share_count = rs.getLong("share_count");
				long comment_count = rs.getLong("comment_count");
				long time_update = rs.getLong("update_time");
				String picture = rs.getString("picture");
				long created_time = rs.getLong("created_time");
				int status = rs.getInt("status");
				String userId = rs.getString("user_id");
				long begin_time = rs.getLong("begin_time");
				PostDetail post = new PostDetail(id1, pageid, message, update_until, like_count, share_count,
						comment_count, time_update, picture, created_time, new HumanDetail("", "", 0),
						status, begin_time, userId);
				listPostUnread.add(post);
			}
		} catch (Exception e) {
			logger.error("queryPostUnread: ", e);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(smt);
			DbUtils.closeQuietly(conn);
		}

		return listPostUnread;
	}*/

	/*@Override
	public boolean updateStatus(String postId, int status) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "update post set status = ? where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, status);
			ps.setString(2, postId);

			return (ps.executeUpdate() > 0);

		} catch (Exception e) {
			logger.error("updateStatus: " + postId);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
		return false;
	}*/

	@Override
	public List<PostDetail> queryAllPostIsRunning() {
		List<PostDetail> list = new ArrayList<PostDetail>();
		Connection conn = null;
		PreparedStatement smt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "Select * from post where update_util > ?";
			smt = conn.prepareStatement(sql);
			smt.setLong(1, (new Date()).getTime());
			rs = smt.executeQuery();
			while (rs.next()) {
				long id1 = rs.getLong("id");
				String post_id = rs.getString("post_id");
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
				String user_name = rs.getString("manager_id");
				//int user_rule = rs.getInt("manager.rule");
				long begin_time = rs.getLong("begin_time");
				PostDetail post = new PostDetail(id1, post_id, pageid, message, update_until, like_count,comment_count, share_count,
						time_update, picture, created_time, new HumanDetail(user_name, "", 1),
						status, begin_time, user_id);
				list.add(post);
			}
		} catch (Exception e) {
			logger.error("queryAllPost: ", e);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(smt);
			DbUtils.closeQuietly(conn);
		}

		return list;
	}

	@Override
	public boolean addListPosts(List<PostDetail> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addPost(PostDetail post) {
		// TODO Auto-generated method stub
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
	public List<PostDetail> queryAllPost(String user_id) {
		// TODO Auto-generated method stub
		return null;
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
	public void deletePost(String postId, String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAllPostIsActive(String user_id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
