package com.myutil.util;

import java.util.ArrayList;
import java.util.List;

import com.toutiao.dao.NewsInfoCRUD;
import com.toutiao.pojo.NewsInfo;


//mybatis分页
public class PageRowsCount {
	
	public Integer setPageStart(String tag) {
		Integer queryStartNum = NewsInfoCRUD.queryStartNum(tag);
		return queryStartNum;
	}
	
	public Integer setPageTotal(String tag) {
		Integer queryTotalNum = NewsInfoCRUD.queryTotalNum(tag);
		return queryTotalNum;
	}
	
	public Integer getPageNum(String tag,Integer pageShow) {
		
		Integer pageTotal = setPageTotal(tag);
		Integer pageStart = setPageStart(tag);
		Integer pagenum = (pageTotal-pageStart)/pageShow+1;

		System.out.println("该页数为："+pagenum);
		
		return pagenum;
		
	}
	
	public List<NewsInfo> getListByCondition(String tag,Integer cursor,Integer pageShow){
		
		if(cursor <= 0) {cursor = 1;}
		List<NewsInfo> list = new ArrayList<NewsInfo>();
		list = NewsInfoCRUD.queryByPage(tag, (cursor-1)*pageShow,pageShow);
		
		
		return list;
		
	}
	
	public static void main(String[] args) {
		PageRowsCount p = new PageRowsCount();
		List<NewsInfo> listByCondition = p.getListByCondition("news_entertainment", 21,10);
		for(NewsInfo n:listByCondition) {
			System.out.println(n.getId());
		}
		
		System.out.println(p.getPageNum("news_entertainment", 15));
	}
	
}
