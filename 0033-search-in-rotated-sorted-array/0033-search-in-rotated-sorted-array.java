class Solution {

    public int search(int[] nums, int target) {

        // Binary search boundaries
        int left = 0;
        int right = nums.length - 1;

        // Continue while there is a valid search range
        while (left <= right) {

            // Calculate middle index safely
            int mid = left + (right - left) / 2;

            // If middle element is the target, return its index
            if (nums[mid] == target) {
                return mid;
            }


            /*
             * At least one half of the rotated array
             * must be sorted.
             *
             * Check whether the LEFT half is sorted.
             *
             * Example:
             *
             * [4, 5, 6, 7, 0, 1, 2]
             *  L        M        R
             *
             * nums[left] <= nums[mid]
             */
            if (nums[left] <= nums[mid]) {

                /*
                 * Left half is sorted.
                 *
                 * Check whether target lies inside
                 * this sorted range.
                 *
                 * nums[left] <= target < nums[mid]
                 */
                if (nums[left] <= target && target < nums[mid]) {

                    // Target must be in the left half
                    right = mid - 1;

                } else {

                    // Target must be in the right half
                    left = mid + 1;
                }

            } else {

                /*
                 * Left half is not sorted,
                 * therefore the RIGHT half is sorted.
                 *
                 * Check whether target lies inside
                 * the sorted right half.
                 *
                 * nums[mid] < target <= nums[right]
                 */
                if (nums[mid] < target && target <= nums[right]) {

                    // Target must be in the right half
                    left = mid + 1;

                } else {

                    // Target must be in the left half
                    right = mid - 1;
                }
            }
        }

        /*
         * If the loop finishes, the target does not
         * exist in the array.
         */
        return -1;
    }
}