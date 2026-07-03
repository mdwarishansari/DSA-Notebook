class Solution {
    public int maxSubArray(int[] nums) {

        // currentSum = Maximum sum of a subarray ending at the current index
        int currentSum = nums[0];

        // maxSum = Maximum subarray sum found so far
        int maxSum = nums[0];

        // Start from index 1 because index 0 is already used to initialize both variables
        for (int i = 1; i < nums.length; i++) {

            /*
             * At every element, we have only two choices:
             *
             * 1. Extend the previous subarray
             *      currentSum + nums[i]
             *
             * 2. Start a brand new subarray from the current element
             *      nums[i]
             *
             * Whichever gives the larger sum becomes the best subarray
             * ending at index i.
             */
            currentSum = Math.max(nums[i], currentSum + nums[i]);

            /*
             * Update the overall maximum subarray sum if the current
             * subarray is better than any seen before.
             */
            maxSum = Math.max(maxSum, currentSum);
        }

        // Return the maximum contiguous subarray sum
        return maxSum;
    }
}