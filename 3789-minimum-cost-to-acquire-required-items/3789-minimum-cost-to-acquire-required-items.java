class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // Strategy 1: Buy only Type 1 and Type 2 items separately to meet both needs.
        long costOnlySingles = (long) need1 * cost1 + (long) need2 * cost2;

        // Strategy 2: Buy only Type 3 items (bundles) to satisfy the maximum required amount.
        long costOnlyBundles = (long) costBoth * Math.max(need1, need2);

        // Strategy 3: Buy Type 3 items for the overlapping requirement (min(need1, need2)), 
        // and cover any remaining deficit using individual Type 1 or Type 2 items.
        int overlap = Math.min(need1, need2);
        long costMixed = (long) costBoth * overlap 
                       + (long) (need1 - overlap) * cost1 
                       + (long) (need2 - overlap) * cost2;

        // Return the minimum cost among all valid strategies
        return Math.min(costOnlySingles, Math.min(costOnlyBundles, costMixed));
    }
}