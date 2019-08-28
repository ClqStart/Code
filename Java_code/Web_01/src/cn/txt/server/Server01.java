package cn.txt.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;



public class Server01 {
	
	private ServerSocket serverSocket;
	public static void main(String[] args) {
		Server01 server = new Server01();
		server.start();
		
	}
	
	
	public void stop() {
		
	}
	
	public void receive() {
		try {
			Socket client=serverSocket.accept();
			System.out.println("一个客户端建立连接。。。。。。。");
			InputStream is = client.getInputStream();
			byte[] datas=new byte[1024*1024];
			int len = is.read(datas);
			String requestInfo = new String(datas,0,len);
			System.out.println(requestInfo);
			send(client);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("客户端连接错误出现错误");
		}
	}
	
	public void send(Socket client) throws IOException {
		StringBuilder  content = new StringBuilder();
		String blank = " ";
		String  CRLF = "\r\n";
		content.append("<html>");
		content.append("<head>");
		content.append("<title>");
		content.append("服务器响应成功");
		content.append("</title>");
		content.append("</head>");
		content.append("<body>");
		content.append("shsxt server终于回来了。。。。");
		content.append("</body>");
		content.append("</html>");
		
		int size = content.toString().getBytes().length;
		StringBuilder  responseInfo = new StringBuilder();
		//1、响应行: HTTP/1.1 200 OK
		responseInfo.append("HTTP/1.1").append(blank);
		responseInfo.append(200).append(blank);
		responseInfo.append("OK").append(CRLF);
		//2、响应头(最后一行存在空行):
		/*
		 Date:Mon,31Dec209904:25:57GMT
		Server:shsxt Server/0.0.1;charset=GBK
		Content-type:text/html
		Content-length:39725426
		 */
		responseInfo.append("Date:").append(new Date()).append(CRLF);
		responseInfo.append("Server:").append("shsxt Server/0.0.1;charset=GBK").append(CRLF);
		responseInfo.append("Content-type:text/html").append(CRLF);
		responseInfo.append("Content-length:").append(size).append(CRLF);
		responseInfo.append(CRLF);
		//3、正文
		responseInfo.append(content.toString());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
	}
	public void start() {
		
		try {
			serverSocket = new ServerSocket(8888);
			receive();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("服务器端启动错误");
		}
	}
	
}
