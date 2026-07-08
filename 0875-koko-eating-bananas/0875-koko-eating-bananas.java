class Solution {

    /*
     * Returns true if Koko can finish all banana piles
     * within h hours while eating at 'speed' bananas/hour.
     */
    private boolean canEatAll(int[] piles, int speed, int h) {

        // Total hours needed at the current speed
        long hours = 0;

        // Calculate hours required for each pile
        for (int bananas : piles) {

            /*
             * Ceiling Division
             *
             * Instead of:
             * Math.ceil((double) bananas / speed)
             *
             * We use integer arithmetic:
             *
             * (bananas + speed - 1) / speed
             *
             * Example:
             * bananas = 11
             * speed = 4
             *
             * (11 + 4 - 1) / 4
             * = 14 / 4
             * = 3
             */
            hours += (bananas + speed - 1) / speed;
        }

        // If required hours are within the limit,
        // this speed is valid.
        return hours <= h;
    }

    public int minEatingSpeed(int[] piles, int h) {

        // Minimum possible eating speed
        int left = 1;

        // Maximum possible eating speed
        // (largest pile size)
        int right = 0;

        // Find the maximum pile
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        /*
         * Binary Search on the Answer
         *
         * We want the FIRST speed that works.
         */
        while (left < right) {

            // Calculate middle speed safely
            int mid = left + (right - left) / 2;

            // If this speed is enough,
            // try to find a smaller valid speed.
            if (canEatAll(piles, mid, h)) {

                right = mid;
            }

            // Speed is too slow.
            // Need to increase it.
            else {

                left = mid + 1;
            }
        }

        /*
         * left == right
         *
         * This is the minimum valid speed.
         */
        return left;
    }
}