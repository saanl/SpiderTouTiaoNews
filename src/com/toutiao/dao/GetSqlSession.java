package com.toutiao.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class GetSqlSession {
	
	private GetSqlSession() {}
	
	private static SqlSessionFactory sqlSessionFactory;
	
	public static SqlSessionFactory getSqlSession(){
		
		if(sqlSessionFactory == null) {
			String resource = "SqlSessionConfig.xml";
			
			InputStream inputStream;
			try {
				inputStream = Resources.getResourceAsStream(resource);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
				
				return sqlSessionFactory;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
			return sqlSessionFactory;
		
	}
	
	public static void main(String[] args) {
	
			SqlSessionFactory  sq = getSqlSession();
			System.out.println(sq);
	}
}
