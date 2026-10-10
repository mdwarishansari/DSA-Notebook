
import java.util.Stack;

class Solution {

    public int maximalRectangle(char[][] matrix) {
        // Handle an empty matrix.
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        // heights[j] stores consecutive '1's in column j,
        // ending at the current row.
        int[] heights = new int[cols];

        // Maximum rectangle area found across all rows.
        int maxArea = 0;

        // Treat every row as the base of a histogram.
        for (int i = 0; i < rows; i++) {

            // Update histogram heights using the current row.
            for (int j = 0; j < cols; j++) {

                if (matrix[i][j] == '1') {
                    // Extend the existing column of consecutive ones.
                    heights[j]++;
                } else {
                    // A zero breaks the consecutive sequence.
                    heights[j] = 0;
                }
            }

            // Find the largest rectangle in this histogram.
            int area = largestRectangleArea(heights);

            // Keep the best rectangle found so far.
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;

        // Store indices of histogram bars in increasing
        // order of their heights.
        Stack<Integer> stack = new Stack<>();

        // The extra iteration with height 0 flushes the stack.
        for (int i = 0; i <= n; i++) {

            // Use a virtual bar of height 0 at the end.
            int currentHeight = (i == n) ? 0 : heights[i];

            // A shorter bar means the popped bar cannot extend
            // any farther to the right.
            while (!stack.isEmpty()
                    && heights[stack.peek()] > currentHeight) {

                // Height of the rectangle being calculated.
                int height = heights[stack.pop()];

                // If no bar remains, the rectangle starts at index 0.
                // Otherwise, the top index marks the left boundary.
                int width = stack.isEmpty()
                        ? i
                        : i - stack.peek() - 1;

                // Calculate the rectangle area and update the maximum.
                maxArea = Math.max(maxArea, height * width);
            }

            // Push actual indices only; do not push the virtual bar.
            if (i < n) {
                stack.push(i);
            }
        }

        return maxArea;
    }
}
