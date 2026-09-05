class Solution {

    public int minSubArrayLen(int target, int[] nums) {

        int left = 0;

        // Stores the sum of the current window
        int sum = 0;

        // Start with a very large value
        int minLength = Integer.MAX_VALUE;

        // Expand the window using right pointer
        for (int right = 0; right < nums.length; right++) {

            // Add the current element to the window
            sum += nums[right];

            // If sum is >= target,
            // the current window is valid.
            //
            // Now try to shrink it from the left
            // to find the smallest possible window.
            while (sum >= target) {

                // Current window:
                // [left ... right]
                //
                // Its length is:
                // right - left + 1
                minLength = Math.min(
                    minLength,
                    right - left + 1
                );

                // Remove the leftmost element
                // and move left forward.
                sum -= nums[left];
                left++;
            }
        }

        // If no valid subarray was found,
        // return 0 as required by the problem.
        if (minLength == Integer.MAX_VALUE) {
            return 0;
        }

        return minLength;
    }
}