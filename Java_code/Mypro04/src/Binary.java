
import java.util.Arrays;;
public class Binary {
	public static void main(String[] args) {
	
		int[] arr= {30,20,50,10,80,9,7,12,100,40,8};
		int value=10;
		Arrays.sort(arr);
		System.out.println(myBinarySeacher(arr, 1));
}		
	
	public static int myBinarySeacher(int[] arr,int value ) {
		int low =0;
		int hight = arr.length-1;
		while(low <= hight) {
			int mid = (low +hight)/2;
			
			if (value ==arr[mid]) {
				return mid;
			}
			if(value < arr[mid]) {
				hight = mid -1;
			}
			if (value >arr[mid]) {
				low = mid +1;
			}
		}
		
		return -1;
	}
}
