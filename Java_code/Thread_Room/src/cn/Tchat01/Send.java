package cn.Tchat01;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable{
	private DataOutputStream dos;
	private Socket client;
	private BufferedReader console;
	private boolean  IsRunning; 
	private String name;
	public Send( Socket client,String name){
		this.client =client;
		console = new BufferedReader(new InputStreamReader(System.in));
		this.IsRunning = true;
		this.name =name;
		try {
			dos = new DataOutputStream(client.getOutputStream());
			//管道好了start()前进行发送名字n
			send(name);
		} catch (IOException e) {
			System.out.println("客户端1");
			this.release();
		}
	}

	@Override
	public void run() {
		while(IsRunning){
			String msg = getStrFromConsole();
			if(! msg.equals("")) {
			send(msg);
			}
		}
		
	}
	
	private void send(String msg) {
		try {
			dos.writeUTF(msg);
			dos.flush();
		} catch (IOException e) {
			System.out.println("客户端3");
			this.release();
		}
		}
	//从控制台获取
	private String getStrFromConsole() {
		try {
			return  console.readLine();
		} catch (IOException e) {
			this.release();
		}
		return "";
	}
	private void release() {
		this.IsRunning =false;
		ChatUtils.close(dos,client);
	}

}
