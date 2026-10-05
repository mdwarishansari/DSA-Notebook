class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {

        int n = dist.length;

        // The first n-1 trains each require at least 1 hour
        // because the next train can only leave at an integer hour.
        if (hour <= n - 1)
            return -1;

        int lo = 1;
        int hi = 10_000_000;

        // Binary search for the minimum valid speed
        while (lo < hi) {

            int mid = lo + (hi - lo) / 2;

            double time = 0;

            // For all trains except the last one,
            // we must wait until the next integer hour.
            for (int i = 0; i < n - 1; i++) {
                time += Math.ceil((double) dist[i] / mid);
            }

            // For the last train, no waiting is required.
            time += (double) dist[n - 1] / mid;

            // This speed is fast enough.
            // Try to find an even smaller speed.
            if (time <= hour) {
                hi = mid;
            }

            // This speed is too slow.
            // Increase the speed.
            else {
                lo = mid + 1;
            }
        }

        return lo;
    }
}