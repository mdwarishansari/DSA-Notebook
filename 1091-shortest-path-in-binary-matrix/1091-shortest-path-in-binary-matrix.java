import java.util.*;

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        
        // 1. Edge case: If the start or destination cell is blocked (1), no path exists
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }
        
        // 8-directional movement vectors: horizontal, vertical, and all 4 diagonals
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            { 0, -1},          { 0, 1},
            { 1, -1}, { 1, 0}, { 1, 1}
        };
        
        // Queue stores [row, col, pathLength]
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});
        
        // Mark the starting cell as visited by setting it to 1
        grid[0][0] = 1;
        
        // 2. Perform standard level-by-level BFS
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int length = curr[2];
            
            // If we reached the bottom-right corner, return the path length immediately
            if (r == n - 1 && c == n - 1) {
                return length;
            }
            
            // Explore all 8 adjacent neighbors
            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                // Check grid bounds and ensure the neighbor cell is clear (0)
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 0) {
                    // Mark as visited immediately upon enqueuing to avoid duplicate visits
                    grid[nr][nc] = 1;
                    queue.offer(new int[]{nr, nc, length + 1});
                }
            }
        }
        
        // Target cell was unreachable
        return -1;
    }
}