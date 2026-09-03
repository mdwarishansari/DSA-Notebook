class Solution {

    public boolean uniformArray(int[] nums1) {

        // Find the smallest element.
        int min = nums1[0];

        // Track whether the array contains both parities.
        boolean hasEven = false;
        boolean hasOdd = false;

        for (int num : nums1) {

            // Update the minimum value.
            min = Math.min(min, num);

            // Check the parity of the current number.
            if ((num & 1) == 0) {
                hasEven = true;   // Number is even
            } else {
                hasOdd = true;    // Number is odd
            }
        }

        // If all numbers are already even or all are already odd,
        // no operation is needed.
        if (!hasEven || !hasOdd) {
            return true;
        }

        /*
         * The array contains both even and odd numbers.
         *
         * If the minimum is odd:
         *   - Keep odd numbers unchanged.
         *   - For every even number x, use:
         *         x - min
         *     Since min is odd, the parity becomes odd.
         *
         * Therefore, construction is possible.
         *
         * If the minimum is even:
         *   - The minimum cannot subtract any smaller element.
         *   - It must remain even.
         *   - Since the array also contains an odd number,
         *     we cannot make the entire array have one parity.
         */
        return (min & 1) == 1;
    }
}