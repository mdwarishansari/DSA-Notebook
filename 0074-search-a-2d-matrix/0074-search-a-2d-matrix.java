class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;

        // Treat the entire matrix as one sorted 1D array.
        int left = 0;
        int right = m * n - 1;

        while (left <= right) {

            // Find the middle index.
            int mid = left + (right - left) / 2;

            // Convert 1D index into 2D row and column.
            int row = mid / n;
            int col = mid % n;

            int value = matrix[row][col];

            // Target found.
            if (value == target) {
                return true;
            }

            // Target must be on the right side.
            if (value < target) {
                left = mid + 1;
            }

            // Target must be on the left side.
            else {
                right = mid - 1;
            }
        }

        // Target does not exist in the matrix.
        return false;
    }
}