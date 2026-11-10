class Solution {
    public boolean isPalindrome(int x) {
        // Special cases:
        // 1. Negative numbers cannot be palindromes due to the leading '-' sign (e.g., -121 != 121-).
        // 2. Numbers ending in 0 (other than 0 itself) cannot be palindromes 
        //    because the first digit cannot be 0 (e.g., 10 != 01).
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;

        // Revert only the second half of the number to prevent integer overflow.
        // When x <= reversedHalf, we have reached or crossed the middle digit.
        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + (x % 10);
            x /= 10;
        }

        // For even-length numbers: x == reversedHalf (e.g., 1221 -> x = 12, reversedHalf = 12)
        // For odd-length numbers: x == reversedHalf / 10 (middle digit discarded, e.g., 12321 -> x = 12, reversedHalf = 123)
        return x == reversedHalf || x == reversedHalf / 10;
    }
}