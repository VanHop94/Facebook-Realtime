package com.datasection.facebook.be.dao;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public abstract class AutoWireJdbcDaoSupport extends JdbcDaoSupport
{
	@Autowired
	protected DataSource dataSource;
	
	@PostConstruct
	private void initialize()
	{
		setDataSource(dataSource);
	}
	
}
