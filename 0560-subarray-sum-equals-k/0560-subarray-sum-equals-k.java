import java.util.HashMap;

class Solution {

    public int subarraySum(int[] nums, int k) {

        // Stores:
        // prefixSum -> number of times it has appeared
        HashMap<Integer, Integer> map = new HashMap<>();

        // Important:
        // Before processing any element, prefix sum is 0.
        //
        // We have seen this prefix sum once.
        map.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {

            // Calculate current prefix sum
            prefixSum += num;

            // We need a previous prefix sum such that:
            //
            // currentPrefixSum - previousPrefixSum = k
            //
            // Therefore:
            //
            // previousPrefixSum = currentPrefixSum - k
            int required = prefixSum - k;

            // If this required prefix sum existed before,
            // every occurrence creates one valid subarray.
            if (map.containsKey(required)) {
                count += map.get(required);
            }

            // Store the current prefix sum.
            //
            // If it already exists, increase its frequency.
            map.put(
                prefixSum,
                map.getOrDefault(prefixSum, 0) + 1
            );
        }

        return count;
    }
}