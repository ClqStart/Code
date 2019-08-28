package cn.Tchat01;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("---Client---");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入用户名");
		String name =br.readLine();
		Socket client= new Socket("localhost",8889);
		//客户端发送消息
		new Thread(new Send(client,name)).start();
		new Thread(new Recieve(client)).start();
		
		}

}
