import java.util.Stack;

class Solution {

    public int longestValidParentheses(String s) {

        // Stack stores indices
        Stack<Integer> stack = new Stack<>();

        // Base index before starting
        stack.push(-1);

        int maxLength = 0;

        // Traverse the string
        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            // Opening bracket
            if (ch == '(') {

                // Store its index
                stack.push(i);

            } else {

                // Remove matching opening bracket (or base index)
                stack.pop();

                // No valid opening bracket left
                if (stack.isEmpty()) {

                    // Current ')' becomes the new base
                    stack.push(i);

                } else {

                    // Calculate current valid substring length
                    int length = i - stack.peek();

                    // Update maximum length
                    maxLength = Math.max(maxLength, length);
                }
            }
        }

        return maxLength;
    }
}