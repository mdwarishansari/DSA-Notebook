class Solution {
    public int maxDepth(String s) {
        int depth = 0;   // Current nesting depth
        int max = 0;     // Maximum depth found so far

        // Traverse every character in the string
        for (char ch : s.toCharArray()) {

            // Opening parenthesis increases nesting depth
            if (ch == '(') {
                depth++;

                // Update maximum depth
                max = Math.max(max, depth);
            }

            // Closing parenthesis decreases nesting depth
            else if (ch == ')') {
                depth--;
            }
        }

        // Return the maximum nesting depth
        return max;
    }
}