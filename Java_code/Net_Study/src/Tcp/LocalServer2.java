package Tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LocalServer2 {
	public static void main(String[] args) throws IOException {
		//1指定端口，使用ServerSocket创建服务器
		System.out.println("-----Server-------");
		ServerSocket  server = new ServerSocket(8888);
		//2.阻塞式等待accept
		Socket client  = server.accept();
		System.out.println("一个客户端建立联系");

		//3.操作：输入输出流操作
		DataInputStream  dis = new DataInputStream(client.getInputStream());
		String  data =dis.readUTF();
		//分析
		String[]  dataArray = data.split("&");
		for(String info:dataArray) {
			String[] userInfo = info.split("=");
			
			System.out.println(userInfo[0]+"---->"+userInfo[1]);
			if(userInfo[0].equals("uname")) {
				System.out.println("你的用户名为："+userInfo[1]);
			}else if(userInfo[0].equals("unpwd")) {
				System.out.println("你的用户名为："+userInfo[1]);
				
			}
		}
		
		
		//4.释放资源
		dis.close();
		client.close();
	}

}