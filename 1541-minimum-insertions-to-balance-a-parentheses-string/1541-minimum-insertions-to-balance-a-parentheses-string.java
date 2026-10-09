
class Solution {
    public int minInsertions(String s) {
        // open stores how many ')' are still required
        // to balance the '(' characters processed so far.
        int open = 0;

        // Counts the number of brackets we must insert.
        int ans = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {

                // Every '(' requires TWO consecutive ')'.
                // If open is odd, we cannot pair the next ')'
                // correctly with the existing requirement.
                // Insert one ')' to complete the previous pair.
                if (open % 2 == 1) {
                    ans++;
                    open--;
                }

                // This '(' requires two closing brackets.
                open += 2;

            } else {

                // Use one ')' to satisfy the pending requirement.
                open--;

                // If open becomes negative, there was no '('
                // available to match this closing bracket.
                // Insert one '(' to fix this problem.
                if (open < 0) {
                    ans++;
                    
                    // The inserted '(' needs one more ')'
                    // because the current ')' already exists.
                    open = 1;
                }
            }
        }

        // Insert all remaining required ')' characters.
        return ans + open;
    }
}
