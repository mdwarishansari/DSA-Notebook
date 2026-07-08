class Solution {
    public int mySqrt(int x) {

        // Special case:
        // Square root of 0 is 0.
        if (x == 0)
            return 0;

        // Search space is from 1 to x.
        int left = 1;
        int right = x;

        // Stores the largest valid square root found so far.
        int ans = 0;

        // Continue searching while the search space is valid.
        while (left <= right) {

            // Calculate middle safely to avoid integer overflow.
            int mid = left + (right - left) / 2;

            /*
             * Instead of checking:
             *      mid * mid <= x
             *
             * we check:
             *      mid <= x / mid
             *
             * This avoids integer overflow for large values of x.
             */

            // Case 1:
            // mid² is less than or equal to x.
            // This means mid is a valid answer.
            if (mid <= x / mid) {

                // Store this valid answer.
                ans = mid;

                // Try to find a larger valid square root.
                left = mid + 1;
            }

            // Case 2:
            // mid² is greater than x.
            // mid is too large, so search in the left half.
            else {
                right = mid - 1;
            }
        }

        // Return the largest integer whose square is <= x.
        return ans;
    }
}