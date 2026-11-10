import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }

        // Standard queue for level-order traversal (BFS)
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // Traverse the tree level by level
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            boolean foundX = false;
            boolean foundY = false;

            // Process all nodes at the current depth
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                // Check if current node is one of the target values
                if (current.val == x) foundX = true;
                if (current.val == y) foundY = true;

                // Check if x and y are siblings (share the same parent)
                // If they share the same parent, they cannot be cousins
                if (current.left != null && current.right != null) {
                    if ((current.left.val == x && current.right.val == y) ||
                        (current.left.val == y && current.right.val == x)) {
                        return false;
                    }
                }

                // Add children to the queue for the next level
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }

            // If both nodes are found at the current level, they are cousins
            if (foundX && foundY) {
                return true;
            }

            // If only one is found at this level, they are at different depths
            if (foundX || foundY) {
                return false;
            }
        }

        return false;
    }
}