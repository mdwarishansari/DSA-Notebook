import java.util.HashMap;

class Solution {

    public int subarraysDivByK(int[] nums, int k) {

        // Stores:
        // remainder -> number of times we have seen it
        HashMap<Integer, Integer> map = new HashMap<>();

        // Prefix sum = 0 exists before the array starts.
        //
        // This helps us count subarrays that start
        // from index 0.
        map.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {

            // Build the running prefix sum
            prefixSum += num;

            // Find the remainder when prefix sum
            // is divided by k.
            int remainder = prefixSum % k;

            // Java can produce negative remainders.
            // Convert it into the range [0, k - 1].
            if (remainder < 0) {
                remainder += k;
            }

            // If we have seen this remainder before,
            // every previous occurrence creates
            // one valid subarray ending here.
            if (map.containsKey(remainder)) {

                count += map.get(remainder);
            }

            // Record this occurrence of the remainder.
            map.put(
                remainder,
                map.getOrDefault(remainder, 0) + 1
            );
        }

        return count;
    }
}