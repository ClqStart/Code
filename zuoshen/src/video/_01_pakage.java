package video;

import java.util.Scanner;

public class _01_pakage {
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
        int[][] dp = new int[N+ 1][ capacity + 1];
        for (int i = 1; i <= N ; i++) {
            for (int j = 1; j <= capacity ; j++) {
                if(j< w[i-1]){
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-w[i-1]]+Value[i-1]);
                }
            }
        }
        for (int i = 0; i <= w.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(dp[N][capacity]);


    }

}
