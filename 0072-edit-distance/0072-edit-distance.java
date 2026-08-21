class Solution {

    public int minDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        // dp[i][j] represents the minimum number of
        // operations required to convert the first i
        // characters of word1 into the first j
        // characters of word2.
        int[][] dp = new int[m + 1][n + 1];

        // --------------------------------------------------
        // Base Case 1:
        // Convert an empty word1 into word2.
        // We need j insertions.
        // --------------------------------------------------
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // --------------------------------------------------
        // Base Case 2:
        // Convert word1 into an empty word2.
        // We need i deletions.
        // --------------------------------------------------
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // --------------------------------------------------
        // Fill the DP table
        // --------------------------------------------------
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                // Current characters of both strings.
                char ch1 = word1.charAt(i - 1);
                char ch2 = word2.charAt(j - 1);

                // --------------------------------------------------
                // Case 1: Characters are already equal
                // --------------------------------------------------
                if (ch1 == ch2) {

                    // No operation is required.
                    // Simply use the answer for the
                    // previous characters of both strings.
                    dp[i][j] = dp[i - 1][j - 1];

                } else {

                    // --------------------------------------------------
                    // Case 2: Characters are different
                    // --------------------------------------------------
                    //
                    // We have three possible operations:
                    //
                    // 1. Delete word1[i - 1]
                    //    -> dp[i - 1][j] + 1
                    //
                    // 2. Insert word2[j - 1]
                    //    -> dp[i][j - 1] + 1
                    //
                    // 3. Replace word1[i - 1]
                    //    -> dp[i - 1][j - 1] + 1
                    //
                    // Choose the operation requiring
                    // the minimum number of operations.

                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j],                    // Delete
                        Math.min(
                            dp[i][j - 1],                // Insert
                            dp[i - 1][j - 1]              // Replace
                        )
                    );
                }
            }
        }

        // Minimum operations required to convert
        // the complete word1 into complete word2.
        return dp[m][n];
    }
}