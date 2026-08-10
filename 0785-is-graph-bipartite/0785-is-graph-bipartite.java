class Solution {

    public boolean isBipartite(int[][] graph) {

        // 0 = uncolored, 1 = first group, 2 = second group.
        int[] color = new int[graph.length];

        // Check every node because the graph can be disconnected.
        for (int node = 0; node < graph.length; node++) {

            // Start DFS only if this node has not been colored.
            if (color[node] == 0) {

                // Give the starting node the first color.
                color[node] = 1;

                // Check whether this component can be colored properly.
                if (!dfs(graph, node, color)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfs(int[][] graph, int node, int[] color) {

        // Check every neighbor connected to the current node.
        for (int neighbor : graph[node]) {

            // If the neighbor is not colored, give it the opposite color.
            if (color[neighbor] == 0) {

                color[neighbor] = 3 - color[node];

                // Continue checking the neighbor's connections.
                if (!dfs(graph, neighbor, color)) {
                    return false;
                }
            }

            // Connected nodes cannot have the same color.
            else if (color[neighbor] == color[node]) {
                return false;
            }
        }

        // No conflict was found.
        return true;
    }
}