import java.util.Arrays;

public class _最大连续子序列和_ {

    public static int findMax(int arr[]) {
        if (arr.length == 1) {
            return arr[0];
        }

        int mid = (arr.length) / 2;
        int[]  leftArr = Arrays.copyOfRange(arr,0,mid);
        int[] rightArr = Arrays.copyOfRange(arr,mid,arr.length);
        int lenleft = findMax(leftArr);
        int lenRight = findMax(rightArr);
        int lenmid = maxInMid(leftArr,rightArr);

        return Math.max(Math.max(lenleft,lenRight),lenmid);


    }

    private static int maxInMid(int[] left, int[] right) {
        int maxLeft =0 ;
        int maxRight = 0;
        int tmpLeft = 0;
        int tmpRight = 0;
        for (int i = 0; i < left.length; i++) {
            tmpLeft = tmpLeft + left[left.length-1-i];
            maxLeft = Math.max(tmpLeft,maxLeft);
        }
        for (int i = 0; i < right.length; i++) {
            tmpRight = tmpRight + right[i];
            maxRight = Math.max(tmpRight,maxRight);
        }
        return  maxLeft+ maxRight;

    }

    public static void main(String[] args) {
        int[] arr = {3,-1,10,1,3,-13,5,1};
        System.out.println(findMax(arr));
    }
}
