class Solution {

    public int maxSubArray(int[] nums) {

        // currentSum = maximum subarray sum
        // ending exactly at the current index.
        int currentSum = nums[0];

        // maxSum = best subarray sum we have found
        // anywhere in the array so far.
        int maxSum = nums[0];

        // Start from the second element.
        for (int i = 1; i < nums.length; i++) {

            /*
             * We have two choices:
             *
             * 1. Start a new subarray from nums[i]
             *
             * 2. Continue the previous subarray
             *
             * We choose whichever gives the larger sum.
             */
            currentSum = Math.max(
                nums[i],
                currentSum + nums[i]
            );

            /*
             * currentSum tells us the best subarray
             * ending at index i.
             *
             * Now compare it with the best answer
             * we have seen so far.
             */
            maxSum = Math.max(maxSum, currentSum);
        }

        // Return the maximum subarray sum.
        return maxSum;
    }
}