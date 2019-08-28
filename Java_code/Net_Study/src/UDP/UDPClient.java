package UDP;
/*
 *   
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UDPClient {
	public static void main(String[] args) throws IOException {
		System.out.println("发送方启动。。。。");
		
		//创建服务器端的DatagramSocket
		DatagramSocket client = new DatagramSocket(8888);
		//准备数据转换成字节数组
		
		String data = "上海尚学堂";
		
		byte[]  datas = data.getBytes();
		//封装成DatagramPacket包裹，需要指定目的地
		DatagramPacket packet = new DatagramPacket(datas, 0,datas.length,new InetSocketAddress("localhost",9999));
		//发送包裹
		client.send(packet);
		
		//关闭释放资源
		client.close();
	}

	
}
