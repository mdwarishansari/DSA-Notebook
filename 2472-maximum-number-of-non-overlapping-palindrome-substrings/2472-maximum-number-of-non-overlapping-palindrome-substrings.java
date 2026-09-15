class Solution {

    public int maxPalindromes(String s, int k) {

        int n = s.length();

        /*
         * dp[start][end] = true if s[start...end] is a palindrome.
         *
         * We build this table from smaller substrings to larger substrings.
         *
         * A substring is a palindrome when:
         * 1. Its two end characters are equal.
         * 2. The inside substring is also a palindrome.
         *
         * Example:
         * "abcba"
         *  ^   ^
         *  equal
         *  inside "bcb" is palindrome
         */
        boolean[][] dp = new boolean[n][n];

        // Every single character is a palindrome.
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        /*
         * Check substrings of length 2, 3, 4, ...
         */
        for (int len = 2; len <= n; len++) {

            for (int start = 0; start + len <= n; start++) {

                int end = start + len - 1;

                // First and last characters must match.
                if (s.charAt(start) == s.charAt(end)) {

                    // Length 2: "aa" is automatically a palindrome.
                    // Length > 2: inside must also be a palindrome.
                    if (len == 2 || dp[start + 1][end - 1]) {
                        dp[start][end] = true;
                    }
                }
            }
        }

        /*
         * Greedy strategy:
         *
         * We scan the string from left to right.
         *
         * Whenever we find a valid palindrome ending at the current
         * position, we select it immediately.
         *
         * Why?
         *
         * We want the MAXIMUM number of NON-OVERLAPPING substrings.
         *
         * Choosing the substring that finishes earliest leaves the
         * maximum possible space for future substrings.
         *
         * This is the same greedy idea used in interval scheduling:
         * always choose the interval with the earliest ending position.
         */

        int answer = 0;

        /*
         * 'lastEnd' stores the ending index of the last selected palindrome.
         *
         * Initially, nothing has been selected.
         */
        int lastEnd = -1;

        /*
         * 'end' represents the ending position of the palindrome
         * we are currently trying to select.
         */
        for (int end = 0; end < n; end++) {

            /*
             * A palindrome ending at 'end' must have at least k characters.
             *
             * Therefore:
             *
             * start <= end - k + 1
             *
             * It also cannot overlap the previously selected palindrome:
             *
             * start > lastEnd
             */
            int earliestPossibleStart = lastEnd + 1;
            int latestPossibleStart = end - k + 1;

            /*
             * If latestPossibleStart < earliestPossibleStart,
             * there isn't enough space for a substring of length k.
             */
            if (latestPossibleStart < earliestPossibleStart) {
                continue;
            }

            /*
             * Look for ANY palindrome ending at 'end'
             * whose length is at least k and which does not overlap
             * the previously selected palindrome.
             *
             * We don't care which start position we choose here.
             *
             * Once we have a valid palindrome ending at 'end',
             * we select it because 'end' is the earliest possible
             * ending position currently available.
             */
            boolean found = false;

            for (int start = earliestPossibleStart;
                 start <= latestPossibleStart;
                 start++) {

                if (dp[start][end]) {

                    /*
                     * We found a palindrome:
                     *
                     * s[start ... end]
                     *
                     * Its length is automatically >= k because
                     * start <= end - k + 1.
                     */
                    answer++;

                    // This palindrome is now the last selected one.
                    lastEnd = end;

                    found = true;

                    /*
                     * We can stop searching starts.
                     *
                     * The important thing is that we found a valid
                     * palindrome ending at the earliest possible 'end'.
                     */
                    break;
                }
            }

            // If found == true, the next palindrome must start after 'end'.
            // The loop naturally handles that using lastEnd.
        }

        return answer;
    }
}