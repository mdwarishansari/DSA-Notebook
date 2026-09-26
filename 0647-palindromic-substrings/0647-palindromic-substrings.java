class Solution {

    public int countSubstrings(String s) {

        int count = 0;

        // Try every possible center
        for (int i = 0; i < s.length(); i++) {

            // Odd-length palindromes
            // Example: "aba"
            count += expandAroundCenter(s, i, i);

            // Even-length palindromes
            // Example: "abba"
            count += expandAroundCenter(s, i, i + 1);
        }

        return count;
    }


    // Expands around the given center
    // and counts every palindrome found.
    private int expandAroundCenter(String s, int left, int right) {

        int count = 0;

        while (left >= 0
                && right < s.length()
                && s.charAt(left) == s.charAt(right)) {

            // Current [left...right] is a palindrome
            count++;

            // Expand outward
            left--;
            right++;
        }

        return count;
    }
}