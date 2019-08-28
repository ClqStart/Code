package Tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client1 {
	public static void main(String[] args) throws  IOException {
		
		System.out.println("-----client------");
		//1指定端口，建立连接
		Socket client = new Socket("localhost",8888);
		//2.操作：输入输出流操作
		DataOutputStream  dos = new DataOutputStream(client.getOutputStream());
		String  data ="hello";
		System.out.println(data);
		dos.writeUTF(data);
		dos.flush();
		//4.释放资源
		dos.close();
		client.close();
		
}
}
