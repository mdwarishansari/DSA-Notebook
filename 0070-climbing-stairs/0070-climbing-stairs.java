
class Solution {
    public int climbStairs(int n) {
        // Base cases: 1 way for step 1, 2 ways for step 2
        if (n <= 2) {
            return n;
        }

        // Ways to reach the first and second steps
        int a = 1;
        int b = 2;

        // Calculate the number of ways for each next step
        for (int i = 3; i <= n; i++) {
            int c = a + b; // Ways from the previous two steps

            a = b;         // Update previous step
            b = c;         // Update current step
        }

        // b stores the number of ways to reach step n
        return b;
    }
}
