
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client= new Socket("localhost",8889);
		boolean  IsRunning =true;
		//客户端发送消息
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		DataInputStream dis = new DataInputStream(client.getInputStream());
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		while(IsRunning) {
			String msg = console.readLine();
			dos.writeUTF(msg);
			dos.flush();
			//获取消息
			msg = dis.readUTF();
			System.out.println(msg);
		}
		
		dos.close();
		dis.close();
		client.close();
	}

}
