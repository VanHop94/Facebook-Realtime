package com.datasection.facebook.be.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

import com.datasection.facebook.be.dao.AutoWireJdbcDaoSupport;
import com.datasection.facebook.be.dao.HumanDao;
import com.datasection.facebook.be.entities.HumanDetail;

public class HumanDaoImpl extends AutoWireJdbcDaoSupport implements HumanDao {

	static Logger logger = Logger.getLogger(HumanDaoImpl.class);
	@Override
	public HumanDetail checkLogin(String username, String password) {
		Connection conn = null;
		PreparedStatement smt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "Select * from manager where username=? and password=?";
			smt = conn.prepareStatement(sql);
			smt.setString(1, username);
			smt.setString(2, password);

			rs = smt.executeQuery();
			if (rs.next()) {
				return new HumanDetail(rs.getString("username"), rs.getString("password"),
						rs.getInt("rule"));
			}
		} catch (Exception e) {
			logger.error("checkLogin",e);
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(smt);
			DbUtils.closeQuietly(conn);
		}
		return null;
	}

}
