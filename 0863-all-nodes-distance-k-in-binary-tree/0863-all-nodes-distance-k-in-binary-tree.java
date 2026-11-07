import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> result = new ArrayList<>();
        if (root == null || target == null) {
            return result;
        }

        // Map to store child -> parent references
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParentMap(root, null, parentMap);

        // Queue for BFS traversal starting from target
        Queue<TreeNode> queue = new LinkedList<>();
        // Set to track visited nodes so we do not cycle back
        Set<TreeNode> visited = new HashSet<>();

        queue.offer(target);
        visited.add(target);

        int currentDistance = 0;

        // Level-order BFS traversal
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // If we have reached distance k, collect all node values in the current level
            if (currentDistance == k) {
                for (int i = 0; i < levelSize; i++) {
                    result.add(queue.poll().val);
                }
                return result;
            }

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                // 1. Traverse left child
                if (current.left != null && !visited.contains(current.left)) {
                    visited.add(current.left);
                    queue.offer(current.left);
                }

                // 2. Traverse right child
                if (current.right != null && !visited.contains(current.right)) {
                    visited.add(current.right);
                    queue.offer(current.right);
                }

                // 3. Traverse parent
                TreeNode parent = parentMap.get(current);
                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    queue.offer(parent);
                }
            }

            currentDistance++;
        }

        return result;
    }

    /**
     * Helper method to map every node to its parent pointer using DFS
     */
    private void buildParentMap(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> parentMap) {
        if (node == null) {
            return;
        }

        if (parent != null) {
            parentMap.put(node, parent);
        }

        buildParentMap(node.left, node, parentMap);
        buildParentMap(node.right, node, parentMap);
    }
}