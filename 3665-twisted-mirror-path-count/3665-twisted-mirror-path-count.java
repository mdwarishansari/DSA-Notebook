
class Solution {
    static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Robot is already at the destination.
        if (m == 1 && n == 1) return 1;

        // Final destination after attempting to enter a cell.
        int[][] right = new int[m][n];
        int[][] down = new int[m][n];

        // Initialize all transitions as invalid.
        for (int i = 0; i < m; i++) {
            java.util.Arrays.fill(right[i], -1);
            java.util.Arrays.fill(down[i], -1);
        }

        // Precompute mirror transitions in reverse order.
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                // Empty cell: enter normally.
                if (grid[i][j] == 0) {
                    right[i][j] = i * n + j;
                    down[i][j] = i * n + j;
                }

                // Mirror cell: RIGHT turns DOWN.
                else {
                    if (i + 1 < m) {
                        right[i][j] = down[i + 1][j];
                    }

                    // Mirror cell: DOWN turns RIGHT.
                    if (j + 1 < n) {
                        down[i][j] = right[i][j + 1];
                    }
                }
            }
        }

        // DP: Number of ways to reach each cell.
        int[][] dp = new int[m][n];

        // Starting position.
        dp[0][0] = 1;

        // Process cells in increasing row and column order.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (dp[i][j] == 0) continue;

                // Option 1: Attempt to move RIGHT.
                if (j + 1 < n) {
                    int pos = right[i][j + 1];

                    if (pos != -1) {
                        int x = pos / n;
                        int y = pos % n;

                        dp[x][y] = (int)(
                            (dp[x][y] + (long) dp[i][j]) % MOD
                        );
                    }
                }

                // Option 2: Attempt to move DOWN.
                if (i + 1 < m) {
                    int pos = down[i + 1][j];

                    if (pos != -1) {
                        int x = pos / n;
                        int y = pos % n;

                        dp[x][y] = (int)(
                            (dp[x][y] + (long) dp[i][j]) % MOD
                        );
                    }
                }
            }
        }

        // Number of ways to reach bottom-right.
        return dp[m - 1][n - 1];
    }
}
