package com.toutiao.dao;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.toutiao.pojo.NewsInfo;
public class NewsInfoCRUD {
	
	private static SqlSessionFactory sqlSessionFactory = GetSqlSession.getSqlSession();
	
	public static void insert(NewsInfo news) {
		//System.out.println(sqlSessionFactory);
		SqlSession session = sqlSessionFactory.openSession(true);
		try {
			NewsInfoMapper mapper = session.getMapper(NewsInfoMapper.class);
			int i = mapper.insert(news);
			//session.commit();
			if(i>0) {
			System.out.println("插入成功："+i);
			}
		} finally {
		  session.close();
		}
	}
	
	public static void query(int i) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			NewsInfoMapper mapper = session.getMapper(NewsInfoMapper.class);
			NewsInfo news = mapper.selectByPrimaryKey(i);
			session.commit();
		} finally {
		  session.close();
		}
	}
}
