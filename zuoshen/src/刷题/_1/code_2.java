package 刷题._1;

import java.util.HashMap;
import java.util.Stack;

/**
 * 括号配对问题
 */

public class code_2 {

    public static boolean isRight(String s) {
        if (s == null || s.length() < 2) return false;
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(' || chars[i] == '[' || chars[i] == '{') {
                stack.push(chars[i]);
            } else {
                if (stack.isEmpty()) return false;
                char left = stack.pop();
                if (chars[i] == ')' && left != '(') return false;
                if (chars[i] == ']' && left != '[') return false;
                if (chars[i] == '}' && left != '}') return false;
            }

        }
        return stack.isEmpty();
    }

    public static boolean isRight1(String s) {
        if (s == null || s.length() < 2) return false;
        char[] chars = s.toCharArray();
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                stack.push(chars[i]);
            } else {
                if (stack.isEmpty()) return false;
                if (map.get(stack.pop()) != chars[i]) return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 判断只有小括号的是否有效
     *
     * @param s
     * @return
     */
    public static boolean isRight2(String s) {
        if (s == null || s.length() < 2) return false;
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            count += chars[i] == '(' ? 1 : -1;
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    public static int isRightandComplete(String s) {
        if (s == null || s.length() < 1) return 0;
        char[] chars = s.toCharArray();
        int count = 0;
        int newadd = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                count++;
            } else {
                if (count == 0) {
                    newadd++;
                } else {
                    count--;
                }
            }
        }
        return count + newadd;
    }
}