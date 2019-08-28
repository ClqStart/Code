package cn.sxt.test;

public class TestStringBuilder {
	public static void main(String[] args) {
		String str;
		
		StringBuilder sb = new StringBuilder("abcdef");
		
		System.out.println(Integer.toHexString(sb.hashCode()));
		System.out.println("#"+sb);
		
		sb.setCharAt(2, 'M');
		System.out.println(Integer.toHexString(sb.hashCode()));
		System.out.println(sb);
		
		StringBuilder s= new StringBuilder();
		
		for(int i=0; i<26;i++) {
			s.append((char)('a'+i));
			
	
		}	
		System.out.println(s);
		s.reverse();//倒转
		System.out.println(s);
		s.setCharAt(3,'高');
		System.out.println(s);
		s.insert(0,'我').insert(1,'爱');
		System.out.println(s);
		
		s.delete(20, 23);
		System.out.println(s);
	

}
	}
