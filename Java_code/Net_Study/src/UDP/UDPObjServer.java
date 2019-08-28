package UDP;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;


public class UDPObjServer {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
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
		
		ObjectInputStream dis = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
		String msg = dis.readUTF();
		int age  = dis.readInt();
		boolean flag = dis.readBoolean();
		char ch = dis.readChar();
		
		java.lang.Object str = dis.readObject();
		java.lang.Object date = dis.readObject();
		java.lang.Object employee = dis.readObject();
		
		System.out.println(flag);
		
		
		if(str instanceof String) {
			String strObj =(String) str;
			System.out.println(strObj);
		}
		
		if(date instanceof Date) {
			Date dateObj =(Date) date;
			System.out.println(dateObj);
		}
		
		if(employee instanceof Employee) {
			Employee empObj =(Employee) employee;
			System.out.println(empObj.getName()+"--->"+empObj.getSalary());
		}
	
		
		// 5。关闭释放资源
		server.close();
	}

}
