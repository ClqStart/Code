package cn.sxt.mycollection;

import javax.management.RuntimeErrorException;

public class SxtArrayList<E> {
	
	 private Object[] elementData;
	 private int size; //默认0
	 private  static final int DEFALT_CAPACTION=10;
	 
	 public SxtArrayList() {
		 elementData = new Object[DEFALT_CAPACTION];
		 
	 }
	 
	 public SxtArrayList(int Capacity) {
		 if(Capacity<0) {
			 throw new RuntimeException("容量不能为负数");
		 }else if(Capacity ==0){
			 elementData = new Object[DEFALT_CAPACTION];
		 }else {
		 elementData = new Object[Capacity];
		 }
		 
	 }
	 public void add(E obj) {
		 //扩容
		 if(size ==elementData.length) {
			 Object[] newArray = new Object[elementData.length+(elementData.length>>1)]; //注意优先级
			 
			 System.arraycopy(elementData, 0, newArray,0, elementData.length);
			 
			 elementData = newArray;
			 
		 }
		 
		 elementData[size++] = obj;
	 }
	 public E get(int index) {
		checkRange(index);
		 return (E)elementData[index];
	 }
	 
	 public void checkRange(int index) {
		 if(index<0||index>size-1) {
			 throw new RuntimeException("索引不合法"+index);
		 }
	 }
	 
	 public void set(E element, int index) {
		 checkRange(index);
		 elementData[index] = element;
		 
	 }
	 public int size() {
		 return size;
	 }
	 
	 public boolean isEmpty() {
		 return size==0?true:false;
	 }
	 @Override
	public String toString() {
		
		 StringBuilder sb = new StringBuilder();
		 
		 sb.append("[");
		 for(int i=0;i<size;i++) {
			 sb.append(elementData[i]+",");
			 }
		 
		 sb.setCharAt(sb.length()-1, ']');
		 return sb.toString();
	}
	 
	 public void remove(E element) {
		 for(int i=0;i<size;i++) {
			 if(element.equals(get(i))) {
				 remove(i);
			 }
		 }
	 }
	public void remove (int index) {
		int unmMoved =elementData.length-index-1;
		if(unmMoved>0) {	
			System.arraycopy(elementData, index+1, elementData, index,unmMoved);
			
		}
		elementData[--size] = null;
		}
	 
	 public static void main(String[] args) {
		 SxtArrayList s1 = new SxtArrayList(20);
		 s1.add("aa");
		 s1.add("bb");
		 System.out.println(s1);
		 
		 for(int i=0;i<40;i++) {
			 s1.add("高"+i);
		 }
		 s1.set("ddd", 10);
		 System.out.println(s1);
		 System.out.println(s1.get(10));
		 s1.remove(3);
		 System.out.println(s1);
		 System.out.println(s1.size);
		 System.out.println(s1.isEmpty());
	 }
}
