class Solution {

    public int minOperations(int[] nums, int x) {

        int n = nums.length;

        // ---------------------------------------------------
        // Step 1: Calculate the total sum of the array
        // ---------------------------------------------------
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        // ---------------------------------------------------
        // Step 2:
        //
        // We need to remove elements whose sum is x.
        //
        // Therefore, the elements we KEEP must have:
        //
        //     totalSum - x
        //
        // as their sum.
        // ---------------------------------------------------
        int target = totalSum - x;

        // If target is negative, it is impossible.
        if (target < 0) {
            return -1;
        }

        // If target is 0, we need to remove the entire array.
        if (target == 0) {
            return n;
        }

        // ---------------------------------------------------
        // Step 3:
        // Find the LONGEST subarray whose sum is target.
        //
        // Because all numbers are positive, sliding window
        // works.
        // ---------------------------------------------------
        int left = 0;
        int windowSum = 0;

        // Length of the longest valid subarray.
        int maxLength = -1;

        // Expand the window using right.
        for (int right = 0; right < n; right++) {

            // Add the current element to the window.
            windowSum += nums[right];

            // If the window sum becomes too large,
            // shrink it from the left.
            while (windowSum > target) {
                windowSum -= nums[left];
                left++;
            }

            // We found a subarray whose sum is exactly target.
            if (windowSum == target) {

                int currentLength = right - left + 1;

                maxLength = Math.max(maxLength, currentLength);
            }
        }

        // ---------------------------------------------------
        // Step 4:
        //
        // If no subarray with target sum exists,
        // the answer is impossible.
        //
        // Otherwise:
        //
        // operations = elements removed
        //            = n - elements kept
        // ---------------------------------------------------
        if (maxLength == -1) {
            return -1;
        }

        return n - maxLength;
    }
}