class Solution {
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // try skipping either left OR right
                return isPalindrome(s, left + 1, right) 
                    || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }

        return true;
    }

    // helper function to check palindrome in range
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}