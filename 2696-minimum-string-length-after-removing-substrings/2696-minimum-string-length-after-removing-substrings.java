
class Solution {
    public int minLength(String s) {
        // Use StringBuilder as a stack.
        StringBuilder st = new StringBuilder();

        // Process every character from left to right.
        for (char ch : s.toCharArray()) {

            // Add the current character to the stack.
            st.append(ch);

            // Check whether the last two characters form
            // either of the removable substrings: AB or CD.
            int n = st.length();

            if (n >= 2) {
                char a = st.charAt(n - 2);
                char b = st.charAt(n - 1);

                if ((a == 'A' && b == 'B') ||
                    (a == 'C' && b == 'D')) {

                    // Remove both characters of the matching pair.
                    st.delete(n - 2, n);
                }
            }
        }

        // The remaining stack gives the minimum possible length.
        return st.length();
    }
}
