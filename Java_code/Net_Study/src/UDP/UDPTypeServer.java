package UDP;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPTypeServer {
	public static void main(String[] args) throws IOException {
		System.out.println("接受中。。。。");
		 //1 创建服务器端的DatagramSocket
		DatagramSocket server = new DatagramSocket(9999);
		 //  2. 准备数据转换成字节数组
		byte[] container = new byte[1024*60];
		DatagramPacket packet = new DatagramPacket(container, 0,container.length);
		 // 3.阻塞式接受包裹
		server.receive(packet);
		
		//4分析数据
		byte[]  datas = packet.getData();
		int len = packet.getLength();
		
		
		DataInputStream dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
		String msg = dis.readUTF();
		int age  = dis.readInt();
		boolean flag = dis.readBoolean();
		char ch = dis.readChar();
		System.out.println(flag+msg);
		
		// 5。关闭释放资源
		server.close();
	}

}
