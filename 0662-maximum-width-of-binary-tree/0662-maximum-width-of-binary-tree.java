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
import java.util.*;

class Solution {
    // Helper class to store a node along with its calculated index position
    static class NodeInfo {
        TreeNode node;
        int index;

        NodeInfo(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxWidth = 0;
        Queue<NodeInfo> queue = new LinkedList<>();
        queue.offer(new NodeInfo(root, 0)); // Root starts at index 0

        while (!queue.isEmpty()) {
            int size = queue.size();
            int minIndex = queue.peek().index; // Index of the first node in the current level
            int first = 0, last = 0;

            for (int i = 0; i < size; i++) {
                NodeInfo current = queue.poll();
                TreeNode node = current.node;
                
                // Normalize index to prevent integer overflow
                int normalizedIndex = current.index - minIndex;

                if (i == 0) first = normalizedIndex;
                if (i == size - 1) last = normalizedIndex;

                // Push left child with index: 2 * i
                if (node.left != null) {
                    queue.offer(new NodeInfo(node.left, 2 * normalizedIndex));
                }

                // Push right child with index: 2 * i + 1
                if (node.right != null) {
                    queue.offer(new NodeInfo(node.right, 2 * normalizedIndex + 1));
                }
            }

            // Calculate width of the current level and update maxWidth
            maxWidth = Math.max(maxWidth, last - first + 1);
        }

        return maxWidth;
    }
}