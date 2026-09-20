class Solution {

    public int findMaxConsecutiveOnes(int[] nums) {

        // Stores the number of consecutive 1s
        // in the current sequence.
        int current = 0;

        // Stores the maximum number of consecutive 1s
        // found anywhere in the array.
        int max = 0;

        // Traverse the entire array
        for (int i = 0; i < nums.length; i++) {

            // If the current element is 1,
            // extend the current consecutive sequence.
            if (nums[i] == 1) {
                current++;
            }

            // If the current element is 0,
            // the consecutive sequence is broken.
            else {
                current = 0;
            }

            // Update the maximum streak found so far.
            max = Math.max(max, current);
        }

        // Return the longest consecutive sequence of 1s.
        return max;
    }
}