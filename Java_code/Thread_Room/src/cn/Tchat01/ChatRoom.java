package cn.Tchat01;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatRoom {
	private static CopyOnWriteArrayList<Channel> all = new CopyOnWriteArrayList<>();
 
	public static void main(String[] args) throws IOException {
		System.out.println("---Server---");
		//1指定端口
		ServerSocket server = new ServerSocket(8889);
		//2.阻塞等待接受accept
		
		while(true) {
			Socket  client = server.accept();
			System.out.println("一个客户建立起来了");
			
			Channel c =new Channel(client);
			all.add(c);
			new Thread(c).start(); 
			
		}
		
	}
	
	static class Channel implements Runnable{
		
		private DataInputStream dis;
		private DataOutputStream dos; 
		private Socket client;
		private boolean  IsRunning;
		private String name;
		public Channel(Socket client) {
			this.client = client;
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
				IsRunning =true;
				//建好管道初始化时拿到该管道的名称
				this.name = receive();
				this.send("欢迎你的到来");
				this.sendOther(this.name+"来到了5020宿舍",true);
			} catch (IOException e1) {
				relese();
			}
	}
	private String receive() {
		String msg ="";
		try {
			msg = dis.readUTF();
		} catch (IOException e) {
			relese();
		}
		
		return msg;
	}
	private void send(String msg) {
		try {
			dos.writeUTF(msg);
			dos.flush();
		} catch (IOException e) {
			relese();
		}
	}
	private void sendOther(String msg,boolean isSys) {
		//私聊 @xxx:msg
		boolean isPrivate = msg.startsWith("@");//返回boolean
		if(isPrivate) {
			int idx = msg.indexOf(":");
			System.out.println(idx);
			String targetName = msg.substring(1,idx);
			msg = msg.substring(idx+1);
			for(Channel other:all) {
				if(other.name.equals(targetName) ) {
					other.send(this.name+"私聊你，对你说"+msg);
				}
			}
			
		}else {
		
			for(Channel other:all) {
				if(other ==this) {
					continue;
				}
				if(!isSys){
					other.send(this.name+"对所有人说："+msg);
				}else {
					other.send(msg);//系统消息
				}
			}	
		}
	}
	private void  relese() {
		this.IsRunning =false;
		ChatUtils.close(dis,dos,client);
		//退出
		all.remove(this);
		sendOther(this.name+"离开了5020", true);
		}
	@Override
	public void run() {
		while(IsRunning) {
			String msg = receive();
			if(!msg.equals("")) {
				//send(msg);
				sendOther(msg,false);
			}
		}
		
	}
	}
}



