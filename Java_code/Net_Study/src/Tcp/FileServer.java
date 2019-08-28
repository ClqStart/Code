package Tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
	public static void main(String[] args) throws IOException {
		//1指定端口，使用ServerSocket创建服务器
		System.out.println("-----Server-------");
		ServerSocket  server = new ServerSocket(8888);
		//2.阻塞式等待accept
		Socket client  = server.accept();
		System.out.println("一个客户端建立联系");

		//3.操作：输入输出流操作
		InputStream is = new BufferedInputStream(client.getInputStream());
		OutputStream os = new BufferedOutputStream(new FileOutputStream("src/tcp.png"));
		
		byte[] flush = new byte[1024];
		int len =-1;
		while((len =is.read(flush))!= -1){
			os.write(flush,0,len);
			}
		os.flush();

		//4.释放资源
		os.close();
		is.close();
		client.close();
	}
}
