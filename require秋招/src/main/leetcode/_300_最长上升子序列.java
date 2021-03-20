import java.util.Arrays;

public class _300_最长上升子序列{
    public int lengthOfLIS(int[] nums) {
        if(nums.length <=1) return nums.length;
        int[] dp = new int[nums.length];
        int max = dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i]=1;
            for (int j = 0; j < i ; j++) {
                if(nums[i] <= nums[j]) continue;
                dp[i] = Math.max(dp[i],dp[j]+1);
            }
            max = Math.max(max,dp[i]);
        }

        return max;

    }
}