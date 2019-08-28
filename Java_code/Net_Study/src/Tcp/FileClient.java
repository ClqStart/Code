package Tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class FileClient {
	public static void main(String[] args) throws  IOException {
		
		System.out.println("-----client------");
		//1指定端口，建立连接
		Socket client = new Socket("localhost",8888);
		//2.操作：输入输出流操作
		InputStream is = new BufferedInputStream(new FileInputStream("src/ball.png"));
		OutputStream os = new BufferedOutputStream(client.getOutputStream());
		
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
