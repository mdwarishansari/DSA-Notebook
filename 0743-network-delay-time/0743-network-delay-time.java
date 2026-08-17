import java.util.*;

class Solution {

    public int networkDelayTime(int[][] times, int n, int k) {

        // Adjacency List
        ArrayList<int[]>[] adj = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Directed Graph
        for (int[] edge : times) {

            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            adj[u].add(new int[]{v, w});
        }

        // Distance Array
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // Source node distance = 0
        dist[k] = 0;

        // Min Heap
        // [node, distance]
        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> a[1] - b[1]);

        pq.offer(new int[]{k, 0});

        while (!pq.isEmpty()) {

            int[] current = pq.poll();

            int node = current[0];
            int currDist = current[1];

            // Ignore outdated entries
            if (currDist > dist[node]) {
                continue;
            }

            // Visit neighbors
            for (int[] neighbor : adj[node]) {

                int nextNode = neighbor[0];
                int weight = neighbor[1];

                // Relaxation
                if (dist[node] + weight < dist[nextNode]) {

                    dist[nextNode] = dist[node] + weight;

                    pq.offer(
                        new int[]{nextNode, dist[nextNode]}
                    );
                }
            }
        }

        int maxTime = 0;

        // Check all nodes
        for (int i = 1; i <= n; i++) {

            // Unreachable node found
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }

            maxTime = Math.max(maxTime, dist[i]);
        }

        return maxTime;
    }
}