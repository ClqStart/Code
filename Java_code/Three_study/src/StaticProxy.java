import java.time.temporal.WeekFields;

/**
 *1.静态代理
 * @author clq
 *
 */
public class StaticProxy {
	public static void main(String[] args) {
		
		
		new WeddingCompany(new You()).happyMarry();
	}

}


interface Marry{
	
	void happyMarry();
}


class You implements Marry {

	@Override
	public void happyMarry() {
		System.out.println("you and 嫦娥奔月");
		
	}
	
}

class WeddingCompany implements Marry{

	private Marry target ;
	
	public WeddingCompany(Marry target) {
		this.target = target;
	}
	
	
	@Override
	public void happyMarry() {
		ready();
		this.target.happyMarry();
		after();
	}
	
	private void ready() {
		System.out.println("准备前");
	}
	
	private void after() {
		System.out.println("结婚后");
	}
	
}
