class Solution {
    public void reverseString(char[] s) {

        // Two pointers:
        // left starts from beginning
        // right starts from end
        int left = 0;
        int right = s.length - 1;

        // Continue until the pointers meet
        while (left < right) {

            // Swap characters
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            // Move both pointers towards the center
            left++;
            right--;
        }
    }
}