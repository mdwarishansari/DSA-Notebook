class Solution {

    public boolean checkInclusion(String s1, String s2) {

        // If s1 is longer, its permutation cannot fit inside s2.
        if (s1.length() > s2.length()) {
            return false;
        }

        // Frequency of characters required from s1.
        int[] need = new int[26];

        // Frequency of characters in the current window of s2.
        int[] window = new int[26];

        // Count characters of s1.
        for (char ch : s1.toCharArray()) {
            need[ch - 'a']++;
        }

        int k = s1.length();

        // Sliding window over s2.
        for (int i = 0; i < s2.length(); i++) {

            // Add current character to the window.
            window[s2.charAt(i) - 'a']++;

            // Keep the window size equal to s1.length().
            if (i >= k) {
                window[s2.charAt(i - k) - 'a']--;
            }

            // Once the window reaches size k,
            // compare its character frequencies with s1.
            if (java.util.Arrays.equals(need, window)) {
                return true;
            }
        }

        // No permutation of s1 was found.
        return false;
    }
}