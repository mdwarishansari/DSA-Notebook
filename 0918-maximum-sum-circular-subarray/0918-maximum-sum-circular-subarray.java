class Solution {

    public int maxSubarraySumCircular(int[] nums) {

        // Total sum of the entire array.
        int totalSum = nums[0];

        // Variables for Maximum Kadane.
        int maxCurrent = nums[0];
        int maxSum = nums[0];

        // Variables for Minimum Kadane.
        int minCurrent = nums[0];
        int minSum = nums[0];

        // Start from the second element.
        for (int i = 1; i < nums.length; i++) {

            int current = nums[i];

            // Add current element to total sum.
            totalSum += current;

            /*
             * -------------------------------
             * Maximum Subarray (Kadane)
             * -------------------------------
             *
             * Either:
             *
             * 1. Start a new subarray at current
             * 2. Continue the previous subarray
             */
            maxCurrent = Math.max(
                current,
                maxCurrent + current
            );

            // Store the best normal subarray sum.
            maxSum = Math.max(maxSum, maxCurrent);


            /*
             * -------------------------------
             * Minimum Subarray (Reverse Kadane)
             * -------------------------------
             *
             * Either:
             *
             * 1. Start a new minimum subarray
             * 2. Continue the previous minimum subarray
             */
            minCurrent = Math.min(
                current,
                minCurrent + current
            );

            // Store the smallest subarray sum.
            minSum = Math.min(minSum, minCurrent);
        }


        /*
         * If every number is negative,
         * maxSum will also be negative.
         *
         * totalSum - minSum would become 0,
         * which represents an EMPTY subarray.
         *
         * The problem requires a non-empty subarray,
         * so return maxSum directly.
         */
        if (maxSum < 0) {
            return maxSum;
        }


        /*
         * -------------------------------
         * Circular Case
         * -------------------------------
         *
         * Maximum circular sum =
         *
         * total array sum
         * -
         * minimum subarray sum
         *
         * We remove the "bad middle"
         * and keep the useful beginning + ending.
         */
        int circularSum = totalSum - minSum;


        // The answer can either:
        //
        // 1. Be a normal subarray
        // 2. Be a circular subarray
        //
        // Take the larger one.
        return Math.max(maxSum, circularSum);
    }
}