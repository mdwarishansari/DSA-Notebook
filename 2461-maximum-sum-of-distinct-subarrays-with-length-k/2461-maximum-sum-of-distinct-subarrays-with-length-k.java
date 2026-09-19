import java.util.HashMap;
import java.util.Map;

class Solution {

    public long maximumSubarraySum(int[] nums, int k) {

        /*
         * HashMap stores the frequency of each number
         * currently present inside the sliding window.
         *
         * Example:
         * window = [1, 5, 4]
         *
         * frequency:
         * 1 -> 1
         * 5 -> 1
         * 4 -> 1
         */
        Map<Integer, Integer> frequency = new HashMap<>();

        // Left boundary of our sliding window.
        int left = 0;

        // Sum of all elements currently inside the window.
        long windowSum = 0;

        // Maximum valid subarray sum found so far.
        long maxSum = 0;

        /*
         * 'right' expands the sliding window one element at a time.
         */
        for (int right = 0; right < nums.length; right++) {

            // Add the new element entering the window.
            windowSum += nums[right];

            // Increase its frequency in the HashMap.
            frequency.put(
                nums[right],
                frequency.getOrDefault(nums[right], 0) + 1
            );

            /*
             * If the window becomes larger than k,
             * remove elements from the left until its size
             * becomes exactly k again.
             */
            if (right - left + 1 > k) {

                // Element that is leaving the window.
                int removed = nums[left];

                // Remove its value from the window sum.
                windowSum -= removed;

                /*
                 * Decrease the frequency of the removed element.
                 */
                frequency.put(
                    removed,
                    frequency.get(removed) - 1
                );

                /*
                 * If its frequency becomes 0,
                 * completely remove it from the map.
                 */
                if (frequency.get(removed) == 0) {
                    frequency.remove(removed);
                }

                // Move the left pointer forward.
                left++;
            }

            /*
             * Now the window has exactly k elements.
             *
             * If frequency.size() == k, it means:
             *
             *     Number of elements in window = k
             *     Number of distinct elements   = k
             *
             * Therefore, every element is distinct.
             */
            if (right - left + 1 == k && frequency.size() == k) {

                // Update the maximum sum.
                maxSum = Math.max(maxSum, windowSum);
            }
        }

        // If no valid subarray exists, maxSum remains 0.
        return maxSum;
    }
}