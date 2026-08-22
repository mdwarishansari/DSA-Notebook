class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        // Put all dictionary words into a HashSet
        // for fast lookup.
        Set<String> wordSet = new HashSet<>(wordDict);

        int n = s.length();

        /*
         * dp[i] means:
         *
         * Can the substring s[0...i-1]
         * be completely segmented into
         * dictionary words?
         */
        boolean[] dp = new boolean[n + 1];

        // Empty string can always be formed
        // by choosing zero words.
        dp[0] = true;

        // Consider every possible ending position.
        for (int i = 1; i <= n; i++) {

            // Try every possible starting position
            // of the last word.
            for (int j = 0; j < i; j++) {

                /*
                 * If the part before j can already
                 * be formed and s[j...i] is a
                 * dictionary word, then the whole
                 * substring up to i can be formed.
                 */
                if (dp[j] && wordSet.contains(s.substring(j, i))) {

                    dp[i] = true;

                    // No need to check other j values
                    // once we know dp[i] is possible.
                    break;
                }
            }
        }

        // dp[n] tells us whether the entire string
        // can be segmented into dictionary words.
        return dp[n];
    }
}