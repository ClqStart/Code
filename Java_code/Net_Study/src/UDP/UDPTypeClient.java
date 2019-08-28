package UDP;
/*
 *   
 */

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPTypeClient {
	public static void main(String[] args) throws IOException {
		System.out.println("发送方启动。。。。");
		
		//创建服务器端的DatagramSocket
		DatagramSocket client = new DatagramSocket(8888);
		//准备数据转换成字节数组
		ByteArrayOutputStream baos= new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(new  BufferedOutputStream(baos));
		dos.writeUTF("编码辛酸泪");
		dos.writeInt(18);
		dos.writeBoolean(false);
		dos.writeChar('a');
		dos.flush();
		byte[]  datas = baos.toByteArray();
		//封装成DatagramPacket包裹，需要指定目的地
		DatagramPacket packet = new DatagramPacket(datas, 0,datas.length,new InetSocketAddress("localhost",9999));
		//发送包裹
		client.send(packet);
		
		//关闭释放资源
		client.close();
	}

	
}
