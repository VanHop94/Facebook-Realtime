package com.datasection.facebook.be.dbutils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class BeanMapper
{
	private BeanMapper()
	{
		
	}
	
	@Target({ ElementType.FIELD })
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface Column
	{
		public String name();
	}
	
	public static Map<String, String> getOverridenFields(Class<?> cla)
	{
		Map<String, String> map = new HashMap<>();
		Field[] dfs = cla.getDeclaredFields();
		for (Field f : dfs)
		{
			if (f.isAnnotationPresent(Column.class))
			{
				Column c = f.getAnnotation(Column.class);
				map.put(c.name(), f.getName());
			}
		}
		return map;
	}
	
	public static <T> BeanListHandler<T> createListHandler(Class<T> type)
	{
		Map<String, String> map = BeanMapper.getOverridenFields(type);
		BeanProcessor dateForProcessor = new BeanProcessor(map);
		BasicRowProcessor rp = new BasicRowProcessor(dateForProcessor);
		BeanListHandler<T> rsh = new BeanListHandler<T>(type, rp);
		return rsh;
	}
	
	public static <T> BeanHandler<T> createHandler(Class<T> type)
	{
		Map<String, String> map = BeanMapper.getOverridenFields(type);
		BeanProcessor dateForProcessor = new BeanProcessor(map);
		BasicRowProcessor rp = new BasicRowProcessor(dateForProcessor);
		BeanHandler<T> rsh = new BeanHandler<T>(type, rp);
		return rsh;
	}
}
