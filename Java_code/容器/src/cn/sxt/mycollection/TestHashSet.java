package cn.sxt.mycollection;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class TestHashSet {
	
		
		HashMap map;
		
		private static final Object   PRESENT = new Object();
		
		
		
		public TestHashSet() {
			map = new HashMap();
			}
		
		public int size(){
			return map.size();
		}
		
		public void add(Object o) {
			map.put(o,PRESENT);
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for(Object key:map.keySet()) {
				sb.append(key+",");
				}
		sb.setCharAt(sb.length()-1, ']');
		return sb.toString();
		}
		public static void main(String[] args) {
			
			Set<Integer> set = new TreeSet<>();
			set.add(300);
			set.add(200);
			set.add(60);
			
			for(Integer m:set) {
				System.out.println(m);
			}
			
			Set<Emp2> set1 = new TreeSet<>();
			set1.add(new Emp2(100,"张三",30000));
			set1.add(new Emp2(200,"李四",300));
			set1.add(new Emp2(50,"王五",8000));
			set1.add(new Emp2(30,"赵六",200));
			set1.add(new Emp2(50,"赵六",200));
		for(Emp2 a:set1) {
			System.out.println(a);
		}
		}

}




class Emp2 implements Comparable<Emp2>{
	int id;
	String name;
	double salary;
	
	public Emp2(int id, String name, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	
	@Override
	public String toString() {
			return "id:"+id+",name:"+name+",salary:"+salary;
 	}
	@Override
	public int compareTo(Emp2 o) {//负数，0，小于；
		
		if(this.salary>o.salary) {
			return 1;
			}else if(this.salary <o.salary){
				return -1;
				}else if(this.id>o.id)
				{
					return 1;}
				else if(this.id<o.id)
				{return -1;}else {
			return 0;
		}
	}
	
}

