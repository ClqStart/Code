package 刷题._1;

/**
 * 二维数组，其中每个数都是正数，要求只能往左往下走，求取最大路径之和
 */
public class code_13_MinPathSum {

    public static int minPathSum1(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i-1][0]+m[i][0];
        }
        for (int i = 1; i < col ; i++) {
            dp[0][i] = dp[0][i-1]+m[0][i];
        }
        for (int i = 1; i <row ; i++) {
            for (int j = 1; j < col ; j++) {
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + m[i][j];
            }
        }
        return dp[row-1][col-1];

    }
    public static int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int[] dp = new int[m[0].length];
        int N = m.length;
        int M = m[0].length;
        dp[0] = m[0][0];
        for(int col = 1; col <M; col++) {
            dp[col] = dp[col-1] + m[0][col];
        }
        for(int row = 1; row < N; row++) {
            dp[0] = dp[0] + m[row][0];
            for(int col = 1;col <M; col++ ) {
                dp[col] = Math.min(dp[col-1], dp[col]) + m[row][col];
            }
        }
        return dp[M-1];
    }


        public static int[][] generateRandomMatrix ( int rowSize, int colSize){
            if (rowSize < 0 || colSize < 0) {
                return null;
            }
            int[][] result = new int[rowSize][colSize];
            for (int i = 0; i != result.length; i++) {
                for (int j = 0; j != result[0].length; j++) {
                    result[i][j] = (int) (Math.random() * 10);
                }
            }
            return result;
        }

        // for test
        public static void printMatrix ( int[][] matrix){
            for (int i = 0; i != matrix.length; i++) {
                for (int j = 0; j != matrix[0].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }

        public static void main (String[]args){
            // int[][] m = generateRandomMatrix(3, 4);
            int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1},
                    {8, 8, 4, 0}};
            printMatrix(m);
            System.out.println(minPathSum1(m));
            System.out.println(minPathSum2(m));
//        System.out.println(minPathSum3(m));

        }
    }
