package 刷题._1;


/**
 * 一共有n带零食，第i带的零食体积为v[i]
 * 总体积不超过背包容量的的情况下，一共有多少种方法（总体积为0也为一种方法）
 */
public class code_17_SnacksWays {
    //递归方法 不超过
    private static int ways1(int[] arr, int w) {
        if (arr.length < 1 || w < 1) return 0;
        return process(arr, 0, w);
    }
    private static int process(int[] arr, int index, int res) {
        if (res < 0) {
            return -1;
        }
        if (index == arr.length) {
            return 1;
        }
        int next1 = process(arr, index + 1, res);  //不要 //res不可能为-1
        int next2 = process(arr, index + 1, res - arr[index]); //要
        return  next1 + (next2 == -1? 0:next2);
    }

    /**
     *  dp中 i表示0-》i 选出任意总容量w
     * @param arr
     * @param w
     * @return
     */
    private static int ways2(int[] arr, int w) {
        int N = arr.length;
        int[][] dp = new int[N][w + 1];
        //左列添值
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }
        //上列添值，不填默认为0
        if(arr[0] <=w){
            dp[0][arr[0]]=1;
        }
        for (int i = 1; i <N ; i++) {
            for (int j = 1; j <=w ; j++) {
                dp[i][j] = dp[i-1][j] + ((j-arr[i])>=0? dp[i-1][j-arr[i]]:0);
            }
        }
        //最后一列表示在0-N-1上选择，体积为1到w的选择
        int res = 0;
        for (int i = 0; i<=w; i++) {
            res += dp[N-1][i];
        }
        return res;
    }

    /**
     *
     * @param arr
     * @param w
     * @return
     */
    private static int ways3(int[] arr, int w) {
        int N = arr.length;
        int[][] dp = new int[N + 1][w + 1];
        for (int j = 0; j <= w; j++) {
            dp[N][j] = 1;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= w; j++) {
                dp[i][j] = dp[i + 1][j] + ((j - arr[i] >= 0) ? dp[i + 1][j - arr[i]] : 0);
            }
        }
        return dp[0][w];
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 9};
        int w = 8;
        System.out.println(ways1(arr, w));
        System.out.println(ways2(arr, w));
        System.out.println(ways3(arr, w));
    }
}
