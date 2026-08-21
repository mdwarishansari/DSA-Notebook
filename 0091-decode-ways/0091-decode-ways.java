class Solution {

    public int numDecodings(String s) {

        int n = s.length();

        // dp[i] = number of ways to decode
        // the first i characters of the string.
        int[] dp = new int[n + 1];

        // Empty string has one way to decode:
        // choose nothing.
        // This is important when we form a valid
        // two-digit number at the beginning.
        dp[0] = 1;

        // First character:
        // '0' cannot be decoded alone.
        if (s.charAt(0) != '0') {
            dp[1] = 1;
        }

        // Start from the second character.
        for (int i = 2; i <= n; i++) {

            // ---------------------------------
            // Choice 1: Use current digit alone
            // ---------------------------------

            char current = s.charAt(i - 1);

            // Any digit from '1' to '9' can
            // represent a valid letter.
            if (current != '0') {
                dp[i] += dp[i - 1];
            }


            // ---------------------------------
            // Choice 2: Use previous + current
            // ---------------------------------

            int twoDigit =
                (s.charAt(i - 2) - '0') * 10
                + (s.charAt(i - 1) - '0');

            // Only numbers 10 to 26 represent
            // valid letters.
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        // Number of ways to decode the entire string.
        return dp[n];
    }
}