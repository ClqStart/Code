package 刷题._2;



//最长严格递增子序列
public class code_4_LIS {


    public static int[] lis1(int[] arr) {
        if (arr == null) return null;
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 1;
        int index = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
                index = i;
            }
        }
        int[] li = new int[max];
        li[--max] = arr[index];
        for (int i = index; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                li[--max] = arr[i];
                index = i;
            }
        }
        return li;
    }

    private static int[] lis2(int[] arr) {
        if (arr == null) return null;
        int[] dp = getdp2(arr);
        int max = 1;
        int index = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
                index = i;
            }
        }
        int[] li = new int[max];
        li[--max] = arr[index];
        for (int i = index; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                li[--max] = arr[i];
                index = i;
            }
        }
        return li;
    }

    public static int[] getdp2(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        ends[0] = arr[0];
        dp[0] = 1;
        int l = 0;
        int m = 0;
        int r = 0;
        int right = 0;  //重开头依次扩充
        //对end进行二分
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l<=r){
                m = (l+r)/2;
                if(arr[i] > ends[m]){
                     l = m+1;
                }else {
                    r = m-1;
                }
            }
            right = Math.max(l,right);
            ends[l] = arr[i];
            dp[i] = l+1;
        }
        return dp;

    }


    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 3, 6, 4, 8, 9, 7};
        printArray(arr);
        printArray(lis1(arr));
        printArray(lis2(arr));

    }


}
