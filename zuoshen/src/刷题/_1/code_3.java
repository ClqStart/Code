package 刷题._1;

/**
 * 返回一个括号字符串中，最长的有效子串长度
 */


public class code_3 {
    public static void main(String[] args) {
        System.out.println(maxLength("()()(())()()"));
    }

    /**
     * 思路： 看当前节点的前一个dp跳跃几位的前一位是否为’）‘，是至少dp加2，再看前一位的dp进行相加
     *
     * @param s
     * @return
     */
    public static int maxLength(String s) {
        if (s == null || s.length() < 2) return 0;
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length];
        int pre = 0;
        int res = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ')') {
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && chars[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                    res = Math.max(res, dp[i]);
                }
            }
        }
        return res;
    }
}
