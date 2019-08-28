package Tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class LocalClient2 {
	public static void main(String[] args) throws  IOException {
		
		System.out.println("-----client------");
		

		BufferedReader  console = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("请输入用户名");
		String uname = console.readLine();
		System.out.print("请输入密码");
		String upwd = console.readLine();
		
		//1指定端口，建立连接
		Socket client = new Socket("localhost",8888);
		//2.操作：输入输出流操作
		DataOutputStream  dos = new DataOutputStream(client.getOutputStream());
		dos.writeUTF("uname="+uname+"&"+"upwd="+upwd);
		dos.flush();
		//4.释放资源
		dos.close();
		client.close();
		
}
}
