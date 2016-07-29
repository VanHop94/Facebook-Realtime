package com.datasection.facebook.be.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;

public class StringGeneratedIDHandler implements ResultSetHandler<String>
{
	@Override
	public String handle(ResultSet rs) throws SQLException
	{
		StringBuilder builder = new StringBuilder();
		while (rs.next())
		{
			builder.append(',');
			builder.append(rs.getInt(1));
		}
		
		if (builder.length() > 0)
		{
			builder.deleteCharAt(0);
		}
		
		return builder.toString();
	}
	
}
