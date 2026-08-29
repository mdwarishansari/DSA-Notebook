import java.util.*;

class Solution {

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        /*
         * Create pairs:
         *   [value, originalIndex]
         *
         * We sort these pairs by value.
         *
         * Example:
         * nums = [1, 7, 6, 18, 2, 1]
         *
         * Sorted:
         * [1,0], [1,5], [2,4], [6,2], [7,1], [18,3]
         */
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i]; // value
            arr[i][1] = i;       // original index
        }

        // Sort according to the value.
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        /*
         * After sorting by value, elements belong to the same
         * connected group if every consecutive difference is
         * <= limit.
         *
         * Example:
         *
         * values = [1, 2, 6, 7, 18]
         * limit = 3
         *
         * 1 -> 2  difference = 1  => same group
         * 2 -> 6  difference = 4  => new group
         * 6 -> 7  difference = 1  => same group
         * 7 -> 18 difference = 11 => new group
         *
         * Groups:
         * [1, 2]
         * [6, 7]
         * [18]
         */

        int start = 0;

        while (start < n) {

            int end = start;

            /*
             * Find the complete connected group.
             *
             * As long as the difference between consecutive
             * values is <= limit, they can be connected through
             * a sequence of valid swaps.
             */
            while (end + 1 < n &&
                   arr[end + 1][0] - arr[end][0] <= limit) {
                end++;
            }

            /*
             * Now arr[start ... end] is one connected component.
             *
             * We have:
             *   - values already sorted because arr is sorted
             *   - indices in arbitrary order
             *
             * To make the final array lexicographically smallest,
             * we should put the smallest value at the smallest
             * original index.
             */

            // Extract the original indices of this group.
            int size = end - start + 1;
            int[] indices = new int[size];

            for (int i = 0; i < size; i++) {
                indices[i] = arr[start + i][1];
            }

            // Sort indices so we process positions from left to right.
            Arrays.sort(indices);

            /*
             * arr[start ... end] is already sorted by value.
             *
             * Therefore:
             *
             * smallest value -> smallest index
             * second smallest -> second smallest index
             * ...
             *
             * This produces the lexicographically smallest result.
             */
            for (int i = 0; i < size; i++) {
                nums[indices[i]] = arr[start + i][0];
            }

            // Move to the next group.
            start = end + 1;
        }

        return nums;
    }
}