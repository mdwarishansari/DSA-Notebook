
import java.util.Arrays;

class Solution {
    public int minMoves2(int[] nums) {

        // Step 1: Sort the array
        Arrays.sort(nums);

        // Step 2: Find the median element
        int median = nums[nums.length / 2];

        // Step 3: Calculate total moves
        long moves = 0;

        for (int num : nums) {

            // Calculate the absolute difference
            // between current element and median
            moves += Math.abs((long) num - median);
        }

        // Return the minimum number of moves
        return (int) moves;
    }
}
