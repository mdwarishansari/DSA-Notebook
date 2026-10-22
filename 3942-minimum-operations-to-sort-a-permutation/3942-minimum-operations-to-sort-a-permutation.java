
import java.util.*;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;

        // Find the position of 0.
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        // Check whether the array follows increasing circular order.
        boolean asc = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != (nums[0] + i) % n) {
                asc = false;
                break;
            }
        }

        // Check whether the array follows decreasing circular order.
        boolean desc = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != (nums[0] - i + n) % n) {
                desc = false;
                break;
            }
        }

        // Neither orientation can produce the sorted permutation.
        if (!asc && !desc) {
            return -1;
        }

        // dist[orientation][rotation] stores minimum operations.
        int[][] dist = new int[2][n];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        // Encode a state as orientation * n + rotation.
        Queue<Integer> q = new LinkedList<>();
        dist[0][0] = 0;
        q.offer(0);

        // BFS finds the minimum number of operations.
        while (!q.isEmpty()) {
            int state = q.poll();
            int o = state / n;
            int k = state % n;
            int d = dist[o][k];

            // The original orientation can be sorted by rotating
            // left until 0 reaches the first position.
            if (o == 0 && asc && k == zero) {
                return d;
            }

            // Reversing the original array gives the other orientation.
            // Its 0 is at position n - 1 - zero.
            if (o == 1 && desc && k == (n - 1 - zero)) {
                return d;
            }

            // Operation 1: rotate left by one.
            int nk = (k + 1) % n;
            if (dist[o][nk] == -1) {
                dist[o][nk] = d + 1;
                q.offer(o * n + nk);
            }

            // Operation 2: reverse the entire array.
            // Reversal switches orientation and maps k to -k mod n.
            int no = 1 - o;
            nk = (n - k) % n;

            if (dist[no][nk] == -1) {
                dist[no][nk] = d + 1;
                q.offer(no * n + nk);
            }
        }

        return -1;
    }
}
