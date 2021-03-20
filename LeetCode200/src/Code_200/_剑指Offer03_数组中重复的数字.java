/*
找出数组中重复的数字。
在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
示例 1：
输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3
限制：
2 <= n <= 100000
*/

package Code_200;


public class _剑指Offer03_数组中重复的数字 {

    class Solution {
        public int findRepeatNumber(int[] nums) {
             int Len = nums.length;
             int[]  Nu = new int[Len];
            for (int i = 0; i < Len; i++) {
                Nu[i] = nums[i];
                for (int j = 0; j < i; j++) {
                    if(nums[i]==Nu[j]) return nums[i];
                }
            }
              return -1;
        }
    }
}




























