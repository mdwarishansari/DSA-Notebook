import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> averages = new ArrayList<>();

        // Base case: check if the tree is empty
        if (root == null) {
            return averages;
        }

        // Standard FIFO queue for level-order (BFS) traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            // Use double for levelSum to prevent 32-bit integer overflow when adding large values
            double levelSum = 0.0;

            // Process all nodes currently in the queue (the entire current level)
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                levelSum += currentNode.val;

                // Push left child to the queue if present
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                // Push right child to the queue if present
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // Calculate and record the average for the current level
            averages.add(levelSum / levelSize);
        }

        return averages;
    }
}