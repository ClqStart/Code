package 刷题._1;


//长度为N的数组，一定可以组成N^2个数值对
//例如 arr=[1,2,3]
//数值对（1，1）,(1,2),(1,3),(2,1),(2,2),(2,3),(3,1),(3,2),(3,3)
//从小到大进行排序，返回第k小的数值对。

import java.util.Arrays;
import java.util.Comparator;

//快排对数据进行排序
//二分法进行
public class code_14_KthMinPair {

    public static class Pair {
        public int x;
        public int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class PairComparator implements Comparator<Pair> {

        @Override
        public int compare(Pair arg0, Pair arg1) {
            return arg0.x != arg1.x ? arg0.x - arg1.x : arg0.y - arg1.y;
        }

    }
    private static int[] kthMinPair1(int[] arr1, int k) {
        int N = arr1.length;
        if (k > N * N) {
            return null;
        }
        Pair[] pair = new Pair[N*N];
        int index =0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                pair[index++] = new Pair(arr1[i],arr1[j]);
            }
        }
        Arrays.sort(pair,new PairComparator());
        return new int[]{pair[k-1].x,pair[k-1].y};
    }
    private static int[] kthMinPair2(int[] arr1, int k) {
        int N = arr1.length;
        if (k > N * N) {
            return null;
        }
        Arrays.sort(arr1);
        int firstNumber = arr1[(k-1)/N];

        //小于的有几个，等于的有几个
        int lessNumberTime =0;
        int firstNumberTimes =0;
        for (int i = 0; i < N; i++) {
            if(arr1[i]<firstNumber){
                lessNumberTime++;
            }
            if(arr1[i] == firstNumber){
                firstNumberTimes++;
            }
        }
        int res = k- lessNumberTime *N;
        return new int[]{firstNumber,arr1[(res-1)/firstNumberTimes]};
    }


    private static int[] kthMinPair3(int[] arr1, int k) {
        int N = arr1.length;
        if(k > N*N)return null;

        //获得第一个元素
        int firstNumber =getMinWith(arr1, (k-1)/N);

        //获得第二个元素
        int lessFirstNumber = 0;
        int firstNumberTime = 0;
        for (int i = 0; i < arr1.length; i++) {
            if(arr1[i] < firstNumber){
                lessFirstNumber++;
            }
            if(arr1[i] == firstNumber){
                firstNumberTime++;
            }
        }
        int res = k - lessFirstNumber * N;
        return new int[]{firstNumber,getMinWith(arr1,(res-1)/firstNumberTime)};
    }

    //在一个无序数组中，找到排序后的arr[index]是什么？
    public static int getMinWith(int[] arr, int index) {
        int L = 0;
        int R = arr.length - 1;
        int pivot = 0;
        int[] range = null;
        while (L < R) {
            pivot = arr[L + (int) (Math.random() * (R - L + 1))];
            range = partition(arr, L, R, pivot);
            if(index<range[0]){
                R = range[0]-1;
            }else if(index > range[1]){
                L= range[1]+1;
            }else {
                return pivot;
            }

        }
        //L=R;
        return arr[L];
    }

    private static int[] partition(int[] arr, int l, int r, int pivot) {
        int less = l - 1;
        int more = r + 1;
        int cur = l;
        while (cur< more){
            if(arr[cur] < pivot){
                swap(arr,++less,cur++);
            }else if (arr[cur] > pivot){
                swap(arr,--more,cur);
            }else {
                cur++;
            }
        }
        return new int[]{less+1,more-1};
    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    // 为了测试，生成值也随机，长度也随机的随机数组
    public static int[] getRandomArray(int max, int len) {
        int[] arr = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // 为了测试
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
        int max = 100;
        int len = 30;
        int testTimes = 100000;
        System.out.println("test bagin, test times : " + testTimes);
        for (int i = 0; i < testTimes; i++) {
            int[] arr = getRandomArray(max, len);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int[] arr3 = copyArray(arr);
            int N = arr.length * arr.length;
            int k = (int) (Math.random() * N) + 1;
            int[] ans1 = kthMinPair1(arr1, k);
            int[] ans2 = kthMinPair2(arr2, k);
            int[] ans3 = kthMinPair3(arr3, k);
            if (ans1[0] != ans2[0] || ans2[0] != ans3[0] || ans1[1] != ans2[1] || ans2[1] != ans3[1]) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }


}
