class Solution {

    public int maxSubarraySumCircular(int[] nums) {

        // Stores the sum of all elements in the array.
        int totalSum = 0;

        // Kadane variables for Maximum Subarray Sum.
        // currMax = Maximum subarray sum ending at current index.
        // maxSum  = Maximum subarray sum found so far.
        int currMax = 0;
        int maxSum = nums[0];

        // Kadane variables for Minimum Subarray Sum.
        // currMin = Minimum subarray sum ending at current index.
        // minSum  = Minimum subarray sum found so far.
        int currMin = 0;
        int minSum = nums[0];

        // Traverse the array once.
        for (int num : nums) {

            /*
             * ---------- Maximum Kadane ----------
             *
             * Two choices:
             *
             * 1. Extend previous maximum subarray.
             *      currMax + num
             *
             * 2. Start a new subarray from current element.
             *      num
             */
            currMax = Math.max(currMax + num, num);

            // Update the best maximum subarray seen so far.
            maxSum = Math.max(maxSum, currMax);


            /*
             * ---------- Minimum Kadane ----------
             *
             * Same idea, but now we are searching for the
             * smallest contiguous subarray.
             */
            currMin = Math.min(currMin + num, num);

            // Update the smallest subarray seen so far.
            minSum = Math.min(minSum, currMin);


            // Keep calculating the total array sum.
            totalSum += num;
        }

        /*
         * If every element is negative:
         *
         * totalSum == minSum
         *
         * totalSum - minSum becomes 0,
         * meaning we removed the entire array,
         * leaving an empty subarray (which is invalid).
         *
         * Therefore simply return the normal Kadane answer.
         */
        if (maxSum < 0) {
            return maxSum;
        }

        /*
         * Two possible answers:
         *
         * 1. Normal maximum subarray.
         * 2. Circular maximum subarray.
         *
         * Circular Maximum =
         * Total Sum - Minimum Subarray Sum
         */
        return Math.max(maxSum, totalSum - minSum);
    }
}