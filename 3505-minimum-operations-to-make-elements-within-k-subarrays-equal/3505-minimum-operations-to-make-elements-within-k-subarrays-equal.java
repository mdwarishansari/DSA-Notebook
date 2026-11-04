
import java.util.*;

class Solution {

    // Stores the smaller and larger halves of the sliding window.
    class MultiSet {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int size = 0;
        long sum = 0;

        // Insert an element.
        void add(int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
            size++;
            sum += val;
        }

        // Remove one occurrence of an element.
        void remove(int val) {
            int count = map.get(val);

            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }

            size--;
            sum -= val;
        }

        // Get the smallest element.
        int first() {
            return map.firstKey();
        }

        // Get the largest element.
        int last() {
            return map.lastKey();
        }

        // Check whether an element exists.
        boolean contains(int val) {
            return map.containsKey(val);
        }
    }

    // Stores the lower and upper halves of the window.
    MultiSet low = new MultiSet();
    MultiSet high = new MultiSet();

    // Maintain a balanced window.
    void balance() {

        // Lower half can contain at most one extra element.
        while (low.size > high.size + 1) {
            int val = low.last();

            low.remove(val);
            high.add(val);
        }

        // Lower half must contain at least as many elements.
        while (low.size < high.size) {
            int val = high.first();

            high.remove(val);
            low.add(val);
        }
    }

    // Add an element to the sliding window.
    void insert(int val) {

        if (low.size == 0 || val <= low.last()) {
            low.add(val);
        } else {
            high.add(val);
        }

        balance();
    }

    // Remove an element from the sliding window.
    void erase(int val) {

        // Remove from the appropriate half.
        if (low.contains(val)) {
            low.remove(val);
        } else {
            high.remove(val);
        }

        balance();
    }

    // Calculate minimum operations using the median.
    long getCost() {

        // Median is the largest element of the lower half.
        long median = low.last();

        // Cost of converting lower and upper halves to median.
        long leftCost = median * low.size - low.sum;
        long rightCost = high.sum - median * high.size;

        return leftCost + rightCost;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Step 1: Calculate the cost of every window of size x.
        long[] cost = new long[n - x + 1];

        // Create the first window.
        for (int i = 0; i < x; i++) {
            insert(nums[i]);
        }

        cost[0] = getCost();

        // Slide the window through the array.
        for (int i = 1; i <= n - x; i++) {

            // Remove the element leaving the window.
            erase(nums[i - 1]);

            // Insert the new element entering the window.
            insert(nums[i + x - 1]);

            // Store the minimum cost for this window.
            cost[i] = getCost();
        }

        // Step 2: Dynamic Programming.
        long INF = Long.MAX_VALUE / 4;

        // Previous and current DP states.
        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        // Select exactly t non-overlapping subarrays.
        for (int t = 1; t <= k; t++) {

            Arrays.fill(curr, INF);

            // Process every prefix of the array.
            for (int i = 1; i <= n; i++) {

                // Option 1: Skip the current element.
                curr[i] = curr[i - 1];

                // Option 2: Select a window ending at i - 1.
                if (i >= x) {

                    int start = i - x;

                    // Check whether the previous t-1 windows
                    // can be selected before this window.
                    if (prev[start] != INF) {

                        long take = prev[start] + cost[start];

                        curr[i] = Math.min(curr[i], take);
                    }
                }
            }

            // Move to the next DP state.
            prev = curr;
            curr = new long[n + 1];
        }

        // Minimum operations to select k valid windows.
        return prev[n];
    }
}
