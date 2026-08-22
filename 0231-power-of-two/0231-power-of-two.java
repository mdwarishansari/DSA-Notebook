class Solution {
    public boolean isPowerOfTwo(int n) {

        /*
         * A positive power of 2 has exactly one '1'
         * bit in its binary representation.
         *
         * For example:
         *
         * 8  = 1000
         * 7  = 0111
         *
         * 1000
         * &
         * 0111
         * ----
         * 0000
         *
         * Therefore, n & (n - 1) == 0 means
         * n has only one set bit.
         *
         * We also need n > 0 because 0 is not
         * a power of two.
         */
        return n > 0 && (n & (n - 1)) == 0;
    }
}