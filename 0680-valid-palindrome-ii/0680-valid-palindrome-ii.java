class Solution {

    public boolean validPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        // Normal two-pointer palindrome check
        while (left < right) {

            // Characters match, so move inward
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            }

            // Characters don't match
            else {

                // We can delete either:
                // 1. s[left]
                // 2. s[right]
                //
                // If either remaining substring is a palindrome,
                // the answer is true.
                return isPalindrome(s, left + 1, right)
                    || isPalindrome(s, left, right - 1);
            }
        }

        // No mismatch found
        return true;
    }


    // Checks whether s[left...right] is a palindrome
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