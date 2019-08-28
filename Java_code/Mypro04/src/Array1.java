
public class Array1 {
	public static void main(String[] args) {
		String[]  str = {"阿里","尚学堂","京东","搜狐","网易"};
		String[] str1= new String[6];
		Array1.dElement(str, 2);
		str = Array1.expand(str);
		
	for(String each:str) {
		System.out.println(each);
	}
 	} 
	
	public static  String[] dElement(String[] s,int index ) {
		System.arraycopy(s, index+1, s, index, s.length-index-1);
		s[s.length-1]=null;
		for(String element:s ){
			System.out.println(element);
			
		}
		return s;
	}
	
	public static String[]  expand(String[] s) {
		String[] s1 = new String[s.length+10];
		System.arraycopy(s, 0, s1, 0, s.length);
		return s1;
	}

}
