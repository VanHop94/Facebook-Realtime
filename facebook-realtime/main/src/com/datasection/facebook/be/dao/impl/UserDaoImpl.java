package com.datasection.facebook.be.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.datasection.facebook.be.dao.AutoWireJdbcDaoSupport;
import com.datasection.facebook.be.dao.UserDao;
import com.datasection.facebook.be.entities.UserDetail;
import com.datasection.web.mvc.config.ArgsConstant;

public class UserDaoImpl extends AutoWireJdbcDaoSupport implements UserDao {

	static Logger logger = Logger.getLogger(UserDaoImpl.class);
	@Autowired
	private ArgsConstant argsConstant;
	
	/*@Override
	public boolean addListUser(List<UserDetail> list) {

		if (list.size() == 0)
			return true;

		Connection conn = null;
		PreparedStatement ps = null;
		// ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "insert into user value(?,?,?,?,?) on duplicate key";
			ps = conn.prepareStatement(sql);
			try {
				for (UserDetail user : list) {

					ps.setString(1, user.getId());
					ps.setString(2, user.getPost());
					ps.setString(3, user.getName());
					ps.setInt(4, user.getStatus());
					ps.setLong(5, user.getCreated_time());
					ps.addBatch();

				}
				ps.executeBatch();
			} catch (Exception e) {
				logger.error("addListUser", e);
			}
		} catch (Exception e) {
			logger.error("addListUser", e);
			return false;
		} finally {
			// DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
		return true;
	}

	@Override
	public boolean addUser(UserDetail user) {

		Connection conn = null;
		PreparedStatement ps = null;
		// ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "insert into user value(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getId());
			ps.setString(2, user.getPost());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getStatus());
			ps.setLong(5, user.getCreated_time());
			ps.execute();
			// if (rs.next())
			// return true;

			return true;
		} catch (Exception e) {
			logger.error("addUser", e);
		} finally {
			// DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
		return false;
	}

	@Override
	public UserDetail queryUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public List<UserDetail> queryListUser(long idPost, long created_time, String userId) {
		List<UserDetail> list = new ArrayList<UserDetail>();
		Connection conn = null;
		PreparedStatement smt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "Select DISTINCT user.id, user.user_id, user.created_time, user.name, user.post_id, user.status from user inner join comment on user.user_id = comment.user_id and user.post_id = comment.post_id inner join post on user.post_id = post.id where comment.post_id = ? and user.created_time > ? and post.manager_id = ? order by comment.datetime desc limit ?";
			smt = conn.prepareStatement(sql);
			smt.setLong(1, idPost);
			smt.setLong(2, created_time);
			smt.setString(3, userId);
			smt.setInt(4, argsConstant.getMaxUserIsViewed());
			rs = smt.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("user.id");
				String user_id = rs.getString("user_id");
				String name = rs.getString("name");
				int status = rs.getInt("status");
				String post = rs.getString("post_id");
				long created_time_user = rs.getLong("created_time");
				UserDetail user = new UserDetail(id, user_id, name, post, status, created_time_user);
				list.add(user);
			}
		} catch (Exception e) {
			logger.error("queryListUser", e);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(smt);
			DbUtils.closeQuietly(conn);
		}
		return list;
	}

	@Override
	public List<UserDetail> queryListUserIsView(long idPost, String userId) {

		List<UserDetail> list = new ArrayList<UserDetail>();
		Connection conn = null;
		PreparedStatement smt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "Select * from user inner join comment on user.user_id = comment.user_id inner join post on user.post_id = post.id where comment.post_id = ? and post.manager_id = ? and user.status = 1";
			smt = conn.prepareStatement(sql);
			smt.setLong(1, idPost);
			smt.setString(2, userId);
			rs = smt.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("id");
				String user_id = rs.getString("user_id");
				String name = rs.getString("name");
				int status = rs.getInt("status");
				String post = rs.getString("post_id");
				long created_time = rs.getLong("created_time");
				UserDetail user = new UserDetail(id, user_id, name, post, status, created_time);
				list.add(user);
			}
		} catch (Exception e) {
			logger.error("queryListUserIsView", e);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(smt);
			DbUtils.closeQuietly(conn);
		}
		return list;
	}

	@Override
	public boolean updateUsers(String[] idUsers, long idPost, int status, String managerId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			String sql = "update user inner join post on user.post_id = post.id set user.status = ? where user_id = ? and user.post_id = ? and post.manager_id = ?";
			ps = conn.prepareStatement(sql);
			System.out.println(idPost + " " + status + " " + managerId);
			for (String id : idUsers) {

				ps.setInt(1, status);
				ps.setString(2, id);
				ps.setLong(3, idPost);
				ps.setString(4, managerId);

				ps.addBatch();
			}
			return (ps.executeBatch().length == idUsers.length) ? true : false;

		} catch (Exception e) {
			logger.error("updateUsers", e);
		} finally {
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}

		return false;
	}

	@Override
	public void updateAllUser(long idPost, int status, String managerId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			String sql = "update user inner join post on user.post_id = post.id set user.status = ? where user.post_id = ? and post.manager_id = ?";
			ps = conn.prepareStatement(sql);

			ps.setInt(1, status);
			ps.setLong(2, idPost);
			ps.setString(3, managerId);

			ps.executeUpdate();

		} catch (Exception e) {
			logger.error("updateAllUser", e);
		} finally {
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}

	}

	@Override
	public boolean addListUser(List<UserDetail> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addUser(UserDetail user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserDetail queryUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
