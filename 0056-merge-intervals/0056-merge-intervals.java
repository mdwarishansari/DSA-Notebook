class Solution {
    public int[][] merge(int[][] intervals) {

        // Sort intervals based on starting point
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();

        // Current interval being merged
        int start = intervals[0][0];
        int end = intervals[0][1];

        // Traverse all remaining intervals
        for (int i = 1; i < intervals.length; i++) {

            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];

            // Overlapping interval
            if (currStart <= end) {

                // Extend current merged interval
                end = Math.max(end, currEnd);

            } else {

                // No overlap, save previous merged interval
                merged.add(new int[]{start, end});

                // Start a new interval
                start = currStart;
                end = currEnd;
            }
        }

        // Add the final merged interval
        merged.add(new int[]{start, end});

        // Convert List<int[]> to int[][]
        return merged.toArray(new int[merged.size()][]);
    }
}                                                                                                                                                                             