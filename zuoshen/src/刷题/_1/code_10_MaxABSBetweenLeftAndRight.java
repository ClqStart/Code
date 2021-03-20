package 刷题._1;

import java.util.Arrays;

/**
 * 给定一个数组长度为N的arr，把任意长度大于0小于N作为左部分，剩下的作为右部分
 * 返回左部分的的最大值和右部分的最大值的最大值，左部分最大值减去右半部分最大值的绝对值
 */
public class code_10_MaxABSBetweenLeftAndRight {

    private static int maxABS1(int[] arr) {

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }

        }
        return max - Math.min(arr[0], arr[arr.length - 1]);
    }


    private static int maxABS2(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int res = 0;
        int leftMax = Integer.MIN_VALUE;
        int rightMax = 0;
        //必须每次进来初始化左边和右边最大值，
        for (int i = 0; i < arr.length - 1; i++) {
            leftMax = Math.max(leftMax, arr[i]);
            rightMax = Integer.MIN_VALUE;
            for (int j = i + 1; j < arr.length; j++) {
                rightMax = Math.max(rightMax, arr[j]);
            }
            res = Math.max(Math.abs(leftMax - rightMax), res);
        }
        return res;
    }

    private static int maxABS3(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int size = arr.length;
        int[] larr = new int[size];
        int[] Rarr = new int[size];
        larr[0] = arr[0];
        Rarr[size - 1] = arr[size - 1];
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > larr[i - 1]) {
                larr[i] = arr[i];
            } else {
                larr[i] = larr[i - 1];
            }
        }
        for (int i = size - 2; i >= 0; i--) {
            if (arr[i] > Rarr[i + 1]) {
                Rarr[i] = arr[i];
            } else {
                Rarr[i] = Rarr[i + 1];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            res = Math.max(Math.abs(larr[i] - Rarr[i]), res);
        }
        return res;
    }


    public static int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i != arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000) - 499;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(200);
        System.out.println(Arrays.toString(arr));
        System.out.println(maxABS1(arr));
        System.out.println(maxABS2(arr));
        System.out.println(maxABS3(arr));
    }


}
