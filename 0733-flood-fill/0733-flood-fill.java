class Solution {

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        // Store the original color of the starting pixel.
        int originalColor = image[sr][sc];

        // If the new color is already the same, nothing needs to change.
        if (originalColor == color) {
            return image;
        }

        // Four possible movements: up, down, left, right.
        int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        // Start DFS from the given starting pixel.
        dfs(image, sr, sc, originalColor, color, directions);

        return image;
    }

    private void dfs(
        int[][] image,
        int row,
        int col,
        int originalColor,
        int newColor,
        int[][] directions
    ) {

        // Stop if the position is outside the grid.
        if (row < 0 || row >= image.length ||
            col < 0 || col >= image[0].length) {
            return;
        }

        // Stop if this pixel does not have the original color.
        if (image[row][col] != originalColor) {
            return;
        }

        // Change the current pixel to the new color.
        image[row][col] = newColor;

        // Explore all four neighboring pixels.
        for (int[] direction : directions) {

            int newRow = row + direction[0];
            int newCol = col + direction[1];

            dfs(
                image,
                newRow,
                newCol,
                originalColor,
                newColor,
                directions
            );
        }
    }
}