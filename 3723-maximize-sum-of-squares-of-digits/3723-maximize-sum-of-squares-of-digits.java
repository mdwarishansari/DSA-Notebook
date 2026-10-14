
class Solution {
    public String maxSumOfSquares(int num, int sum) {

        // A digit can be at most 9.
        // Therefore, num digits can have a maximum sum of num * 9.
        if (sum > num * 9) {
            return "";
        }

        // Used to build the answer efficiently.
        StringBuilder ans = new StringBuilder();

        // Construct exactly num digits.
        for (int i = 0; i < num; i++) {

            // Greedily take the largest possible digit.
            // If sum is greater than 9, choose 9.
            // Otherwise, use the remaining sum.
            int digit = Math.min(9, sum);

            // Append the chosen digit to the answer.
            ans.append(digit);

            // Remove the chosen digit from the remaining sum.
            sum -= digit;
        }

        // Return the maximum-scoring number as a string.
        return ans.toString();
    }
}
