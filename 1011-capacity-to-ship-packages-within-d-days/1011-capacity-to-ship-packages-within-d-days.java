class Solution {
    public int shipWithinDays(int[] weights, int days) {

        // Minimum possible capacity:
        // The ship must be able to carry the heaviest package.
        int low = 0;

        // Maximum possible capacity:
        // If we ship everything in one day, the capacity
        // must be equal to the total weight of all packages.
        int high = 0;

        for (int w : weights) {
            low = Math.max(low, w); // Heaviest package
            high += w;              // Total weight
        }

        // Binary search for the minimum capacity
        // that can ship all packages within 'days' days.
        while (low < high) {

            // Try the middle capacity.
            // This form also avoids integer overflow.
            int mid = low + (high - low) / 2;

            // Start with the first day.
            int usedDays = 1;

            // Weight currently loaded on the ship.
            int currentWeight = 0;

            // Try shipping every package using capacity 'mid'.
            for (int w : weights) {

                // If adding this package exceeds the capacity,
                // we must start a new day.
                if (currentWeight + w > mid) {
                    usedDays++;

                    // The current package becomes the first
                    // package of the new day's shipment.
                    currentWeight = 0;
                }

                // Load the package onto the ship.
                currentWeight += w;
            }

            // If we can ship everything within the required
            // number of days, 'mid' is a valid capacity.
            //
            // But we want the MINIMUM capacity, so search left.
            if (usedDays <= days) {
                high = mid;
            }

            // If we need more days than allowed, the capacity
            // is too small, so search right.
            else {
                low = mid + 1;
            }
        }

        // When low == high, we have found the smallest
        // capacity that can ship all packages within 'days'.
        return low;
    }
}