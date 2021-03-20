import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int calcSimilarity(String name1, String name2) {
        int num1 = 0;
        int num2 = 0;
        char[] array1 = name1.toCharArray();
        char[] array2 = name2.toCharArray();
        for (int i = 0; i < array1.length; i++) {
            num1 += (int)array1[i];
        }
        for (int i = 0; i < array2.length; i++) {
            num2 += (int)array2[i];
        }
        



        return 0;
    }
    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String name1 = in.nextLine();
            String name2 = in.nextLine();

            int sum = calcSimilarity(name1, name2);
            System.out.println(sum);
        }
    }
}

