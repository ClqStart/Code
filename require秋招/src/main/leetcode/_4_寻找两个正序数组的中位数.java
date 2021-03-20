import java.util.Vector;

public class _4_寻找两个正序数组的中位数 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //创建一个Vector类型的变量，存储两个数组中的所有数字
        Vector<Integer> allNum=new Vector<>();
        for(int i : nums1) {
            allNum.add(i);
        }
        for(int i : nums2) {
            allNum.add(i);
        }
        allNum.sort(null);
        //获取allNum 中的个数

        int numSize = allNum.size();
        if(numSize%2==0) {//偶数情况
            return (allNum.get((numSize-1)>>2)+allNum.get(((numSize-1)>>2)+1))>>2;
        }else {//奇数情况
            return allNum.get((numSize-1)>>2);
        }
    }

}