package 容器;

import java.util.*;

public class TestList {
	public static void main(String[] args) {
		Collection<String> c = new ArrayList<>();
		System.out.println(c.size());
		System.out.println(c.isEmpty());
		
		c.add("高老大");
		c.add("高老二  ");
		
		System.out.println(c.size());
		System.out.println(c.isEmpty());
		
		c.remove("高老大");
		System.out.println(c.size());
		//转换成数组
		Object[] obj = c.toArray();
		System.out.println(Arrays.toString(obj));
		System.out.println(c.contains("高老大"));
		c.clear();
		System.out.println(c.size());
		System.out.println(c.isEmpty());
		System.out.println(c.contains("高老"));
		System.out.println("#############");
		test03();
		
	}

	public static void test03() {
		List<String>  list= new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("a");
		list.add("c");
		list.add("d");
		
		System.out.println(list);
		
		list.add(2,"e");
		System.out.println(list);
		list.remove(2);
		System.out.println(list);
		
		
		list.set(2, "李");
		System.out.println(list);
		System.out.println(list.indexOf("b"));
		System.out.println(list.lastIndexOf("b"));
		
	}


}