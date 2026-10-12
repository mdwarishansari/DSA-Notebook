import java.util.*;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final int MOD = 1_000_000_007;
        int[] bits = new int[32];

        // Count how many numbers contain each set bit.
        for (int num : nums) {
            for (int b = 0; b < 32; b++) {
                if ((num & (1 << b)) != 0) {
                    bits[b]++;
                }
            }
        }

        long ans = 0;

        // Construct the largest possible numbers one by one.
        for (int i = 0; i < k; i++) {
            long num = 0;

            // Use every bit that is still available.
            for (int b = 0; b < 32; b++) {
                if (bits[b] > 0) {
                    num |= (1L << b);
                    bits[b]--;
                }
            }

            // Add the square modulo MOD.
            ans = (ans + num * num) % MOD;
        }

        return (int) ans;
    }
}