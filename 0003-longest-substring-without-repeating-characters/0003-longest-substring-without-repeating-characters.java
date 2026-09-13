class Solution {
    public int lengthOfLongestSubstring(String s) {

        // Stores whether a character is currently present
        // inside our sliding window.
        boolean[] seen = new boolean[128];

        // Left boundary of the sliding window.
        int left = 0;

        // Stores the maximum length found so far.
        int maxLength = 0;

        // Move the right boundary through the string.
        for (int right = 0; right < s.length(); right++) {

            char current = s.charAt(right);

            /*
             * If the current character is already inside
             * our window, the window contains a duplicate.
             *
             * We cannot keep this duplicate, so we move
             * the left boundary forward until the duplicate
             * is removed.
             */
            while (seen[current]) {
                char removed = s.charAt(left);

                // Remove the leftmost character from the window.
                seen[removed] = false;

                left++;
            }

            /*
             * The current character is now not present
             * in the window, so we can safely add it.
             */
            seen[current] = true;

            /*
             * Current window is:
             *
             *     left ........ right
             *
             * Its length is:
             *
             *     right - left + 1
             */
            int currentLength = right - left + 1;

            // Keep the longest valid window.
            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }
}