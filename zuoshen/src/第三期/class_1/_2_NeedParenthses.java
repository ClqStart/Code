package 第三期.class_1;

/**
 * 括号有效配对是指：
 * 1）任何一个左括号都能找到和其正确配对的右括号
 * 2）任何一个右括号都能找到和其正确配对的左括号
 * 有效的：    (())  ()()   (()())  等
 * 无效的：     (()   )(     等
 * 问题一：怎么判断一个括号字符串有效？
 * 问题二：如果一个括号字符串无效，返回至少填几个字符能让其整体有效
 */
public class _2_NeedParenthses {

    public static boolean isRight(String s) {
        if (s == null || s.length() < 2) return false;
        char[] array = s.toCharArray();
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            count += array[i] == '(' ? 1 : -1;
            if (count < 0) return false;
        }
        return count == 0;
    }
    public static int isRight2(String s) {
        if (s == null || s.length() < 2) return 0;
        char[] array = s.toCharArray();
        int count = 0;
        int add=0;
        for (int i = 0; i < array.length; i++) {
             if(array[i]=='('){
                 count++;
             }else {//遇到‘)'让其不是-1，变为0;
                 if(count ==0){
                     add++;
                 }else {
                     count--;
                 }

             }
        }
        return  add+count;
    }
}
