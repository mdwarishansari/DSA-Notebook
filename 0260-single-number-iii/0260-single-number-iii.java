class Solution {
    public int[] singleNumber(int[] nums) {

        // Step 1:
        // XOR all numbers.
        //
        // Every duplicate cancels:
        // a ^ a = 0
        //
        // So xor contains:
        // unique1 ^ unique2
        int xor = 0;

        for (int num : nums) {
            xor ^= num;
        }

        // Step 2:
        // Find a bit where the two unique numbers are different.
        //
        // xor has 1 at every bit where unique1 and unique2 differ.
        //
        // xor & -xor extracts the RIGHTMOST set bit.
        int differentBit = xor & -xor;

        // These will store the two unique numbers.
        int num1 = 0;
        int num2 = 0;

        // Step 3:
        // Divide numbers into two groups based on differentBit.
        //
        // Group 1: bit is 0
        // Group 2: bit is 1
        //
        // Every duplicate pair goes into the same group,
        // so they cancel each other using XOR.
        for (int num : nums) {

            if ((num & differentBit) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }

        // The two unique numbers are now separated.
        return new int[]{num1, num2};
    }
}