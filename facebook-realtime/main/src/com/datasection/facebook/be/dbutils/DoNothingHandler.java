package com.datasection.facebook.be.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;

public class DoNothingHandler implements ResultSetHandler<Object>
{
	@Override
	public Object handle(ResultSet rs) throws SQLException
	{
		return null;
	}
}
