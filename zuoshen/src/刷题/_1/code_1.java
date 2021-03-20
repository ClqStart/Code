package 刷题._1;


import java.util.Arrays;

/**
 * 给定一个数组，从左往右依次表示X轴上从左往右点的位置
 * 该定一个正整数k，返回如果有一根长度为k的绳子，最多能覆盖住几个点，
 * 绳子边缘碰到x轴上的点也算
 */
public class code_1 {

    public static int maxPoint(int[] arr, int L) {
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int l = 0;
            int r = i;
            int index = r;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if(arr[mid] >= arr[i]-L){
                    r = mid -1;
                    index = mid;
                }else {
                    l = mid +1;
                }
            }
            res = Math.max(res,i-index+1);
        }

        return res;
    }


    public static int maxPoint1(int[] arr, int L) {
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int nearst = nearstIndex(arr, i, arr[i] - L);
            res = Math.max(res, i - nearst + 1);
        }
        return res;
    }

    private static int nearstIndex(int[] arr, int R, int value) {
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

    //滑动窗口
    public static int maxPoint2(int[] arr, int L) {
        int left = 0;
        int right = 0;
        int N = arr.length;
        int max = 0;
        while (left < N) {
            while (right < N && arr[right] - arr[left] <= L) {
                right++;
            }
            max = Math.max(max, right - (left++));
        }
        return max;
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
            int ans2 = maxPoint2(arr, L);
            int ans3 = test(arr, L);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("oops!");
                break;
            }
        }


    }
}
