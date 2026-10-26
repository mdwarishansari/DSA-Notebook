
class Solution {
    private int m, n;
    private boolean[][] visited;
    private int[][] grid;

    public int countIslands(int[][] grid, int k) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        visited = new boolean[m][n];
        int count = 0;

        // Examine every cell to find new islands.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Start DFS only from unvisited land.
                if (grid[i][j] > 0 && !visited[i][j]) {

                    // Calculate the total value of this island.
                    long sum = dfs(i, j);

                    // Count the island if its sum is divisible by k.
                    if (sum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private long dfs(int r, int c) {
        // Stop if the cell is outside the grid.
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return 0;
        }

        // Stop at water or a cell already explored.
        if (grid[r][c] == 0 || visited[r][c]) {
            return 0;
        }

        // Mark this land cell to avoid revisiting it.
        visited[r][c] = true;

        // Include the current cell's value.
        long sum = grid[r][c];

        // Explore all four permitted directions.
        sum += dfs(r - 1, c); // Up
        sum += dfs(r + 1, c); // Down
        sum += dfs(r, c - 1); // Left
        sum += dfs(r, c + 1); // Right

        return sum;
    }
}
