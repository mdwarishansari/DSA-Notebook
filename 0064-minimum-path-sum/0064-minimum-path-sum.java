class Solution {

    public int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // dp[i][j] represents the minimum path sum
        // required to reach cell (i, j).
        int[][] dp = new int[m][n];

        // -----------------------------------------
        // Starting cell
        // -----------------------------------------
        //
        // We start directly at grid[0][0],
        // so its value is the initial path sum.
        dp[0][0] = grid[0][0];

        // -----------------------------------------
        // Fill the first row
        // -----------------------------------------
        //
        // In the first row, we can ONLY move right.
        // Therefore, there is only one possible path
        // to each cell.
        for (int col = 1; col < n; col++) {

            dp[0][col] = dp[0][col - 1] + grid[0][col];
        }

        // -----------------------------------------
        // Fill the first column
        // -----------------------------------------
        //
        // In the first column, we can ONLY move down.
        // Therefore, there is only one possible path
        // to each cell.
        for (int row = 1; row < m; row++) {

            dp[row][0] = dp[row - 1][0] + grid[row][0];
        }

        // -----------------------------------------
        // Fill the remaining cells
        // -----------------------------------------
        for (int row = 1; row < m; row++) {

            for (int col = 1; col < n; col++) {

                // We can reach the current cell from:
                //
                // 1. Above  -> dp[row - 1][col]
                // 2. Left   -> dp[row][col - 1]
                //
                // Choose the path with the smaller sum,
                // then add the current cell's value.
                dp[row][col] = grid[row][col]
                        + Math.min(
                            dp[row - 1][col],
                            dp[row][col - 1]
                        );
            }
        }

        // The bottom-right cell contains
        // the minimum possible path sum.
        return dp[m - 1][n - 1];
    }
}