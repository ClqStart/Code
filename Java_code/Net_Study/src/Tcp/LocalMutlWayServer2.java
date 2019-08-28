package Tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class LocalMutlWayServer2 {
	public static void main(String[] args) throws  UnknownHostException, IOException {
		//1指定端口，使用ServerSocket创建服务器
		System.out.println("-----Server-------");
		ServerSocket  server = new ServerSocket(8888);
		
		boolean isRunning = true;
		
		//2.阻塞式等待accept
		while(isRunning) {
			Socket client  = server.accept();
			System.out.println("一个客户端建立联系");
			new Thread(new Channel(client)).start();
		}
	
	 server.close();
	}
	static class Channel implements Runnable{
		private Socket client;
		//3.操作：输入输出流操作
		private DataInputStream dis;
		private DataOutputStream  dos;
		
		public Channel(Socket client) {

			this.client = client;
			try {
				dis = new DataInputStream(client.getInputStream());
				dos= new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
				release();
			}
		}
		//接收数据
		private String receive() {
			
			String datas ="";
			try {
				datas = dis.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return datas;
	}  
		//发送数据
		private void send(String msg) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		//释放
		private void release() {
			try {
				if(null !=dos) {
				dos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {if(null !=dis) {
				dis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(null != client) {
				client.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

		@Override
		public void run() {

			String uname="";
			String upwd ="";
			
			//分析
			String[]  dataArray = receive().split("&");
			for(String info:dataArray) {
				String[] userInfo = info.split("=");
				System.out.println(userInfo[0]);
			
				//System.out.println(userInfo[0]+"---->"+userInfo[1]);
				if(userInfo[0].equals("uname")) {
					System.out.println("你的用户名为："+userInfo[1]);
					uname =userInfo[1];
				}else if(userInfo[0].equals("upwd")) {
					System.out.println("你的用户名为："+userInfo[1]);
					upwd = userInfo[1];
				}
			}
			
			//输出
			if(uname.equals("abc")&& upwd.equals("abc")) {
				send("登陆成功");
			}else {
				send("登陆失败");
			}

			//4.释放资源
			release();
			
		}
		
}
	
}