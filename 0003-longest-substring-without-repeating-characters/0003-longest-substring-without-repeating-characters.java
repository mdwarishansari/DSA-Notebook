import java.util.HashSet;
import java.util.Set;

class Solution {

    public int lengthOfLongestSubstring(String s) {

        // HashSet stores only UNIQUE characters
        // It represents the current sliding window
        Set<Character> set = new HashSet<>();

        // i = Left pointer (start of window)
        // j = Right pointer (end of window)
        int i = 0, j = 0;

        // Stores the maximum length found so far
        int maxLen = 0;

        // Traverse the string using the right pointer
        while (j < s.length()) {

            // Current character at right pointer
            char ch = s.charAt(j);

            /*
             * If current character already exists in the window,
             * keep removing characters from the LEFT side
             * until the duplicate is removed.
             *
             * Example:
             *
             * Window = "abc"
             * Next character = 'a'
             *
             * Remove 'a' from left
             * Window becomes "bc"
             *
             * Now duplicate is gone.
             */
            while (set.contains(ch)) {

                // Remove leftmost character from HashSet
                set.remove(s.charAt(i));

                // Shrink the window
                i++;
            }

            /*
             * Current character is now unique,
             * so add it to the window.
             */
            set.add(ch);

            /*
             * Current window:
             * i -------- j
             *
             * Length = (j - i + 1)
             *
             * Update maximum answer.
             */
            maxLen = Math.max(maxLen, j - i + 1);

            // Expand the window by moving right pointer
            j++;
        }

        // Return the longest valid substring length
        return maxLen;
    }
}