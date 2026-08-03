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

    public List<List<Integer>> levelOrder(TreeNode root) {

        // Final answer which will store all levels
        List<List<Integer>> answer = new ArrayList<>();

        // Edge case:
        // If tree is empty, simply return empty answer.
        if (root == null) {
            return answer;
        }

        // Queue is used for Breadth First Search (Level Order Traversal)
        Queue<TreeNode> queue = new LinkedList<>();

        // Start BFS from the root node
        queue.offer(root);

        // Continue until all nodes are processed
        while (!queue.isEmpty()) {

            // Number of nodes currently present in the queue.
            // These nodes belong to exactly one level.
            int levelSize = queue.size();

            // Stores all node values of the current level
            List<Integer> currentLevel = new ArrayList<>();

            // Process every node of the current level
            for (int i = 0; i < levelSize; i++) {

                // Remove the front node from the queue
                TreeNode currentNode = queue.poll();

                // Store its value
                currentLevel.add(currentNode.val);

                // Add left child into the queue
                // so it will be processed in the next level.
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                // Add right child into the queue
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // Current level is completely processed.
            // Save it into the final answer.
            answer.add(currentLevel);
        }

        // Return all levels
        return answer;
    }
}