package cn.txt.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Request {
	
	private  String requestInfo;
	//请求方式
	private String method;
	//url
	private String URL;
	//请求参数
	private String queryStr;
	private  final String CRLF="\r\n";
	
	public Request(Socket client) throws IOException {
		this(client.getInputStream());
		}
	
	public Request(InputStream is) {
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
			System.out.println("------------");
			String qStr = this.requestInfo.substring(this.requestInfo.lastIndexOf(CRLF)).trim();
			if(null==queryStr) {
				this.queryStr =qStr;
			}else {
				this.queryStr += "&" +qStr;
			}
		}
		queryStr = null==queryStr? "":queryStr;
		System.out.println(method +"--->"+URL+"--->"+queryStr);
		
	}
}
