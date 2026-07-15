import java.util.Stack;

class Solution {

    public int scoreOfParentheses(String s) {

        // Stack stores the score before entering a new '(' group
        Stack<Integer> stack = new Stack<>();

        // Current score of the current level
        int score = 0;

        // Traverse every character
        for (char ch : s.toCharArray()) {

            if (ch == '(') {

                // Save current score before starting a new group
                stack.push(score);

                // Reset score for the new inner group
                score = 0;

            } else {

                // Retrieve the score before this '('
                int previousScore = stack.pop();

                // If score is 0, it means we found "()"
                if (score == 0) {
                    score = previousScore + 1;
                }

                // Otherwise we found "(A)"
                // Multiply inner score by 2 and add previous score
                else {
                    score = previousScore + (2 * score);
                }
            }
        }

        // Final calculated score
        return score;
    }
}