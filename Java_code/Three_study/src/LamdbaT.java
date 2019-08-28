
public class LamdbaT {
	//静态内部类
	static class Like2 implements ILike{

		
		public  void lambda() {
			System.out.println("I am lambda2");
		}
		

	public static void main(String[] args) {
		ILike like = new Like();
		like.lambda();
		
		 class Like3 implements ILike{

			
			public  void lambda() {
				System.out.println("I am lambda3");
			}
		 }
			
		
		
		like = new Like2();
		like.lambda();
		
		like = new Like3();
		like.lambda();
		
		
		like = new ILike() {
			public  void lambda() {
			System.out.println("I am lambda4");
		}
		};
		like.lambda();

		
		//Lambda
		
		like = ()->{
			System.out.println("I am lambda5");
		};
		like.lambda();
	}
	
}
	
}

interface ILike{
	
	void lambda();
}

class Like implements ILike{

	
	public void lambda() {
		System.out.println("I am lambda1");
	}
}
