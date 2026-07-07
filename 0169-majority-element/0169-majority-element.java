class Solution {
    public int majorityElement(int[] nums) {

        // Stores the current majority candidate
        int candidate = 0;

        // Vote count for the candidate
        int count = 0;

        // Traverse the array
        for (int num : nums) {

            // If no candidate currently has votes,
            // choose the current number as candidate.
            if (count == 0) {
                candidate = num;
            }

            // Same as candidate -> gain one vote
            if (num == candidate) {
                count++;
            }
            // Different -> lose one vote
            else {
                count--;
            }
        }

        // Since the problem guarantees that
        // a majority element always exists,
        // the candidate is the answer.
        return candidate;
    }
}