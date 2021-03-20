package 第三期.class_1;
/*
题目一
给定一个有序数组arr，从左到右依次表示X轴上从左往右点的位置
给定一个正整数K，返回如果有一根长度为K的绳子，最多能盖住几个点
绳子的边缘点碰到X轴上的点，也算盖住
*
*/

import java.util.Arrays;


public class _1_CordCoverMaxPoint {

    // 小贪心:每次都是压最后一个点
    public static int maxPoint(int[] arr, int L) {
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int MaxleftIndex = searchIndex(arr, i, arr[i] - L);
            res = Math.max(res, i - MaxleftIndex + 1);

        }
        return res;
    }

    private static int searchIndex(int[] arr, int R, int value) {
        int L = 0;
        int index = R;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }


    public static int maxPoint1(int[] arr, int L) {
        int res = 0;
        int l = 0;
        int r = 0;
        while (l < arr.length) {
            while (r < arr.length && arr[r] - arr[l] <= L) {
                r++;
            }
            res = Math.max(res,r-(l++));
        }
        return  res;
    }


    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = maxPoint(arr, L);
            int ans2 = maxPoint1(arr, L);
            int ans3 = test(arr, L);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("oops!");
                break;
            }
        }


    }
}
