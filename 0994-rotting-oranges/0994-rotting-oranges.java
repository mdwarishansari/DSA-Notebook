import java.util.*;

class Solution {

    public int orangesRotting(int[][] grid) {

        // Number of rows in the grid
        int rows = grid.length;

        // Number of columns in the grid
        int cols = grid[0].length;

        // Queue stores the coordinates of rotten oranges.
        //
        // Each element is an int[] containing:
        // {row, column}
        Queue<int[]> queue = new LinkedList<>();

        // Count the total number of fresh oranges.
        int freshCount = 0;

        // ---------------------------------------------------------
        // Step 1: Traverse the grid
        //
        // - Put every initially rotten orange into the queue.
        // - Count every fresh orange.
        // ---------------------------------------------------------
        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {

                // If the orange is already rotten,
                // add its coordinates to the queue.
                if (grid[row][col] == 2) {

                    queue.offer(new int[]{row, col});
                }

                // If the orange is fresh,
                // increase the fresh orange count.
                else if (grid[row][col] == 1) {

                    freshCount++;
                }
            }
        }

        // If there are no fresh oranges,
        // all oranges are already rotten.
        // Therefore, the answer is 0 minutes.
        if (freshCount == 0) {
            return 0;
        }

        // ---------------------------------------------------------
        // Direction array
        //
        // We can move only in 4 directions:
        //
        // {-1,  0} -> UP
        // { 1,  0} -> DOWN
        // { 0, -1} -> LEFT
        // { 0,  1} -> RIGHT
        //
        // Diagonal movement is NOT allowed.
        // ---------------------------------------------------------
        int[][] directions = {
            {-1, 0},   // UP
            {1, 0},    // DOWN
            {0, -1},   // LEFT
            {0, 1}     // RIGHT
        };

        // Number of BFS levels processed.
        int minutes = 0;

        // ---------------------------------------------------------
        // Step 2: Multi-Source BFS
        //
        // Initially, the queue contains ALL rotten oranges.
        // They all start spreading the rot simultaneously.
        // ---------------------------------------------------------
        while (!queue.isEmpty()) {

            // Number of rotten oranges currently in this level.
            //
            // All these oranges belong to the same minute.
            int levelSize = queue.size();

            // -----------------------------------------------------
            // Process all rotten oranges of the current level.
            // -----------------------------------------------------
            while (levelSize-- > 0) {

                // Remove the front rotten orange.
                int[] current = queue.poll();

                int currentRow = current[0];
                int currentCol = current[1];

                // -------------------------------------------------
                // Check all four directions.
                // -------------------------------------------------
                for (int[] direction : directions) {

                    // Calculate the neighboring cell.
                    int newRow = currentRow + direction[0];
                    int newCol = currentCol + direction[1];

                    // -------------------------------------------------
                    // The neighbor can become rotten ONLY when:
                    //
                    // 1. It is inside the grid.
                    // 2. It contains a fresh orange.
                    // -------------------------------------------------
                    if (newRow >= 0 &&
                        newRow < rows &&
                        newCol >= 0 &&
                        newCol < cols &&
                        grid[newRow][newCol] == 1) {

                        // Make the fresh orange rotten.
                        grid[newRow][newCol] = 2;

                        // One fresh orange has become rotten.
                        freshCount--;

                        // Add the newly rotten orange to the queue.
                        //
                        // It will spread the rot in the NEXT
                        // BFS level / next minute.
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }

            // One complete BFS level has been processed.
            // Therefore, one minute has passed.
            minutes++;
        }

        // ---------------------------------------------------------
        // Step 3: Final result
        //
        // If freshCount == 0:
        //     Every fresh orange was successfully rotten.
        //
        //     We return minutes - 1 because the final BFS
        //     iteration only processed oranges that were already
        //     rotten and did not rot anything new.
        //
        // If freshCount > 0:
        //     Some fresh oranges could not be reached.
        //     Therefore, return -1.
        // ---------------------------------------------------------
        return freshCount == 0 ? minutes - 1 : -1;
    }
}