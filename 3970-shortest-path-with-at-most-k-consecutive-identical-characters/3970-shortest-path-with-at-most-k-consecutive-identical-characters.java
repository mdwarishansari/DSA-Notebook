
import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {

        // If even one character is not allowed, no valid path exists.
        if (k < 1) return -1;

        // Build adjacency list: {destination, weight}
        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }

        // dist[node][count] = minimum cost to reach this state.
        long[][] dist = new long[n][k + 1];

        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        // Priority Queue: {cost, node, consecutive count}
        PriorityQueue<long[]> pq = new PriorityQueue<>(
            Comparator.comparingLong(a -> a[0])
        );

        // Start from node 0. Its character has appeared once.
        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {

            long[] curr = pq.poll();

            long cost = curr[0];
            int u = (int) curr[1];
            int count = (int) curr[2];

            // Ignore outdated states.
            if (cost != dist[u][count]) continue;

            // Reached the destination with a valid path.
            if (u == n - 1) {
                return (int) cost;
            }

            // Explore all outgoing edges.
            for (int[] edge : adj[u]) {

                int v = edge[0];
                int weight = edge[1];

                // Calculate consecutive identical character count.
                int newCount;

                if (labels.charAt(u) == labels.charAt(v)) {
                    newCount = count + 1;
                } else {
                    newCount = 1;
                }

                // Reject paths exceeding the allowed limit.
                if (newCount > k) continue;

                long newCost = cost + weight;

                // Relax the new state if a shorter path is found.
                if (newCost < dist[v][newCount]) {

                    dist[v][newCount] = newCost;

                    pq.offer(new long[]{
                        newCost, v, newCount
                    });
                }
            }
        }

        // Destination cannot be reached through any valid path.
        return -1;
    }
}
