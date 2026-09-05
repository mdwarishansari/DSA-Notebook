class Solution {

    public int numSubarrayProductLessThanK(int[] nums, int k) {

        // Since every number is at least 1,
        // the product of any non-empty subarray is at least 1.
        //
        // Therefore, if k <= 1, no subarray can have
        // product strictly less than k.
        if (k <= 1) {
            return 0;
        }

        int left = 0;

        // Product of the current sliding window.
        long product = 1;

        // Total number of valid subarrays.
        int answer = 0;

        // Expand the window using right pointer.
        for (int right = 0; right < nums.length; right++) {

            // Add nums[right] to the current window.
            product *= nums[right];

            // If product is too large, shrink the window
            // from the left until product becomes < k.
            while (product >= k) {
                product /= nums[left];
                left++;
            }

            // Current window is:
            //
            // [left ... right]
            //
            // Number of valid subarrays ending at 'right':
            //
            // [right]
            // [right-1 ... right]
            // [right-2 ... right]
            // ...
            // [left ... right]
            //
            // Total = right - left + 1
            answer += right - left + 1;
        }

        return answer;
    }
}