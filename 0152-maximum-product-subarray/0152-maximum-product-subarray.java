class Solution {

    public int maxProduct(int[] nums) {

        // Maximum product of a subarray ending
        // at the current position.
        int maxProduct = nums[0];

        // Minimum product of a subarray ending
        // at the current position.
        //
        // We need this because:
        // negative × negative = positive
        int minProduct = nums[0];

        // Best answer found so far.
        int answer = nums[0];

        for (int i = 1; i < nums.length; i++) {

            int current = nums[i];

            // Save the previous values BEFORE changing them.
            int previousMax = maxProduct;
            int previousMin = minProduct;

            /*
             * We have three choices:
             *
             * 1. Start a new subarray with current
             * 2. Extend the previous maximum product
             * 3. Extend the previous minimum product
             */
            maxProduct = Math.max(
                current,
                Math.max(
                    previousMax * current,
                    previousMin * current
                )
            );

            /*
             * Same three possibilities,
             * but now we want the minimum.
             */
            minProduct = Math.min(
                current,
                Math.min(
                    previousMax * current,
                    previousMin * current
                )
            );

            // Update the global maximum answer.
            answer = Math.max(answer, maxProduct);
        }

        return answer;
    }
}