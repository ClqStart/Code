package 刷题._2;


/**
 * 给定义整数power,给定一个数组arr,给定一个数组reverse,含义如下：
 * arr的长度一定是2的Power次方
 */

public class code {

    public static int maxSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int N = m.length;
        int M = m[0].length;
        int[] compact = new int [M];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int  cur = 0;
                for (int k = 0; k < M; k++) {
                    compact[k] += m[j][k];

                    cur += compact[k];
                    max = Math.max(max,cur);
                    cur = Math.max(cur,0);
                }

            }
            //清空数据，进行下一行的遍历压缩
            for (int j = 0; j < M; j++) {
                compact[j] = 0;
            }
        }

      return  max;
    }

    public static void main(String[] args) {
        int[][] matrix = {{-90, 48, 78}, {64, -40, 64}, {-81, -7, 66}};
        System.out.println(maxSum(matrix));
    }

}

