package 第三期.class_2;

/**
 * 在行也有序、列也有序的二维数组中，找num，找到返回true，否则false
 * 如果讲过，本题快速跳过
 */
public class _1_FindSortNumber {
    public  static  boolean isContains(int[][] matrix,int K){
         int r=matrix.length;
         int c = matrix[0].length;
         int rank =0;
         while (rank < r && c>=0){
             if(matrix[rank][c-1] > K){
                 c--;
             }else if (matrix[rank][c-1] < K){
                 rank++;
             }else {
                 return true;
             }
         }
         return false;

    }



    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },// 0
                { 10, 12, 13, 15, 16, 17, 18 },// 1
                { 23, 24, 25, 26, 27, 28, 29 },// 2
                { 44, 45, 46, 47, 48, 49, 50 },// 3
                { 65, 66, 67, 68, 69, 70, 71 },// 4
                { 96, 97, 98, 99, 100, 111, 122 },// 5
                { 166, 176, 186, 187, 190, 195, 200 },// 6
                { 233, 243, 321, 341, 356, 370, 380 } // 7
        };
        int K = 233;
        System.out.println(isContains(matrix, K));
    }


}
