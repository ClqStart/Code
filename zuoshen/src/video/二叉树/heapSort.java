package video.二叉树;

import java.util.Arrays;

public class heapSort {
    
    

    public static void heapSort(int[] num) {
        if (num == null || num.length < 2) return;

        int len = num.length;
        for (int i = 0; i < len; i++) {
            heapinsert(num, i);
        }
        swap(num, 0, --len);
        while (len > 0) {
            heapfy(num, 0, len);
            swap(num, 0, --len);
        }

    }

    private static void heapfy(int[] num, int index, int heapsize) {
        int left = (index * 2) + 1;
        while (left < heapsize) {
            int largest = left + 1 < heapsize && num[left + 1] > num[left] ? left + 1 : left;
            largest = num[index] < num[largest] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(num, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void heapinsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {   //上溢到根节点自动-1/2=0停止
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }

    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
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

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }


}
