import java.util.*;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> ans = new ArrayList<>();

        // If p is longer than s, an anagram is impossible
        if (p.length() > s.length()) {
            return ans;
        }

        // Store the frequency of each character in p
        int[] pFreq = new int[26];

        // Store the frequency of characters in the current window of s
        int[] winFreq = new int[26];

        // Count characters of p
        for (char ch : p.toCharArray()) {
            pFreq[ch - 'a']++;
        }

        int k = p.length();

        // Sliding window over s
        for (int i = 0; i < s.length(); i++) {

            // Add the current character to the window
            winFreq[s.charAt(i) - 'a']++;

            // If window size becomes greater than k,
            // remove the character from the left side
            if (i >= k) {
                winFreq[s.charAt(i - k) - 'a']--;
            }

            // When window size becomes k,
            // check if its frequency matches p
            if (i >= k - 1 && Arrays.equals(pFreq, winFreq)) {

                // Starting index of the current window
                ans.add(i - k + 1);
            }
        }

        return ans;
    }
}