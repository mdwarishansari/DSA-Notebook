
import java.util.Arrays;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;

        // Total operations available for both arrays.
        long k = (long) k1 + k2;

        // Store absolute differences between corresponding elements.
        long[] diff = new long[n];
        long max = 0, sum = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs((long) nums1[i] - nums2[i]);

            // Track the largest difference and the total difference.
            max = Math.max(max, diff[i]);
            sum += diff[i];
        }

        // If all differences can be eliminated, the answer is zero.
        if (sum <= k) return 0;

        // Binary search for the smallest maximum difference
        // achievable using at most k operations.
        long low = 0, high = max;

        while (low < high) {
            long mid = low + (high - low) / 2;
            long needed = 0;

            // Count operations needed to bring every difference
            // down to at most mid.
            for (long d : diff) {
                needed += Math.max(0L, d - mid);
            }

            if (needed <= k) {
                // We can reach this level; try a smaller one.
                high = mid;
            } else {
                // Too many operations are required.
                low = mid + 1;
            }
        }

        // Reduce every difference to at most the optimal level.
        long used = 0;
        for (int i = 0; i < n; i++) {
            long original = diff[i];
            diff[i] = Math.min(diff[i], low);

            // Count the operations used for this difference.
            used += original - diff[i];
        }

        // Operations still available after leveling the differences.
        k -= used;

        // Sort so the largest remaining differences come last.
        Arrays.sort(diff);

        // Use remaining operations to reduce the largest positive
        // differences by one each. This minimizes the sum of squares.
        for (int i = n - 1; i >= 0 && k > 0; i--) {
            if (diff[i] > 0) {
                diff[i]--;
                k--;
            }
        }

        // Calculate the final sum of squared differences.
        long answer = 0;
        for (long d : diff) {
            answer += d * d;
        }

        return answer;
    }
}
