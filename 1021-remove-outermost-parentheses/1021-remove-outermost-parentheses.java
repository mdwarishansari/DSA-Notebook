class Solution {
    public String removeOuterParentheses(String s) {

        // Stores the final answer
        StringBuilder ans = new StringBuilder();

        // Keeps track of current parentheses depth
        int balance = 0;

        // Traverse every character
        for (char c : s.toCharArray()) {

            if (c == '(') {

                // If balance is already > 0,
                // this is NOT the outermost '('
                if (balance > 0) {
                    ans.append(c);
                }

                // Increase depth after processing '('
                balance++;

            } else {

                // Decrease depth first for ')'
                balance--;

                // If balance is still > 0,
                // this is NOT the outermost ')'
                if (balance > 0) {
                    ans.append(c);
                }
            }
        }

        return ans.toString();
    }
}