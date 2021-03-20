package 刷题._1;


/**
 * 给定一个有序数组，和一个正整数m;
 * 找不重复的和为m的二元组和三元组
 */
public class code_16_PrintUniquePairAndTriad {

    private static void printUniquePair(int[] arr1, int sum) {
        int left = 0;
        int right = arr1.length - 1;
        while (left < right) {
            if (arr1[left] + arr1[right] < sum) {
                left++;
            } else if (arr1[left] + arr1[right] > sum) {
                right--;
            } else {
                if (left == 0 || arr1[left - 1] != arr1[left]) {
                    System.out.println("[" + arr1[left++] + " " + arr1[right] + "]");
                }
            }
        }
    }

    private static void printUniqueTriad(int[] arr1, int sum) {
        if (arr1.length < 3) return;
        for (int i = 1; i < arr1.length; i++) {
            if (arr1[i] !=arr1[i-1]) {
                printUniquePair(arr1,i,arr1[i-1],sum);
            }
        }

        int left = 0;
        int right = arr1.length - 1;

    }

    private static void printUniquePair(int[] arr, int start, int firstNum, int sum) {
        int left = start;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[left] + arr[right] < sum-firstNum) {
                left++;
            } else if (arr[left] + arr[right] > sum-firstNum) {
                right--;
            } else {
                if ((left == start || arr[left - 1] != arr[left])) {
                    System.out.println("["+ firstNum+" " + arr[left++] + " " + arr[right] + "]");
                }
            }
        }
    }


    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int sum = 10;
        int[] arr1 = {-8, -4, -3, 0, 1, 2, 4, 5, 8, 9};
        printArray(arr1);
        System.out.println("====");
        printUniquePair(arr1, sum);
        System.out.println("====");
        printUniqueTriad(arr1, sum);

    }
}
