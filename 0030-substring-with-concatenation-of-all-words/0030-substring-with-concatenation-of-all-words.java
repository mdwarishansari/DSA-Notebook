import java.util.*;

class Solution {

    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();

        // Basic safety check.
        if (s.length() == 0 || words.length == 0) {
            return result;
        }

        /*
         * All words have the same length.
         */
        int wordLen = words[0].length();

        /*
         * Total number of words that must be present
         * in a valid substring.
         */
        int wordCount = words.length;

        /*
         * Total length of a valid concatenated substring.
         *
         * Example:
         *
         * words = ["foo", "bar"]
         * wordLen = 3
         * wordCount = 2
         *
         * totalLen = 6
         */
        int totalLen = wordLen * wordCount;

        /*
         * If the required concatenated length is greater
         * than the entire string, no answer is possible.
         */
        if (totalLen > s.length()) {
            return result;
        }

        /*
         * need stores the required frequency of every word.
         *
         * Example:
         *
         * words = ["foo", "bar", "foo"]
         *
         * need:
         * foo -> 2
         * bar -> 1
         */
        Map<String, Integer> need = new HashMap<>();

        for (String word : words) {
            need.put(word, need.getOrDefault(word, 0) + 1);
        }

        /*
         * We need to check different starting offsets.
         *
         * If wordLen = 3, offsets are:
         *
         * 0, 1, 2
         *
         * This ensures that we consider every possible
         * word-aligned starting position.
         */
        for (int offset = 0; offset < wordLen; offset++) {

            // Left boundary of the sliding window.
            int left = offset;

            // Right boundary of the sliding window.
            int right = offset;

            /*
             * Number of complete words currently inside
             * the sliding window.
             */
            int count = 0;

            /*
             * Stores the frequency of words currently
             * present inside our sliding window.
             */
            Map<String, Integer> window = new HashMap<>();

            /*
             * Take one complete word at a time.
             *
             * right + wordLen must stay inside s.
             */
            while (right + wordLen <= s.length()) {

                /*
                 * Extract exactly one word-sized chunk.
                 */
                String word = s.substring(right, right + wordLen);

                // Move right by one complete word.
                right += wordLen;

                /*
                 * CASE 1:
                 *
                 * This word does not exist in 'need'.
                 *
                 * Therefore, the current window can never
                 * form a valid concatenation.
                 *
                 * Start a completely new window.
                 */
                if (!need.containsKey(word)) {

                    window.clear();

                    count = 0;

                    // Start after this invalid word.
                    left = right;

                    continue;
                }

                /*
                 * CASE 2:
                 *
                 * The word is required.
                 *
                 * Add it to our current window.
                 */
                window.put(
                    word,
                    window.getOrDefault(word, 0) + 1
                );

                count++;

                /*
                 * If this word occurs more times than allowed,
                 * shrink the window from the left.
                 *
                 * Example:
                 *
                 * need:
                 * foo -> 1
                 *
                 * window:
                 * foo -> 2
                 *
                 * We must remove words from the left until
                 * foo's frequency becomes valid again.
                 */
                while (window.get(word) > need.get(word)) {

                    // Get the leftmost word.
                    String leftWord =
                        s.substring(left, left + wordLen);

                    // Remove one occurrence of that word.
                    window.put(
                        leftWord,
                        window.get(leftWord) - 1
                    );

                    // Move left by one complete word.
                    left += wordLen;

                    // One word has been removed from the window.
                    count--;
                }

                /*
                 * CASE 3:
                 *
                 * If the number of words in the current
                 * window equals the number of words required,
                 * then every required word appears with the
                 * correct frequency.
                 */
                if (count == wordCount) {

                    /*
                     * The current window starts at 'left',
                     * so 'left' is a valid starting index.
                     */
                    result.add(left);

                    /*
                     * We continue searching for overlapping
                     * answers.
                     *
                     * Remove the leftmost word and continue
                     * expanding the window.
                     */
                    String leftWord =
                        s.substring(left, left + wordLen);

                    window.put(
                        leftWord,
                        window.get(leftWord) - 1
                    );

                    left += wordLen;
                    count--;
                }
            }
        }

        return result;
    }
}