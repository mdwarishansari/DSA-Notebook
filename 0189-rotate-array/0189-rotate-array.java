class Solution {

    public void rotate(int[] nums, int k) {

        int n = nums.length;

        // If k is greater than n,
        // only k % n rotations are actually needed.
        k = k % n;

        /*
         * Step 1:
         * Reverse the entire array.
         *
         * [1,2,3,4,5,6,7]
         *       ↓
         * [7,6,5,4,3,2,1]
         */
        reverse(nums, 0, n - 1);

        /*
         * Step 2:
         * Reverse the first k elements.
         *
         * [7,6,5 | 4,3,2,1]
         *       ↓
         * [5,6,7 | 4,3,2,1]
         */
        reverse(nums, 0, k - 1);

        /*
         * Step 3:
         * Reverse the remaining elements.
         *
         * [5,6,7 | 4,3,2,1]
         *       ↓
         * [5,6,7 | 1,2,3,4]
         */
        reverse(nums, k, n - 1);
    }

    // Reverses the elements between left and right
    private void reverse(int[] nums, int left, int right) {

        while (left < right) {

            // Swap nums[left] and nums[right]
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            // Move toward the center
            left++;
            right--;
        }
    }
}