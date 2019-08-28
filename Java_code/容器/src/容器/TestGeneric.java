package 容器;

public class TestGeneric {
	public static void main(String[] args) {
		
		MyCollection<String> mc = new MyCollection<String>();
		mc.set("高企",0);
//		mc.set(222, 1);
		
		
//		Integer a = (Integer)mc.get(1);
		String b = mc.get(0);
		
//		System.out.println(a);
		System.out.println(b);
	}
	
}
	
	class MyCollection<E>{
		Object[] objs= new Object[5];
		
		public void set(E e,int index) {
			objs[index] = e;
		}
		public E get(int index) {
			return (E) objs[index];
		}
	}

