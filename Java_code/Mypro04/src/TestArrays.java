import java.util.Arrays;

public class TestArrays {

	public static void main(String[] args) {
		
		int[]  a = {10,20,30,5,85,3,24};
		System.out.println(a);
		
		System.out.println(a.toString());
		System.out.println(Arrays.toString(a));
		
		
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		
		
		System.out.println(Arrays.binarySearch(a, 30));
		
		
		
		//二维数组
		
		int[][] b = new int[3][];
		b[0] = new int[]{20,30};
		b[1] =  new int[] {10,15,80};
		b[2] = new int[] {50,60};
		
		System.out.println(b[1][2]);
		
		int[][]  c = {
				{20,30,40},
				{50,20},
				{200,300,400}
				};
		
		System.out.println(c[2][2]);
		
		
		Object[] a1 = {1001,"高淇",18,"讲师","2006-2-14"};
		Object[] a2 = {1002,"高小七",19,"助教","2007-10-10"};
		Object[] a3 = {1003,"高小琴",20,"班主任","2008-5-5"};
		
		
		Object[][]  tabledata = new Object[3][];
		tabledata[0] = a1;
		tabledata[1] = a2;
		tabledata[2] =a3;
		
		for(Object[] temp: tabledata) {
			System.out.println(Arrays.toString(temp));
		}
				
		
		
	}
}
