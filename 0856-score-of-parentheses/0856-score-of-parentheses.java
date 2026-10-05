import java.util.*;

class Solution {
    public int scoreOfParentheses(String s) {

        Stack<Integer> st = new Stack<>();

        // Score of the outermost level
        st.push(0);

        for (char c : s.toCharArray()) {

            if (c == '(') {

                // Start a new nested level
                st.push(0);

            } else {

                // Get the score inside this pair
                int inner = st.pop();

                int score;

                // "()" directly -> score 1
                if (inner == 0) {
                    score = 1;
                }

                // "(A)" -> 2 * score(A)
                else {
                    score = 2 * inner;
                }

                // Add this score to the parent level
                st.push(st.pop() + score);
            }
        }

        return st.peek();
    }
}