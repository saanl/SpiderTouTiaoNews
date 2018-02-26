package com.toutiao.me;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import com.toutiao.dao.NewsInfoCRUD;
import com.toutiao.me.Test.TrustAnyHostnameVerifier;
import com.toutiao.me.Test.TrustAnyTrustManager;
import com.toutiao.pojo.NewsInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/*
 *  &category=news_society ：头条类型，必填
	&max_behot_time=0 &max_behot_time_tmp=0 ：打开网页的时间（格林威治秒）和时间戳
	&as=A1B5093B4F12B65&cp=59BF52DB0655DE1  ：as和cp是用来提取和验证访问者页面停留时间的参数，cp对浏览器的时间进行了加密混淆，as通过md5来对时间进行验证
 * */
public class Spider {
	
	private static final String HOT = "news_hot";
	private static final String TECH = "news_tech";
	private static final String ENTER = "news_entertainment";
	private static final String GAME = "news_game";
	private static final String SPORT = "news_sports";
	private static final String CAR = "news_car";
	private static final String FINANCE = "news_finance";
	private static final String FUN = "funny";
	
	
	public String httpGet(String url) throws KeyManagementException, IOException, NoSuchAlgorithmException {
		
		URL path = new URL(url);
		
		HttpURLConnection conn = (HttpsURLConnection) path.openConnection();
		
		 if (conn instanceof HttpsURLConnection)  {  
	            SSLContext sc = SSLContext.getInstance("SSL");  
	            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());  
	            ((HttpsURLConnection) conn).setSSLSocketFactory(sc.getSocketFactory());  
	            ((HttpsURLConnection) conn).setHostnameVerifier(new TrustAnyHostnameVerifier());  
	        }
		 
		 conn.setRequestProperty("authority","www.toutiao.com");
		 
		 conn.setRequestProperty("cookie","uuid=\"w:c998ecd0fba2465f895f79ccd63bad7d\"; UM_distinctid=161cd685ebb4f7-0f1b7893f8b0ed-454c092b-1fa400-161cd685ebc543; _ga=GA1.2.1067928882.1519569625; _gid=GA1.2.1954782076.1519569625; tt_webid=6526501928754742792; tt_webid=6526501928754742792; WEATHER_CITY=%E5%8C%97%E4%BA%AC; __tasessionId=1q0byaurn1519636229962; CNZZDATA1259612802=1806596730-1519564461-https%253A%252F%252Fwww.baidu.com%252F%7C1519634702");
		 
		 conn.setRequestProperty("referer","https://www.toutiao.com/ch/news_hot/");
		 
		 
		 conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
		 
		 
		 conn.setConnectTimeout(2000);
		
		 conn.setReadTimeout(2000);
		 	
		int response_code = conn.getResponseCode();
		
		if(response_code==200) {
			InputStream in = conn.getInputStream();
			String str = parseInputStream(in);
			return parseStringToJson(str);
		}else {
			System.out.println("error");
		}
			return 0+"";
	}
	
	public void getInfo(String type,String max_behot_time,String max_behot_time_tmp
			,String as,String cp,String suffix) throws IOException, NoSuchAlgorithmException, KeyManagementException {
		
		String url = "https://www.toutiao.com/api/pc/feed/?category="+type+"&utm_source=toutiao&widen=1&max_behot_time="+max_behot_time+"&max_behot_time_tmp="+max_behot_time_tmp+"&tadrequire=true&as="+as+"&cp="+cp+"&_signature="+suffix+"";
		
		max_behot_time_next = httpGet(url);
		
		System.out.println("**********************************************************************************");
	}

	public String parseStringToJson(String str) {
		
		JSONObject json = JSONObject.fromObject(str);
		
		JSONArray data = json.getJSONArray("data");
		
		JSONObject nexttime = json.getJSONObject("next");
		
		String max_behot_time = nexttime.get("max_behot_time").toString();
		
		for(int i = 0 ;i<data.size();i++) {
			NewsInfo news = new NewsInfo();
			JSONObject new_ = data.getJSONObject(i);
			String abstract_ = new_.get("abstract") == null ? null:new_.get("abstract").toString();
			String title_ = new_.get("title") == null ? "":new_.get("title").toString();
			String sourceurl_ = new_.get("source_url") == null ? null:new_.get("source_url").toString();
			String littleimageurl_ = new_.get("middle_image") == null ? "":new_.get("middle_image").toString();
			String label_ = new_.get("label") == null ? "":new_.get("label").toString();
			String image_url = new_.get("image_url") == null ? null:new_.get("image_url").toString();
			String group_id = new_.get("group_id") == null ? null:new_.get("group_id").toString();
			//JSONArray image_list = new_.getJSONArray("image_list");
			//System.out.println(i+1+": "+abstract_+" | "+title_+label_ + image_url);
			news.setAbstractValue(abstract_);
			news.setImageurl(image_url);
			news.setLabel(label_.length()>50? label_.substring(0, 49):label_);
			news.setLittleimageurl(littleimageurl_.length()>100? "":littleimageurl_);
			news.setSourceurl(sourceurl_);
			
			news.setTag(CAR);
			
			news.setTitle(title_.length()>100? title_.substring(0, 99):title_);
			
			//去广告
			if(news.getAbstractValue()==null||news.getLabel()==null||!news.getAbstractValue().equals("")||!news.getLabel().equals("广告")) {
				NewsInfoCRUD.insert(news);
			}
			
			System.out.println(news.getTitle()+group_id);
		}
		
		return max_behot_time;
		
	}

	public String parseInputStream(InputStream in) throws IOException {
		
		BufferedInputStream buff = new BufferedInputStream(in);
		
		byte[] b = new byte[1024*1024];
		
		int len = 0;
		
		StringBuilder sb = new StringBuilder();
		while((len = buff.read(b))!=-1) {
			sb.append(new String(b,0,len));
		}
		
		System.out.println(sb);
		
		return sb.toString();
	
	}
	
	 static String max_behot_time_next = "0";//1519626309
	
	 /*	hot last:1519609772 
	  * game last:1519485047
	  * tech
	  * fun:
	  * finace last:1519541523
	  * */
	public static void main(String[] args) throws IOException, KeyManagementException, NoSuchAlgorithmException, InterruptedException {
		Spider s = new Spider();
		for(int i = 0 ;i<50;i++) {
			System.out.println("max_behot_time_next:"+max_behot_time_next);
			String[] asandcp = TouTiaoCPANDAS.getCpAndAS()==""? null:TouTiaoCPANDAS.getCpAndAS().split("#");
			//System.out.println(asandcp[0]+"||"+asandcp[1]);
			System.out.println("第"+i+1);
			Thread.sleep(1000);
			s.getInfo(CAR,max_behot_time_next,max_behot_time_next,asandcp[0],asandcp[1],"RN6O1wAAHk-4p2SrXHek40Tejs");
			//记得修改news中tag属性
		}
	}
	
	
	
}
