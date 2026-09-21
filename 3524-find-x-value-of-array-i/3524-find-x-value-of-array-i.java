class Solution {

    public long[] resultArray(int[] nums, int k) {

        // result[r] = total number of subarrays
        // whose product % k == r
        long[] result = new long[k];

        // dp[r] = number of subarrays ending at the
        // previous index whose product % k == r
        long[] dp = new long[k];

        // Process every element as the RIGHTMOST element
        // of the subarray.
        for (int num : nums) {

            // Counts for subarrays ending at the current element.
            long[] newDp = new long[k];

            /*
             * Case 1:
             * Start a completely new subarray with just [num].
             *
             * Example:
             * num = 5, k = 3
             * 5 % 3 = 2
             *
             * Therefore [5] has remainder 2.
             */
            int remainder = num % k;
            newDp[remainder]++;

            /*
             * Case 2:
             * Extend every subarray that ended at the previous index.
             *
             * If an old subarray had:
             *
             *     product % k = r
             *
             * then after appending num:
             *
             *     new remainder = (r * num) % k
             */
            for (int r = 0; r < k; r++) {

    if (dp[r] == 0) {
        continue;
    }

    // Use long to prevent integer overflow.
    int newRemainder = (int) (((long) r * num) % k);

    newDp[newRemainder] += dp[r];
}

            /*
             * newDp now contains ALL subarrays
             * ending at the current element.
             */
            dp = newDp;

            /*
             * Add these subarrays to the global result.
             */
            for (int r = 0; r < k; r++) {
                result[r] += dp[r];
            }
        }

        return result;
    }
}