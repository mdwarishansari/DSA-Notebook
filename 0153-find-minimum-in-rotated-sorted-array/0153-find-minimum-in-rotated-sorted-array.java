class Solution {
    public int findMin(int[] nums) {

        // Start of the search range
        int left = 0;

        // End of the search range
        int right = nums.length - 1;

        /*
         * Continue searching until only one element remains.
         *
         * We use (left < right) instead of (left <= right)
         * because eventually left and right will point to
         * the minimum element.
         */
        while (left < right) {

            /*
             * If the current search range is already sorted,
             * then the leftmost element is the minimum.
             *
             * Example:
             * [0,1,2,3]
             */
            if (nums[left] <= nums[right]) {
                return nums[left];
            }

            // Calculate middle index safely.
            int mid = left + (right - left) / 2;

            /*
             * Compare middle element with the last element.
             */

            // Case 1:
            // Middle element is greater than the last element.
            //
            // This means mid lies in the LEFT sorted half.
            //
            // Example:
            // [4,5,6,7,0,1,2]
            //        ^
            //
            // The minimum must be to the right of mid.
            if (nums[mid] > nums[right]) {

                // Discard left half including mid.
                left = mid + 1;
            }

            // Case 2:
            // Middle element is less than or equal to the last element.
            //
            // This means mid lies in the RIGHT sorted half,
            // where the minimum exists.
            //
            // Mid itself could be the minimum,
            // so we keep it.
            else {

                // Search left part including mid.
                right = mid;
            }
        }

        /*
         * When left == right,
         * both point to the minimum element.
         */
        return nums[left];
    }
}