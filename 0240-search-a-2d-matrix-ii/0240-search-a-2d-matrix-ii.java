class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Start from the top-right corner.
        int row = 0;
        int col = cols - 1;

        while (row < rows && col >= 0) {

            int current = matrix[row][col];

            // Target found.
            if (current == target) {
                return true;
            }

            // Current value is too large.
            // Move left to get smaller values.
            if (current > target) {
                col--;
            }

            // Current value is too small.
            // Move down to get larger values.
            else {
                row++;
            }
        }

        // Target was not found.
        return false;
    }
}