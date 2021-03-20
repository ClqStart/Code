package 刷题._2;

/**
 * 给当一个整形矩阵，返回矩阵的最大累加和
 */
public class code_7_SubMatrixMaxSum {

    public static int maxSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int N = m.length;
        int M = m[0].length;
        int[] compat = new int[M];
        int max = Integer.MIN_VALUE;
        for (int i= 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int cur=0;
                //遍历数组按列压缩成一行；
                for (int k = 0; k < N; k++) {
                    compat[k] += m[j][k];

                    //求取最大的连续字串  , 进行了合并
                    cur += compat[k];
                    max  = Math.max(max,cur);
                    cur = Math.max(cur,0);
                }
            }
            //清空数据，进行下一行的遍历压缩
            for (int j = 0; j < M; j++) {
                compat[j] = 0;
            }
        }


        return max;
    }
    public static int maxSum1(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int[] s = null;
        for (int i = 0; i != m.length; i++) { // 开始的行号i
            s = new int[m[0].length]; //
            for (int j = i; j != m.length; j++) { // 结束的行号j，i~j行是我讨论的范围
                cur = 0;
                for (int k = 0; k != s.length; k++) {
                    s[k] += m[j][k];
                    cur += s[k];
                    max = Math.max(max, cur);
                    cur = cur < 0 ? 0 : cur;
                }
            }
        }
        return max;
    }


    public static void main(String[] args) {
        int[][] matrix = {{-90, 48, 78}, {64, -40, 64}, {-81, -7, 66}};
        System.out.println(maxSum(matrix));
        System.out.println(maxSum1(matrix));

    }

}
