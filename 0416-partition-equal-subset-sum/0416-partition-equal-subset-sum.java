class Solution {
    public boolean canPartition(int[] nums) {

        // Calculate the total sum of all elements.
        int total = 0;

        for (int num : nums) {
            total += num;
        }

        // If total sum is odd, it is impossible
        // to divide it into two equal integer sums.
        if (total % 2 != 0) {
            return false;
        }

        // We only need to find one subset
        // whose sum is exactly half of the total.
        int target = total / 2;

        // dp[sum] = true means we can create
        // this sum using the elements processed so far.
        boolean[] dp = new boolean[target + 1];

        // Sum 0 is always possible by choosing
        // no elements.
        dp[0] = true;

        // Process every number in the array.
        for (int num : nums) {

            // Traverse backwards so that the current
            // number is used at most once.
            for (int sum = target; sum >= num; sum--) {

                // If we could make (sum - num) before,
                // then adding 'num' allows us to make 'sum'.
                dp[sum] = dp[sum] || dp[sum - num];
            }
        }

        // If target can be formed, the array can be
        // divided into two subsets with equal sums.
        return dp[target];
    }
}