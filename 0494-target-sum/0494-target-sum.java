class Solution {
    public int findTargetSumWays(int[] nums, int target) {

        // Calculate the total sum of all numbers.
        int total = 0;

        for (int num : nums) {
            total += num;
        }

        /*
         * Let:
         * P = sum of numbers with '+'
         * N = sum of numbers with '-'
         *
         * We know:
         *
         * P - N = target
         * P + N = total
         *
         * Adding both equations:
         *
         * 2P = total + target
         *
         * Therefore:
         *
         * P = (total + target) / 2
         *
         * So we only need to count how many subsets
         * have sum = (total + target) / 2.
         */

        // If total + target is negative, the required
        // subset sum is impossible.
        if (total + target < 0) {
            return 0;
        }

        // If total + target is odd, it cannot be
        // divided by 2 to get an integer subset sum.
        if ((total + target) % 2 != 0) {
            return 0;
        }

        // This is the subset sum we need to form.
        int requiredSum = (total + target) / 2;

        /*
         * dp[s] = number of ways to create sum 's'
         * using the numbers processed so far.
         */
        int[] dp = new int[requiredSum + 1];

        // There is exactly one way to create sum 0:
        // choose nothing.
        dp[0] = 1;

        // Process every number.
        for (int num : nums) {

            /*
             * Traverse backwards so that each number
             * is used at most once.
             */
            for (int sum = requiredSum; sum >= num; sum--) {

                /*
                 * We can create 'sum' by:
                 *
                 * 1. Taking the current number 'num'
                 * 2. Previously creating (sum - num)
                 *
                 * Therefore, add those previous ways.
                 */
                dp[sum] += dp[sum - num];
            }
        }

        // Number of ways to create the required subset sum.
        return dp[requiredSum];
    }
}