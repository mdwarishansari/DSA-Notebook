class Solution {

    public String minWindow(String s, String t) {

        // If t is longer than s, it is impossible
        // for s to contain all characters of t.
        if (t.length() > s.length()) {
            return "";
        }

        /*
         * need[c] = how many times character c is required
         * according to string t.
         *
         * ASCII has 128 characters, which is enough here
         * because the problem only contains English letters.
         */
        int[] need = new int[128];

        for (char ch : t.toCharArray()) {
            need[ch]++;
        }

        /*
         * required = number of DISTINCT characters that
         * must be completely satisfied.
         *
         * Example:
         * t = "AABC"
         *
         * required = 3
         * because we need A, B and C.
         */
        int required = 0;

        for (int i = 0; i < 128; i++) {
            if (need[i] > 0) {
                required++;
            }
        }

        /*
         * window[c] = frequency of character c
         * inside the current sliding window.
         */
        int[] window = new int[128];

        // Number of distinct required characters currently satisfied.
        int formed = 0;

        // Left boundary of the sliding window.
        int left = 0;

        // Right boundary of the sliding window.
        int right = 0;

        /*
         * Store the best answer found so far.
         *
         * bestLen = length of smallest valid window.
         * Start with a very large value.
         */
        int bestLen = Integer.MAX_VALUE;
        int bestLeft = 0;

        /*
         * Expand the window by moving right.
         */
        while (right < s.length()) {

            // Add the current character to the window.
            char ch = s.charAt(right);
            window[ch]++;

            /*
             * If the current character has now reached
             * exactly the frequency required by t,
             * one more required character type is satisfied.
             *
             * Example:
             * need['A'] = 2
             *
             * window['A'] becomes 2
             * => A is now satisfied.
             */
            if (need[ch] > 0 && window[ch] == need[ch]) {
                formed++;
            }

            /*
             * If all required character types are satisfied,
             * the current window is valid.
             *
             * Now try to shrink it from the left to find
             * the smallest possible valid window.
             */
            while (formed == required) {

                /*
                 * Current window is:
                 *
                 * [left ... right]
                 *
                 * Check whether it is smaller than
                 * our previously recorded answer.
                 */
                int currentLen = right - left + 1;

                if (currentLen < bestLen) {
                    bestLen = currentLen;
                    bestLeft = left;
                }

                /*
                 * Remove the leftmost character.
                 */
                char leftChar = s.charAt(left);
                window[leftChar]--;

                /*
                 * If removing this character causes its
                 * frequency to become smaller than required,
                 * the window is no longer valid.
                 *
                 * Example:
                 *
                 * need['A'] = 2
                 * window['A'] = 2
                 *
                 * Remove one A:
                 *
                 * window['A'] = 1
                 *
                 * Now we don't have enough A's, so
                 * this required character is no longer satisfied.
                 */
                if (need[leftChar] > 0
                        && window[leftChar] < need[leftChar]) {

                    formed--;
                }

                // Move the left boundary forward.
                left++;
            }

            // Expand the window from the right.
            right++;
        }

        /*
         * If bestLen was never updated, no valid window exists.
         */
        if (bestLen == Integer.MAX_VALUE) {
            return "";
        }

        /*
         * Return the substring starting at bestLeft
         * having length bestLen.
         */
        return s.substring(bestLeft, bestLeft + bestLen);
    }
}