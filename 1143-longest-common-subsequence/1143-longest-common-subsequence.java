class Solution {

    public int longestCommonSubsequence(String text1, String text2) {

        int m = text1.length();
        int n = text2.length();

        // dp[i][j] represents the length of the
        // Longest Common Subsequence between:
        //
        // text1[0 ... i-1]
        // text2[0 ... j-1]
        //
        // We use m + 1 and n + 1 so that row 0
        // and column 0 represent an empty string.
        int[][] dp = new int[m + 1][n + 1];

        // Start from 1 because row 0 and column 0
        // represent empty strings.
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                // Get the current characters.
                char ch1 = text1.charAt(i - 1);
                char ch2 = text2.charAt(j - 1);

                // -----------------------------------------
                // Case 1: Characters are the same
                // -----------------------------------------
                if (ch1 == ch2) {

                    // We can include this character
                    // in the common subsequence.
                    //
                    // Move diagonally because we have
                    // consumed one character from both strings.
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                } else {

                    // -----------------------------------------
                    // Case 2: Characters are different
                    // -----------------------------------------
                    //
                    // We cannot use both characters together.
                    //
                    // Option 1:
                    // Skip the current character of text1.
                    //
                    // Option 2:
                    // Skip the current character of text2.
                    //
                    // Take whichever gives the longer LCS.
                    dp[i][j] = Math.max(
                        dp[i - 1][j],
                        dp[i][j - 1]
                    );
                }
            }
        }

        // dp[m][n] contains the LCS length
        // of the complete two strings.
        return dp[m][n];
    }
}