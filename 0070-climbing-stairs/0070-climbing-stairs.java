class Solution {

    public int climbStairs(int n) {

        // Base case:
        // There is only 1 way to reach the first step.
        if (n == 1) {
            return 1;
        }

        // Number of ways to reach:
        // step 1 -> 1 way
        // step 2 -> 2 ways
        int prev2 = 1; // ways[i - 2]
        int prev1 = 2; // ways[i - 1]

        // Calculate the number of ways for every step
        // starting from step 3.
        for (int i = 3; i <= n; i++) {

            // Current ways = ways of previous step
            //              + ways of two steps before
            int current = prev1 + prev2;

            // Move the values forward:
            // prev2 becomes the previous step's value
            // prev1 becomes the current step's value
            prev2 = prev1;
            prev1 = current;
        }

        // prev1 now contains the number of ways
        // to reach the nth step.
        return prev1;
    }
}