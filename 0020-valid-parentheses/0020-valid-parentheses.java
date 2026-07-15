import java.util.Stack;

class Solution {

    public boolean isValid(String s) {

        // Stack stores opening brackets
        Stack<Character> stack = new Stack<>();

        // Traverse every character of the string
        for (char ch : s.toCharArray()) {

            // If opening bracket, push into stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }

            // If closing bracket
            else {

                // No opening bracket available
                if (stack.isEmpty()) {
                    return false;
                }

                // Get the top opening bracket
                char top = stack.peek();

                // Check if brackets match
                if ((ch == ')' && top == '(') ||
                    (ch == '}' && top == '{') ||
                    (ch == ']' && top == '[')) {

                    // Matching pair found
                    stack.pop();

                } else {

                    // Wrong matching bracket
                    return false;
                }
            }
        }

        // Valid only if no unmatched opening brackets remain
        return stack.isEmpty();
    }
}