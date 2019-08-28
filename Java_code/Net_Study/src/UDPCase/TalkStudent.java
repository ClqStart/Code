package UDPCase;

public class TalkStudent {
	public static void main(String[] args) {
		new Thread(new TalkSend(7777,"localhost", 9999)).start();
		new Thread(new TalkRecive(6666,"Teacher")).start();
	}
}
