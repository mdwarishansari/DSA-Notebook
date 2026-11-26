import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Queue for Multi-Source Breadth-First Search (BFS) storing {row, col} coordinates
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;
        
        // Step 1: Scan the entire grid to enqueue all initially rotten oranges (2)
        // and count the total number of fresh oranges (1)
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    freshCount++;
                }
            }
        }
        
        // Edge case: If there are no fresh oranges to begin with, 0 minutes are needed
        if (freshCount == 0) {
            return 0;
        }
        
        int minutes = 0;
        // 4-directional movement vectors: Up, Down, Left, Right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        // Step 2: Process level-by-level (each level represents 1 elapsed minute)
        while (!queue.isEmpty() && freshCount > 0) {
            int levelSize = queue.size();
            minutes++; // Increment time for the current wave of rotting
            
            for (int i = 0; i < levelSize; i++) {
                int[] current = queue.poll();
                int r = current[0];
                int c = current[1];
                
                // Spread rot to all valid 4-directionally adjacent fresh oranges
                for (int[] dir : directions) {
                    int nextR = r + dir[0];
                    int nextC = c + dir[1];
                    
                    // Check grid boundaries and whether the adjacent cell has a fresh orange
                    if (nextR >= 0 && nextR < rows && nextC >= 0 && nextC < cols && grid[nextR][nextC] == 1) {
                        grid[nextR][nextC] = 2; // Mark orange as rotten
                        freshCount--;           // Decrement remaining fresh oranges
                        queue.offer(new int[]{nextR, nextC}); // Enqueue newly rotten orange for the next minute
                    }
                }
            }
        }
        
        // Step 3: If all fresh oranges rotted, return elapsed minutes; otherwise return -1
        return freshCount == 0 ? minutes : -1;
    }
}