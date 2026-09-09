class Solution {

    public long countCommas(long n) {

        long answer = 0;

        /*
         * 'power' represents the first number that needs
         * a comma at the current comma position.
         *
         * 1000     -> first comma
         * 1000000  -> second comma
         * 1000000000 -> third comma
         * ...
         */
        long power = 1000;

        /*
         * For every comma position:
         *
         * Numbers from 'power' to 'n' contain this comma.
         *
         * Count = n - power + 1
         *
         * Example:
         * n = 1002, power = 1000
         * count = 1002 - 1000 + 1 = 3
         */
        while (power <= n) {

            answer += n - power + 1;

            /*
             * Move to the next comma position.
             *
             * 1000 -> 1,000,000
             * 1,000,000 -> 1,000,000,000
             *
             * Since n <= 10^15, multiplying by 1000
             * is safe within long.
             */
            power *= 1000;
        }

        return answer;
    }
}