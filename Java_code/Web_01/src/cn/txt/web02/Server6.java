package cn.txt.web02;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;





public class Server6 {
	private ServerSocket serverSocket ;
	private boolean isRunning;
	public static void main(String[] args) {
		Server6 server = new Server6();
		server.start();
	}
	//启动服务
	public void start() {
		try {
			serverSocket =  new ServerSocket(8888);
			isRunning = true;
			 receive();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("服务器启动失败....");
			stop();
		}
	}
	//接受连接处理
	public void receive() {
		while(isRunning) {
		try {
			
			Socket client = serverSocket.accept();
			System.out.println("一个客户端建立了连接....");
			new Thread(new DisPatcher(client)).start();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("客户端错误");
		}
	} 
}
	//停止服务
	public void stop() {
		isRunning = false;
		try {
			this.serverSocket.close();
			System.out.println("服务器停止");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
