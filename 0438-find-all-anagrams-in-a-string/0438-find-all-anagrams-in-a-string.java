import java.util.*;

class Solution {

    public List<Integer> findAnagrams(String s, String p) {

        // Stores all starting indices of valid anagrams
        List<Integer> result = new ArrayList<>();

        // Frequency array for characters of p
        // count[i] tells how many of that character are still needed
        int[] count = new int[26];

        // Build frequency map of pattern string
        for (char ch : p.toCharArray()) {
            count[ch - 'a']++;
        }

        // Left pointer of sliding window
        int i = 0;

        // Right pointer of sliding window
        int j = 0;

        // Fixed window size = length of pattern
        int k = p.length();

        // Number of characters still needed to form an anagram
        int needed = k;

        // Traverse the main string
        while (j < s.length()) {

            // Current character entering the window
            char right = s.charAt(j);

            /*
             * If this character is still needed
             * Example:
             * Need:
             * a=1 b=1 c=1
             *
             * Current = 'a'
             *
             * We matched one required character
             */
            if (count[right - 'a'] > 0) {
                needed--;
            }

            /*
             * Decrease frequency because
             * current character is now inside the window.
             *
             * Frequency may become negative,
             * meaning extra occurrences.
             */
            count[right - 'a']--;

            /*
             * Check only when window size becomes equal
             * to pattern length.
             */
            if (j - i + 1 == k) {

                /*
                 * needed == 0 means every required character
                 * has been matched.
                 */
                if (needed == 0) {
                    result.add(i);
                }

                /*
                 * Remove the leftmost character
                 * because window will slide forward.
                 */
                char left = s.charAt(i);

                /*
                 * If count >= 0 BEFORE increment,
                 * this character was contributing to the match.
                 *
                 * Removing it creates a shortage,
                 * so increase needed.
                 */
                if (count[left - 'a'] >= 0) {
                    needed++;
                }

                /*
                 * Restore its frequency because
                 * it is no longer inside the window.
                 */
                count[left - 'a']++;

                // Slide window
                i++;
            }

            // Expand window
            j++;
        }

        return result;
    }
}