class Solution {

    public int findMin(int[] nums) {

        // Start with the complete array.
        int left = 0;
        int right = nums.length - 1;


        /*
         * We continue until left and right point
         * to the same element.
         */
        while (left < right) {

            // Calculate middle index safely.
            int mid = left + (right - left) / 2;


            /*
             * Case 1:
             *
             * nums[mid] > nums[right]
             *
             * Example:
             *
             * [4, 5, 6, 7, 0, 1, 2]
             *          M           R
             *
             * 7 > 2
             *
             * This means the rotation point (minimum)
             * must be somewhere AFTER mid.
             *
             * Therefore, discard mid and everything
             * before it.
             */
            if (nums[mid] > nums[right]) {

                left = mid + 1;

            } else {

                /*
                 * Case 2:
                 *
                 * nums[mid] < nums[right]
                 *
                 * Example:
                 *
                 * [4, 5, 6, 7, 0, 1, 2]
                 *          M        R
                 *
                 * 0 < 2
                 *
                 * The right portion is sorted.
                 *
                 * The minimum can be:
                 *
                 * 1. nums[mid] itself
                 * 2. Somewhere before mid
                 *
                 * Therefore, we CANNOT do mid - 1.
                 *
                 * We keep mid as a possible answer.
                 */
                right = mid;
            }
        }


        /*
         * When left == right,
         * both pointers are pointing to the minimum element.
         */
        return nums[left];
    }
}