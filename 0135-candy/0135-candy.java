class Solution {
    public int candy(int[] ratings) {

        int n = ratings.length;

        // Give every child 1 candy initially
        int candy = n;

        int i = 1;

        while (i < n) {

            // Equal ratings don't create any additional requirement
            if (ratings[i] == ratings[i - 1]) {
                i++;
                continue;
            }

            // -------------------------------
            // Increasing slope -> Peak
            // -------------------------------
            int peak = 0;

            while (i < n && ratings[i] > ratings[i - 1]) {

                peak++;

                // Add candies needed for this increasing position
                candy += peak;

                i++;

                // Reached the end of the array
                if (i == n) {
                    return candy;
                }
            }

            // -------------------------------
            // Decreasing slope
            // -------------------------------
            int dip = 0;

            while (i < n && ratings[i] < ratings[i - 1]) {

                dip++;

                // Add candies needed for this decreasing position
                candy += dip;

                i++;
            }

            // The peak may have been counted twice.
            // Remove the smaller contribution because
            // the peak only needs to satisfy the longer slope.
            candy -= Math.min(peak, dip);
        }

        return candy;
    }
}