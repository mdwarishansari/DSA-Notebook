class Solution {
    public int minEatingSpeed(int[] piles, int h) {

        int low = 1;
        int high = 0;

        // Find the maximum pile
        for (int pile : piles) {
            high = Math.max(high, pile);
        }

        // Binary search for minimum valid speed
        while (low < high) {

            int mid = low + (high - low) / 2;

            long hours = 0;

            // Calculate total hours needed at speed mid
            for (int pile : piles) {

                // Ceiling division: ceil(pile / mid)
                hours += (pile + mid - 1) / mid;
            }

            if (hours <= h) {

                // mid works, try a smaller speed
                high = mid;

            } else {

                // mid is too slow
                low = mid + 1;
            }
        }

        return low;
    }
}