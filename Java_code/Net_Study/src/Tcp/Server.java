package Tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
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
		System.out.println(data);


		//4.释放资源
		dis.close();
		client.close();
	}
}
