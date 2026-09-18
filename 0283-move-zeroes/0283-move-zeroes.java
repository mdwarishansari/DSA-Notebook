class Solution {

    public void moveZeroes(int[] nums) {

        // Position where the next non-zero element
        // should be placed.
        int insertPos = 0;

        /*
         * First pass:
         *
         * Move all non-zero elements to the beginning
         * while maintaining their original order.
         */
        for (int i = 0; i < nums.length; i++) {

            // If the current element is non-zero,
            // place it at insertPos.
            if (nums[i] != 0) {

                nums[insertPos] = nums[i];

                insertPos++;
            }
        }

        /*
         * At this point:
         *
         * nums[0 ... insertPos-1]
         * contains all non-zero elements.
         *
         * Now fill the remaining positions with zero.
         */
        while (insertPos < nums.length) {

            nums[insertPos] = 0;

            insertPos++;
        }
    }
}