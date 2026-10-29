
import java.util.*;

class Solution {

    private List<Integer>[] adj;
    private int[] parent, depth, size, heavy;
    private int[] head, pos, nodeAtPos, seg;
    private int timer;
    private char[] labels;

    public List<Boolean> palindromePath(int n, int[][] edges,
                                         String s, String[] queries) {

        labels = s.toCharArray();

        // 1. Build the adjacency list.
        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        nodeAtPos = new int[n];

        Arrays.fill(parent, -1);
        Arrays.fill(heavy, -1);

        // 2. Iteratively calculate parent, depth and traversal order.
        int[] order = new int[n];
        int[] stack = new int[n];
        int top = 0, count = 0;

        stack[top++] = 0;

        while (top > 0) {
            int u = stack[--top];
            order[count++] = u;

            for (int v : adj[u]) {
                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        // 3. Calculate subtree sizes and heavy children.
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            size[u] = 1;
            int maxSize = 0;

            for (int v : adj[u]) {
                if (parent[v] != u) {
                    continue;
                }

                size[u] += size[v];

                if (size[v] > maxSize) {
                    maxSize = size[v];
                    heavy[u] = v;
                }
            }
        }

        // 4. Assign heavy-light decomposition positions.
        int[] chainStack = new int[n];
        int chainTop = 0;

        chainStack[chainTop++] = 0;
        timer = 0;

        while (chainTop > 0) {
            int start = chainStack[--chainTop];

            for (int u = start; u != -1; u = heavy[u]) {
                head[u] = start;
                pos[u] = timer;
                nodeAtPos[timer++] = u;

                // Light children start new chains.
                for (int v : adj[u]) {
                    if (parent[v] == u && v != heavy[u]) {
                        chainStack[chainTop++] = v;
                    }
                }
            }
        }

        // 5. Build the segment tree with ACTUAL character masks.
        seg = new int[4 * n];
        build(1, 0, n - 1);

        List<Boolean> answer = new ArrayList<>();

        // 6. Process updates and path queries.
        for (String q : queries) {
            String[] parts = q.trim().split("\\s+");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                labels[u] = c;

                // Update this node's character mask.
                update(1, 0, n - 1,
                       pos[u], 1 << (c - 'a'));

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int mask = pathXor(u, v);

                // True if zero or one character has odd frequency.
                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    // Build segment tree using HLD position -> original node mapping.
    private void build(int node, int left, int right) {
        if (left == right) {
            int u = nodeAtPos[left];

            // FIX: Store the actual character bit, not zero.
            seg[node] = 1 << (labels[u] - 'a');
            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        seg[node] = seg[node * 2] ^ seg[node * 2 + 1];
    }

    // XOR all character masks in the inclusive range [ql, qr].
    private int query(int node, int left, int right,
                      int ql, int qr) {

        if (ql <= left && right <= qr) {
            return seg[node];
        }

        if (right < ql || left > qr) {
            return 0;
        }

        int mid = left + (right - left) / 2;

        return query(node * 2, left, mid, ql, qr)
             ^ query(node * 2 + 1, mid + 1, right, ql, qr);
    }

    // Replace one position and update its ancestors.
    private void update(int node, int left, int right,
                        int index, int value) {

        if (left == right) {
            seg[node] = value;
            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        seg[node] = seg[node * 2] ^ seg[node * 2 + 1];
    }

    // Return the XOR mask for every character on path u -> v.
    private int pathXor(int u, int v) {
        int mask = 0;
        int n = pos.length;

        // Process heavy chains until both nodes share a chain.
        while (head[u] != head[v]) {

            // Always process the chain whose head is deeper.
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }

            mask ^= query(1, 0, n - 1,
                          pos[head[u]], pos[u]);

            u = parent[head[u]];
        }

        // Include the remaining segment, including the LCA.
        int left = Math.min(pos[u], pos[v]);
        int right = Math.max(pos[u], pos[v]);

        mask ^= query(1, 0, n - 1, left, right);

        return mask;
    }
}
