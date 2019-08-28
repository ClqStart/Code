package IOInOut;

/**
 * 1.数据的还原,序列化和反序列
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class Object {
		public static void main(String[] args) throws IOException,ClassNotFoundException {
			
			//写出
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream  ois = new ObjectOutputStream(new BufferedOutputStream(baos));
			
			ois.writeUTF("编码辛酸泪");
			ois.writeInt(18);
			ois.writeBoolean(false);
			ois.writeChar('a');
			
			ois.writeObject("谁解其中类");
			ois.writeObject(new Date());
			Employee emp = new Employee("马云",400);
			ois.writeObject(emp);
			
			ois.flush();
			
			byte[] datas = baos.toByteArray();
			
			//读取
			
			ObjectInputStream dis = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
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
		}
	}

class Employee implements java.io.Serializable{
	private  transient String name;   //transient 该数据不需要序列化
	private double salary;
	
	
	Employee(String name , double salary) {
		this.name = name;
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
}

