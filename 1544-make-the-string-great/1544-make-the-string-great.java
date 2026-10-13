
class Solution {
    public String makeGood(String s) {
        // Use StringBuilder as a stack to store valid characters.
        StringBuilder st = new StringBuilder();

        // Process each character from left to right.
        for (char ch : s.toCharArray()) {

            // Check whether the current character and stack top
            // are the same letter with opposite cases.
            if (st.length() > 0 &&
                Math.abs(st.charAt(st.length() - 1) - ch) == 32) {

                // Remove the bad pair by popping the stack top.
                st.deleteCharAt(st.length() - 1);

            } else {
                // No bad pair exists, so add the current character.
                st.append(ch);
            }
        }

        // Return the string after all bad pairs are removed.
        return st.toString();
    }
}
