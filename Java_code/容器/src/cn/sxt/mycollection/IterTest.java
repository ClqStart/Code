package cn.sxt.mycollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class IterTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		
		for(int i=0;i<5;i++) {
			list.add("a"+i);
		}
		System.out.println(list);
		
		for(Iterator<String> iter=list.iterator();iter.hasNext();) {
			String temp = iter.next();
			System.out.print(temp+" ");
			if (temp.endsWith("3")) {
				iter.remove();
			}
			
		}
		//wile 循环
		
//		Iterator  iter2 =list.iterator();
//		while(iter2.hasNext()){
//		    Object  obj = iter2.next();
//		    iter2.remove();//如果要遍历时，删除集合中的元素，建议使用这种方式！
//		    System.out.println(obj);
//		}

		
		
		System.out.println();
		System.out.println(list);
		Set1();
		MyMap();
		MyMa1p();
	}
	
	public static void Set1() {
		Set<Integer> myset = new HashSet<>();
		for(int i=0;i<10;i++) {
			myset.add(i);
		}
		System.out.println(myset);
		
		for(Iterator<Integer> iter1 = myset.iterator();iter1.hasNext();) {
			Integer temp = iter1.next();
			System.out.print(temp +" ");
			}
		System.out.println();
	}
	
	
	public static void MyMap() {
		Map<Integer, String> map1 = new HashMap<>();
		map1.put(2, "高四");
		map1.put(1, "高三");
		
		System.out.println(map1);
		Set<Entry<Integer,String>> ss = map1.entrySet();
		
		for(Iterator<Entry<Integer,String>> s1 = ss.iterator();s1.hasNext();) {
			Entry<Integer,String> e = s1.next();
			System.out.println(e.getKey()+"--- "+e.getValue());
		}
	}
	
	public static void MyMa1p() {
		Map<Integer, String> map2 = new HashMap<>();
		map2.put(2, "高四");
		map2.put(1, "高三");
		
		System.out.println(map2);
		
		Set<Integer>  k = map2.keySet();
		for(Iterator<Integer> it =k.iterator();it.hasNext();) {
			Integer key = it.next();
			System.out.println(key+"----"+map2.get(key));
		}
		}

}
