class Solution {

    public int countCommas(int n) {

        // Stores the total number of commas.
        int count = 0;

        // The first number that needs a comma is 1,000.
        // After that, every number gets at least one comma.
        int threshold = 1000;

        // Keep checking powers of 1000:
        // 1,000       -> 1 comma
        // 1,000,000   -> 2 commas
        // 1,000,000,000 -> 3 commas
        while (threshold <= n) {

            /*
             * Every number from 'threshold' to 'n'
             * contains at least one comma at this level.
             *
             * Example: n = 1002
             *
             * Numbers:
             * 1000 -> 1 comma
             * 1001 -> 1 comma
             * 1002 -> 1 comma
             *
             * Count = 1002 - 1000 + 1 = 3
             */
            count += n - threshold + 1;

            // Move to the next comma level.
            threshold *= 1000;
        }

        return count;
    }
}