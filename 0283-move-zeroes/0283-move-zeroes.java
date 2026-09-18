class Solution {

    public void moveZeroes(int[] nums) {

        // Points to the position where the next
        // non-zero element should be placed.
        int insertPos = 0;

        // Scan every element of the array.
        for (int i = 0; i < nums.length; i++) {

            // We only care about non-zero elements.
            if (nums[i] != 0) {

                /*
                 * Swap the current non-zero element
                 * with the element at insertPos.
                 *
                 * This moves the non-zero element
                 * toward the front.
                 */
                int temp = nums[i];
                nums[i] = nums[insertPos];
                nums[insertPos] = temp;

                // Move to the next position where
                // a non-zero element should be placed.
                insertPos++;
            }
        }
    }
}