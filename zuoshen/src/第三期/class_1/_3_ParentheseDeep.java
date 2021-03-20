package 第三期.class_1;


/*
括号有效配对是指：
1）任何一个左括号都能找到和其正确配对的右括号
2）任何一个右括号都能找到和其正确配对的左括号
返回一个括号字符串中，最长的括号有效子串的长度
*/

//动态规划
public class _3_ParentheseDeep {

    public static void main(String[] args) {
        System.out.println(maxLength("()()(())()()"));
    }

    /**
     * 思路： 看当前节点的前一个dp跳跃几位的前一位是否为’）‘，是至少dp加2，再看前一位的dp进行相加
     * 就是看dp[i-1]的有效括号前面一个的情况
     *
     * @param s
     * @return
     */
    public static int maxLength(String s) {
        if (s == null || s.length() < 2) return 0;
        char[] arr = s.toCharArray();
        int N = arr.length;
        int[] dp = new int[N];
        int res = 0;
        for (int i = 1; i < N; i++) {
            if (arr[i] == ')') {
                int pre = i - dp[i - 1] - 1;
                if (pre >= 0 && arr[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    /**
     * 有效括号最大深度
     *
     * @param s
     * @return
     */
    public static int deep(String s) {
        if (s == null || s.length() < 1) return 0;
        char[] chars = s.toCharArray();
        int count = 0;
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            count += chars[i] == '(' ? 1 : -1;
            res = Math.max(res, count);
        }
        return res;
    }
}
