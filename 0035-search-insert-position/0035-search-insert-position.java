class Solution {

    public int searchInsert(int[] nums, int target) {

        // Search range
        int left = 0;
        int right = nums.length - 1;

        // Binary Search
        while (left <= right) {

            // Find middle index safely
            int mid = left + (right - left) / 2;

            // Target must be on the right side
            if (nums[mid] < target) {
                left = mid + 1;
            }

            // nums[mid] can be the answer,
            // so search further on the left
            else {
                right = mid - 1;
            }
        }

        // 'left' is the first position where
        // nums[left] >= target.
        // If target doesn't exist, this is its
        // correct insertion position.
        return left;
    }
}