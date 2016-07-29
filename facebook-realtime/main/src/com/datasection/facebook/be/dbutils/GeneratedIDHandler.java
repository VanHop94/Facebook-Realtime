package com.datasection.facebook.be.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

public class GeneratedIDHandler implements ResultSetHandler<List<Integer>>
{
	@Override
	public List<Integer> handle(ResultSet rs) throws SQLException
	{
		ArrayList<Integer> ls = new ArrayList<>();
		while (rs.next())
		{
			ls.add(rs.getInt(1));
		}
		
		return ls;
	}
	
}
