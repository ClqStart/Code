package 刷题._2;

import java.util.*;

public class code_1_GetFolderTree {

    public static class Node {
        // 上一个节点是通过哪条路，到我的
        public String path;
        //TreeMap具有字典排序
        // key : node下级的路   value：node在key这条路上对应的节点是什么
        public TreeMap<String, Node> nextMap;

        public Node(String p) {
            this.path = p;
            nextMap = new TreeMap<>();
        }
    }

    // folderPaths ->  [   "a\b\c","a\b\s" , "a\d\e" ,"e\f\sty"     ]
    public static void print(String[] folderPaths) {
        if (folderPaths == null || folderPaths.length == 0) {
            return;
        }
        // 根据所有字符串，把前缀树建立好，头节点为head
        Node head = generateFolderTree(folderPaths);

        // 打印
        printProcess(head, 0);
    }

    public static Node generateFolderTree(String[] folderPaths) {
        Node head = new Node(""); //头节点
        for (String folderPath : folderPaths) {
            Node cur = head;  //每次遍历从头节点开始
            String[] strings = folderPath.split("\\\\");
            for (String string : strings) {
                if(!cur.nextMap.containsKey(string)){
                    cur.nextMap.put(string,new Node(string));
                }
                cur = cur.nextMap.get(string);
            }
        }
         return head;
    }

    // head节点，当前在level层
    public static void printProcess(Node node, int level) {
        if (level != 0) {
            // 2 * (level - 1)
            System.out.println(get4nSpace(level) + node.path);
        }
        for (Node next : node.nextMap.values()) {
            printProcess(next, level + 1);
        }
    }

    public static String get4nSpace(int n) {
        String res = "";
        for (int i = 1; i < n; i++) {
            res += "    ";
        }
        return res;
    }




    public static void main(String[] args) {

        //    "a\b\c" '\'  a,b,c
        String test = "a\\b\\cd";





        //  "a\b\c"    "\"    a,b,c
        String[] arr = test.split("\\\\"); //    \\\\    \\   \
        for(String str : arr) {
            System.out.println(str);
        }
    }
}
