package video;

import java.util.Scanner;

public class _compete_pakage {
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
            for (int j = capacity; j >= 1; j--) {
                for (int k = 0; k <= j / w[i - 1]; k++) {
                    dp[j] = Math.max(dp[j], dp[j - w[i - 1] * k] + Value[i - 1] * k);
                }
            }
        }
        System.out.println(dp[capacity]);
    }

}


