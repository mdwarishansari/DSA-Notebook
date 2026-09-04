class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        // suffixMin[i] = minimum element from index i to n - 1
        int[] suffixMin = new int[n];

        // Last element is the minimum of the suffix containing only itself
        suffixMin[n - 1] = nums[n - 1];

        // Build suffix minimum array from right to left
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        // Maximum value from index 0 to the current index
        int prefixMax = Integer.MIN_VALUE;

        // Check indices from left to right.
        // The first valid index is automatically the smallest stable index.
        for (int i = 0; i < n; i++) {
            // Update maximum in nums[0..i]
            prefixMax = Math.max(prefixMax, nums[i]);

            // Instability score:
            // max(nums[0..i]) - min(nums[i..n-1])
            int instability = prefixMax - suffixMin[i];

            // If instability is <= k, this is the first stable index
            if (instability <= k) {
                return i;
            }
        }

        // No stable index exists
        return -1;
    }
}