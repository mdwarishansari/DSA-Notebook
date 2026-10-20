
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int total = 0;

        // Calculate the sum of all elements
        for (int num : nums) {
            total += num;
        }

        // Target cannot exceed the total sum
        if (Math.abs(target) > total) {
            return 0;
        }

        // The required positive subset sum must be an integer
        if ((total + target) % 2 != 0) {
            return 0;
        }

        int sum = (total + target) / 2;

        // dp[s] = number of subsets having sum s
        int[] dp = new int[sum + 1];

        // One way to make sum zero: choose no elements
        dp[0] = 1;

        for (int num : nums) {
            // Traverse backwards to use each element only once
            for (int s = sum; s >= num; s--) {
                dp[s] += dp[s - num];
            }
        }

        return dp[sum];
    }
}
