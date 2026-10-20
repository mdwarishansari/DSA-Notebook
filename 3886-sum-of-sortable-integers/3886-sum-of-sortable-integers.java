
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        // prefMax[i] = maximum element before index i
        int[] prefMax = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefMax[i + 1] = Math.max(prefMax[i], nums[i]);
        }

        // suffMin[i] = minimum element from index i to the end
        int[] suffMin = new int[n + 1];
        suffMin[n] = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            suffMin[i] = Math.min(nums[i], suffMin[i + 1]);
        }

        // prefDrop[i] = number of descents up to index i
        int[] prefDrop = new int[n];
        for (int i = 1; i < n; i++) {
            prefDrop[i] = prefDrop[i - 1]
                    + (nums[i - 1] > nums[i] ? 1 : 0);
        }

        int ans = 0;

        // Enumerate divisors of n in pairs
        for (int i = 1; i <= n / i; i++) {
            if (n % i != 0) continue;

            if (check(nums, prefMax, suffMin, prefDrop, i)) {
                ans += i;
            }

            int k = n / i;

            // Avoid checking a square-root divisor twice
            if (k != i && check(nums, prefMax, suffMin, prefDrop, k)) {
                ans += k;
            }
        }

        return ans;
    }

    private boolean check(int[] nums, int[] prefMax,
                          int[] suffMin, int[] prefDrop, int k) {
        int n = nums.length;

        // Check every consecutive block of length k
        for (int start = 0; start < n; start += k) {
            int end = start + k - 1;

            // Count descents inside the block
            int drops = prefDrop[end] - prefDrop[start];

            // Include the circular connection: last -> first
            if (nums[end] > nums[start]) {
                drops++;
            }

            // More than one circular descent cannot be fixed by rotation
            if (drops > 1) {
                return false;
            }

            // Previous elements must not exceed any later element
            if (prefMax[start] > suffMin[start]) {
                return false;
            }
        }

        return true;
    }
}
