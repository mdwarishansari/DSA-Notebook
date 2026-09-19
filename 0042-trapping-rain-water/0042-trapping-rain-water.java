class Solution {

    public int trap(int[] height) {

        // Pointer starting from the left side.
        int left = 0;

        // Pointer starting from the right side.
        int right = height.length - 1;

        // Maximum height encountered from the left.
        int leftMax = 0;

        // Maximum height encountered from the right.
        int rightMax = 0;

        // Total amount of trapped rain water.
        int water = 0;

        /*
         * Keep processing until the two pointers cross.
         *
         * Each position is processed only once,
         * so the overall time complexity is O(n).
         */
        while (left <= right) {

            /*
             * If the current left height is smaller than
             * or equal to the current right height,
             * process the LEFT side.
             *
             * The left side is the limiting boundary.
             */
            if (height[left] <= height[right]) {

                /*
                 * If current height is greater than or equal
                 * to the highest wall we've seen from the left,
                 * this becomes our new left boundary.
                 *
                 * No water is trapped at this position.
                 */
                if (height[left] >= leftMax) {

                    leftMax = height[left];

                } else {

                    /*
                     * Current height is smaller than leftMax.
                     *
                     * Therefore, water can be trapped here.
                     *
                     * Water height =
                     * highest left wall - current wall
                     */
                    water += leftMax - height[left];
                }

                // Move left pointer toward the center.
                left++;

            } else {

                /*
                 * The current right height is smaller.
                 *
                 * Therefore, process the RIGHT side.
                 *
                 * The right side is the limiting boundary.
                 */
                if (height[right] >= rightMax) {

                    // Update the highest wall seen from the right.
                    rightMax = height[right];

                } else {

                    /*
                     * Current height is smaller than rightMax,
                     * so water can be trapped here.
                     *
                     * Water height =
                     * highest right wall - current wall
                     */
                    water += rightMax - height[right];
                }

                // Move right pointer toward the center.
                right--;
            }
        }

        // Return the total amount of trapped water.
        return water;
    }
}