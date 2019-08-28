package cn.sxt.mycollection;

import java.util.Map;
import java.util.TreeMap;

public class TestTreeMap {
	public static void main(String[] args) {
		Map<Integer,String> treemap1=new TreeMap<>();
		
		treemap1.put(20,"aa");
		treemap1.put(2,"bb");
		treemap1.put(10,"cc");
		
		for(Integer key:treemap1.keySet()) {
			System.out.println(key+"----"+treemap1.get(key));
		}
		
		
Map<Emp,String> treemap2=new TreeMap<>();
		
		treemap2.put(new Emp(100,"张三",50000),"你是张山");
		treemap2.put(new Emp(110,"李四",5000),"我是李四");
		treemap2.put(new Emp(200,"王五",600),"我是王五");
		treemap2.put(new Emp(300,"赵六",600),"我是赵六");
		
		for(Emp key:treemap2.keySet()) {
			System.out.println(key+"----"+treemap2.get(key));
		}
		
	}

}

class Emp implements Comparable<Emp>{
	int id;
	String name;
	double salary;
	
	public Emp(int id, String name, double salary) {
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
	public int compareTo(Emp o) {//负数，0，小于；
		
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
