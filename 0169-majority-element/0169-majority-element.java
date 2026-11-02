
class Solution {
    public int majorityElement(int[] nums) {

        // Stores the potential majority element
        int candidate = 0;

        // Tracks the voting balance
        int count = 0;

        // Traverse every element in the array
        for (int num : nums) {

            // If count becomes zero, select a new candidate
            if (count == 0) {
                candidate = num;
            }

            // Same element supports the candidate
            if (num == candidate) {
                count++;
            }

            // Different element cancels one vote
            else {
                count--;
            }
        }

        // Return the majority element
        return candidate;
    }
}
