import java.util.*;

class Solution {

    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();

        // Basic validation.
        if (s == null || words == null || words.length == 0) {
            return result;
        }

        int wordLength = words[0].length();
        int totalWords = words.length;

        // Total length that a valid concatenated substring must have.
        int totalLength = wordLength * totalWords;

        // If the required substring is longer than s,
        // no answer is possible.
        if (totalLength > s.length()) {
            return result;
        }

        /*
         * required stores how many times each word
         * must appear.
         *
         * Example:
         *
         * words = ["foo", "bar", "foo"]
         *
         * required:
         * foo -> 2
         * bar -> 1
         */
        Map<String, Integer> required = new HashMap<>();

        for (String word : words) {
            required.put(
                word,
                required.getOrDefault(word, 0) + 1
            );
        }

        /*
         * Because every word has the same length, we need to
         * consider different starting offsets.
         *
         * Example if wordLength = 3:
         *
         * offset 0:
         * 0, 3, 6, 9, ...
         *
         * offset 1:
         * 1, 4, 7, 10, ...
         *
         * offset 2:
         * 2, 5, 8, 11, ...
         *
         * This guarantees that we examine every possible
         * word-aligned substring.
         */
        for (int offset = 0; offset < wordLength; offset++) {

            int left = offset;
            int right = offset;

            /*
             * window stores the frequency of words currently
             * inside our sliding window.
             */
            Map<String, Integer> window = new HashMap<>();

            /*
             * count = number of complete words currently
             * inside the sliding window.
             */
            int count = 0;

            /*
             * Move right by one complete word at a time.
             */
            while (right + wordLength <= s.length()) {

                /*
                 * Extract the next word-sized chunk.
                 */
                String currentWord =
                    s.substring(right, right + wordLength);

                right += wordLength;

                /*
                 * Case 1:
                 *
                 * currentWord does not exist in words.
                 *
                 * Therefore, no valid concatenation can cross
                 * this word.
                 *
                 * Start a completely new window after it.
                 */
                if (!required.containsKey(currentWord)) {

                    window.clear();
                    count = 0;
                    left = right;

                    continue;
                }

                /*
                 * Case 2:
                 *
                 * currentWord is required.
                 *
                 * Add it to the current window.
                 */
                window.put(
                    currentWord,
                    window.getOrDefault(currentWord, 0) + 1
                );

                count++;

                /*
                 * If currentWord appears more times than allowed,
                 * shrink the window from the left.
                 *
                 * Example:
                 *
                 * required:
                 * foo -> 1
                 *
                 * window:
                 * foo -> 2
                 *
                 * We must remove words from the left until
                 * foo appears only once.
                 */
                while (window.get(currentWord)
                       > required.get(currentWord)) {

                    /*
                     * Get the leftmost word.
                     */
                    String leftWord =
                        s.substring(left, left + wordLength);

                    /*
                     * Remove it from the window.
                     */
                    window.put(
                        leftWord,
                        window.get(leftWord) - 1
                    );

                    count--;

                    /*
                     * Move left by one complete word.
                     */
                    left += wordLength;
                }

                /*
                 * If we currently have exactly the same number
                 * of words as required, then:
                 *
                 * 1. Every word belongs to words.
                 * 2. No word occurs more than allowed.
                 * 3. Number of words is exactly words.length.
                 *
                 * Therefore this is a valid answer.
                 */
                if (count == totalWords) {

                    result.add(left);

                    /*
                     * Continue searching for another overlapping
                     * valid window.
                     *
                     * Remove the leftmost word and move left
                     * forward by one word.
                     */
                    String leftWord =
                        s.substring(left, left + wordLength);

                    window.put(
                        leftWord,
                        window.get(leftWord) - 1
                    );

                    count--;

                    left += wordLength;
                }
            }
        }

        return result;
    }
}