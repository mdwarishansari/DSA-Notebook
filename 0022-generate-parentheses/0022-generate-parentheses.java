class Solution {

    // Stores all valid parentheses combinations
    List<String> result = new ArrayList<>();

    // Recursive function
    // curr  -> Current string being built
    // n     -> Number of pairs
    // open  -> Number of '(' used
    // close -> Number of ')' used
    private void solve(StringBuilder curr, int n, int open, int close) {

        // ============================
        // BASE CASE
        // ============================
        // If current string length becomes 2*n,
        // we have used all parentheses.
        if (curr.length() == 2 * n) {

            // Convert StringBuilder into String
            // and store in result.
            result.add(curr.toString());

            return;
        }

        // ============================
        // CHOICE 1 : Add '('
        // ============================
        // We can add '(' only if
        // we still have opening brackets left.
        if (open < n) {

            // -------- CHOOSE --------
            curr.append('(');

            // -------- EXPLORE --------
            solve(curr, n, open + 1, close);

            // -------- BACKTRACK (UNDO) --------
            // Remove last character
            curr.deleteCharAt(curr.length() - 1);
        }

        // ============================
        // CHOICE 2 : Add ')'
        // ============================
        // We can add ')' only if
        // there is an unmatched '(' available.
        if (close < open) {

            // -------- CHOOSE --------
            curr.append(')');

            // -------- EXPLORE --------
            solve(curr, n, open, close + 1);

            // -------- BACKTRACK (UNDO) --------
            // Remove last character
            curr.deleteCharAt(curr.length() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {

        // Empty StringBuilder
        StringBuilder curr = new StringBuilder();

        // Start recursion
        solve(curr, n, 0, 0);

        return result;
    }
}