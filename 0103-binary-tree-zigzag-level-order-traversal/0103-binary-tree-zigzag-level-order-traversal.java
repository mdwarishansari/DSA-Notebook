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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        // Final answer that will store all levels
        List<List<Integer>> answer = new ArrayList<>();

        // Edge case:
        // If tree is empty, return empty list.
        if (root == null) {
            return answer;
        }

        // Queue is used for normal BFS traversal.
        Queue<TreeNode> queue = new LinkedList<>();

        // Start BFS from the root node.
        queue.offer(root);

        // true  -> Left to Right
        // false -> Right to Left
        boolean leftToRight = true;

        // Continue until every node is processed.
        while (!queue.isEmpty()) {

            // Number of nodes present in the current level.
            int levelSize = queue.size();

            // LinkedList is used because addFirst() is O(1).
            LinkedList<Integer> currentLevel = new LinkedList<>();

            // Process every node of the current level.
            for (int i = 0; i < levelSize; i++) {

                // Remove the front node.
                TreeNode currentNode = queue.poll();

                // Store value according to the current direction.
                if (leftToRight) {
                    // Normal order.
                    currentLevel.addLast(currentNode.val);
                } else {
                    // Reverse order.
                    currentLevel.addFirst(currentNode.val);
                }

                // Add left child for the next level.
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                // Add right child for the next level.
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // Store the completed level.
            answer.add(currentLevel);

            // Reverse the direction for the next level.
            leftToRight = !leftToRight;
        }

        return answer;
    }
}