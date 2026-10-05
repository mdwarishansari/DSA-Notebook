class Solution {
    public int minDays(int[] bloomDay, int m, int k) {

        int n = bloomDay.length;

        // Need m bouquets, each requiring k flowers.
        // If total required flowers are more than available flowers,
        // making the bouquets is impossible.
        if ((long) m * k > n)
            return -1;

        int lo = Integer.MAX_VALUE;
        int hi = Integer.MIN_VALUE;

        // Find the minimum and maximum blooming days.
        for (int day : bloomDay) {
            lo = Math.min(lo, day);
            hi = Math.max(hi, day);
        }

        // Binary search for the minimum valid day.
        while (lo < hi) {

            int mid = lo + (hi - lo) / 2;

            int bouquets = 0;
            int consecutive = 0;

            // Check how many bouquets can be made by 'mid' day.
            for (int day : bloomDay) {

                // Flower has bloomed by mid.
                if (day <= mid) {
                    consecutive++;

                    // We have k adjacent flowers.
                    if (consecutive == k) {
                        bouquets++;

                        // These flowers are used.
                        consecutive = 0;
                    }
                }

                // Flower has not bloomed, so adjacency breaks.
                else {
                    consecutive = 0;
                }
            }

            // Enough bouquets can be made.
            if (bouquets >= m) {
                hi = mid;
            }

            // Not enough bouquets, need more days.
            else {
                lo = mid + 1;
            }
        }

        return lo;
    }
}