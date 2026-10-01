class Solution {

    public int findPeakElement(int[] nums) {

        // Binary search boundaries
        int left = 0;
        int right = nums.length - 1;


        /*
         * We continue until left and right
         * point to the same element.
         */
        while (left < right) {

            // Calculate middle index safely
            int mid = left + (right - left) / 2;


            /*
             * Compare nums[mid] with the element
             * immediately to its right.
             *
             * Case 1:
             *
             * nums[mid] > nums[mid + 1]
             *
             * We are going downhill.
             *
             * Example:
             *
             *       5
             *      / \
             *     /   3
             *
             * The peak is at mid or somewhere
             * on the LEFT side.
             *
             * So keep mid as a possible answer.
             */
            if (nums[mid] > nums[mid + 1]) {

                right = mid;

            } else {

                /*
                 * Case 2:
                 *
                 * nums[mid] < nums[mid + 1]
                 *
                 * We are going uphill.
                 *
                 * Example:
                 *
                 *       5
                 *      /
                 *     3
                 *
                 * A peak must exist somewhere
                 * on the RIGHT side.
                 *
                 * mid cannot be the peak because
                 * nums[mid + 1] is greater.
                 */
                left = mid + 1;
            }
        }


        /*
         * When left == right,
         * we have narrowed the search down to
         * one guaranteed peak element.
         */
        return left;
    }
}