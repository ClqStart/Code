package Decorate;


/**
 *1. 抽象()
 */
public class Decorate {
	public static void main(String[] args) {
		
		Person p = new Person();
		p.say();
		
		Amplifier am = new Amplifier(p);
		am.say();
	}

}


interface Say {
	void say();
}


class Person implements Say{
	private int voice=10;

	@Override
	public void say() {
		System.out.println("人的声音为："+this.getVoice());
		
	}

	public int getVoice() {
		return voice;
	}

	public void setVoice(int voice) {
		this.voice = voice;
	}
		
}

class Amplifier implements Say{

	private Person p;
	
	Amplifier(Person p) {
		this.p =p;
	}
	
	@Override
	public void say() {
		System.out.println("人的声音为："+p.getVoice()*100);
		System.out.println("这是噪音");
		
		
	}
	
}
