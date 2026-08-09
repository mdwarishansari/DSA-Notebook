import java.util.*;

class Solution {

    public int[][] updateMatrix(int[][] mat) {

        int rows = mat.length;
        int cols = mat[0].length;

        Queue<int[]> queue = new LinkedList<>();

        // Step 1:
        // Put every 0 cell into the queue.
        // These are our starting points for Multi-Source BFS.
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                if (mat[row][col] == 0) {
                    queue.offer(new int[]{row, col});
                } 
                else {
                    // Mark 1 cells as unvisited.
                    // -1 means we haven't calculated their distance yet.
                    mat[row][col] = -1;
                }
            }
        }

        // Directions:
        //        up
        //         ↑
        // left ← cell → right
        //         ↓
        //       down
        int[][] directions = {
            {-1, 0},  // up
            {1, 0},   // down
            {0, -1},  // left
            {0, 1}    // right
        };

        // Step 2:
        // BFS starting from all 0 cells.
        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];

            // Check all 4 neighboring cells.
            for (int[] direction : directions) {

                int newRow = row + direction[0];
                int newCol = col + direction[1];

                // Make sure the new position is inside the matrix
                // and has not been visited yet.
                if (newRow >= 0 && newRow < rows &&
                    newCol >= 0 && newCol < cols &&
                    mat[newRow][newCol] == -1) {

                    // Distance of neighbor =
                    // distance of current cell + 1
                    mat[newRow][newCol] = mat[row][col] + 1;

                    // Process this cell later.
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        return mat;
    }
}