class Solution {

    public int firstStableIndex(int[] nums, int k) {

        int n = nums.length;

        // prefixMax[i] = maximum element from index 0 to i
        int[] prefixMax = new int[n];

        // suffixMin[i] = minimum element from index i to n - 1
        int[] suffixMin = new int[n];


        // --------------------------------------------------
        // Step 1: Build prefix maximum array
        // --------------------------------------------------

        prefixMax[0] = nums[0];

        for (int i = 1; i < n; i++) {

            // Maximum between:
            // 1. Current element
            // 2. Maximum we already found on the left
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        }


        // --------------------------------------------------
        // Step 2: Build suffix minimum array
        // --------------------------------------------------

        suffixMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {

            // Minimum between:
            // 1. Current element
            // 2. Minimum we already found on the right
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }


        // --------------------------------------------------
        // Step 3: Find the first stable index
        // --------------------------------------------------

        for (int i = 0; i < n; i++) {

            // According to the problem:
            //
            // instability =
            // maximum(nums[0 ... i])
            // -
            // minimum(nums[i ... n-1])
            //
            // prefixMax[i] gives the first part.
            // suffixMin[i] gives the second part.

            int instability = prefixMax[i] - suffixMin[i];

            // Stable if instability <= k
            if (instability <= k) {
                return i;
            }
        }


        // No stable index exists
        return -1;
    }
}