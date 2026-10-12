
import java.util.*;

class Solution {
    public int calculate(String s) {
        // Stores numbers that will eventually be added together.
        Stack<Integer> st = new Stack<>();

        // Builds a number when the expression contains multiple digits.
        int num = 0;

        // The first number is treated as if it has a '+' before it.
        char op = '+';

        // Process every character, including a virtual operator
        // at the end by using the last character condition.
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Build the current number digit by digit.
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            // Process the number when an operator is found
            // or when we reach the end of the string.
            if ((!Character.isDigit(c) && c != ' ') ||
                i == s.length() - 1) {

                // Apply the PREVIOUS operator to the current number.
                if (op == '+') {
                    st.push(num);
                } else if (op == '-') {
                    st.push(-num);
                } else if (op == '*') {
                    st.push(st.pop() * num);
                } else if (op == '/') {
                    // Java integer division truncates toward zero.
                    st.push(st.pop() / num);
                }

                // Save the current operator for the next number.
                op = c;

                // Reset the number for the next sequence of digits.
                num = 0;
            }
        }

        // Add all remaining values to obtain the final answer.
        int ans = 0;
        while (!st.isEmpty()) {
            ans += st.pop();
        }

        return ans;
    }
}
