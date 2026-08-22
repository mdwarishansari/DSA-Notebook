class Solution {
    public int longestPalindromeSubseq(String s) {

        int n = s.length();

        // dp[i][j] represents the length of the
        // longest palindromic subsequence in s[i...j].
        int[][] dp = new int[n][n];

        // A single character is always a palindrome
        // of length 1.
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        /*
         * We need to calculate smaller ranges first
         * because dp[i][j] depends on:
         *
         * dp[i + 1][j - 1]
         * dp[i + 1][j]
         * dp[i][j - 1]
         *
         * So i moves from right to left.
         */
        for (int i = n - 2; i >= 0; i--) {

            // j must be to the right of i.
            for (int j = i + 1; j < n; j++) {

                // If both characters are equal,
                // they can become the two ends
                // of our palindrome.
                if (s.charAt(i) == s.charAt(j)) {

                    dp[i][j] = dp[i + 1][j - 1] + 2;

                } else {

                    /*
                     * Characters are different.
                     *
                     * We cannot use both as matching ends.
                     * So try:
                     *
                     * 1. Skip s[i]
                     * 2. Skip s[j]
                     *
                     * Take whichever gives the longer
                     * palindromic subsequence.
                     */
                    dp[i][j] = Math.max(
                        dp[i + 1][j],
                        dp[i][j - 1]
                    );
                }
            }
        }

        // The answer for the complete string
        // is stored in dp[0][n - 1].
        return dp[0][n - 1];
    }
}