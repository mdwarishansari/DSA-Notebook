class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        // Every single element subarray satisfies prod == lcm * gcd (since a == a * a is false, wait: 
        // actually for length 1, prod = a, lcm = a, gcd = a -> a == a * a only if a == 1, 
        // however any subarray of length 2 always satisfies prod(a, b) == lcm(a, b) * gcd(a, b)).
        int maxLen = 1;

        // Calculate the maximum possible LCM of the entire array to prevent integer overflow
        // when multiplying elements together in the inner loop.
        long maxLcm = 1;
        for (int num : nums) {
            maxLcm = lcm(maxLcm, num);
        }

        // Iterate over every possible starting index 'i' of a subarray
        for (int i = 0; i < n; i++) {
            long currentProd = 1;
            long currentGcd = 0;
            long currentLcm = 1;

            // Expand the subarray to ending index 'j'
            for (int j = i; j < n; j++) {
                currentProd *= nums[j];
                
                // Optimization & Overflow Guard: 
                // Since gcd >= 1, prod == lcm * gcd implies prod >= lcm.
                // Also, currentLcm can never exceed maxLcm, and currentGcd <= nums[j].
                // If currentProd grows larger than maxLcm * nums[j], it can never match lcm * gcd again.
                if (currentProd > maxLcm * nums[j]) {
                    break;
                }

                // Update running GCD and LCM for the subarray nums[i...j]
                currentGcd = gcd(currentGcd, nums[j]);
                currentLcm = lcm(currentLcm, nums[j]);

                // Check the "product equivalent" condition: prod(arr) == lcm(arr) * gcd(arr)[cite: 1]
                if (currentProd == currentLcm * currentGcd) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    // Helper method to compute Greatest Common Divisor (GCD) using Euclidean algorithm
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Helper method to compute Least Common Multiple (LCM)
    private long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return (a / gcd(a, b)) * b;
    }
}