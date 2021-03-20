package 刷题._1;
//https://leetcode-cn.com/problems/trapping-rain-water/submissions/
//42题

public class code_11_TrappingRainWater {

    private static int water1(int[] arr) {
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
            int minNum = Math.min(Rarr[i],larr[i]);
            if(arr[i] >= minNum)continue;
            res +=(minNum-arr[i]);
        }
        return res;

    }
    private static int water2(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int res = 0;
        int leftMax = Integer.MIN_VALUE;
        int rightMax = 0;
        int size = arr.length;
        int min;
        for (int i = 0; i < size-1; i++) {
            leftMax = Math.max(leftMax,arr[i]);
            rightMax = Integer.MIN_VALUE;
            for (int j = i+1; j < size ; j++) {
                rightMax = Math.max(rightMax,arr[j]);
            }
            min=Math.min(leftMax,rightMax);
            res += min > arr[i] ?min-arr[i]:0;
        }
        return res;
    }
    private static int water3(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int size = arr.length;
        int[] Rarr = new int[size];
        int res=0;
        Rarr[size-1] = arr[size-1];
        for (int i = size-2; i >=0 ; i--) {
            if(arr[i] > Rarr[i+1]){
                Rarr[i] = arr[i];
            }else {
                Rarr[i] = Rarr[i+1];
            }
        }
        int leftMax =arr[0];
        for (int i = 1; i < arr.length-1; i++) {
             int min = Math.min(leftMax,Rarr[i]);
             if(min > arr[i]){
                 res += min-arr[i];
             }
             leftMax = Math.max(leftMax,arr[i]);
        }
        return res;
    }
    private static int water4(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int size = arr.length;
        int leftMax = arr[0];
        int rightMax = arr[size - 1];
        int L =1;
        int R = arr.length-2;
        int res=0;
        while (L <= R){
            if(leftMax <= rightMax){
                res += Math.max(0,leftMax-arr[L]);
                leftMax = Math.max(leftMax,arr[L++]);
            }else {
                res += Math.max(0,rightMax-arr[R]);
                rightMax = Math.max(rightMax,arr[R--]);
            }

        }
        return res;
    }



    // for test
    public static int[] generateRandomArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int len = 100;
        int value = 200;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(len, value);
            int ans1 = water1(arr);
            int ans2 = water2(arr);
            int ans3 = water3(arr);
            int ans4 = water4(arr);
            if(i ==10000){
            System.out.println(ans1+" "+ans2+" "+ans3 +" "+ans4);
            }
            if (ans1 != ans2 || ans3 != ans4 || ans1 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }




}
