
class Solution {
    public boolean backspaceCompare(String s, String t) {
        // Start from the last character of both strings.
        int i = s.length() - 1;
        int j = t.length() - 1;

        // Process characters from right to left.
        while (i >= 0 || j >= 0) {
            int skipS = 0;
            int skipT = 0;

            // Find the next valid character in string s.
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    // A backspace means one previous character is deleted.
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    // Skip a character deleted by a backspace.
                    skipS--;
                    i--;
                } else {
                    // Found a character that survives backspacing.
                    break;
                }
            }

            // Find the next valid character in string t.
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    // Count the backspace.
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    // Skip a character deleted by a backspace.
                    skipT--;
                    j--;
                } else {
                    // Found a character that survives backspacing.
                    break;
                }
            }

            // If only one string has a valid character, they differ.
            if (i >= 0 && j < 0 || i < 0 && j >= 0) {
                return false;
            }

            // Compare the valid characters when both exist.
            if (i >= 0 && j >= 0 &&
                s.charAt(i) != t.charAt(j)) {
                return false;
            }

            // Move to the next characters on the left.
            i--;
            j--;
        }

        // All surviving characters matched.
        return true;
    }
}
