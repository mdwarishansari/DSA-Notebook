class Solution {

    public int countSubstrings(String s) {

        /*
         * This will store the total number of palindromic
         * substrings found.
         */
        int count = 0;

        /*
         * Every character can be the center of an
         * odd-length palindrome.
         *
         * Example:
         *        a
         *       / \
         *      b   b
         *
         * Center = i
         */
        for (int i = 0; i < s.length(); i++) {

            /*
             * Odd-length palindromes.
             *
             * left and right start at the SAME character.
             *
             * Example:
             * "aba"
             *    ^
             *    i
             */
            count += expand(s, i, i);

            /*
             * Even-length palindromes.
             *
             * The center is BETWEEN two characters.
             *
             * Example:
             * "abba"
             *      ^
             *   center between b and b
             *
             * Therefore:
             * left = i
             * right = i + 1
             */
            count += expand(s, i, i + 1);
        }

        /*
         * Return the total number of palindromic substrings.
         */
        return count;
    }


    /*
     * Expands outward from the given center.
     *
     * left  -> left side of the palindrome
     * right -> right side of the palindrome
     *
     * Every time s[left] == s[right],
     * we have found ONE palindrome.
     */
    private int expand(String s, int left, int right) {

        // Number of palindromes found from this center
        int count = 0;

        /*
         * Keep expanding while:
         *
         * 1. left is inside the string
         * 2. right is inside the string
         * 3. characters on both sides are equal
         */
        while (left >= 0 &&
               right < s.length() &&
               s.charAt(left) == s.charAt(right)) {

            /*
             * The current [left ... right] is a palindrome.
             *
             * Therefore, increase the count.
             */
            count++;

            /*
             * Expand outward to see whether a larger
             * palindrome exists around the same center.
             */
            left--;
            right++;
        }

        /*
         * Return how many palindromic substrings were
         * discovered around this particular center.
         */
        return count;
    }
}