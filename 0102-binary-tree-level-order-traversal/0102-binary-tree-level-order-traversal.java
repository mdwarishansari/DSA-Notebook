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

        // Store the final level order traversal
        List<List<Integer>> result = new ArrayList<>();

        // If the tree is empty, return an empty list
        if (root == null) {
            return result;
        }

        // Queue is used for Breadth-First Search (BFS)
        Queue<TreeNode> queue = new LinkedList<>();

        // Add the root node to the queue
        queue.offer(root);

        // Process nodes level by level
        while (!queue.isEmpty()) {

            // Number of nodes present at the current level
            int size = queue.size();

            // Store values of the current level
            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes of the current level
            for (int i = 0; i < size; i++) {

                // Remove the front node from the queue
                TreeNode current = queue.poll();

                // Add its value to the current level
                currentLevel.add(current.val);

                // Add left child if it exists
                if (current.left != null) {
                    queue.offer(current.left);
                }

                // Add right child if it exists
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            // Add the completed level to the result
            result.add(currentLevel);
        }

        // Return the level order traversal
        return result;
    }
}
