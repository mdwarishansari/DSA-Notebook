class Solution {
    public int search(int[] nums, int target) {

        // Start of the search range
        int left = 0;

        // End of the search range
        int right = nums.length - 1;

        // Continue searching while a valid search space exists
        while (left <= right) {

            // Calculate middle index safely
            int mid = left + (right - left) / 2;

            // Case 1:
            // Target found
            if (nums[mid] == target) {
                return mid;
            }

            /*
             * One side of the array is ALWAYS sorted.
             * First, determine which half is sorted.
             */

            // -------------------------------
            // LEFT HALF IS SORTED
            // -------------------------------
            if (nums[left] <= nums[mid]) {

                /*
                 * Check if target lies inside
                 * the sorted left half.
                 *
                 * Range:
                 * nums[left] <= target < nums[mid]
                 */
                if (target >= nums[left] && target < nums[mid]) {

                    // Search in the left half
                    right = mid - 1;

                } else {

                    // Target must be in the right half
                    left = mid + 1;
                }
            }

            // -------------------------------
            // RIGHT HALF IS SORTED
            // -------------------------------
            else {

                /*
                 * Check if target lies inside
                 * the sorted right half.
                 *
                 * Range:
                 * nums[mid] < target <= nums[right]
                 */
                if (target > nums[mid] && target <= nums[right]) {

                    // Search in the right half
                    left = mid + 1;

                } else {

                    // Target must be in the left half
                    right = mid - 1;
                }
            }
        }

        // Target not found
        return -1;
    }
}