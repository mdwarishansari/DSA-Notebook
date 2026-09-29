class Solution {

    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // A valid parentheses string must have even length.
        int len = m + n - 1;
        if (len % 2 != 0) {
            return false;
        }

        /*
         * dp[j] contains all possible balances at cell (i, j).
         * Balance = number of '(' - number of ')'.
         *
         * We use boolean arrays because the maximum path length is
         * at most 199, so balance can never exceed 199.
         */
        boolean[][][] dp = new boolean[m][n][len + 1];

        // Starting cell must be '('.
        if (grid[0][0] == ')') {
            return false;
        }

        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Skip the starting cell because it is already initialized.
                if (i == 0 && j == 0) {
                    continue;
                }

                // Current cell's effect on the balance.
                int change = (grid[i][j] == '(') ? 1 : -1;

                for (int balance = 0; balance <= len; balance++) {

                    // We can come from the cell above.
                    if (i > 0 && dp[i - 1][j][balance]) {
                        int newBalance = balance + change;

                        if (newBalance >= 0 && newBalance <= len) {
                            dp[i][j][newBalance] = true;
                        }
                    }

                    // We can come from the cell on the left.
                    if (j > 0 && dp[i][j - 1][balance]) {
                        int newBalance = balance + change;

                        if (newBalance >= 0 && newBalance <= len) {
                            dp[i][j][newBalance] = true;
                        }
                    }
                }
            }
        }

        // At the end, a valid parentheses string must have balance 0.
        return dp[m - 1][n - 1][0];
    }
}