package video.二叉树;


//中序遍历中的前后继点
//后继节点两种情况：一个有right子节点的节点，node.right.next.next...
//没有right的节点，一路  node.parent.parent   node = node.parnet   node是
public class Code_03_SuccessorNode {
    /**
     *
     * @param node
     * @return
     */
    private static Node getSuccessorNode(Node node) {
        if(node ==null)return node;
        if(node.right !=null ){
            return getLeftMost(node.right);
        }else {
            while (node.parent!=null){
                if(node.parent.left == node)return node.parent;
                node = node.parent;
            }
        }
        return node.parent;

    }
    private static Node getpreNode(Node node){
        if(node ==null)return node;
        if(node.left !=null ){
            return geRightMost(node.left);
        }else {
            while (node.parent!=null){
                if(node.parent.right == node)return node.parent;
                node = node.parent;
            }
        }
        return node.parent;
    }

    private static Node geRightMost(Node node) {
        if(node == null) return node;
        while (node.right !=null){
            node = node.right;
        }
        return  node;
    }

    private static Node getLeftMost(Node node) {
        if(node == null) return node;
        while (node.left !=null){
            node = node.left;
        }
        return  node;
    }


    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }


    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
        System.out.println("============================================");
        test = head.left.left;
        System.out.println(test.value + " next: " + getpreNode(test));
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getpreNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getpreNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getpreNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getpreNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getpreNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getpreNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getpreNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getpreNode(test).value);
        test = head.right.right;
        System.out.println(test.value + " next: " + getpreNode(test).value);
    }


}
