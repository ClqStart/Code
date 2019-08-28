package UDPCase;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TalkRecive implements Runnable {
	
	private DatagramSocket server;
	private String from;
	
	 public TalkRecive(int port, String from) {
		 this.from = from;
		 try {
			server = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		while(true) {
			byte[] container = new byte[1024*60];
			DatagramPacket packet = new DatagramPacket(container, 0,container.length);
			 // 3.阻塞式接受包裹
			try {
				server.receive(packet);
				//分析数据
				byte[]  datas = packet.getData();
				int len = packet.getLength();
				
				String data =new String(datas,0,len); 
				System.out.println("from " +this.from + ":"+data);
				
				if(data.equals("bye")) {
					break;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 5。关闭释放资源
		server.close();
		
	}

}
