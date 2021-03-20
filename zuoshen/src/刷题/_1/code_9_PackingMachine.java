package 刷题._1;
//https://leetcode-cn.com/problems/super-washing-machines/
//517题
//多台机器包裹，最终具有相同数量，每轮机器都可以向左或者向右移动一件，至少进行多少轮，达不到相同返回-1

//思路：1.遍历从左往右，依次丢包最小值
//2、每个元素丢包分两种情况：1》 左>当前元素>右、 左<当前元素<右、 左>当前元素<右  求左右仍包的最小值
                        //2>  左<当前元素>右  （左+右）
public class code_9_PackingMachine {

    public static int MinOps(int[] arr) {
        int sumall=0;
        for (int i = 0; i < arr.length; i++) {
            sumall += arr[i];
        }
        if( sumall % arr.length!=0) return -1;
        int me = sumall / arr.length;
        int leftall=0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int leftMust = leftall- i* me;
            int rightMust = (sumall-leftall-arr[i])-(arr.length-1-i)*me;
            if(leftMust < 0 &&rightMust < 0){
                max = Math.max(max,Math.abs(leftMust)+Math.abs(rightMust));
            }else {
                max = Math.max(max,Math.max(Math.abs(leftMust),Math.abs(rightMust)));
            }
            leftall +=arr[i];
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(MinOps(new int[]{0,3,0}));
    }

}
