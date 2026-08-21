class Solution {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // dp[i][j] = number of unique paths to reach
        // cell (i, j) without crossing any obstacle.
        int[][] dp = new int[m][n];

        // --------------------------------------------------
        // Fill the first row
        // --------------------------------------------------
        //
        // We can only move RIGHT in the first row.
        // Therefore, once we encounter an obstacle,
        // every cell after it becomes unreachable.
        //
        for (int col = 0; col < n; col++) {

            if (obstacleGrid[0][col] == 1) {

                // This cell itself is an obstacle.
                dp[0][col] = 0;

            } else if (col > 0 && dp[0][col - 1] == 0) {

                // The cell on the left is unreachable.
                // Since we can only move right in the first row,
                // this cell is also unreachable.
                dp[0][col] = 0;

            } else {

                // No obstacle before this cell,
                // so there is exactly one path to it.
                dp[0][col] = 1;
            }
        }

        // --------------------------------------------------
        // Fill the first column
        // --------------------------------------------------
        //
        // We can only move DOWN in the first column.
        // Therefore, once we encounter an obstacle,
        // every cell below it becomes unreachable.
        //
        for (int row = 0; row < m; row++) {

            if (obstacleGrid[row][0] == 1) {

                // This cell itself is an obstacle.
                dp[row][0] = 0;

            } else if (row > 0 && dp[row - 1][0] == 0) {

                // The cell above is unreachable.
                // Since we can only move down in the first column,
                // this cell is also unreachable.
                dp[row][0] = 0;

            } else {

                // No obstacle before this cell,
                // so there is exactly one path to it.
                dp[row][0] = 1;
            }
        }

        // --------------------------------------------------
        // Fill the remaining cells
        // --------------------------------------------------
        //
        // For every normal cell, the robot can arrive from:
        //
        // 1. Above  -> dp[row - 1][col]
        // 2. Left   -> dp[row][col - 1]
        //
        // Therefore:
        //
        // dp[row][col] =
        //     dp[row - 1][col] + dp[row][col - 1]
        //
        for (int row = 1; row < m; row++) {

            for (int col = 1; col < n; col++) {

                // If this cell is an obstacle,
                // the robot cannot enter it.
                if (obstacleGrid[row][col] == 1) {

                    dp[row][col] = 0;

                } else {

                    // Number of ways from above
                    // + number of ways from the left.
                    dp[row][col] =
                            dp[row - 1][col]
                            + dp[row][col - 1];
                }
            }
        }

        // The bottom-right cell contains
        // the total number of valid paths.
        return dp[m - 1][n - 1];
    }
}