

public class _5_最长回文字串 {
    public String longestPalindrome(String s) {
        if (s == null) return "";
        char[] chars = s.toCharArray();
        if (chars.length == 0) return "";
        int start = 0;
        int max = 1;

        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (j - i + 1 > max && validateString(i, j, chars)) {
                    start = i;
                    max = j - i + 1;
                }
            }

        }
        return s.substring(start, start + max);

    }

    private boolean validateString(int start, int end, char[] chars) {
        while (start < end) {
            if (chars[start] != chars[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    //马拉车算法
    public String longestPalindrome1(String s) {
        if (s == null || s.length() < 2) return s;
        char[] chars = s.toCharArray();
        int ll = 0;
        int rr = 0;
        int max = 1;
        for (int i = 1; i < chars.length; i++) {
            int l = i - 1;
            int r = i + 1;
            while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
                if (r - l + 1 > max) {
                    ll = l;
                    rr = r;
                    max = Math.max(max, rr - ll + 1);
                }
                r++;
                l--;
            }
        }
        for (int i = 0; i < chars.length; i++) {
            int l = i;
            int r = i + 1;
            while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
                if (r - l + 1 > max) {
                    ll = l;
                    rr = r;
                    max = Math.max(max, rr - ll + 1);
                }
                r++;
                l--;
            }
        }
        return s.substring(ll, ll + max);

    }

    // 动态规划
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 2) return s;
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[chars.length][chars.length];
        int l = 0;
        int max = 1;
        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < chars.length; j++) {
            for (int i = 0;i < chars.length-1 && i < j; i++) {
                if (chars[i] == chars[j]) {
                    if (j - i <= 2) {
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                }else {
                    dp[i][j] = false;
                }
                if(dp[i][j] && j-i+1>max){
                    l = i;
                    max = j-i+1;
                }
            }
        }

        return s.substring(l,l+max);
    }


    public static void main(String[] args) {
        String s = new _5_最长回文字串().longestPalindrome2("cbba");
        System.out.println(s);
    }

}
