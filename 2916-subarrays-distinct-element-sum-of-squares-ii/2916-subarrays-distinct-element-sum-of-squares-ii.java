
import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final long MOD = 1_000_000_007L;

    public int sumCounts(int[] nums) {
        int n = nums.length;

        // Stores the most recent index of every value.
        Map<Integer, Integer> lastSeen = new HashMap<>();

        // Each leaf represents a subarray starting at an index.
        SegmentTree seg = new SegmentTree(n);

        long answer = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            // Find the previous occurrence of the current value.
            Integer prev = lastSeen.get(x);

            if (prev == null) {
                // x is new: every subarray ending at i gains
                // one distinct element.
                seg.update(1, 0, n - 1, 0, i, 1);
            } else {
                // Subarrays starting after the previous occurrence
                // gain one distinct element.
                seg.update(1, 0, n - 1, prev + 1, i, 1);
            }

            // Record the current occurrence.
            lastSeen.put(x, i);

            // Sum of squares for all subarrays ending at index i.
            answer = (answer + seg.querySquares()) % MOD;
        }

        return (int) answer;
    }

    static class SegmentTree {
        private final int n;
        private final long[] sum;
        private final long[] squareSum;
        private final long[] lazy;

        SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            squareSum = new long[4 * n];
            lazy = new long[4 * n];
        }

        // Apply an increment to every value in a segment.
        private void apply(int node, int left, int right, long delta) {
            long len = right - left + 1L;

            // If every value becomes v + delta:
            // (v + delta)^2 = v^2 + 2 * delta * v + delta^2
            squareSum[node] = (
                squareSum[node]
                + 2L * delta * sum[node]
                + len * delta % MOD * delta
            ) % MOD;

            // Update the sum after changing every value.
            sum[node] = (
                sum[node] + len * delta
            ) % MOD;

            // Save the pending increment for the children.
            lazy[node] += delta;
        }

        // Push pending increments down to the child segments.
        private void push(int node, int left, int right) {
            if (lazy[node] == 0 || left == right) {
                return;
            }

            int mid = left + (right - left) / 2;
            long delta = lazy[node];

            apply(node * 2, left, mid, delta);
            apply(node * 2 + 1, mid + 1, right, delta);

            lazy[node] = 0;
        }

        // Add delta to every value in the inclusive range [ql, qr].
        void update(
            int node, int left, int right,
            int ql, int qr, long delta
        ) {
            // No overlap.
            if (ql > right || qr < left || ql > qr) {
                return;
            }

            // Complete overlap.
            if (ql <= left && right <= qr) {
                apply(node, left, right, delta);
                return;
            }

            // Partial overlap: propagate pending updates first.
            push(node, left, right);

            int mid = left + (right - left) / 2;

            update(node * 2, left, mid, ql, qr, delta);
            update(node * 2 + 1, mid + 1, right, ql, qr, delta);

            // Merge information from both children.
            sum[node] = (
                sum[node * 2] + sum[node * 2 + 1]
            ) % MOD;

            squareSum[node] = (
                squareSum[node * 2]
                + squareSum[node * 2 + 1]
            ) % MOD;
        }

        // Returns the sum of squares for all active subarrays.
        long querySquares() {
            return squareSum[1];
        }
    }
}
