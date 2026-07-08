class Solution {
    public int searchInsert(int[] nums, int target) {

        // Start of the search range
        int left = 0;

        // End of the search range
        int right = nums.length - 1;

        /*
         * Binary Search
         *
         * Goal:
         * Find the first element that is
         * greater than or equal to the target.
         */
        while (left <= right) {

            // Calculate middle index safely
            int mid = left + (right - left) / 2;

            /*
             * If middle element is greater than or equal to target,
             * the answer could be:
             * 1. mid itself
             * 2. somewhere on the left
             *
             * So keep searching in the left half.
             */
            if (nums[mid] >= target) {
                right = mid - 1;
            }

            /*
             * Otherwise,
             * target must be somewhere on the right.
             */
            else {
                left = mid + 1;
            }
        }

        /*
         * When the loop ends,
         * left is exactly the position where target
         * should be inserted.
         *
         * If target exists:
         *     left = target's index
         *
         * If target doesn't exist:
         *     left = insertion position
         */
        return left;
    }
}