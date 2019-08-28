package cn.Tchat01;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Recieve implements Runnable {
	private DataInputStream dis;
	private Socket client;
	private boolean  IsRunning;
	public Recieve(Socket client) {
		this.client = client;
		this.IsRunning = true;
		try {
			dis = new DataInputStream(client.getInputStream());
		} catch (IOException e) {
			this.release();
		}
	} 
	

	@Override
	public void run() {
		
		while(IsRunning) {
			String msg = receive();
			if(! msg.equals("")) {
				System.out.println(msg);
			}
			
		}
	}

	private String receive() {
		String msg ="";
		try {
			msg = dis.readUTF();
		} catch (IOException e) {
			this.release();
		}
		
		return msg;
	}
	
	private void release() {
		this.IsRunning =false;
		ChatUtils.close(dis,client);
	}
	
	

}
