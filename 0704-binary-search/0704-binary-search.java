class Solution {
    public int search(int[] nums, int target) {

        // Start of the search range
        int left = 0;

        // End of the search range
        int right = nums.length - 1;

        // Continue searching while the search space is valid
        while (left <= right) {

            /*
             * Find the middle index.
             *
             * We use:
             * left + (right - left) / 2
             *
             * instead of:
             * (left + right) / 2
             *
             * because for very large values of left and right,
             * (left + right) may overflow.
             */
            int mid = left + (right - left) / 2;

            // Case 1: Target found
            if (nums[mid] == target) {
                return mid;
            }

            // Case 2: Target is greater than middle element
            // Ignore the left half including mid
            else if (nums[mid] < target) {
                left = mid + 1;
            }

            // Case 3: Target is smaller than middle element
            // Ignore the right half including mid
            else {
                right = mid - 1;
            }
        }

        // Target does not exist in the array
        return -1;
    }
}