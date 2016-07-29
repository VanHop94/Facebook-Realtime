package com.datasection.facebook.be.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.datasection.facebook.be.dao.ActionDao;
import com.datasection.facebook.be.dao.AutoWireJdbcDaoSupport;
import com.datasection.facebook.be.entities.ActionInfo;
import com.datasection.facebook.be.entities.ActionObservation;
import com.datasection.facebook.be.entities.ListFixedSize;
import com.datasection.facebook.be.entities.PostDetail;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;

import net.arnx.jsonic.JSON;

public class ActionDaoImpl extends AutoWireJdbcDaoSupport implements ActionDao {

	final static Logger logger = Logger.getLogger(ActionDaoImpl.class);

	@Override
	public ActionInfo queryActionOfPost(PostDetail post, String userId) {

		Connection conn = null;
		PreparedStatement smt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "Select action.*, post.update_time from action inner join post on action.post_id = post.id where action.post_id = ? and post.manager_id = ?";
			smt = conn.prepareStatement(sql);

			smt.setLong(1, post.getId());
			smt.setString(2, userId);
			rs = smt.executeQuery();
			if (rs.next()) {
				// String id = rs.getString("post_id");
				Long like_convertion = rs.getLong("like_variation");
				Long share_convertion = rs.getLong("share_variation");
				Long comment_convertion = rs.getLong("comment_variation");
				String action_observation = rs.getString("action_observation");
				int update_count = rs.getInt("update_count");
				long time_update = rs.getLong("post.update_time");
				ListFixedSize<ActionObservation> listFixedSize = new ListFixedSize<ActionObservation>();
				JsonObject jb = new JsonObject(action_observation);
				JsonArray arr = jb.getJsonArray("list");
				for (int i = 0; i < arr.length(); i++) {
					ObjectMapper op = new ObjectMapper();
					try {
						ActionObservation ac = op.readValue(arr.getString(i), ActionObservation.class);
						listFixedSize.add(ac);
					} catch (Exception e) {
						logger.error("queryActionOfPost:" + post.getPost_id());
					}
				}
				ActionInfo actionInfo = new ActionInfo(0, post.getId(), time_update, like_convertion, comment_convertion,
						share_convertion, listFixedSize, update_count);

				return actionInfo;
			}
		} catch (Exception e) {
			logger.error("queryActionOfPost", e);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(smt);
			DbUtils.closeQuietly(conn);
		}
		return null;
	}

	@Override
	public boolean updateActionOfPost(ActionInfo actionInfo) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "insert into action value(?,?,?,?,?,?,?,?) on duplicate key update like_variation = ? , share_variation = ? , comment_variation = ?, action_observation = ?, update_count = ?, time_update = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, 0);
			ps.setLong(2, actionInfo.getPost_id());
			ps.setLong(3, actionInfo.getLike_count());
			ps.setLong(4, actionInfo.getShare_count());
			ps.setLong(5, actionInfo.getComment_count());
			ps.setString(6, JSON.encode(actionInfo.getStorage()));
			ps.setInt(7, actionInfo.getUpdate_count());
			ps.setLong(8, actionInfo.getDate_time());
			ps.setLong(9, actionInfo.getLike_count());
			ps.setLong(10, actionInfo.getShare_count());
			ps.setLong(11, actionInfo.getComment_count());
			ps.setString(12, JSON.encode(actionInfo.getStorage()));
			ps.setInt(13, actionInfo.getUpdate_count());
			ps.setLong(14, actionInfo.getDate_time());

			return (ps.executeUpdate() > 0);

		} catch (Exception e) {
			logger.error("updateActionOfPost", e);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			DbUtils.closeQuietly(conn);
		}
		return false;
	}

}
