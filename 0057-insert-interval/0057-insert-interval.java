import java.util.*;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        // List to store the final intervals
        List<int[]> result = new ArrayList<>();

        int i = 0;

        // 1. Add all intervals that come completely before newInterval
        while (i < intervals.length &&
               intervals[i][1] < newInterval[0]) {

            result.add(intervals[i]);
            i++;
        }

        // 2. Merge all intervals that overlap with newInterval
        while (i < intervals.length &&
               intervals[i][0] <= newInterval[1]) {

            // Expand the start of newInterval if needed
            newInterval[0] =
                Math.min(newInterval[0], intervals[i][0]);

            // Expand the end of newInterval if needed
            newInterval[1] =
                Math.max(newInterval[1], intervals[i][1]);

            i++;
        }

        // Add the merged interval
        result.add(newInterval);

        // 3. Add all remaining intervals
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }

        // Convert List<int[]> to int[][]
        return result.toArray(new int[result.size()][]);
    }
}