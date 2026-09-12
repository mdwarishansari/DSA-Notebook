class Solution {

    public int maxAbsoluteSum(int[] nums) {

        /*
         * currentMax:
         * Maximum subarray sum that ends at the current index.
         *
         * currentMin:
         * Minimum subarray sum that ends at the current index.
         *
         * maxSum:
         * Maximum subarray sum found anywhere so far.
         *
         * minSum:
         * Minimum subarray sum found anywhere so far.
         *
         * We initialize everything to 0 because the problem allows
         * choosing an empty subarray whose sum is 0.
         */
        int currentMax = 0;
        int currentMin = 0;

        int maxSum = 0;
        int minSum = 0;

        /*
         * Process every element once.
         */
        for (int num : nums) {

            /*
             * Maximum subarray ending at this position.
             *
             * We have two choices:
             *
             * 1. Start a new subarray with just 'num'
             * 2. Extend the previous maximum subarray
             *
             * Take whichever gives the larger sum.
             */
            currentMax = Math.max(num, currentMax + num);

            /*
             * Minimum subarray ending at this position.
             *
             * Again, there are two choices:
             *
             * 1. Start a new subarray with just 'num'
             * 2. Extend the previous minimum subarray
             *
             * Take whichever gives the smaller sum.
             */
            currentMin = Math.min(num, currentMin + num);

            /*
             * Update the best maximum subarray sum found so far.
             */
            maxSum = Math.max(maxSum, currentMax);

            /*
             * Update the smallest (most negative) subarray sum
             * found so far.
             */
            minSum = Math.min(minSum, currentMin);
        }

        /*
         * The maximum absolute subarray sum can come from:
         *
         * 1. The largest positive subarray sum -> maxSum
         * 2. The most negative subarray sum -> -minSum
         *
         * Example:
         *
         * maxSum = 8
         * minSum = -12
         *
         * Answer = max(8, 12) = 12
         */
        return Math.max(maxSum, -minSum);
    }
}