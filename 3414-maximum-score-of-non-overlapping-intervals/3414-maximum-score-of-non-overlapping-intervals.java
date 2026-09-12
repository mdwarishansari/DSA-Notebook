import java.util.*;

class Solution {

    // Stores one interval along with its original index.
    static class Interval {
        int start;
        int end;
        int weight;
        int index;

        Interval(int start, int end, int weight, int index) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.index = index;
        }
    }

    // Stores the best answer for a DP state.
    static class State {
        long score;
        int[] indices;

        State(long score, int[] indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        /*
         * Step 1:
         * Convert the input into Interval objects.
         * We keep the original index because the answer
         * must contain ORIGINAL indices.
         */
        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            List<Integer> in = intervals.get(i);

            int start = in.get(0);
            int end = in.get(1);
            int weight = in.get(2);

            arr[i] = new Interval(start, end, weight, i);
        }

        /*
         * Step 2:
         * Sort intervals by ending position.
         *
         * Why?
         * Weighted interval scheduling becomes much easier
         * when intervals are sorted by their end time.
         */
        Arrays.sort(arr, (a, b) -> {
            if (a.end != b.end) {
                return Integer.compare(a.end, b.end);
            }

            // Tie-breaker is not essential for correctness,
            // but makes ordering deterministic.
            if (a.start != b.start) {
                return Integer.compare(a.start, b.start);
            }

            return Integer.compare(a.index, b.index);
        });

        /*
         * Step 3:
         * For every interval i, find how many intervals
         * finish BEFORE arr[i].start.
         *
         * Important:
         *
         * Intervals sharing a boundary are considered overlapping.
         *
         * Example:
         * [1, 3] and [3, 5]
         *
         * These overlap.
         *
         * Therefore we need:
         *
         * previousEnd < currentStart
         *
         * NOT:
         *
         * previousEnd <= currentStart
         */
        int[] previous = new int[n];

        int[] ends = new int[n];

        for (int i = 0; i < n; i++) {
            ends[i] = arr[i].end;
        }

        for (int i = 0; i < n; i++) {
            previous[i] = lowerBound(ends, arr[i].start);
        }

        /*
         * Step 4:
         *
         * dp[k][i] =
         * best answer using the FIRST i intervals
         * while choosing AT MOST k intervals.
         *
         * k can only be 0..4.
         *
         * This is why the complexity stays small.
         */
        State[][] dp = new State[5][n + 1];

        /*
         * Base case:
         * Choosing at most 0 intervals gives score 0
         * and an empty index list.
         */
        for (int i = 0; i <= n; i++) {
            dp[0][i] = new State(0, new int[0]);
        }

        /*
         * Build the DP.
         */
        for (int k = 1; k <= 4; k++) {

            // With zero intervals considered, answer is empty.
            dp[k][0] = new State(0, new int[0]);

            for (int i = 1; i <= n; i++) {

                /*
                 * Current interval is arr[i - 1].
                 */
                Interval current = arr[i - 1];

                /*
                 * OPTION 1:
                 * Do NOT choose the current interval.
                 *
                 * Then the answer is simply the answer
                 * from the first i-1 intervals.
                 */
                State skip = dp[k][i - 1];

                /*
                 * OPTION 2:
                 * Choose the current interval.
                 *
                 * To avoid overlap, we can only combine it with
                 * intervals that end BEFORE current.start.
                 *
                 * previous[i - 1] tells us exactly how many such
                 * intervals exist.
                 */
                State previousState = dp[k - 1][previous[i - 1]];

                /*
                 * Add current interval's weight.
                 */
                long takeScore =
                        previousState.score + current.weight;

                /*
                 * Add current original index into the index array.
                 *
                 * The answer must be sorted by original index,
                 * so we insert it into the correct position.
                 */
                int[] takeIndices =
                        insertSorted(previousState.indices, current.index);

                State take = new State(takeScore, takeIndices);

                /*
                 * Choose the better of:
                 *
                 * 1. skip
                 * 2. take
                 *
                 * First compare total score.
                 *
                 * If scores are equal, compare index arrays
                 * lexicographically.
                 */
                dp[k][i] = better(skip, take);
            }
        }

        /*
         * dp[4][n] contains the best answer using AT MOST 4 intervals.
         *
         * Because dp is already "at most k", this directly gives
         * the required answer.
         */
        return dp[4][n].indices;
    }

    /*
     * Finds the first position where:
     *
     * arr[position] >= target
     *
     * This gives the number of intervals whose end is
     * strictly smaller than target.
     */
    private int lowerBound(int[] arr, int target) {

        int left = 0;
        int right = arr.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /*
     * Inserts x into an already sorted array.
     *
     * Example:
     *
     * [1, 3, 7]
     *
     * insert 4
     *
     * => [1, 3, 4, 7]
     */
    private int[] insertSorted(int[] arr, int x) {

        int[] result = new int[arr.length + 1];

        int pos = 0;

        /*
         * Find insertion position.
         */
        while (pos < arr.length && arr[pos] < x) {
            pos++;
        }

        /*
         * Copy elements before insertion point.
         */
        for (int i = 0; i < pos; i++) {
            result[i] = arr[i];
        }

        /*
         * Insert x.
         */
        result[pos] = x;

        /*
         * Copy remaining elements.
         */
        for (int i = pos; i < arr.length; i++) {
            result[i + 1] = arr[i];
        }

        return result;
    }

    /*
     * Returns whichever state is better.
     *
     * Priority:
     *
     * 1. Larger score
     * 2. If score is equal -> lexicographically smaller indices
     */
    private State better(State a, State b) {

        if (a.score > b.score) {
            return a;
        }

        if (b.score > a.score) {
            return b;
        }

        /*
         * Same score.
         * Choose lexicographically smaller index array.
         */
        if (compareLexicographically(a.indices, b.indices) <= 0) {
            return a;
        } else {
            return b;
        }
    }

    /*
     * Compares two integer arrays lexicographically.
     *
     * Example:
     *
     * [1, 3, 5] < [1, 4, 2]
     *
     * because at the first different position:
     *
     * 3 < 4
     *
     * If one array is a prefix of another,
     * the shorter one is considered smaller.
     */
    private int compareLexicographically(int[] a, int[] b) {

        int len = Math.min(a.length, b.length);

        for (int i = 0; i < len; i++) {

            if (a[i] != b[i]) {
                return Integer.compare(a[i], b[i]);
            }
        }

        return Integer.compare(a.length, b.length);
    }
}