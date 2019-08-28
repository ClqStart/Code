package cn.txt.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request1 {
	
	private  String requestInfo;
	//请求方式
	private String method;
	//url
	private String URL;
	//请求参数
	private String queryStr;
	
	private Map<String ,List<String>> parameterMap;
	private  final String CRLF="\r\n";
	
	public Request1(Socket client) throws IOException {
		this(client.getInputStream());
		}
	
	public Request1(InputStream is) {
		parameterMap = new HashMap<String,List<String>>();
		byte[] datas = new byte[1024*1024];
		int len;
		try {
			 len = is.read(datas);
			 this.requestInfo = new String(datas,0,len);
		} catch (IOException e) {
			e.printStackTrace();
		}
		parseRequestInfo();
		
	}
	private void parseRequestInfo() {
		System.out.println("------分解-------");
		System.out.println(requestInfo);
		
		this.method = this.requestInfo.substring(0,this.requestInfo.indexOf('/')).toLowerCase().trim();
		
		
		int startIndex = this.requestInfo.indexOf('/')+1;
		int endIndex =this.requestInfo.indexOf("HTTP/");
		this.URL = this.requestInfo.substring(startIndex,endIndex).trim();
		
		int querIndex = this.requestInfo.indexOf("?");
		if(querIndex>=0) {
			String[] urlArry = this.URL.split("\\?");
			this.URL = urlArry[0];
			this.queryStr = urlArry[1];
		}
		//post和get参数位置不同
		if(method.equals("post")) {
			String qStr = this.requestInfo.substring(this.requestInfo.lastIndexOf(CRLF)).trim();
			if(null==queryStr) {
				this.queryStr =qStr;
			}else {
				this.queryStr += "&" +qStr;
			}
		}
		queryStr = null==queryStr? "":queryStr;
		System.out.println(method +"--->"+URL+"--->"+queryStr);
		convertMap();
		}
	//fav=1&fav=2&uname=shsxt&age=18&others=
	private void convertMap() {
		//1、分割字符串 &
		String[] keyValues =this.queryStr.split("&");
		for(String queryStr:keyValues) {
			//2、再次分割字符串  =
			String[] kv = queryStr.split("=");
			kv =Arrays.copyOf(kv, 2);
			//获取key和value
			String key = kv[0];
			String value = kv[1]==null?null:decode( kv[1],"utf-8");
			//存储到map中
			if(!this.parameterMap.containsKey(key)) { //第一次
				this.parameterMap.put(key, new ArrayList<String>());
			}
			parameterMap.get(key).add(value);			
		}
	}
	/**
	 * 处理中文
	 * @return
	 */
	private String decode(String value,String enc) {
		
			try {
				return java.net.URLDecoder.decode(value,enc);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		return null;	
	}
	public String[] getparameterValues(String key) {
		List<String> values = this.parameterMap.get(key);
		if(null==values ||values.size()<1) {
			return null;
		}
		return values.toArray(new String[0]);
	}

	public String getparameterValue(String key) {
		String[] values = getparameterValues(key);
		return values ==null?null:values[0];
	}

	public String getURL() {
		return URL;
	}

	
	public String getQueryStr() {
		return queryStr;
	}




	public Map<String, List<String>> getParameterMap() {
		return parameterMap;
	}



	
}
