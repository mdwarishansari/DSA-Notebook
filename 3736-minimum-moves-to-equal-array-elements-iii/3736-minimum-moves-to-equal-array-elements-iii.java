class Solution {
    public int minMoves(int[] nums) {
        int max = nums[0];

        // Find the maximum element
        for (int n : nums) {
            max = Math.max(max, n);
        }

        int moves = 0;

        // Calculate moves required for each element
        for (int n : nums) {
            moves += max - n;
        }

        return moves;
    }
}