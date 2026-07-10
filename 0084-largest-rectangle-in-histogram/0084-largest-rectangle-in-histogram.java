import java.util.Stack;

class Solution {

    public int largestRectangleArea(int[] heights) {

        int n = heights.length;

        // ---------------------------------------------------------
        // leftSmall[i] = index of nearest smaller bar on the left
        // If no smaller exists, store -1
        // ---------------------------------------------------------
        int[] leftSmall = new int[n];

        // ---------------------------------------------------------
        // rightSmall[i] = index of nearest smaller bar on the right
        // If no smaller exists, store n
        // ---------------------------------------------------------
        int[] rightSmall = new int[n];

        Stack<Integer> stack = new Stack<>();

        // =========================================================
        // STEP 1 : Find Nearest Smaller Element on LEFT
        // =========================================================
        for (int i = 0; i < n; i++) {

            // Remove all bars that are greater than or equal
            // because they cannot be the nearest smaller.
            while (!stack.isEmpty() &&
                    heights[stack.peek()] >= heights[i]) {

                stack.pop();
            }

            // If stack becomes empty,
            // no smaller element exists on the left.
            if (stack.isEmpty()) {
                leftSmall[i] = -1;
            } else {
                leftSmall[i] = stack.peek();
            }

            // Push current index.
            stack.push(i);
        }

        // Reuse the stack.
        stack.clear();

        // =========================================================
        // STEP 2 : Find Nearest Smaller Element on RIGHT
        // =========================================================
        for (int i = n - 1; i >= 0; i--) {

            while (!stack.isEmpty() &&
                    heights[stack.peek()] >= heights[i]) {

                stack.pop();
            }

            // If stack becomes empty,
            // no smaller element exists on the right.
            if (stack.isEmpty()) {
                rightSmall[i] = n;
            } else {
                rightSmall[i] = stack.peek();
            }

            stack.push(i);
        }

        // =========================================================
        // STEP 3 : Calculate Maximum Rectangle Area
        // =========================================================
        int maxArea = 0;

        for (int i = 0; i < n; i++) {

            // Width = Right Smaller - Left Smaller - 1
            int width = rightSmall[i] - leftSmall[i] - 1;

            // Area = Height × Width
            int area = heights[i] * width;

            // Update answer.
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}