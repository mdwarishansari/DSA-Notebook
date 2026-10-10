
import java.util.Stack;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;

        // Store indices of bars in increasing order of height.
        Stack<Integer> stack = new Stack<>();

        // Go one step beyond the array to process remaining bars.
        for (int i = 0; i <= n; i++) {

            // Treat the extra position as a bar of height 0.
            // This forces all remaining bars to be processed.
            int currentHeight = (i == n) ? 0 : heights[i];

            // If the current bar is shorter than the stack's top bar,
            // the top bar's rectangle cannot extend any farther right.
            while (!stack.isEmpty()
                    && heights[stack.peek()] > currentHeight) {

                // Remove the bar whose maximum rectangle we calculate.
                int height = heights[stack.pop()];

                // If the stack is empty, no smaller bar exists on the
                // left, so the rectangle extends from index 0 to i - 1.
                // Otherwise, the new stack top is the nearest smaller
                // bar on the left.
                int width = stack.isEmpty()
                        ? i
                        : i - stack.peek() - 1;

                // Calculate the area using the popped bar's height.
                int area = height * width;

                // Keep track of the largest rectangle found so far.
                maxArea = Math.max(maxArea, area);
            }

            // Do not push the virtual bar at index n.
            // Push the current index to maintain increasing heights.
            if (i < n) {
                stack.push(i);
            }
        }

        return maxArea;
    }
}
