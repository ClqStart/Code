package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPFileServer {
	public static void main(String[] args) throws IOException {
		System.out.println("接受中。。。。");
		 //1 创建服务器端的DatagramSocket
		DatagramSocket server = new DatagramSocket(9999);
		 //  2. 准备数据转换成字节数组
		byte[] container = new byte[1024*60];
		DatagramPacket packet = new DatagramPacket(container, 0,container.length);
		 // 3.阻塞式接受包裹
		server.receive(packet);
		
		//分析数据
		byte[]  datas = packet.getData();
		int len = packet.getLength();
		IOUtils.byteArrayToFile(datas, "src/copy.png");
		
		// 5。关闭释放资源
		server.close();
	}

}
