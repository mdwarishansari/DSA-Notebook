
class Solution {
    public int sortPermutation(int[] nums) {
        // Start with all bits set to 1.
        int ans = -1;

        // Tracks whether any element is misplaced.
        boolean found = false;

        // Check every position in the permutation.
        for (int i = 0; i < nums.length; i++) {

            // In a sorted permutation, nums[i] must equal i.
            if (nums[i] != i) {

                // This element must participate in sorting.
                // AND all misplaced values to find the maximum k.
                ans &= nums[i];

                // At least one misplaced element exists.
                found = true;
            }
        }

        // If the array is already sorted, return 0.
        if (!found) {
            return 0;
        }

        // Return the maximum possible value of k.
        return ans;
    }
}
