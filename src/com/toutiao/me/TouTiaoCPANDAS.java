package com.toutiao.me;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TouTiaoCPANDAS {
	
	//java时间戳13位转成10位
	public static Integer changeTime() {
		
		long time_0 = new Date().getTime();
		
		String time_1 = String.valueOf(time_0);
		
		String time_2 = time_1.substring(0, time_1.length()-3);
		
		return Integer.parseInt(time_2);
	}
	
	public static String getCpAndAS() {
		
			
		Integer time = changeTime();
		//Integer time = 1519623609;
		
		//System.out.println(time);
		
		String key = time.toHexString(time).toUpperCase();
		
	    String md5Key = MD5.MD51(String.valueOf(time));
		
		if (8 != key.length()) {
	       
			String as = "479BB4B7254C150";
	       	        
			String cp = "7E0AC8874BB0985";
			
	    }else {
	    	
	    	char[] md5KeyAsc5 = md5Key.substring(0, 5).toCharArray();
	        char[] md5KeyDesc5 = md5Key.substring(md5Key.length()-5,md5Key.length()).toCharArray();
	        String as = ""; String cp= "";
	        char[] keychar = key.toCharArray();
	        
	        for (int i = 0;i < 5; i ++) {
	            as = as+md5KeyAsc5[i]+keychar[i];
	            cp = cp+keychar[i+3]+md5KeyDesc5[i];
	        }
	        
	        as = "A1"+as+key.substring(key.length()-3,key.length());
	        cp = key.substring(0, 3)+cp+"E1";
	       
	       //  System.out.println(as);
	       // System.out.println(cp);
	        
	        return as+"#"+cp;
	    }
			return "";
	}
	
	public static void main(String[] args) {
		getCpAndAS();
	}
}
