package back;


import java.util.*;

public class _字符串反转 {

    public  static String reverse(String str){

        String[] strs = str.split("\\s+");
        List<String> stringList = Arrays.asList(strs);
        Collections.reverse(stringList);
        str = String.join(" ", stringList);
        return str;

    }
    //双指针
    public  static String reverse1 (String str){
       if(str == null || str.length()<0) return null;
       int Right = str.length()-1;
       int left = Right;
        StringBuilder sb = new StringBuilder();
        while (left >=0){
           while (left >=0 && (str.charAt(left) != ' ')) left--;
           //substring 包头不包尾巴
           sb.append(str.substring(left+1,Right+1));
           while (left >=0 && (str.charAt(left) == ' ')) left--;
           sb.append(' ');
           Right = left;
       }
        return sb.toString();
    }

    public  static String reverse2 (String str){
        if(str == null || str.length()<0) return null;
        char[] array = str.toCharArray();
        int Right = array.length-1;
        int left = Right;
        StringBuilder sb = new StringBuilder();
        while (left >=0){
            while (left >=0 && (array[left] != ' ')) left--;
            //substring 包头不包尾巴
            sb.append(str.substring(left+1,Right+1));
            while (left >=0 && (array[left] == ' ')) left--;
            sb.append(' ');
            Right = left;
        }
        return sb.toString();
    }

    //双端队列
    public  static String reverse3 (String str){
        if(str == null || str.length()<0) return null;
        char[] array = str.toCharArray();
        int Right = array.length-1;
        int left = 0;
        ArrayDeque<String> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        while (left <= Right){
           if(sb.length() !=0 && array[left] == ' '){
               deque.offerFirst(sb.toString());
               sb.setLength(0);
           }else if (array[left] != ' '){
               sb.append(array[left]);
           }
           left++;
        }
        deque.offerFirst(sb.toString());

        return  String.join(" ",deque);
    }


    public static void main(String[] args) {
        String str="the sky is blue";
        System.out.println(reverse(str));
        System.out.println(reverse1(str));
        System.out.println(reverse2(str));
        System.out.println(reverse3(str));




    }
}
