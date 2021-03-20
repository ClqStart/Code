package video;


public class fourEdge_print {

    public static void spiraOrderPrint(int[][] matrix) {
        int lr = 0;
        int lc = 0;
        int rr = matrix.length;
        int rc = matrix[0].length;
        while (lr <= rr && lc <= rc) {
            printEdge(matrix, lr++, lc++, rr--, rc--);
        }
    }

    /**
     * @param M               坐标矩阵
     * @param LeftUpRank：     左上角的行
     * @param LeftUpColumn    左上角的列
     * @param RightdownRank   右下角的行
     * @param RightdownColumn 右小角的列
     */
    public static void printEdge(int[][] M, int LeftUpRank, int LeftUpColumn, int RightdownRank, int RightdownColumn) {
        if (LeftUpRank == RightdownRank) {
            for (int i = LeftUpColumn; i <= RightdownColumn; i++) {
                System.out.println(M[LeftUpRank][i] + " ");
            }
        } else if (LeftUpColumn == RightdownColumn) {
            for (int i = LeftUpRank; i <= RightdownRank; i++) {
                System.out.println(M[i][LeftUpColumn] + " ");
            }
        } else {
            int curColumn = LeftUpColumn;
            int curRank = LeftUpRank;
            while (curColumn != RightdownColumn) {
                System.out.println(M[curRank][curColumn]);
                curColumn++;
            }
            while (curRank != RightdownRank) {
                System.out.println(M[curRank][curColumn]);
                curRank++;
            }
            while (curColumn != LeftUpColumn) {
                System.out.println(M[curRank][curColumn]);
                curColumn--;
            }
            while (curRank != LeftUpRank) {
                System.out.println(M[curRank][curColumn]);
                curRank--;
            }

        }
    }

    public static void rotateEdge(int[][] M, int LeftUpRank, int LeftUpColumn, int RightdownRank, int RightdownColumn) {
        int time = RightdownColumn - LeftUpColumn;
        int tmp = 0;
        for (int i = 0; i < time; i++) {
            tmp = M[LeftUpRank][LeftUpColumn + i];
            M[LeftUpRank][LeftUpColumn+i] = M[RightdownRank-i][LeftUpColumn];
            M[RightdownRank-i][LeftUpColumn]=M[RightdownRank][RightdownColumn -i];
            M[RightdownRank][RightdownColumn -i]= M[LeftUpRank + i][RightdownColumn];
            M[LeftUpRank + i][LeftUpColumn] = tmp;
        }

    }
}
