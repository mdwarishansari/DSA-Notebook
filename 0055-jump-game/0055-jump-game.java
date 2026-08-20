class Solution {
    public boolean canJump(int[] nums) {

        // Farthest index that we can currently reach
        int farthest = 0;

        // Traverse every index
        for (int i = 0; i < nums.length; i++) {

            // If the current index is beyond our reachable range,
            // then we can never reach this index or anything after it.
            if (i > farthest) {
                return false;
            }

            // From index i, we can potentially reach i + nums[i].
            // Keep the maximum reach found so far.
            farthest = Math.max(farthest, i + nums[i]);

            // If our farthest reach has reached the last index,
            // we are guaranteed to succeed.
            if (farthest >= nums.length - 1) {
                return true;
            }
        }

        // Normally the method returns earlier,
        // but this keeps the method complete.
        return true;
    }
}