package cn.txt.web02;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import cn.txt.web02.Response;



public class Server4 {
	private ServerSocket serverSocket ;
	public static void main(String[] args) {
		Server4 server = new Server4();
		server.start();
	}
	//启动服务
	public void start() {
		try {
			serverSocket =  new ServerSocket(8888);
			 receive();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("服务器启动失败....");
		}
	}
	//接受连接处理
	public void receive() {
		try {
			Socket client = serverSocket.accept();
			System.out.println("一个客户端建立了连接....");
			//获取请求协议
			Request1 request = new Request1(client);
			Response response = new Response(client);
			Servlet server =null;
			if (request.getURL().equals("login")) {
				server =new LoginServlet();
			}else if(request.getURL().equals("reg")) {
				 server=new RegisterServlet();
			}else {
				//
			}
			server.service(request, response);
			
			response.pushToBrowser(200);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("客户端错误");
		}
	}
	//停止服务
	public void stop() {
		
	}
}
