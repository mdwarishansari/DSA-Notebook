class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        /*
         * Store the input string in a separate variable.
         * This is not needed for the algorithm itself.
         */
        String calendrix = s;

        int n = s.length();

        /*
         * Count the frequency of every character.
         *
         * freq[0] -> number of 'a'
         * freq[1] -> number of 'b'
         * ...
         * freq[25] -> number of 'z'
         */
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        /*
         * For a string to be rearranged into a palindrome:
         *
         * - If n is even, every character must occur an even
         *   number of times.
         *
         * - If n is odd, exactly one character can occur an
         *   odd number of times.
         *
         * We find that possible middle character here.
         */
        char middle = 0;

        for (int i = 0; i < 26; i++) {

            if (freq[i] % 2 == 1) {

                // We already found another odd-frequency character.
                // Therefore, forming a palindrome is impossible.
                if (middle != 0) {
                    return "";
                }

                middle = (char) ('a' + i);
            }
        }

        /*
         * A palindrome looks like:
         *
         *      LEFT + MIDDLE + reverse(LEFT)
         *
         * Therefore, we only need to decide the LEFT half.
         *
         * Every character can contribute half of its frequency
         * to the left half.
         *
         * Example:
         *
         * s = "aabb"
         *
         * frequencies:
         * a -> 2
         * b -> 2
         *
         * left half:
         * a -> 1
         * b -> 1
         */
        int[] halfCount = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCount[i] = freq[i] / 2;
        }

        /*
         * Number of characters that the left half must contain.
         *
         * For:
         * n = 5 -> left length = 2
         * n = 6 -> left length = 3
         */
        int halfLength = n / 2;

        /*
         * This will contain the left half of our answer.
         */
        StringBuilder left = new StringBuilder();

        /*
         * Build the left half from LEFT -> RIGHT.
         *
         * At every position, we try characters from 'a' to 'z'.
         *
         * Why?
         *
         * We want the lexicographically SMALLEST palindrome
         * that is strictly greater than target.
         *
         * Therefore, we should always try the smallest possible
         * character first.
         */
        for (int pos = 0; pos < halfLength; pos++) {

            boolean found = false;

            /*
             * Try every possible character in increasing order.
             */
            for (int c = 0; c < 26; c++) {

                /*
                 * We cannot use a character if none are left
                 * for the left half.
                 */
                if (halfCount[c] == 0) {
                    continue;
                }

                /*
                 * Temporarily choose this character.
                 */
                halfCount[c]--;
                left.append((char) ('a' + c));

                /*
                 * Now we need to determine:
                 *
                 * "Can this choice eventually produce a palindrome
                 * that is greater than target?"
                 *
                 * To answer that, construct the LARGEST palindrome
                 * possible with the remaining characters.
                 *
                 * If even that largest palindrome is not greater
                 * than target, then this choice can never work.
                 */
                String maximum = buildMaximum(
                    left,
                    halfCount,
                    middle
                );

                if (maximum.compareTo(target) > 0) {

                    /*
                     * Great!
                     *
                     * There exists at least one valid palindrome
                     * starting with our current prefix.
                     *
                     * Since we tried characters from 'a' to 'z',
                     * this is the smallest character that can work.
                     */
                    found = true;
                    break;

                } else {

                    /*
                     * This character cannot lead to a valid answer.
                     *
                     * Undo our temporary choice and try the
                     * next character.
                     */
                    left.deleteCharAt(left.length() - 1);
                    halfCount[c]++;
                }
            }

            /*
             * If no character works at this position,
             * then no valid palindrome exists.
             */
            if (!found) {
                return "";
            }
        }

        /*
         * We now have the correct left half.
         *
         * Construct the complete palindrome:
         *
         *       LEFT + MIDDLE + reverse(LEFT)
         */
        StringBuilder answer = new StringBuilder();

        // Add the left half.
        answer.append(left);

        // Add the middle character if the length is odd.
        if (middle != 0) {
            answer.append(middle);
        }

        // Add the reversed left half.
        for (int i = left.length() - 1; i >= 0; i--) {
            answer.append(left.charAt(i));
        }

        String result = answer.toString();

        /*
         * Final safety check:
         *
         * The result must be STRICTLY greater than target.
         *
         * compareTo() returns:
         *
         * > 0  -> result is greater
         * = 0  -> result is equal
         * < 0  -> result is smaller
         */
        return result.compareTo(target) > 0 ? result : "";
    }


    /*
     * ---------------------------------------------------------
     * buildMaximum()
     * ---------------------------------------------------------
     *
     * Creates the LARGEST palindrome possible using:
     *
     *     1. The already selected prefix
     *     2. The remaining characters
     *     3. The possible middle character
     *
     * Why do we need the largest palindrome?
     *
     * Suppose we temporarily choose 'a' at the current position.
     *
     * If the LARGEST palindrome we can create after choosing
     * 'a' is still <= target, then there is absolutely no way
     * that choosing 'a' can produce a valid answer.
     *
     * Therefore we can safely reject 'a'.
     */
    private String buildMaximum(
        StringBuilder prefix,
        int[] halfCount,
        char middle
    ) {

        /*
         * Start with the prefix that we already fixed.
         */
        StringBuilder left = new StringBuilder(prefix);

        /*
         * To make the palindrome as large as possible,
         * put the largest available characters first.
         *
         * Example:
         *
         * remaining:
         * a -> 1
         * b -> 2
         * d -> 1
         *
         * Largest possible left half:
         *
         * "dbba"
         */
        for (int c = 25; c >= 0; c--) {

            /*
             * halfCount[c] tells us how many copies of
             * this character are still available.
             */
            for (int k = 0; k < halfCount[c]; k++) {

                left.append((char) ('a' + c));
            }
        }

        /*
         * Now create the complete palindrome.
         *
         * Example:
         *
         * left   = "dbba"
         * middle = none
         *
         * result = "dbbaabbd"
         */
        StringBuilder palindrome = new StringBuilder();

        // Left half
        palindrome.append(left);

        // Middle character (only for odd length)
        if (middle != 0) {
            palindrome.append(middle);
        }

        // Reverse of left half
        for (int i = left.length() - 1; i >= 0; i--) {
            palindrome.append(left.charAt(i));
        }

        return palindrome.toString();
    }
}