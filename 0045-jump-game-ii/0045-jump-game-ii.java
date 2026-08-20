class Solution {
    public int jump(int[] nums) {

        // Number of jumps needed
        int jumps = 0;

        // Farthest index we can reach using the current number of jumps
        int currentEnd = 0;

        // Farthest index we can reach with one additional jump
        int farthest = 0;

        // We don't need to process the last index.
        // We only need to reach it.
        for (int i = 0; i < nums.length - 1; i++) {

            // Find the farthest position we can reach
            // from all indices in the current range.
            farthest = Math.max(farthest, i + nums[i]);

            // We have reached the end of the current jump range.
            if (i == currentEnd) {

                // We must make another jump.
                jumps++;

                // The next jump can take us as far as 'farthest'.
                currentEnd = farthest;
            }
        }

        return jumps;
    }
}