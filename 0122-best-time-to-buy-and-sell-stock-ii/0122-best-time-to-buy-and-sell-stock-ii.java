class Solution {
    public int maxProfit(int[] prices) {

        // Stores the total profit we can make.
        int profit = 0;

        // Start from the second day and compare
        // each price with the previous day's price.
        for (int i = 1; i < prices.length; i++) {

            // If today's price is higher than yesterday's,
            // we can make a profit from this increase.
            if (prices[i] > prices[i - 1]) {

                // Add today's increase to our total profit.
                profit += prices[i] - prices[i - 1];
            }
        }

        // Return the maximum total profit.
        return profit;
    }
}