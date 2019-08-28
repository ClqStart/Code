package cn.sxt.mycollection;

public class SxtHahMap01<K,V> {
	
	Node2[] table;
	int size;
	
	
	public SxtHahMap01() {
		table = new Node2[16];
		}
	
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder("{");
		
		for(int i =0;i<table.length;i++) {
			Node2 temp = table[i];
			while(temp!=null) {
				sb.append(temp.key+":"+temp.value+",");
				temp = temp.next;
			}
		}	
		
		sb.setCharAt(sb.length()-1, '}');
		return sb.toString();
	}
	
	
	public V get(K key) {
		int hash = myHush(key.hashCode(),table.length);
		V value =null;
		if (table[hash] !=null) {
			Node2 temp = table[hash];
		while(temp!= null) {
			if(temp.key.equals(key)) {
				value =(V)temp.value;
				break;
			}else {
				temp = temp.next;
			}
			
		}
		}
		
		return value;
	}
	
	public void put(K key,V value) {
		
		Node2 newNode = new Node2();
		newNode.hash = myHush(key.hashCode(), table.length);
		newNode.value = value;
		newNode.key = key;
		newNode.next = null;
		
		Node2 temp = table[newNode.hash];
		Node2 iterLast = null;
		boolean keyRepeat = false;
		
		if(temp ==null) {
			table[newNode.hash] = newNode;
			size++;
		}else { 
			while(temp!= null) {
				//重复进行覆盖
				if(temp.key.equals(key)){
					keyRepeat = true;
					temp.value = value;
					break;
				}
				           //不重复需要遍历
				else {
					iterLast =temp;
					temp = temp.next;
					}
				}
		
			if(!keyRepeat) {
			
			iterLast.next = newNode;
			size++;
				}
		}
	}

	
	public static void main(String[] args) {
		SxtHahMap01<Integer,String>  m = new SxtHahMap01<>();
		m.put(10, "aa");
		m.put(20, "bb");
		m.put(30, "cc");
		m.put(20, "dd");
		m.put(53, "dc");
		m.put(69, "kk");
		m.put(85, "kl");
	
		
		System.out.println(m);
		System.out.println(m.get(85));
		
	}
	
	public static int myHush(int v, int length) {
		
		System.out.println("hash in myHash:"+(v&(length-1)));
		System.out.println("hash in myHash:"+(v%(length-1)));
		return v%(length-1);
	}

}
