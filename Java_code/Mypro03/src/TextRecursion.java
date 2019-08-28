

public class TextRecursion {
	public static  void main(String[] args) {
		long d1 = System.currentTimeMillis();
		System.out.printf("%d的阶层结果为%s%n:",10,factorial(10));
		long d2 = System.currentTimeMillis();
		System.out.printf("运行时间：%s%n",d2-d1);
		
		
		  int sum=0;
	        for(int i=1;i<10;i++){
	            do{
	                i++;
	                if(i%2!=0)
	                    sum+=i;
	            }while(i<6);
	        }
	        System.out.println(sum);
	}
	
	static long factorial(int n){
		if(n==1) {
			return 1;
		}else {
			return n*factorial(n-1);
		}
		}
	
}
