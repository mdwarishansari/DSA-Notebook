class Solution {

    // parent[i] stores the parent of node i.
    private int[] parent;

    // rank[i] helps keep the tree balanced while merging.
    private int[] rank;

    public int[] findRedundantConnection(int[][] edges) {

        int n = edges.length;

        // Initialize DSU for nodes 1 to n.
        parent = new int[n + 1];
        rank = new int[n + 1];

        // Initially, every node is its own parent.
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // Process every edge in input order.
        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];

            // If both nodes already have the same root,
            // they are already connected, so this edge creates a cycle.
            if (find(u) == find(v)) {
                return edge;
            }

            // Otherwise, merge their two components.
            union(u, v);
        }

        // The problem guarantees that a redundant edge exists.
        return new int[0];
    }

    // Finds the root/representative of a node.
    // Path compression makes future find operations faster.
    private int find(int node) {

        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }

        return parent[node];
    }

    // Combines the components containing u and v.
    private void union(int u, int v) {

        int rootU = find(u);
        int rootV = find(v);

        // Already connected.
        if (rootU == rootV) {
            return;
        }

        // Attach the smaller-rank tree under the larger-rank tree.
        if (rank[rootU] < rank[rootV]) {

            parent[rootU] = rootV;

        } else if (rank[rootU] > rank[rootV]) {

            parent[rootV] = rootU;

        } else {

            // Same rank: choose rootU as the new root.
            parent[rootV] = rootU;

            // Increase the rank because the tree height may increase.
            rank[rootU]++;
        }
    }
}