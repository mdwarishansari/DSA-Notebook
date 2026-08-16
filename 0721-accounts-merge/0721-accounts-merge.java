import java.util.*;

class Solution {

    // ============================
    // Disjoint Set (Union Find)
    // ============================
    class DisjointSet {

        int[] parent;
        int[] size;

        public DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i; // every node is its own parent initially
                size[i] = 1;   // size of every component is 1
            }
        }

        // Find ultimate parent with path compression
        public int findParent(int node) {

            if (parent[node] == node) {
                return node;
            }

            return parent[node] = findParent(parent[node]);
        }

        // Union by size
        public void unionBySize(int u, int v) {

            int ulpU = findParent(u);
            int ulpV = findParent(v);

            // already in same component
            if (ulpU == ulpV) {
                return;
            }

            // attach smaller component to larger component
            if (size[ulpU] < size[ulpV]) {
                parent[ulpU] = ulpV;
                size[ulpV] += size[ulpU];
            } else {
                parent[ulpV] = ulpU;
                size[ulpU] += size[ulpV];
            }
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        int n = accounts.size();

        // Create DSU for all accounts
        DisjointSet ds = new DisjointSet(n);

        /*
         * Stores:
         * email -> account index
         *
         * Example:
         * johnsmith@mail.com -> 0
         */
        Map<String, Integer> emailToAccount = new HashMap<>();

        // =====================================
        // Step 1: Connect accounts having
        // common emails
        // =====================================
        for (int i = 0; i < n; i++) {

            List<String> account = accounts.get(i);

            // start from index 1 because index 0 is name
            for (int j = 1; j < account.size(); j++) {

                String email = account.get(j);

                // first time seeing this email
                if (!emailToAccount.containsKey(email)) {
                    emailToAccount.put(email, i);
                }
                // email already belongs to another account
                else {
                    int existingAccount = emailToAccount.get(email);

                    // merge both account nodes
                    ds.unionBySize(i, existingAccount);
                }
            }
        }

        // =====================================
        // Step 2: Collect emails according to
        // their ultimate parent
        // =====================================

        List<String>[] mergedEmails = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            mergedEmails[i] = new ArrayList<>();
        }

        for (Map.Entry<String, Integer> entry : emailToAccount.entrySet()) {

            String email = entry.getKey();
            int accountIndex = entry.getValue();

            // find representative account
            int parent = ds.findParent(accountIndex);

            mergedEmails[parent].add(email);
        }

        // =====================================
        // Step 3: Build final answer
        // =====================================
        List<List<String>> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            // skip empty groups
            if (mergedEmails[i].size() == 0) {
                continue;
            }

            // emails must be sorted
            Collections.sort(mergedEmails[i]);

            List<String> current = new ArrayList<>();

            // add account name
            current.add(accounts.get(i).get(0));

            // add all sorted emails
            current.addAll(mergedEmails[i]);

            result.add(current);
        }

        return result;
    }
}