
public class Useer {
	int id;
	String name;
	String pwd;
	
	
	public Useer(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public void  testParameterTransfer01(Useer u){
		u.name = "高校七";
	}
	//创建了新的对象地址 ，传值进行副本的拷贝传值
	public void  testParameterTransfer02(Useer u){
		u = new Useer(200,"高三");
		u.name = "高校8";
	}
	
	
	public static void main(String[] args) {
		Useer u1 = new Useer(100,"六");
		
		u1.testParameterTransfer01(u1);
		System.out.println(u1.name);
		
		u1.testParameterTransfer02(u1);
		System.out.println(u1.name);

	}
}
