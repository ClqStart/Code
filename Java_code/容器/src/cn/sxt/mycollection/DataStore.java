package cn.sxt.mycollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DataStore {
	public static void main(String[] args) {
		
	 JavaBean();	
	 Map_List();	
	 
	
	
	
	}
	
	
	public static void JavaBean() {
		
		User user1 = new User(1001,"张三",20000,"2018.5.5");
		User user2 = new User(1002,"李四",2000,"1996.3.5");
		User user3 = new User(1003,"王五",5000,"2020.1.20");
		User user4 = new User(1004,"赵六",8900,"2000.3.28");
		
		List<User> mylist = new ArrayList<User>();
		
		mylist.add(user1);
		mylist.add(user2);
		mylist.add(user3);
		mylist.add(user3);
		
		for(User li:mylist) {
			System.out.println(li);
		}
	}
	
	
	
	public static void Map_List() {
		
		

		Map<String,Object> row1 = new HashMap<>();
		row1.put("id",1001);
		row1.put("姓名","张三");
		row1.put("薪资",20000);
		row1.put("日期","2018.5.5");
		
		Map<String,Object> row2 = new HashMap<>();
		row2.put("id",1002);
		row2.put("姓名","李四");
		row2.put("薪资",2000);
		row2.put("日期","1996.3.5");
		
		Map<String,Object> row3 = new HashMap<>();
		row3.put("id",1003);
		row3.put("姓名","王五");
		row3.put("薪资",5000);
		row3.put("日期","2020.1.20");
		
		Map<String,Object> row4 = new HashMap<>();
		row4.put("id",1004);
		row4.put("姓名","赵六");
		row4.put("薪资",8900);
		row4.put("日期","2000.3.28");
		
	List<Map<String,Object>> table = new ArrayList<Map<String,Object>>();
		table.add(row1);
		table.add(row2);
		table.add(row3);
		table.add(row4);
//	
//	for(Map<String,Object> m : table) {
//		Set<String> keyset =m.keySet();
//		System.out.println(keyset);
//		for(String key:keyset) {
//			System.out.print(key+":"+m.get(key));
//		}
//		System.out.println();
//	}
		
		for (Map<String,Object> m : table) {
			Set<Entry<String,Object>> map2 = m.entrySet();
			for(Iterator<Entry<String ,Object>> ss = map2.iterator();ss.hasNext();) {
				Entry<String ,Object> temp = ss.next();
				System.out.print(temp.getKey()+":"+temp.getValue());	
			}
			System.out.println();
			
		}
	
	}
}


 class User{
	 private  int     id;
	 private  String name;
	 private  int        salary;
	 private   String   date;
	 
	 
	 public User() {}
	 
	public User(int id, String name, int salary, String date) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "id:"+getId() +"\tname:"+ getName()+"\t\tSalary:" +getSalary()+"\tdate:"+getDate();
	}
	 
	 
	 
}