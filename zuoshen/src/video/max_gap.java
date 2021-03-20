package video;

import java.util.Arrays;



 //计算一个数组的数排序后的最大差值，
// 运用非排序的算法
public class max_gap {

    public static int maxGap(int[] num) {
        if (num == null || num.length < 2) return 0;
        int len = num.length;
        int[] maxbucket = new int[len + 1];
        int[] minBucket = new int[len + 1];
        boolean[] isEmpty = new boolean[len + 1];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < len; i++) {
            max = Math.max(max, num[i]);
            min = Math.min(min, num[i]);
        }
        if (max == min) {
            return 0;
        }
        for (int i = 0; i < len; i++) {
            int bucketNumber = getBucket(num[i], min, max, len);
            maxbucket[bucketNumber] = isEmpty[bucketNumber] ? Math.max(maxbucket[bucketNumber], num[i]) : maxbucket[bucketNumber];
            minBucket[bucketNumber] = isEmpty[bucketNumber] ? Math.min(maxbucket[bucketNumber], num[i]) : maxbucket[bucketNumber];
            isEmpty[bucketNumber] = true;
        }
        int res = 0;
        int laste = maxbucket[0];
        for (int i = 1; i < len + 1; i++) {
            if (isEmpty[i]) {
                res = Math.max(res, minBucket[i] - laste);
                laste = maxbucket[i];
            }
        }
        return res;
    }

    private static int getBucket(int num, int min, int max, int len) {
        return (num - min) * len / (max - min);
    }







    public static int comparator(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int gap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            gap = Math.max(nums[i] - nums[i - 1], gap);
        }
        return gap;
    }











    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (maxGap(arr1) != comparator(arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
