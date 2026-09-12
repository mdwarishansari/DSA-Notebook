class Solution {

    public int maxSubArray(int[] nums) {

        /*
         * currentSum:
         * Maximum sum of a subarray that MUST end
         * at the current index.
         *
         * maxSum:
         * Maximum subarray sum found anywhere
         * in the array so far.
         *
         * We initialize both using nums[0] because
         * the problem requires a NON-EMPTY subarray.
         */
        int currentSum = nums[0];
        int maxSum = nums[0];

        /*
         * Start from index 1 because index 0 was
         * already used for initialization.
         */
        for (int i = 1; i < nums.length; i++) {

            /*
             * We have two choices for the subarray
             * ending at index i:
             *
             * 1. Start a new subarray:
             *      nums[i]
             *
             * 2. Extend the previous subarray:
             *      currentSum + nums[i]
             *
             * Choose whichever gives the larger sum.
             */
            currentSum = Math.max(
                nums[i],
                currentSum + nums[i]
            );

            /*
             * currentSum represents the best subarray
             * ending at the current index.
             *
             * Compare it with the best answer found
             * anywhere before this point.
             */
            maxSum = Math.max(
                maxSum,
                currentSum
            );
        }

        /*
         * maxSum now contains the maximum sum of
         * any non-empty contiguous subarray.
         */
        return maxSum;
    }
}