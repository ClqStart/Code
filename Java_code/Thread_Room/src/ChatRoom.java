import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatRoom {
 
	public static void main(String[] args) throws IOException {
		System.out.println("---Server---");
		//1指定端口
		ServerSocket server = new ServerSocket(8889);
		//2.阻塞等待接受accept
		
		while(true) {
			Socket  client = server.accept();
			System.out.println("一个客户建立起来了");
			
			new Thread(()->{
				DataInputStream dis =null;
				DataOutputStream dos =null;
			
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			boolean  IsRunning =true;
			while(IsRunning) {
				//3.接受消息
				String msg;
				try {
					msg = dis.readUTF();
					//4.返回消息
					dos.writeUTF(msg);
					dos.flush();
				} catch (IOException e) {
					e.printStackTrace();
					IsRunning =false;
				}
				
				}
			//释放资源
			try {
				if(null ==dos) {
				dos.close();
				}
			} catch (IOException e) {	
				e.printStackTrace();
			}
			try {if(null ==dis) {
				dis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {if(null ==client) {
				client.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			}).start(); 
			
		
		}
		
	}
}
