import java.util.Arrays;

public class BubbleSort {
	public static void main(String[] args) {
		boolean flag=true;
		int[]  values = {1,5,2,7,3,9,4,8,6};
		
		for (int i=0;i<values.length-2;i++) {
		for(int j =0;j<values.length-i-1;j++) {
			if(values[j]>values[j+1]) {
				
				int t = values[j]; 
				values[j] = values[j+1];
				values[j+1]=t;
				
				flag=false;
			}
			
			if(flag) {
				break;
			}
			
		}
		}
		System.out.println(Arrays.toString(values));
		
		for(int tem: values ) {
			System.out.println(tem);
		}
	}

}
