import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class Solution {
    // 16 bits easily holds any bitmask for n <= 12 (up to 4095) without colliding with `node`.
    private static final int SHIFT = 16;
    private static final int MASK_BITS = (1 << SHIFT) - 1;

    public int shortestPathLength(int[][] graph) {
        int n = graph.length;

        // 0 or 1 node already visited with 0 edges traversed.
        if (n <= 1) {
            return 0;
        }

        // Target bitmask where all n nodes (bits 0 through n - 1) are marked visited.
        final int FULL_MASK = (1 << n) - 1;

        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        // Multi-source BFS: start simultaneously from every node with only that node visited.
        for (int i = 0; i < n; i++) {
            int startState = encode(i, 1 << i);
            queue.offer(startState);
            visited.add(startState);
        }

        int steps = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // Explore all states reachable in the current number of `steps`.
            for (int i = 0; i < levelSize; i++) {
                int state = queue.poll();
                int currNode = state >>> SHIFT;
                int currMask = state & MASK_BITS;

                // First time reaching FULL_MASK guarantees the shortest path length.
                if (currMask == FULL_MASK) {
                    return steps;
                }

                // Traverse to each adjacent node and update the visited bitmask.
                for (int nextNode : graph[currNode]) {
                    int nextMask = currMask | (1 << nextNode);
                    int nextState = encode(nextNode, nextMask);

                    if (!visited.contains(nextState)) {
                        visited.add(nextState);
                        queue.offer(nextState);
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    // Packs (node, mask) into a single 32-bit int using SHIFT = 16.
    private int encode(int node, int mask) {
        return (node << SHIFT) | mask;
    }
}