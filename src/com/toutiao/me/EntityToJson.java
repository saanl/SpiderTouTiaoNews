package com.toutiao.me;

import java.util.List;

import com.toutiao.dao.NewsInfoCRUD;
import com.toutiao.pojo.NewsInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EntityToJson {
	
	//jsonlib方式将单个的entity转化为json
	public static JSONObject entityToJson(NewsInfo obj) {
		if(obj==null) {System.out.println("传参为空");return null;}
		JSONObject json = JSONObject.fromObject(obj);
		//System.out.println(json);
		return json;
	}
	
	//jsonlib方式将list转化为jsonarray
	public static JSONArray entitiesToJson(List<NewsInfo> listobj) {
		if(listobj==null) {System.out.println("传参为空");return null;}
		JSONArray jsonarray = JSONArray.fromObject(listobj);
		//System.out.println(jsonarray);
		return jsonarray;
		
	}
	
	public static void main(String[] args) {
		entitiesToJson(null);
	}
	
}
