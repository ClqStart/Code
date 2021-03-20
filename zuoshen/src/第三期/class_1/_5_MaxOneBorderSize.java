package 第三期.class_1;


/**
 * 给定一个N*N的矩阵matrix，只有0和1两种值，返回边框全是1的最大正方形的边长长度。
 * 例如:
 * 01111
 * 01001
 * 01001
 * 01111
 * 01011
 * 其中边框全是1的最大正方形的大小为4*4，所以返回4。
 */
public class _5_MaxOneBorderSize {


    public static void setBorderMap(int[][] matrix, int[][] right, int[][] down) {
        int r = matrix.length;
        int c = matrix.length;
        if (matrix[r - 1][c - 1] == 1) {
            right[r - 1][c - 1] = 1;
            down[r - 1][c - 1] = 1;
        }
        for (int i = r - 2; i >= 0; i--) {
            if (matrix[i][c - 1] == 1) {
                right[i][c - 1] = 1;
                down[i][c - 1] = down[i + 1][c - 1] + 1;
            }
        }
        for (int i = c - 2; i >= 0; i--) {
            if (matrix[r - 1][i] == 1) {
                right[r - 1][i] = right[r - 1][i + 1] + 1;
                down[r - 1][i] = 1;
            }
        }
        for (int i = r - 2; i >= 0; i--) {
            for (int j = c - 2; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
            }

        }

    }


    private static int getMaxSize(int[][] matrix) {
        int[][] right = new int[matrix.length][matrix[0].length];
        int[][] down = new int[matrix.length][matrix[0].length];
        setBorderMap(matrix, right, down);
        for (int size = Math.min(matrix[0].length, matrix.length); size > 0; size--) {
            for (int i = 0; i < matrix.length - size + 1; i++) {
                for (int j = 0; j < matrix[0].length - size + 1; j++) {
                    if (right[i][j] >= size && down[i][j]>=size
                    && right[i+size-1][j]>=size &&down[i][j+size-1]>=size){
                        return size;
                    }

                }
            }

        }
       return 0;
    }


    public static int[][] generateRandom01Matrix(int rowSize, int colSize) {
        int[][] res = new int[rowSize][colSize];
        for (int i = 0; i != rowSize; i++) {
            for (int j = 0; j != colSize; j++) {
                res[i][j] = (int) (Math.random() * 2);
            }
        }
        return res;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = generateRandom01Matrix(7, 7);
        printMatrix(matrix);
        System.out.println(getMaxSize(matrix));
    }


}
