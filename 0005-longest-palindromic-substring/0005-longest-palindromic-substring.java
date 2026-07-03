class Solution {

    public String longestPalindrome(String s) {

        // If the string is empty or contains only one character,
        // it is already the longest palindrome.
        if (s == null || s.length() < 2) {
            return s;
        }

        // Stores the starting and ending index of the
        // longest palindrome found so far.
        int start = 0;
        int end = 0;

        // Every palindrome has a center.
        // Try every index as a possible center.
        for (int i = 0; i < s.length(); i++) {

            /*
             * Check odd-length palindrome.
             *
             * Example:
             *      "racecar"
             *          ^
             *       Center = e
             */
            int len1 = expand(s, i, i);

            /*
             * Check even-length palindrome.
             *
             * Example:
             *      "abba"
             *         ^^
             *     Center is between two characters.
             */
            int len2 = expand(s, i, i + 1);

            // Choose the longer palindrome from the two cases.
            int len = Math.max(len1, len2);

            /*
             * Current longest palindrome length
             * = end - start + 1
             *
             * If the new palindrome is longer,
             * update the answer.
             */
            if (len > end - start + 1) {

                /*
                 * expand() only returns the length.
                 *
                 * We already know:
                 *      Center = i
                 *      Length = len
                 *
                 * Convert (center + length)
                 * into (start index + end index).
                 *
                 * This formula works for BOTH:
                 *
                 * Odd palindrome:
                 *      bab
                 *
                 * Even palindrome:
                 *      abba
                 */
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        /*
         * substring(start, end)
         * excludes the ending index.
         *
         * Therefore use end + 1.
         */
        return s.substring(start, end + 1);
    }

    /*
     * Expands around the given center and
     * returns the length of the palindrome.
     *
     * left == right     -> Odd palindrome
     * left + 1 == right -> Even palindrome
     */
    private int expand(String s, int left, int right) {

        /*
         * Keep expanding while:
         *
         * 1. Left pointer is inside the string.
         * 2. Right pointer is inside the string.
         * 3. Characters are equal.
         */
        while (left >= 0 &&
               right < s.length() &&
               s.charAt(left) == s.charAt(right)) {

            // Expand one character outward.
            left--;
            right++;
        }

        /*
         * The loop stops AFTER moving one step
         * outside the palindrome.
         *
         * Example:
         *
         *      abba
         *
         * Valid palindrome:
         *      0 1 2 3
         *
         * After expansion:
         *      left = -1
         *      right = 4
         *
         * Actual length:
         *
         *      right - left - 1
         */
        return right - left - 1;
    }
}