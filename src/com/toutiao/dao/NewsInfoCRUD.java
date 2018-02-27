package com.toutiao.dao;


import java.util.ArrayList;
import java.util.List;

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
	
	public static NewsInfo query(int i) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			NewsInfoMapper mapper = session.getMapper(NewsInfoMapper.class);
			NewsInfo news = mapper.selectByPrimaryKey(i);
			session.commit();
			return news;
		} finally {
		  session.close();
		}
	}
	
	public static List<NewsInfo> queryNews(NewsInfo condition) {
		SqlSession session = sqlSessionFactory.openSession();
		List<NewsInfo> list = new ArrayList<NewsInfo>();
		try {
			NewsInfoMapper mapper = session.getMapper(NewsInfoMapper.class);
			list = mapper.queryNewsBySingleCondition(condition);
			session.commit();
			return list;
		} finally {
		  session.close();
		}
	}
	
	public static Integer queryTotalNum(String tag) {
		SqlSession session = sqlSessionFactory.openSession();
		Integer total;
		try {
			NewsInfoMapper mapper = session.getMapper(NewsInfoMapper.class);
			total = mapper.queryTotalNumOfTag(tag);
			session.commit();
			return total;
		} finally {
		  session.close();
		}
	}

	public static Integer queryStartNum(String tag) {
		SqlSession session = sqlSessionFactory.openSession();
		Integer start;
		try {
			NewsInfoMapper mapper = session.getMapper(NewsInfoMapper.class);
			start = mapper.querystartNumOfTag(tag);
			session.commit();
			return start;
		} finally {
		  session.close();
		}
	}

	public static List<NewsInfo> queryByPage(String tag,Integer start, Integer pageShow) {
		
		SqlSession session = sqlSessionFactory.openSession();
		List<NewsInfo> list = new ArrayList<NewsInfo>();
		try {
			NewsInfoMapper mapper = session.getMapper(NewsInfoMapper.class);
			list = mapper.queryNewsByPage(tag,start,pageShow);
			session.commit();
			return list;
		} finally {
		  session.close();
		}
		
	}
}
