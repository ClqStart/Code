
public class Back01 {
	public static void main(String[] args) {
		
		Ilove love=(int a)->{

			System.out.println("I am lambda"+a);
		};
		
		love.lambda(100);
		
		
		Ilove love1=(a)->{

			System.out.println("I am lambda"+a);
		};
		love1.lambda(50);
	
	
	
		Ilove love2=a->{

		System.out.println("I am lambda"+a);
		};
		love2.lambda(10);
		
		
		Ilove love3=a->System.out.println("I am lambda"+a);
			love3.lambda(5);
		
		
		

}
}




interface Ilove{
	
	void lambda(int a);
}

class  love implements  Ilove{

	
	public void lambda(int a) {
		System.out.println("I am lambda"+a);
	}
}
