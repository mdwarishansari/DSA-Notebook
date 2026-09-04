import java.util.HashMap;
import java.util.Map;

class Solution {

    public long maximumSubarraySum(int[] nums, int k) {

        // Stores the frequency of each element
        // inside the current sliding window.
        //
        // Example:
        // [9, 9, 1] -> {9=2, 1=1}
        Map<Integer, Integer> frequency = new HashMap<>();

        // Sum of elements in the current window.
        // long is used because the answer can be larger
        // than the range of int.
        long windowSum = 0;

        // Maximum sum among all valid windows.
        long maxSum = 0;

        // 'right' expands the sliding window.
        for (int right = 0; right < nums.length; right++) {

            // -------------------------------------------------
            // STEP 1: Add the new element to the window
            // -------------------------------------------------

            windowSum += nums[right];

            // Increase the frequency of nums[right].
            frequency.put(
                nums[right],
                frequency.getOrDefault(nums[right], 0) + 1
            );

            // -------------------------------------------------
            // STEP 2: If window size becomes greater than k,
            // remove the leftmost element.
            // -------------------------------------------------

            if (right >= k) {

                // This is the element leaving the window.
                int leftElement = nums[right - k];

                // Remove its value from the running sum.
                windowSum -= leftElement;

                // Get its current frequency.
                int count = frequency.get(leftElement);

                if (count == 1) {

                    // This was the last occurrence of the
                    // element in the window, so remove it
                    // completely from the map.
                    frequency.remove(leftElement);

                } else {

                    // Another occurrence still exists,
                    // so only decrease its frequency.
                    frequency.put(leftElement, count - 1);
                }
            }

            // -------------------------------------------------
            // STEP 3: Check the window
            // -------------------------------------------------

            /*
             * Once right >= k - 1, the window contains
             * exactly k elements.
             *
             * If frequency.size() == k, then all k elements
             * are distinct.
             */
            if (right >= k - 1 && frequency.size() == k) {

                // Update the maximum valid window sum.
                maxSum = Math.max(maxSum, windowSum);
            }
        }

        return maxSum;
    }
}