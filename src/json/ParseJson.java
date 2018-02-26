package json;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.toutiao.pojo.NewsInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ParseJson {
	
	public static void parseJson(String filename) throws Exception {
		FileInputStream fis = new FileInputStream("src/json/"+filename);
		
		String json = parseInputStream(fis);
		
		stringToJson(json);
	}
	
	public static String parseInputStream(InputStream in) throws IOException {
		
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
	
	public static void stringToJson(String str) {
		JSONObject json = JSONObject.fromObject(str);
		
		JSONArray data = json.getJSONArray("data");
		
		JSONObject nexttime = json.getJSONObject("next");
		
		String max_behot_time = nexttime.get("max_behot_time").toString();
		
		for(int i = 0 ;i<data.size();i++) {
			NewsInfo news = new NewsInfo();
			JSONObject new_ = data.getJSONObject(i);
			String abstract_ = new_.get("abstract").toString();
			String title_ = new_.get("title").toString();
			String sourceurl_ = new_.get("source_url").toString();
			String littleimageurl_ = new_.get("middle_image") == null ? null:new_.get("middle_image").toString();
			String label_ = new_.get("label") == null ? null:new_.get("label").toString();
			String image_url = new_.get("image_url") == null ? null:new_.get("image_url").toString();
			String group_id = new_.get("group_id") == null ? null:new_.get("group_id").toString();
	
			System.out.println(title_+group_id);
		}
	}
	
	public static void main(String[] args) throws Exception {
		parseJson("test5.json");
	}
}
