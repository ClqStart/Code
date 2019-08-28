public class Test01 {
 
	public static void main(String[] args) {
		int[] arr01 = new  int[10];
		String att02[] = new String[5];
		User[] arr03 = new User[3];
		
		for(int i=0;i<arr01.length;i++) {
			arr01[i] = i*10;
			}
		for (int i =0;i<arr01.length;i++) {
			System.out.println(arr01[i]);
		}
	
	//一般只用来读取foreach
		System.out.println("#######################");
		for (int m:arr01) {
			System.out.println(m);
		}
	
	
	}
}
class User{
	private int id;
	private String name;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}