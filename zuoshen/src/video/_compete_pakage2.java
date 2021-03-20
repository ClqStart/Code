package video;

import java.util.Scanner;

//逆向推 0-1背包
//顺向推 完全背包
public class _compete_pakage2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int capacity = scanner.nextInt();
        int N = scanner.nextInt();
        int[] Value = new int[N];
        int[] w = new int[N];
        for (int i = 0; i < N; i++) {
            w[i] = scanner.nextInt();
            Value[i] = scanner.nextInt();
        }
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <=capacity; j++) {
                if (j >= w[i - 1]) {
                    dp[j] = Math.max(dp[j], dp[j - w[i - 1]] + Value[i - 1]);
                }
            }
        }
        System.out.println(dp[capacity]);
    }

}


