package cn.sxt.mycollection;

import java.util.HashMap;
import java.util.Map;

public class TestMap2 {
	public static void main(String[] args) {
		Employee e1 = new Employee(1001, "高企", 500000);
		Employee e2 = new Employee(1002, "小二", 3000);
		Employee e3 = new Employee(1003, "小四", 90000);
		
		Map<Integer,Employee> map = new HashMap<>();
		
		map.put(1, e1);
		map.put(2, e2);
		map.put(3, e3);
		
		System.out.println(map.get(1).getEname());
		
	}
}


class Employee{
	private int id;
	private String ename;
	private double  salary;
	
	
	public Employee(int id, String ename, double salary) {
		super();
		this.id = id;
		this.ename = ename;
		this.salary = salary;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEname() {
		return ename;
	}


	public void setEname(String ename) {
		this.ename = ename;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
	
}