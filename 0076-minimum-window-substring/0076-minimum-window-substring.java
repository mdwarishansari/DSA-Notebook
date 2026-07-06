class Solution {

    public String minWindow(String s, String t) {

        // Impossible case
        if (s.length() < t.length())
            return "";

        // Frequency array.
        // count[c] tells how many of character c
        // are still needed.
        int[] count = new int[128];

        // Build frequency of pattern string.
        for (char c : t.toCharArray()) {
            count[c]++;
        }

        // Left pointer
        int i = 0;

        // Right pointer
        int j = 0;

        // Total required characters still missing.
        int required = t.length();

        // Stores best answer found.
        int minLen = Integer.MAX_VALUE;

        // Starting index of best answer.
        int start = 0;

        // Expand window.
        while (j < s.length()) {

            // Character entering window.
            char right = s.charAt(j);

            /*
             * Include current character.
             *
             * Reduce its required frequency.
             *
             * Example:
             * Need A=1
             * After taking A
             * Need A=0
             */
            count[right]--;

            /*
             * If frequency is still >=0,
             * this character was actually needed.
             *
             * Therefore one required character
             * has now been matched.
             */
            if (count[right] >= 0) {
                required--;
            }

            /*
             * If required becomes zero,
             * current window contains every
             * character of t.
             *
             * Now try making window smaller.
             */
            while (required == 0) {

                // Update minimum answer.
                if (j - i + 1 < minLen) {
                    minLen = j - i + 1;
                    start = i;
                }

                // Left character leaving window.
                char left = s.charAt(i);

                /*
                 * Restore frequency because
                 * character leaves window.
                 */
                count[left]++;

                /*
                 * If frequency becomes positive,
                 * window now misses one required
                 * character.
                 *
                 * Window becomes invalid.
                 */
                if (count[left] > 0) {
                    required++;
                }

                // Shrink window.
                i++;
            }

            // Expand window.
            j++;
        }

        // If no answer found.
        if (minLen == Integer.MAX_VALUE)
            return "";

        return s.substring(start, start + minLen);
    }
}