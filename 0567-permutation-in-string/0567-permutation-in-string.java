import java.util.*;

class Solution {

    public boolean checkInclusion(String s1, String s2) {

        // If pattern is longer than the text,
        // permutation is impossible.
        if (s1.length() > s2.length()) {
            return false;
        }

        // Frequency array.
        // count[i] represents how many of each character
        // are still needed.
        int[] count = new int[26];

        // Build frequency of s1.
        for (char ch : s1.toCharArray()) {
            count[ch - 'a']++;
        }

        // Left pointer of sliding window
        int i = 0;

        // Traverse s2 using right pointer
        for (int j = 0; j < s2.length(); j++) {

            /*
             * Current character enters the window.
             * Reduce its required frequency.
             *
             * Positive -> still needed
             * Zero     -> perfectly matched
             * Negative -> extra occurrence
             */
            count[s2.charAt(j) - 'a']--;

            /*
             * Keep window size equal to s1.length().
             * If it becomes larger,
             * remove leftmost character.
             */
            if (j - i + 1 > s1.length()) {

                // Restore frequency because
                // left character leaves the window.
                count[s2.charAt(i) - 'a']++;

                // Slide window
                i++;
            }

            /*
             * Window size is exactly equal to pattern.
             * If every frequency becomes zero,
             * current window is a permutation.
             */
            if (j - i + 1 == s1.length() && allZero(count)) {
                return true;
            }
        }

        // No permutation found.
        return false;
    }

    // Checks whether every frequency is zero.
    // Zero means perfect match.
    private boolean allZero(int[] count) {

        for (int x : count) {
            if (x != 0) {
                return false;
            }
        }

        return true;
    }
}