class Solution {

    public int uniquePaths(int m, int n) {

        // dp[j] represents the number of ways
        // to reach the current cell in column j.
        //
        // Initially, every cell in the first row
        // has exactly 1 way to reach it:
        //
        // Start -> Right -> Right -> ...
        int[] dp = new int[n];

        // First row: every cell has exactly 1 path.
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
        }

        // Start from the second row.
        for (int i = 1; i < m; i++) {

            // Start from the second column.
            for (int j = 1; j < n; j++) {

                // dp[j] before updating = ways from ABOVE.
                //
                // dp[j - 1] = ways from the LEFT.
                //
                // So the current cell can be reached
                // in both of those ways.
                dp[j] = dp[j] + dp[j - 1];
            }
        }

        // Last position contains the number
        // of unique paths to the bottom-right.
        return dp[n - 1];
    }
}