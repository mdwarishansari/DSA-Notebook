import java.util.ArrayDeque;
import java.util.Deque;

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
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        // Initialize forward (smallest-to-largest) and reverse (largest-to-smallest) iterators
        BSTIterator leftIterator = new BSTIterator(root, false);
        BSTIterator rightIterator = new BSTIterator(root, true);

        int leftVal = leftIterator.next();
        int rightVal = rightIterator.next();

        // Standard two-pointer search until pointers meet
        while (leftVal < rightVal) {
            int sum = leftVal + rightVal;

            if (sum == k) {
                return true;
            } else if (sum < k) {
                leftVal = leftIterator.next();
            } else {
                rightVal = rightIterator.next();
            }
        }

        return false;
    }

    /**
     * Controlled BST Iterator supporting both standard in-order (ascending)
     * and reverse in-order (descending) traversal in O(h) space.
     */
    private static class BSTIterator {
        private final Deque<TreeNode> stack = new ArrayDeque<>();
        private final boolean reverse;

        public BSTIterator(TreeNode root, boolean reverse) {
            this.reverse = reverse;
            pushBranch(root);
        }

        /**
         * Returns the next element in the traversal order.
         */
        public int next() {
            TreeNode node = stack.pop();
            // Move to the opposite child after visiting the current node
            if (reverse) {
                pushBranch(node.left);
            } else {
                pushBranch(node.right);
            }
            return node.val;
        }

        /**
         * Pushes the entire left spine (or right spine if reversed) onto the stack.
         */
        private void pushBranch(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = reverse ? node.right : node.left;
            }
        }
    }
}