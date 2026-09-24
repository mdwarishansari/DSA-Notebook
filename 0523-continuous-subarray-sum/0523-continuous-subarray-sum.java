import java.util.*;

class Solution {

    public boolean checkSubarraySum(int[] nums, int k) {

        // Stores:
        // remainder -> first index where this remainder appeared
        HashMap<Integer, Integer> map = new HashMap<>();

        // Remainder 0 is considered to appear
        // before the array starts.
        //
        // This helps us detect subarrays that start
        // from index 0.
        map.put(0, -1);

        int prefixSum = 0;

        for (int i = 0; i < nums.length; i++) {

            // Add current element to the running prefix sum
            prefixSum += nums[i];

            // We only care about the remainder
            // when dividing the prefix sum by k.
            int remainder = prefixSum % k;

            // Java can produce a negative remainder
            // when prefixSum is negative.
            // Convert it into the range [0, k-1].
            if (remainder < 0) {
                remainder += k;
            }

            // Have we seen this remainder before?
            if (map.containsKey(remainder)) {

                // Get the FIRST index where we saw
                // this remainder.
                int previousIndex = map.get(remainder);

                // Length of the subarray between
                // previousIndex and current index.
                int length = i - previousIndex;

                // The problem requires at least
                // 2 elements.
                if (length >= 2) {
                    return true;
                }

            } else {

                // Store only the FIRST occurrence.
                //
                // Keeping the earliest index gives us
                // the longest possible subarray later.
                map.put(remainder, i);
            }
        }

        // No valid subarray was found.
        return false;
    }
}