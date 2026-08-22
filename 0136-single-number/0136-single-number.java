class Solution {
    public int singleNumber(int[] nums) {

        // Start with 0 because 0 ^ x = x.
        int result = 0;

        // XOR every number in the array.
        for (int num : nums) {

            // Duplicate numbers cancel each other:
            // x ^ x = 0
            //
            // The number that appears only once
            // will remain in result.
            result ^= num;
        }

        // Return the number that appeared only once.
        return result;
    }
}