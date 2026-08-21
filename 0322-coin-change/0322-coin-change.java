import java.util.Arrays;

class Solution {

    public int coinChange(int[] coins, int amount) {

        // dp[i] = minimum number of coins needed
        //         to make amount i.
        int[] dp = new int[amount + 1];

        // Initially, treat every amount as impossible.
        // amount + 1 is safely larger than any possible
        // answer because using coin 1 would require at most
        // 'amount' coins.
        Arrays.fill(dp, amount + 1);

        // Base case:
        // 0 coins are needed to make amount 0.
        dp[0] = 0;

        // Build the answer for every amount from 1 to amount.
        for (int currentAmount = 1; currentAmount <= amount; currentAmount++) {

            // Try every available coin.
            for (int coin : coins) {

                // We can only use this coin if it does not
                // make the amount negative.
                if (coin <= currentAmount) {

                    // Use the current coin.
                    //
                    // dp[currentAmount - coin]
                    // = minimum coins needed for the remaining amount.
                    //
                    // +1 = the current coin we just used.
                    dp[currentAmount] = Math.min(
                        dp[currentAmount],
                        dp[currentAmount - coin] + 1
                    );
                }
            }
        }

        // If dp[amount] was never updated, the amount
        // cannot be formed.
        if (dp[amount] == amount + 1) {
            return -1;
        }

        return dp[amount];
    }
}