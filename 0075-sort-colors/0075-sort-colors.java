class Solution {

    public void sortColors(int[] nums) {

        // 'low' points to the position where the next 0 should go.
        int low = 0;

        // 'mid' is the current element we are checking.
        int mid = 0;

        // 'high' points to the position where the next 2 should go.
        int high = nums.length - 1;

        /*
         * We only need to process the unknown region.
         *
         * Regions:
         *
         * [0 ... low-1]       -> all 0s
         * [low ... mid-1]     -> all 1s
         * [mid ... high]      -> unknown
         * [high+1 ... n-1]    -> all 2s
         */
        while (mid <= high) {

            // Case 1: Current element is 0.
            if (nums[mid] == 0) {

                /*
                 * Move this 0 to the left region.
                 */
                swap(nums, low, mid);

                // The 0 region grows.
                low++;

                // The current element is now processed.
                mid++;
            }

            // Case 2: Current element is 1.
            else if (nums[mid] == 1) {

                /*
                 * 1 already belongs in the middle.
                 * Nothing needs to be swapped.
                 */
                mid++;
            }

            // Case 3: Current element is 2.
            else {

                /*
                 * Move this 2 to the right region.
                 */
                swap(nums, mid, high);

                // The 2 region grows.
                high--;

                /*
                 * DO NOT increment mid here.
                 *
                 * The element swapped from 'high' into 'mid'
                 * has not been checked yet.
                 */
            }
        }
    }

    // Helper method to swap two elements.
    private void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}            