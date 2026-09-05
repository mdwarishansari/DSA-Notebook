import java.util.HashMap;

class Solution {

    public int subarraysWithKDistinct(int[] nums, int k) {

        // Exactly K distinct
        // = At most K distinct - At most (K - 1) distinct
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }

    // Counts subarrays having AT MOST k distinct integers.
    private int atMostK(int[] nums, int k) {

        // No non-empty subarray can have
        // at most 0 distinct integers.
        if (k == 0) {
            return 0;
        }

        // Stores:
        // number -> frequency inside current window
        HashMap<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int count = 0;

        for (int right = 0; right < nums.length; right++) {

            // Add nums[right] to the window.
            map.put(
                nums[right],
                map.getOrDefault(nums[right], 0) + 1
            );

            // If the window contains more than k
            // distinct numbers, shrink it.
            while (map.size() > k) {

                int leftValue = nums[left];

                // Remove one occurrence of nums[left].
                map.put(
                    leftValue,
                    map.get(leftValue) - 1
                );

                // If frequency becomes 0,
                // this number is no longer in the window.
                if (map.get(leftValue) == 0) {
                    map.remove(leftValue);
                }

                left++;
            }

            // Current window:
            //
            // [left ........ right]
            //
            // Every subarray ending at 'right'
            // and starting from left to right
            // has at most k distinct integers.
            //
            // Number of such subarrays:
            //
            // right - left + 1
            count += right - left + 1;
        }

        return count;
    }
}