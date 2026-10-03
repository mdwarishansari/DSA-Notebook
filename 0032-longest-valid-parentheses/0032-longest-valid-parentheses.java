class Solution {
    public int longestValidParentheses(String s) {

        Stack<Integer> stack = new Stack<>();

        // Boundary before the string starts
        stack.push(-1);

        int ans = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {

                // Store index of '('
                stack.push(i);

            } else {

                // Try to match ')'
                stack.pop();

                if (stack.isEmpty()) {

                    // This ')' cannot be matched.
                    // Make it the new boundary.
                    stack.push(i);

                } else {

                    // Length of current valid substring
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }

        return ans;
    }
}