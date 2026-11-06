
import java.util.*;

class Solution {

    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        int m = queries.length;

        // Sort logs based on request time (logs[i][1]).
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // Store each query with its original index.
        // queries[i][0] = query time
        // queries[i][1] = original index
        int[][] sortedQueries = new int[m][2];

        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        // Sort queries based on their time.
        Arrays.sort(sortedQueries,
            (a, b) -> Integer.compare(a[0], b[0]));

        // Stores the number of requests received by each server
        // within the current sliding window.
        Map<Integer, Integer> map = new HashMap<>();

        // Final answer array.
        int[] answer = new int[m];

        // Pointer for processing logs.
        int left = 0;
        int right = 0;

        // Number of servers having at least one active request.
        int activeServers = 0;

        // Process queries in increasing order of time.
        for (int i = 0; i < m; i++) {

            int queryTime = sortedQueries[i][0];
            int originalIndex = sortedQueries[i][1];

            // Calculate the inclusive time window.
            int startTime = queryTime - x;

            // STEP 1: Add all requests with time <= queryTime.
            while (right < logs.length &&
                   logs[right][1] <= queryTime) {

                int serverId = logs[right][0];

                // Get the current request count.
                int count = map.getOrDefault(serverId, 0);

                // If this is the first active request,
                // this server becomes active.
                if (count == 0) {
                    activeServers++;
                }

                // Increase the server's request count.
                map.put(serverId, count + 1);

                right++;
            }

            // STEP 2: Remove requests with time < startTime.
            // The interval is inclusive, so requests at
            // startTime must NOT be removed.
            while (left < right &&
                   logs[left][1] < startTime) {

                int serverId = logs[left][0];

                int count = map.get(serverId);

                // Decrease the request count.
                count--;

                if (count == 0) {

                    // This server has no active requests now.
                    map.remove(serverId);

                    activeServers--;

                } else {

                    map.put(serverId, count);
                }

                left++;
            }

            // STEP 3: Count servers having zero requests.
            answer[originalIndex] = n - activeServers;
        }

        return answer;
    }
}
