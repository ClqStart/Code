package video;

//之字打印矩阵
public class ZigZagPrintMatrix {

    public static void printMatrixZigZag(int[][] matrix) {
        int ar = 0;
        int ac = 0;
        int br = 0;
        int bc = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean flag = false;
        while (ar != endR + 1) {
            //注意值的变化，接下来用的改变的值
            printLevel(matrix, ar, ac, br, bc, flag);
            ar = ac == endC ? ++ar : ar;
            ac = ac == endC ? ac : ++ac;
            bc = br == endR ? ++bc : bc;
            br = br == endR ? br : ++br;
            flag = !flag;
        }
        System.out.println();

    }

    private static void printLevel(int[][] matrix, int ar, int ac, int br, int bc, boolean flag) {
        if (flag) {
            while (ar != br + 1) {
                System.out.print(matrix[ar++][ac--] + " ");
            }

        } else {
            while (br != ar - 1) {
                System.out.print(matrix[br--][bc++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        printMatrixZigZag(matrix);

    }
}
