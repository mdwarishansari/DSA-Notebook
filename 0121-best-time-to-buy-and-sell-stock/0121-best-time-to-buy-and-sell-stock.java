class Solution {
    public int maxProfit(int[] prices) {

        // Cheapest stock price seen so far.
        int minPrice = prices[0];

        // Maximum profit found so far.
        int maxProfit = 0;

        // Start from the second day because
        // prices[0] is already our initial minimum.
        for (int i = 1; i < prices.length; i++) {

            // Current price is the selling price.
            // Buy at the cheapest price seen before today.
            int profit = prices[i] - minPrice;

            // Keep the best profit found so far.
            maxProfit = Math.max(maxProfit, profit);

            // If today's price is cheaper, make it
            // the new best buying price.
            minPrice = Math.min(minPrice, prices[i]);
        }

        // If prices always decrease, maxProfit remains 0,
        // meaning no profitable transaction is possible.
        return maxProfit;
    }
}