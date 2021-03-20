package 刷题._1;

public class code_18_LCSubsequence {

    public static int lcs(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        return process(str1, str2, N - 1, M - 1);
    }

    private static int process(char[] str1, char[] str2, int str1Len, int str2Len) {
        if (str1Len == 0 && str2Len == 0) return str1[str1Len] == str2[str2Len] ? 1 : 0;

        if (str1Len == 0)
            return (str1[str1Len] == str2[str2Len] || process(str1, str2, str1Len, str2Len - 1) == 1 ? 1 : 0);
        if (str2Len == 0)
            return (str1[str1Len] == str2[str2Len] || process(str1, str2, str1Len - 1, str2Len) == 1 ? 1 : 0);

        int p1 = process(str1, str2, str1Len - 1, str2Len - 1);
        int p2 = process(str1, str2, str1Len, str2Len - 1);
        int p3 = process(str1, str2, str1Len - 1, str2Len);
        int p4 = 0;
        if (str1[str1Len] == str2[str2Len]) {
            p4 = p1 + 1;
        }
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    public static int dp(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) return 0;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = s1.length();
        int M = s2.length();
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < N; i++) {
            dp[0][i] = str1[0] == str2[i] ? 1 : dp[0][i - 1];
            for (int j = 1; j < M; j++) {
                dp[j][0]= str1[j] == str2[0] ? 1 : dp[j-1][0];
                if (str1[i] == str2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

                }

            }
        }

        return dp[N - 1][M - 1];
    }

    public static String lcse(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) return null;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = s1.length() - 1;
        int M = s2.length() - 1;
        int[][] dp = new int[s1.length()][s2.length()];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i <= N; i++) {
            dp[0][i] = str1[0] == str2[i] ? 1 : dp[0][i - 1];
            for (int j = 1; j <= M; j++) {
                dp[j][0]= str1[j] == str2[0] ? 1 : dp[j-1][0];
                if (i > 0 && j > 0) {
                    if (str1[i] == str2[j]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
        }
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        char[] res = new char[dp[N][M]];
        int index = res.length - 1;
        while (index >= 0) {
            if (M > 0 && dp[N][M] == dp[N][M-1]) {
                M--;
            } else if (N > 0 && dp[N][M] == dp[N-1][M]) {
                N--;
            } else {
                res[index--] = str2[M];
                N--;
                M--;
            }
        }

        return String.valueOf(res);
    }

    public static String lcse1(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = getdp(chs1, chs2);
        int m = chs1.length - 1;
        int n = chs2.length - 1;
        char[] res = new char[dp[m][n]];
        int index = res.length - 1;
        while (index >= 0) {
            if (n > 0 && dp[m][n] == dp[m][n - 1]) {
                n--;
            } else if (m > 0 && dp[m][n] == dp[m - 1][n]) {
                m--;
            } else {
                res[index--] = chs1[m];
                m--;
                n--;
            }
        }
        return String.valueOf(res);
    }

    public static int[][] getdp(char[] str1, char[] str2) {
        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < str1.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }
        for (int j = 1; j < str2.length; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], str1[0] == str2[j] ? 1 : 0);
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp;
    }


    public static void main(String[] args) {
        String str1 = "A1BC23Z4";
        String str2 = "12O3YU4P";
        System.out.println(lcs(str1, str2));

        System.out.println(lcse(str1, str2));
        System.out.println(lcse1(str1, str2));
        System.out.println(dp(str1, str2));

    }


}
