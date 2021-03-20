package Code_200;

/*

请实现一个函数，把字符串 s 中的每个空格替换成"%20"。

示例 1：

输入：s = "We are happy."
输出："We%20are%20happy."
 
限制：
0 <= s 的长度 <= 10000
 */

public class _剑指Offer_05_替换空格 {

    class Solution {
        public String replaceSpace(String s) {
            char[] array = s.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                if (array[i] == ' ') {
                    sb.append("%20");
                }else {
                    sb.append(array[i]);
                }
            }
            return sb.toString();

        }
    }
}
