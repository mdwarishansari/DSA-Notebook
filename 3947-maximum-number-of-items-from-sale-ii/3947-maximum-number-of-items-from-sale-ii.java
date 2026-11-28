import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        long remainingBudget = budget;

        // Track the cheapest item price and the largest factor value (1 <= factor <= n).
        long minPrice = Long.MAX_VALUE;
        int maxFactor = 0;

        for (int[] item : items) {
            int factor = item[0];
            long price = item[1];
            if (price < minPrice) {
                minPrice = price;
            }
            if (factor > maxFactor) {
                maxFactor = factor;
            }
        }

        // cnt[f] = number of items with factor == f.
        int[] cnt = new int[maxFactor + 1];
        for (int[] item : items) {
            cnt[item[0]]++;
        }

        // multiplesCount[f] = total number of items whose factor is a multiple of f.
        int[] multiplesCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (cnt[f] == 0) {
                continue;
            }
            for (int mult = f; mult <= maxFactor; mult += f) {
                multiplesCount[f] += cnt[mult];
            }
        }

        // Each item with factor f can unlock free copies for other items (j != i)
        // whose factor is divisible by f. There are (multiplesCount[f] - 1) such items.
        // Buying `k` copies of this item (up to `gain` copies) yields `k` purchased + `k` free = `2 * k` items.
        // Buying `k` copies of the cheapest item at `minPrice` yields `k` items at cost `k * minPrice`.
        // Thus, a boosted copy is strictly attractive when `price < 2 * minPrice`.
        List<long[]> boostedOffers = new ArrayList<>();
        for (int[] item : items) {
            int factor = item[0];
            long price = item[1];
            int gain = multiplesCount[factor] - 1;

            if (gain > 0 && price < 2 * minPrice) {
                boostedOffers.add(new long[]{price, gain});
            }
        }

        // Greedily take the cheapest boosted offers first to maximize items per unit of budget.
        boostedOffers.sort(Comparator.comparingLong(a -> a[0]));

        long totalItems = 0;
        for (long[] offer : boostedOffers) {
            long price = offer[0];
            long maxBoostedCopies = offer[1];

            if (remainingBudget < price) {
                break;
            }

            long take = Math.min(maxBoostedCopies, remainingBudget / price);
            totalItems += 2 * take; // `take` purchased copies + `take` free copies
            remainingBudget -= take * price;
        }

        // Spend any remaining budget on as many copies of the cheapest item as possible.
        totalItems += remainingBudget / minPrice;

        return (int) totalItems;
    }
}