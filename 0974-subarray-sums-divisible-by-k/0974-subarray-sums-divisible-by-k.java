import java.util.HashMap;

class Solution {

    public int subarraysDivByK(int[] nums, int k) {

        // HashMap stores:
        // Key   -> Remainder (prefixSum % k)
        // Value -> How many times this remainder has appeared
        HashMap<Integer, Integer> map = new HashMap<>();

        /*
         * Very Important:
         * Before starting, prefix sum = 0.
         * 0 % k = 0
         *
         * This helps count subarrays that start from index 0.
         *
         * Example:
         * nums = [5]
         * prefix = 5
         * rem = 0
         *
         * Since map already contains remainder 0 once,
         * we correctly count subarray [5].
         */
        map.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        // Traverse every element
        for (int num : nums) {

            // Calculate running prefix sum
            prefixSum += num;

            // Current remainder
            int rem = prefixSum % k;

            /*
             * Java can produce negative remainder.
             *
             * Example:
             * -1 % 5 = -1
             *
             * We convert it into positive remainder.
             *
             * -1 ---> 4
             */
            if (rem < 0) {
                rem += k;
            }

            /*
             * If this remainder has appeared before,
             * then every previous occurrence forms
             * one valid subarray.
             *
             * Example:
             * Current remainder = 4
             * Map contains:
             * 4 -> 3
             *
             * Then 3 different subarrays end here.
             */
            if (map.containsKey(rem)) {
                count += map.get(rem);
            }

            // Store current remainder frequency
            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }

        return count;
    }
}