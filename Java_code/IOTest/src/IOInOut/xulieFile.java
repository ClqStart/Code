package IOInOut;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class xulieFile {
	public static void main(String[] args) throws IOException,ClassNotFoundException {
		
		//写出
		ObjectOutputStream  ois = new ObjectOutputStream(new FileOutputStream("if.txt"));
		
		ois.writeUTF("编码辛酸泪");
		ois.writeInt(18);
		ois.writeBoolean(false);
		ois.writeChar('a');
		
		ois.writeObject("谁解其中类");
		ois.writeObject(new Date());
		Employee emp = new Employee("马云",400);
		ois.writeObject(emp);
		
		ois.flush();
		ois.close();
		
		
		//读取
		
		ObjectInputStream dis = new ObjectInputStream(new BufferedInputStream(new FileInputStream("if.txt")));
		String msg = dis.readUTF();
		int age  = dis.readInt();
		boolean flag = dis.readBoolean();
		char ch = dis.readChar();
		
		java.lang.Object str = dis.readObject();
		java.lang.Object date = dis.readObject();
		java.lang.Object employee = dis.readObject();
		
		System.out.println(flag);
		
		
		if(str instanceof String) {
			String strObj =(String) str;
			System.out.println(strObj);
		}
		
		if(date instanceof Date) {
			Date dateObj =(Date) date;
			System.out.println(dateObj);
		}
		
		if(employee instanceof Employee) {
			Employee empObj =(Employee) employee;
			System.out.println(empObj.getName()+"--->"+empObj.getSalary());
		}
		dis.close();
	}

}
