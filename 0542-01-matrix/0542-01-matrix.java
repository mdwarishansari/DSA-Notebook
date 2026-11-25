import java.util.*;

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        
        // Queue to store coordinates [row, col] for Multi-Source BFS
        Queue<int[]> queue = new LinkedList<>();
        
        // 1. Initialize the matrix and enqueue all '0' cells as starting sources
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (mat[r][c] == 0) {
                    queue.offer(new int[]{r, c});
                } else {
                    // Mark '1' cells as -1 (unvisited) so we don't confuse distance 1 with initial 1
                    mat[r][c] = -1;
                }
            }
        }
        
        // 4-directional movement vectors: Up, Down, Left, Right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        // 2. Perform BFS level by level
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            
            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                // Check bounds and ensure the neighbor is an unvisited cell (-1)
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && mat[nr][nc] == -1) {
                    // Distance to nearest 0 is 1 + distance of current cell
                    mat[nr][nc] = mat[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        
        return mat;
    }
}