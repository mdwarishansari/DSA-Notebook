class Solution {
    public int numDistinct(String s, String t) {

        int m = s.length();
        int n = t.length();

        /*
         * dp[j] = number of ways to form t.substring(0, j)
         *         using the characters processed from s so far.
         *
         * dp[0] = 1 because there is exactly ONE way to form
         * the empty string:
         *
         *       choose nothing
         */
        int[] dp = new int[n + 1];

        dp[0] = 1;

        /*
         * Process every character of s.
         */
        for (int i = 1; i <= m; i++) {

            /*
             * Traverse t from RIGHT to LEFT.
             *
             * Why backwards?
             *
             * dp[j - 1] is needed to calculate dp[j].
             * If we went left -> right, dp[j - 1] would already
             * be modified for the current character of s.
             *
             * Going right -> left keeps dp[j - 1] from the
             * previous iteration unchanged.
             */
            for (int j = n; j >= 1; j--) {

                /*
                 * If the current characters match, we have
                 * two possibilities:
                 *
                 * 1. Don't use s[i - 1]
                 *    -> dp[j] ways already exist.
                 *
                 * 2. Use s[i - 1]
                 *    -> we need to form t[0 ... j-2] first.
                 *    -> dp[j - 1] ways.
                 *
                 * Therefore:
                 *
                 * dp[j] = dp[j] + dp[j - 1]
                 */
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        /*
         * dp[n] contains the number of distinct subsequences
         * of s that are exactly equal to t.
         */
        return dp[n];
    }
}