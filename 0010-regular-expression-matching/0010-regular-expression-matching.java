class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // dp[i][j] represents whether s[0...i-1] matches p[0...j-1]
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Base case: empty string matches empty pattern
        dp[0][0] = true;

        // Base case: patterns like a*, a*b*, a*b*c* can match an empty string
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                // '*' eliminates the preceding character by matching 0 occurrences
                dp[0][j] = dp[0][j - 2];
            }
        }

        // Fill DP table for all prefixes of s and p
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char currPattern = p.charAt(j - 1);

                if (currPattern == '*') {
                    // Case 1: Match 0 occurrences of the preceding element
                    dp[i][j] = dp[i][j - 2];

                    // Case 2: Match 1 or more occurrences of the preceding element
                    char prevPatternChar = p.charAt(j - 2);
                    if (prevPatternChar == '.' || prevPatternChar == s.charAt(i - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else if (currPattern == '.' || currPattern == s.charAt(i - 1)) {
                    // Current characters match directly, or '.' matches any single character
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        // Result: whether entire string s matches entire pattern p
        return dp[m][n];
    }
}