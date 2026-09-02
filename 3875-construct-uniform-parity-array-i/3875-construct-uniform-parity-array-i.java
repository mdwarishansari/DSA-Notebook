class Solution {

    public boolean uniformArray(int[] nums1) {

        /*
         * There are only two possibilities:
         *
         * 1. All numbers have the same parity.
         *    -> Keep every number as it is.
         *       nums2[i] = nums1[i]
         *       Therefore, all elements have the same parity.
         *
         * 2. The array contains both even and odd numbers.
         *    -> For every element, subtract an element of the
         *       opposite parity.
         *
         *       even - odd = odd
         *       odd  - even = odd
         *
         *    Therefore, we can make every element of nums2 odd.
         *
         * Hence, a valid uniform-parity array can ALWAYS be constructed.
         */

        return true;
    }
}