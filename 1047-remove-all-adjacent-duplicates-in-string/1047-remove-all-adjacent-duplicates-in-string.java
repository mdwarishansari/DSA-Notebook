
class Solution {
    public String removeDuplicates(String s) {
        // Use StringBuilder as a stack to store remaining characters.
        StringBuilder st = new StringBuilder();

        // Process every character from left to right.
        for (char ch : s.toCharArray()) {

            // If the current character matches the stack's top,
            // remove the top character to eliminate the duplicate pair.
            if (st.length() > 0 &&
                st.charAt(st.length() - 1) == ch) {

                st.deleteCharAt(st.length() - 1);

            } else {
                // No adjacent duplicate exists, so add the character.
                st.append(ch);
            }
        }

        // The remaining characters form the final string.
        return st.toString();
    }
}
