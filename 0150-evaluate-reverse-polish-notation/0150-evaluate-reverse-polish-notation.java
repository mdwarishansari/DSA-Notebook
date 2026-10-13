
import java.util.Stack;

class Solution {
    public int evalRPN(String[] tokens) {
        // Stores operands and intermediate results.
        Stack<Integer> st = new Stack<>();

        // Process every token from left to right.
        for (String t : tokens) {

            // If the token is an operator, perform the operation.
            if (t.equals("+") || t.equals("-") ||
                t.equals("*") || t.equals("/")) {

                // The second popped value is the right operand.
                int b = st.pop();

                // The first popped value is the left operand.
                int a = st.pop();

                int res = 0;

                // Calculate the result using the current operator.
                switch (t) {
                    case "+":
                        res = a + b;
                        break;

                    case "-":
                        res = a - b;
                        break;

                    case "*":
                        res = a * b;
                        break;

                    case "/":
                        // Java integer division truncates toward zero.
                        res = a / b;
                        break;
                }

                // Push the intermediate result back onto the stack.
                st.push(res);

            } else {
                // The token is a number; parse and push it.
                st.push(Integer.parseInt(t));
            }
        }

        // A valid RPN expression leaves exactly one result.
        return st.pop();
    }
}
