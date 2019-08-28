package cn.sxt.test;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;



public class TestDate {
	
	public static void main(String[] args) throws ParseException {
	
		Date d = new Date();
		System.out.println(d);
		
		//时间对象按照”格式字符串“进行转换
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		String str = df.format(new Date());
		System.out.println(str);
		
		//字符串”按照格式进行转换“
		DateFormat df2 = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");
		
		Date date = df2.parse("1983年9月10日 10时45分34秒");
		System.out.println(date);
		
		DateFormat df3 = new SimpleDateFormat("D");
		
		String str3 = df3.format(new Date());
		System.out.println(str3);
		
		
		//获取日期
		Calendar calendar = new GregorianCalendar(2099,10,9,22,10,50);
		int year = calendar.get(YEAR);
		int month= calendar.get(MONTH);
		int day = calendar.get(DATE);
		int weekday = calendar.get(Calendar.DAY_OF_WEEK);
		
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		System.out.println(weekday);
		
		//设置日期
		
		Calendar c2= new GregorianCalendar();
		
		
		
		
	
		
	System.out.println("请输入日期（格式:2020-4-10）");
	Scanner scanner = new Scanner(System.in);
		
	
//	String[] str = dateString.split("-");
//    int year = Integer.parseInt(str[0]);
//    int month = new Integer(str[1]);
//    int day = new Integer(str[2]);
//    Calendar c = new GregorianCalendar(year, month - 1, day);
		
	String str1 =scanner.nextLine();
	
	DateFormat t = new SimpleDateFormat("yyyy-MM-dd"); 
	Date date1 = t.parse(str1);
	
	Calendar c = new GregorianCalendar();
	c.setTime(date1);
	
	
	int day1 = c.get(Calendar.DAY_OF_MONTH);
	int days = c.getActualMaximum(Calendar.DATE);
	
	
	System.out.println("日\t一\t二\t三\t四\t五\t六\t");
	c.set(Calendar.DAY_OF_MONTH,1);
	
	
	for(int i=0;i<c.get(Calendar.DAY_OF_WEEK)-1;i++) {
		System.out.print("\t");
	}
	
	for (int i=1; i<=days;i++) {
		
		if(day1==c.get(Calendar.DAY_OF_MONTH)) {
			System.out.print(c.get(Calendar.DAY_OF_MONTH)+"*\t");
		}else {
		System.out.print(c.get(Calendar.DAY_OF_MONTH)+"\t");
		}
		if(c.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY) {
			System.out.println();
		}
		
		c.add(Calendar.DAY_OF_MONTH,1);
	}
	}

}
