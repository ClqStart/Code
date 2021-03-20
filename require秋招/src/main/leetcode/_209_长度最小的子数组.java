import com.sun.org.apache.xerces.internal.impl.dv.xs.AnyURIDV;

/**
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 * <p>
 * 示例:
 * <p>
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 */

public class _209_长度最小的子数组 {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length < 2)  return nums[0] > s ? 1:0;
        int l = 0, r = -1;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while (r < nums.length) {

            while (r < nums.length) {
                r++;
                if (r < nums.length) {
                    sum += nums[r];
                }
                if (sum >= s) {
                   res = Math.min(res,r-l+1);
                   break;
                }

            }
            if(r == nums.length)
                break;

            while (l<r){
                sum -= nums[l];
                l++;
                if(sum >=s){
                    res=Math.min(res,r-l+1);
                }else {
                    break;
                }
            }

        }
        return res == Integer.MAX_VALUE ? 0 : res ;
    }

}
