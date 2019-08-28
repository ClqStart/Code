package cn.clq.Net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class SpiderTest2 {
	public static void main(String[] args) throws IOException {
		//获取URL
		URL url = new URL("https://www.dianping.com");
		//下载资源
		
		HttpURLConnection conn =(HttpURLConnection )url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("User-Agent"," Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		
		String msg =null;
		while(null !=(msg = br.readLine())){
			System.out.println(msg);
		}
		
		br.close();
	}	
	
}
