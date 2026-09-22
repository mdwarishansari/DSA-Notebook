class Solution {

    public int minSubArrayLen(int target, int[] nums) {

        /*
         * Sliding window:
         *
         * [left ........ right]
         *
         * sum = sum of all elements inside the window.
         */

        int left = 0;

        // Current window sum.
        int sum = 0;

        /*
         * Start with a very large value because
         * we don't know the answer yet.
         */
        int minLength = Integer.MAX_VALUE;


        /*
         * Expand the window using right.
         */
        for (int right = 0; right < nums.length; right++) {

            /*
             * Add nums[right] to the current window.
             */
            sum += nums[right];


            /*
             * If the current sum is >= target,
             * we have a valid subarray.
             *
             * But we want the MINIMUM length.
             *
             * Therefore keep removing elements from
             * the left as long as the window remains valid.
             */
            while (sum >= target) {

                /*
                 * Current window:
                 *
                 * [left ........ right]
                 *
                 * Length:
                 *
                 * right - left + 1
                 */
                int currentLength = right - left + 1;

                /*
                 * Keep the smallest valid window found.
                 */
                minLength = Math.min(
                    minLength,
                    currentLength
                );


                /*
                 * Remove the leftmost element
                 * so that we can try to make the
                 * window even smaller.
                 */
                sum -= nums[left];

                left++;
            }
        }


        /*
         * If minLength was never changed,
         * no subarray had sum >= target.
         *
         * In that case the problem asks us to
         * return 0.
         */
        if (minLength == Integer.MAX_VALUE) {
            return 0;
        }

        return minLength;
    }
}