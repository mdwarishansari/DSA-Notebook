
import java.util.*;

class Solution {

    // Helper method to calculate the sum of digits.
    // Example: 123 -> 1 + 2 + 3 = 6
    private int digitSum(int num) {

        int sum = 0;

        // Extract each digit until num becomes zero.
        while (num > 0) {

            // Add the last digit to sum.
            sum += num % 10;

            // Remove the last digit.
            num /= 10;
        }

        return sum;
    }

    public int minSwaps(int[] nums) {

        int n = nums.length;

        // Step 1: Create a copy of the original array.
        // We must preserve nums to track original positions.
        Integer[] sorted = new Integer[n];

        for (int i = 0; i < n; i++) {
            sorted[i] = nums[i];
        }

        // Step 2: Sort by digit sum.
        // If sums are equal, sort by actual number.
        Arrays.sort(sorted, (a, b) -> {

            int sumA = digitSum(a);
            int sumB = digitSum(b);

            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }

            // Tie-breaker: smaller number comes first.
            return Integer.compare(a, b);
        });

        // Step 3: Store the correct position
        // of every number in the sorted array.
        Map<Integer, Integer> target = new HashMap<>();

        for (int i = 0; i < n; i++) {
            target.put(sorted[i], i);
        }

        // Step 4: Track visited indices
        // to avoid processing the same cycle twice.
        boolean[] visited = new boolean[n];

        int swaps = 0;

        // Step 5: Find all permutation cycles.
        for (int i = 0; i < n; i++) {

            // Skip positions already processed.
            if (visited[i]) {
                continue;
            }

            int current = i;
            int cycleSize = 0;

            // Follow target positions until
            // we reach an already visited index.
            while (!visited[current]) {

                // Mark current index as visited.
                visited[current] = true;

                // Count elements in this cycle.
                cycleSize++;

                // Move to the correct position
                // of the current element.
                current = target.get(nums[current]);
            }

            // A cycle of size k requires k - 1 swaps.
            // A cycle of size 1 needs zero swaps.
            if (cycleSize > 1) {
                swaps += cycleSize - 1;
            }
        }

        // Step 6: Return minimum swaps.
        return swaps;
    }
}
