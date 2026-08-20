import java.util.*;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {

        // Sort intervals by their starting point
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Number of intervals that need to be removed
        int removals = 0;

        // End of the last interval we decided to keep
        int previousEnd = intervals[0][1];

        // Start checking from the second interval
        for (int i = 1; i < intervals.length; i++) {

            // Check if current interval overlaps with previous interval
            if (intervals[i][0] < previousEnd) {

                // Overlap exists, so one interval must be removed
                removals++;

                // Keep the interval that ends earlier
                previousEnd = Math.min(previousEnd, intervals[i][1]);

            } else {

                // No overlap, so keep the current interval
                previousEnd = intervals[i][1];
            }
        }

        // Return the minimum number of intervals removed
        return removals;
    }
}