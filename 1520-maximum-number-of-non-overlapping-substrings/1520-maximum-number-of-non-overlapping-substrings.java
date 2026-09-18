import java.util.*;

class Solution {

    public List<String> maxNumOfSubstrings(String s) {

        // Store the first and last occurrence of each character.
        int[] first = new int[26];
        int[] last = new int[26];

        // Initialize first[] with -1 because 0 is a valid index.
        Arrays.fill(first, -1);

        // Find first and last occurrence of every character.
        for (int i = 0; i < s.length(); i++) {

            int index = s.charAt(i) - 'a';

            // First time we see this character.
            if (first[index] == -1) {
                first[index] = i;
            }

            // Keep updating the last occurrence.
            last[index] = i;
        }

        /*
         * Each valid substring is represented as:
         * [start, end]
         */
        List<int[]> intervals = new ArrayList<>();

        // Try to construct a valid substring starting
        // from the first occurrence of each character.
        for (int i = 0; i < s.length(); i++) {

            int currentChar = s.charAt(i) - 'a';

            // We only need to start from the first occurrence
            // of a character.
            if (first[currentChar] != i) {
                continue;
            }

            int start = i;
            int end = last[currentChar];

            boolean valid = true;

            /*
             * Expand the interval whenever we encounter
             * another character whose last occurrence is
             * outside our current interval.
             */
            for (int j = start; j <= end; j++) {

                int c = s.charAt(j) - 'a';

                /*
                 * If this character appeared before 'start',
                 * then including it would require us to include
                 * characters before 'start'.
                 *
                 * Therefore, this interval is invalid.
                 */
                if (first[c] < start) {
                    valid = false;
                    break;
                }

                // Include all occurrences of this character.
                end = Math.max(end, last[c]);
            }

            // Add only valid intervals.
            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        /*
         * To maximize the number of non-overlapping intervals,
         * choose the interval that finishes earliest.
         *
         * This is the standard greedy interval scheduling idea.
         */
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> result = new ArrayList<>();

        // End position of the previously selected substring.
        int previousEnd = -1;

        for (int[] interval : intervals) {

            int start = interval[0];
            int end = interval[1];

            /*
             * The current substring does not overlap
             * with the previously selected substring.
             */
            if (start > previousEnd) {

                result.add(s.substring(start, end + 1));

                previousEnd = end;
            }
        }

        return result;
    }
}