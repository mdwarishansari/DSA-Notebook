class Solution {

    /*
     * Each segment-tree node represents one contiguous segment.
     *
     * product:
     *     Product of ALL elements in this segment modulo k.
     *
     * count[r]:
     *     Number of prefixes of this segment whose product % k == r.
     *
     * Example:
     *
     * k = 3
     * segment = [2, 2, 4]
     *
     * Prefixes:
     * [2]       -> 2 % 3 = 2
     * [2, 2]    -> 4 % 3 = 1
     * [2, 2, 4] -> 16 % 3 = 1
     *
     * Therefore:
     * count[0] = 0
     * count[1] = 2
     * count[2] = 1
     */
    static class Node {
        int product;
        int[] count;

        Node(int k) {
            count = new int[k];
        }
    }

    private int k;
    private Node[] tree;
    private int n;

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.n = nums.length;
        this.k = k;

        // Segment tree
        tree = new Node[4 * n];

        // Build the initial tree.
        build(1, 0, n - 1, nums);

        int[] answer = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            /*
             * Step 1:
             * Update nums[index] = value.
             *
             * This update persists for all future queries.
             */
            update(1, 0, n - 1, index, value);

            /*
             * Step 2:
             *
             * Remove nums[0 ... start-1].
             *
             * Therefore the remaining array starts at `start`.
             *
             * Then we can remove any suffix.
             *
             * So possible remaining arrays are:
             *
             * nums[start ... start]
             * nums[start ... start+1]
             * nums[start ... start+2]
             * ...
             * nums[start ... n-1]
             *
             * We need to count how many have product % k == x.
             */
            Node result = query(1, 0, n - 1, start, n - 1);

            answer[q] = result.count[x];
        }

        return answer;
    }

    // ---------------------------------------------------------
    // BUILD
    // ---------------------------------------------------------

    private void build(int node, int left, int right, int[] nums) {

        if (left == right) {

            tree[node] = new Node(k);

            int value = nums[left] % k;

            /*
             * A leaf has exactly one prefix:
             *
             * [nums[left]]
             *
             * Its product is nums[left] % k.
             */
            tree[node].product = value;
            tree[node].count[value] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid, nums);
        build(node * 2 + 1, mid + 1, right, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // ---------------------------------------------------------
    // MERGE TWO NODES
    // ---------------------------------------------------------

    private Node merge(Node leftNode, Node rightNode) {

        Node result = new Node(k);

        /*
         * Product of the complete segment:
         *
         * product(left + right)
         * =
         * product(left) * product(right)
         *
         * We only care about the remainder modulo k.
         */
        result.product =
                (leftNode.product * rightNode.product) % k;

        /*
         * First:
         *
         * Prefixes that end completely inside the LEFT segment
         * remain exactly the same.
         */
        for (int r = 0; r < k; r++) {
            result.count[r] += leftNode.count[r];
        }

        /*
         * Second:
         *
         * Consider a prefix of the RIGHT segment.
         *
         * Suppose:
         *
         * left product = P
         * right prefix product = R
         *
         * Then:
         *
         * combined product = P * R
         *
         * So:
         *
         * combined remainder = (P * R) % k
         */
        for (int r = 0; r < k; r++) {

            int newRemainder =
                    (leftNode.product * r) % k;

            result.count[newRemainder] +=
                    rightNode.count[r];
        }

        return result;
    }

    // ---------------------------------------------------------
    // POINT UPDATE
    // ---------------------------------------------------------

    private void update(
            int node,
            int left,
            int right,
            int index,
            int value) {

        if (left == right) {

            /*
             * We reached the element that must be updated.
             */
            tree[node] = new Node(k);

            int remainder = value % k;

            tree[node].product = remainder;
            tree[node].count[remainder] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {

            update(
                    node * 2,
                    left,
                    mid,
                    index,
                    value
            );

        } else {

            update(
                    node * 2 + 1,
                    mid + 1,
                    right,
                    index,
                    value
            );
        }

        /*
         * After updating one child, rebuild the current node
         * using its two children.
         */
        tree[node] =
                merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // ---------------------------------------------------------
    // RANGE QUERY
    // ---------------------------------------------------------

    private Node query(
            int node,
            int left,
            int right,
            int queryLeft,
            int queryRight) {

        /*
         * Completely inside the requested range.
         */
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }

        int mid = left + (right - left) / 2;

        /*
         * Range is completely inside LEFT child.
         */
        if (queryRight <= mid) {
            return query(
                    node * 2,
                    left,
                    mid,
                    queryLeft,
                    queryRight
            );
        }

        /*
         * Range is completely inside RIGHT child.
         */
        if (queryLeft > mid) {
            return query(
                    node * 2 + 1,
                    mid + 1,
                    right,
                    queryLeft,
                    queryRight
            );
        }

        /*
         * Range overlaps both children.
         *
         * Important:
         * We MUST merge them in LEFT -> RIGHT order
         * because multiplication prefixes depend on order.
         */
        Node leftResult = query(
                node * 2,
                left,
                mid,
                queryLeft,
                queryRight
        );

        Node rightResult = query(
                node * 2 + 1,
                mid + 1,
                right,
                queryLeft,
                queryRight
        );

        return merge(leftResult, rightResult);
    }
}