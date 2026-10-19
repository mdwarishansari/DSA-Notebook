
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // Always binary search on the smaller array.
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int low = 0, high = m;

        // Binary search for the correct partition.
        while (low <= high) {
            int i = low + (high - low) / 2;
            int j = (m + n + 1) / 2 - i;

            // Left and right boundary values of both arrays.
            int l1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int r1 = (i == m) ? Integer.MAX_VALUE : nums1[i];

            int l2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int r2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // Check whether every left element is <= every right element.
            if (l1 <= r2 && l2 <= r1) {

                // Odd total: median is the largest element on the left.
                if ((m + n) % 2 == 1) {
                    return Math.max(l1, l2);
                }

                // Even total: average of the two middle elements.
                int leftMax = Math.max(l1, l2);
                int rightMin = Math.min(r1, r2);

                return ((double) leftMax + rightMin) / 2.0;
            }

            // Too many elements from nums1 on the left.
            else if (l1 > r2) {
                high = i - 1;
            }

            // Too few elements from nums1 on the left.
            else {
                low = i + 1;
            }
        }

        return 0.0; // Unreachable for valid LeetCode input.
    }
}
