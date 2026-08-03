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

    public List<Integer> rightSideView(TreeNode root) {

        // Stores the final right-side view of the tree.
        List<Integer> answer = new ArrayList<>();

        // Edge case:
        // If the tree is empty, return an empty list.
        if (root == null) {
            return answer;
        }

        // Queue is used for normal BFS (Level Order Traversal).
        Queue<TreeNode> queue = new LinkedList<>();

        // Start BFS from the root node.
        queue.offer(root);

        // Continue until all nodes are processed.
        while (!queue.isEmpty()) {

            // Number of nodes in the current level.
            int levelSize = queue.size();

            // Process every node of the current level.
            for (int i = 0; i < levelSize; i++) {

                // Remove the front node.
                TreeNode currentNode = queue.poll();

                // If this is the last node of the current level,
                // then it is the rightmost node.
                if (i == levelSize - 1) {
                    answer.add(currentNode.val);
                }

                // Add the left child for the next level.
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                // Add the right child for the next level.
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }

        // Return the right-side view.
        return answer;
    }
}