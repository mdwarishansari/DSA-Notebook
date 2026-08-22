class Solution {
    public int hammingWeight(int n) {

        // Stores the number of 1 bits.
        int count = 0;

        // Continue until all 1 bits have been removed.
        while (n != 0) {

            /*
             * n & (n - 1) removes the rightmost 1 bit.
             *
             * Example:
             *
             * n     = 1011
             * n - 1 = 1010
             *
             * 1011
             * &
             * 1010
             * ----
             * 1010
             *
             * One '1' has been removed.
             */
            n = n & (n - 1);

            // We successfully removed one 1 bit.
            count++;
        }

        // Return the total number of 1 bits.
        return count;
    }
}