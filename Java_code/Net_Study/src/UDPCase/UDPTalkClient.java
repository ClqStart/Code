package UDPCase;
/*
 *   
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UDPTalkClient {
	public static void main(String[] args) throws IOException {
		System.out.println("发送方启动。。。。");
		
		//创建服务器端的DatagramSocket
		DatagramSocket client = new DatagramSocket(8888);
		//准备数据转换成字节数组
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String data = reader.readLine();
			
			byte[]  datas = data.getBytes();
			//封装成DatagramPacket包裹，需要指定目的地
			DatagramPacket packet = new DatagramPacket(datas, 0,datas.length,new InetSocketAddress("localhost",9999));
			//发送包裹
			client.send(packet);
		    if(data.equals("bye")) {
		    	break;
		    }
		}
			//关闭释放资源
			client.close();

	}
}
