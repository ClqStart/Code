package cn.sxt.mycollection;

public class Node {
	Node previous;
	Node next;
	Object element;
	
	public Node(Node preNode,Node next,Object element) {
		super();
		this.previous =preNode;
		this.element=element;
		this.next = next;
		}

	public Node(Object element) {
		super();
		this.element = element;
	}
	

}
