class Solution {

    public int numIslands(char[][] grid) {

        int islands = 0;

        // Store the four possible movements: up, down, left, right.
        int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        // Visit every cell in the grid.
        for (int row = 0; row < grid.length; row++) {

            for (int col = 0; col < grid[0].length; col++) {

                // A new unvisited land cell means we found a new island.
                if (grid[row][col] == '1') {

                    islands++;

                    // Visit the complete island starting from this cell.
                    dfs(grid, row, col, directions);
                }
            }
        }

        return islands;
    }

    private void dfs(char[][] grid, int row, int col, int[][] directions) {

        // Stop if the cell is outside the grid or is water.
        if (row < 0 || row >= grid.length ||
            col < 0 || col >= grid[0].length ||
            grid[row][col] == '0') {
            return;
        }

        // Mark this land cell as visited.
        grid[row][col] = '0';

        // Explore all four neighboring cells.
        for (int[] direction : directions) {

            int newRow = row + direction[0];
            int newCol = col + direction[1];

            dfs(grid, newRow, newCol, directions);
        }
    }
}