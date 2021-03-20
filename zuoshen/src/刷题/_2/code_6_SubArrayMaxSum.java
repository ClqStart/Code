package 刷题._2;

//假设答案法

//给定一个数组arr,返回子数组的最大累加和
// 以当前的数前面必须>0,小于0时重新开始将cur重新置为0
public class code_6_SubArrayMaxSum {

    private static int maxSum(int[] arr1) {
        if (arr1 == null || arr1.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr1.length; i++) {
            cur += arr1[i];
            max = Math.max(max, cur);
            cur = Math.max(cur, 0);
        }
       return max;
    }


    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = {-2, -3, -5, 40, -10, -10, 100, 1};
        System.out.println(maxSum(arr1));

        int[] arr2 = {-2, -3, -5, 0, 1, 2, -1};
        System.out.println(maxSum(arr2));

        int[] arr3 = {-2, -3, -5, -1};
        System.out.println(maxSum(arr3));

    }


}
