import java.util.*;

class Solution {

    Set<String> ans = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {

        int l = 0, r = 0;

        // Find the minimum number of '(' and ')' to remove.
        for (char c : s.toCharArray()) {

            if (c == '(') {
                l++;
            } 
            else if (c == ')') {

                if (l > 0)
                    l--;       // Match this ')' with an earlier '('
                else
                    r++;       // Extra ')' must be removed
            }
        }

        dfs(s, 0, l, r, 0, new StringBuilder());

        return new ArrayList<>(ans);
    }

    private void dfs(String s, int i, int l, int r,
                     int bal, StringBuilder path) {

        // More ')' than '(' -> invalid.
        if (bal < 0)
            return;

        // Entire string processed.
        if (i == s.length()) {

            if (l == 0 && r == 0 && bal == 0)
                ans.add(path.toString());

            return;
        }

        char c = s.charAt(i);

        // -------------------------
        // Option 1: Remove character
        // -------------------------

        if (c == '(' && l > 0) {
            dfs(s, i + 1, l - 1, r, bal, path);
        }

        if (c == ')' && r > 0) {
            dfs(s, i + 1, l, r - 1, bal, path);
        }

        // -------------------------
        // Option 2: Keep character
        // -------------------------

        path.append(c);

        if (c == '(') {

            dfs(s, i + 1, l, r, bal + 1, path);

        } else if (c == ')') {

            // ')' can only be kept if it has
            // an unmatched '(' to pair with.
            if (bal > 0)
                dfs(s, i + 1, l, r, bal - 1, path);

        } else {

            // Letter: balance doesn't change.
            dfs(s, i + 1, l, r, bal, path);
        }

        // Backtrack.
        path.deleteCharAt(path.length() - 1);
    }
}