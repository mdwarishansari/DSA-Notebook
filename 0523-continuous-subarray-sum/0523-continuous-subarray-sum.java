import java.util.HashMap;

class Solution {

    public boolean checkSubarraySum(int[] nums, int k) {

        /*
         * Map:
         *
         * remainder -> first index where this remainder occurred
         *
         * We store the FIRST occurrence because we want the
         * subarray to have at least 2 elements.
         */
        HashMap<Integer, Integer> map = new HashMap<>();

        /*
         * Important:
         *
         * We put remainder 0 at index -1.
         *
         * This handles cases where the subarray starts
         * from index 0.
         *
         * Example:
         *
         * nums = [6, 2]
         * k = 6
         *
         * prefix sum at index 0 = 6
         * 6 % 6 = 0
         *
         * index - (-1) = 1
         *
         * So we correctly get a subarray length of 2
         * when reaching index 1 if appropriate.
         */
        map.put(0, -1);

        /*
         * Running prefix sum.
         */
        int prefixSum = 0;

        for (int i = 0; i < nums.length; i++) {

            /*
             * Add current element to prefix sum.
             */
            prefixSum += nums[i];

            /*
             * We only care about the remainder when
             * dividing by k.
             */
            int remainder = prefixSum % k;

            /*
             * Java can produce a negative remainder when
             * nums contains negative numbers.
             *
             * Convert it to a positive remainder.
             */
            if (remainder < 0) {
                remainder += k;
            }

            /*
             * Have we seen this remainder before?
             */
            if (map.containsKey(remainder)) {

                /*
                 * Get the FIRST index where this remainder
                 * appeared.
                 */
                int previousIndex = map.get(remainder);

                /*
                 * If the distance between the two indices
                 * is at least 2, then the subarray contains
                 * at least 2 elements.
                 *
                 * Because:
                 *
                 * prefixSum[i] % k
                 * ==
                 * prefixSum[previousIndex] % k
                 *
                 * Therefore:
                 *
                 * (prefixSum[i] - prefixSum[previousIndex]) % k
                 * == 0
                 *
                 * So the subarray sum is divisible by k.
                 */
                if (i - previousIndex >= 2) {
                    return true;
                }

            } else {

                /*
                 * Store ONLY the first occurrence.
                 *
                 * We don't update it later because the earlier
                 * index gives us a longer possible subarray.
                 */
                map.put(remainder, i);
            }
        }

        /*
         * No valid subarray was found.
         */
        return false;
    }
}