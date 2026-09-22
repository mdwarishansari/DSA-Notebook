import java.util.HashMap;
import java.util.Map;

class Solution {

    public int subarraysWithKDistinct(int[] nums, int k) {

        /*
         * exactly K distinct
         *
         * = at most K distinct
         *   - at most (K - 1) distinct
         *
         * Example:
         *
         * atMost(2) contains:
         *     1 distinct
         *     2 distinct
         *
         * atMost(1) contains:
         *     1 distinct
         *
         * Subtracting removes all 1-distinct subarrays,
         * leaving only exactly 2-distinct subarrays.
         */
        return countAtMost(nums, k)
             - countAtMost(nums, k - 1);
    }


    /*
     * Returns the number of subarrays containing
     * AT MOST k distinct integers.
     */
    private int countAtMost(int[] nums, int k) {

        /*
         * If k == 0, no non-empty subarray can have
         * at most 0 distinct integers.
         */
        if (k == 0) {
            return 0;
        }

        /*
         * frequency stores how many times each number
         * occurs inside the current sliding window.
         *
         * Example:
         *
         * window = [1, 2, 1]
         *
         * frequency:
         * 1 -> 2
         * 2 -> 1
         */
        Map<Integer, Integer> frequency = new HashMap<>();

        int left = 0;

        /*
         * Number of different integers currently
         * present in the window.
         */
        int distinct = 0;

        /*
         * Total number of valid subarrays.
         *
         * Use long internally because the number of
         * subarrays can be large.
         */
        long count = 0;

        /*
         * Expand the window using right.
         */
        for (int right = 0; right < nums.length; right++) {

            int value = nums[right];

            /*
             * If this value was not already inside
             * the window, we found a new distinct integer.
             */
            if (!frequency.containsKey(value)) {
                distinct++;
            }

            /*
             * Add the current value to the frequency map.
             */
            frequency.put(
                value,
                frequency.getOrDefault(value, 0) + 1
            );


            /*
             * If the window has more than k distinct
             * integers, it is invalid.
             *
             * Shrink the window from the left until
             * it becomes valid again.
             */
            while (distinct > k) {

                int leftValue = nums[left];

                /*
                 * Remove one occurrence of nums[left].
                 */
                frequency.put(
                    leftValue,
                    frequency.get(leftValue) - 1
                );

                /*
                 * If its frequency becomes zero,
                 * that number no longer exists inside
                 * the current window.
                 *
                 * Therefore distinct decreases.
                 */
                if (frequency.get(leftValue) == 0) {
                    frequency.remove(leftValue);
                    distinct--;
                }

                /*
                 * Move the left boundary forward.
                 */
                left++;
            }


            /*
             * At this point:
             *
             * nums[left ... right]
             *
             * contains AT MOST k distinct integers.
             *
             * Therefore every subarray ending at `right`
             * and starting anywhere from `left` to `right`
             * is also valid.
             *
             * Starting positions:
             *
             * left, left+1, ..., right
             *
             * Number of choices:
             *
             * right - left + 1
             *
             * Example:
             *
             * left = 2
             * right = 5
             *
             * Valid subarrays ending at 5:
             *
             * [2 ... 5]
             * [3 ... 5]
             * [4 ... 5]
             * [5 ... 5]
             *
             * Total = 5 - 2 + 1 = 4
             */
            count += right - left + 1;
        }

        return (int) count;
    }
}