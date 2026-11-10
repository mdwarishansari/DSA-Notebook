import java.util.Arrays;

class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[i] represents the minimum total cost to reach step i
        int[] dp = new int[n + 1];

        // Initialize all dp values to infinity except the starting step 0
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // Starting at step 0 with cost 0

        // Iterate through each step from 1 to n to find the optimal previous step
        for (int j = 1; j <= n; j++) {
            // Can jump to step j from (j - 1), (j - 2), or (j - 3)
            for (int jump = 1; jump <= 3; jump++) {
                int i = j - jump; // Previous step

                if (i >= 0 && dp[i] != Integer.MAX_VALUE) {
                    // Cost formula: costs[j] + (j - i)^2
                    // Note: costs array is 1-indexed in problem description, so costs[j - 1] in 0-indexed Java array
                    int stepCost = costs[j - 1] + jump * jump;
                    dp[j] = Math.min(dp[j], dp[i] + stepCost);
                }
            }
        }

        // Return minimum total cost to reach step n
        return dp[n];
    }
}