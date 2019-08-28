
public class TestIf {

	public static void main(String[] args) {
		double d =Math.random();
		System.out.println(d);
		
		
		int i = (int)(6*Math.random())+1;
		System.out.println(i);
		if(i<=3) {
			System.out.println("小小");
		}
		
		
	System.out.println("################");
	double r = 4*Math.random();
	double area = Math.PI * Math.pow(r, 2);
	double circle = 2*Math.PI*r;
	
	System.out.println("面积是:"+area);
	System.out.println("周长"+area);

	
	char c = 'a';
	int rand = (int)(26*Math.random());
	char c2 = (char)(c +rand);
	System.out.print(c2+":");
	switch(c2) {
	case 'a':
	case  'e':
	case 'i':
	case 'o':
	case 'u':
		System.out.println("元音");
		break;
	case 'y':
	case 'w':
		System.out.println("半元音");
		break;
	default:
		System.out.println("辅音");
		break;
	
	}
	
	System.out.println("___________________");
	int t=1;
	int sum=0;
	while(t<=100) {
		sum += t++;
		
	}
	System.out.println(sum);
	
	
	for(int e=1;e<=5;e++) {
		
		 System.out.println(e+"\t");
	}
	
}
	}


