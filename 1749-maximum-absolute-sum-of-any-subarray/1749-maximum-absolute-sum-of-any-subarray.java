class Solution {

    public int maxAbsoluteSum(int[] nums) {

        // Maximum subarray sum ending at current index.
        int maxCurrent = 0;

        // Minimum subarray sum ending at current index.
        int minCurrent = 0;

        // Largest subarray sum found so far.
        int maxSum = 0;

        // Smallest subarray sum found so far.
        int minSum = 0;

        for (int num : nums) {

            /*
             * Kadane for maximum sum:
             *
             * Either:
             * 1. Start a new subarray with num
             * 2. Continue the previous subarray
             */
            maxCurrent = Math.max(
                num,
                maxCurrent + num
            );

            /*
             * Kadane for minimum sum:
             *
             * Either:
             * 1. Start a new subarray with num
             * 2. Continue the previous subarray
             */
            minCurrent = Math.min(
                num,
                minCurrent + num
            );

            // Update the largest and smallest sums.
            maxSum = Math.max(maxSum, maxCurrent);
            minSum = Math.min(minSum, minCurrent);
        }

        /*
         * The answer can come from:
         *
         * positive maximum → maxSum
         * negative minimum → -minSum
         *
         * Take whichever absolute value is larger.
         */
        return Math.max(maxSum, Math.abs(minSum));
    }
}