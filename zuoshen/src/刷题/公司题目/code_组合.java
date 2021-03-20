package 刷题.公司题目;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 给定义整数power,给定一个数组arr,给定一个数组reverse,含义如下：
 * arr的长度一定是2的Power次方
 */

public class code_组合 {
    public static void main(String[] args) {
        String str = "abc";
        ArrayList<String> list = Permutation(str);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static ArrayList<String> Permutation(String str) {
        ArrayList res = new ArrayList<String>();
        if (str.length() == 1) {
            res.add(str);
            return res;
        }
        Set<String> set = new LinkedHashSet<>();
        char[] ch = str.toCharArray();
        int post = 0;
        perm(post, ch, set);
        for (String c : set) {
            res.add(c);
        }
        Collections.sort(res);
        return res;

    }

    public static void perm(int post, char[] s, Set<String> set) {
        if (post + 1 == s.length) {
            set.add(new String(s));
            return;
        }

        for (int i = 0; i < s.length; i++) {
            swap(post, i, s);
            perm(post + 1, s, set);
            swap(post, i, s);
        }
    }

    public static void swap(int i1, int i2, char[] s) {
        char tmp = s[i1];
        s[i1] = s[i2];
        s[i2] = tmp;
    }

}
