package cn.sxt.mycollection;

public class SxtLinkedList01<E>{
		private Node first;
		private Node last;
		
		private int size;
		
	
	public void add(int index, E element) { //alt+shift+r
		checkrange(index);
			
		Node newNode = new Node(element);
		
		Node temp = getNode(index);
		
		if(temp != null) {
			Node up = temp.previous;
			
			up.next = newNode;
			newNode.previous = up;
			
			newNode.next = temp;
			temp.previous = newNode;
		}
	}
		
		
	public  void remove(int index) {
		checkrange(index);

		Node temp =getNode(index);
		if(temp !=null) {
			Node up= temp.previous;
			Node down = temp.next;
			
			if(up !=null) {
			up.next = down;
			}
			if(down!=null){
			down.previous = up;
			}
			//被删除的是第一个
			if(index==0) {
				first = down;
			}
			//被删除的是最后一个
			if(index == size-1) {
				last = up;
			}
			size--;
		}
	}
	
	public E get( int index ) {
		
		checkrange(index);
		Node temp =getNode(index);
		
		return temp!=null? (E)temp.element:null;
	}
	
	private void checkrange(int index) {
		if(index<0||index>size-1) {
			throw new RuntimeException("索引数字不合格"+index);
		}
	}
	
	private Node getNode(int index) {
		
		checkrange(index);
		Node temp = null;
		
		if(index<=(size>>1)) {
			temp =first;
			for(int i=0;i<index;i++) {
				temp = temp.next;
			}
		}else {
			temp =last;
			for(int i=size-1;i<index;i--) {
				temp = temp.previous;
				}
			}
		return temp;
	}
		
	   @Override
	public String toString() {
		 StringBuilder sb = new StringBuilder("[");
		 Node tmp = first;
		 while(tmp != null){
			sb.append(tmp.element+",");
			 tmp = tmp.next;
		 }
		 sb.setCharAt(sb.length()-1, ']');
		   
		 return sb.toString();
	}		
		
	public void add(E element) {
		Node node = new Node(element);
		
		if(first==null) {
			first =node;
			last = node;
		}else {
			node.previous =last;
			node.next=null;
			
			last.next=node;
			last =node;
		}
		size++;
		}
		
		public static void main(String[] args) {
			SxtLinkedList01<String> list = new SxtLinkedList01<>();
			list.add("a");
			list.add("c");
			list.add("d");
			list.add("e");
			list.add("f");
			list.add("g");
			
			
			System.out.println(list);
			System.out.println(list.get(1));
			
			list.remove(5);
			System.out.println(list);
			list.add(2,"高");
			System.out.println(list);
			
		}
			
		
	}
	
		
