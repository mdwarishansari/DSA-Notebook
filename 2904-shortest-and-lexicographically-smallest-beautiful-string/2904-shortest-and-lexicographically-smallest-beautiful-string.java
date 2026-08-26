class Solution {

    public String shortestBeautifulSubstring(String s, int k) {

        int n = s.length();

        // Store positions of all 1s
        int[] ones = new int[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ones[count++] = i;
            }
        }

        // Not enough 1s in the entire string
        if (count < k) {
            return "";
        }

        int bestStart = -1;
        int bestEnd = -1;
        int bestLength = Integer.MAX_VALUE;

        /*
         * Every group of k consecutive 1s gives one possible
         * shortest beautiful substring.
         *
         * Example:
         * s = 100011001
         * k = 3
         *
         * Positions of 1s:
         * [0, 4, 5, 8]
         *
         * Groups:
         * [0, 4, 5] -> "10001"
         * [4, 5, 8] -> "11001"
         *
         * Both have length 5.
         * "11001" is lexicographically smaller than "10001".
         */
        for (int i = 0; i + k - 1 < count; i++) {

            int start = ones[i];
            int end = ones[i + k - 1];

            int length = end - start + 1;

            // Found a shorter substring
            if (length < bestLength) {
                bestLength = length;
                bestStart = start;
                bestEnd = end;
            }

            // Same length -> choose lexicographically smaller one
            else if (length == bestLength) {

                /*
                 * Since both strings have the same length,
                 * compare them lexicographically.
                 */
                if (s.substring(start, end + 1)
                        .compareTo(s.substring(bestStart, bestEnd + 1)) < 0) {

                    bestStart = start;
                    bestEnd = end;
                }
            }
        }

        return s.substring(bestStart, bestEnd + 1);
    }
}