class Solution {
    public int minAddToMakeValid(String s) {

        // Counts unmatched opening '(' brackets.
        int open = 0;

        // Counts how many opening brackets '('
        // we need to insert for unmatched ')'.
        int ans = 0;

        // Traverse every character in the string.
        for (char c : s.toCharArray()) {

            // If we find an opening bracket,
            // it can potentially match a future ')'.
            if (c == '(') {
                open++;
            }

            // If we find a closing bracket ')':
            else {

                // If there is an unmatched '(' available,
                // use it to match this ')'.
                if (open > 0) {
                    open--;
                }

                // Otherwise, this ')' has no matching '('.
                // We must insert one '(' before it.
                else {
                    ans++;
                }
            }
        }

        // Any remaining '(' still needs a ')' to match it.
        // Therefore, add them to the answer.
        ans += open;

        return ans;
    }
}