package UDPCase;

public class TalkTeacher {
	public static void main(String[] args) {
		new Thread(new TalkRecive(9999,"Student")).start();
		new Thread(new TalkSend(3333,"localhost", 6666)).start();
		
	}
}
