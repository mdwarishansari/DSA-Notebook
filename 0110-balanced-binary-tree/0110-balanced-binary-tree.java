class Solution {

    // Main function.
    // If checkHeight() returns -1,
    // the tree is not balanced.
    // Otherwise it is balanced.
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    // Returns:
    // Height of subtree -> if balanced
    // -1                -> if subtree is unbalanced
    private int checkHeight(TreeNode node) {

        // Base Case:
        // Empty tree has height 0.
        if (node == null) {
            return 0;
        }

        // Recursively calculate the height of the left subtree.
        int left = checkHeight(node.left);

        // If the left subtree is already unbalanced,
        // immediately return -1.
        // No need to check anything else.
        if (left == -1) {
            return -1;
        }

        // Recursively calculate the height of the right subtree.
        int right = checkHeight(node.right);

        // If the right subtree is already unbalanced,
        // immediately return -1.
        if (right == -1) {
            return -1;
        }

        // If the current node itself becomes unbalanced,
        // return -1 so that all ancestors also know
        // the tree is not balanced.
        if (Math.abs(left - right) > 1) {
            return -1;
        }

        // Current subtree is balanced.
        // Return its height.
        //
        // Height =
        // max(left subtree height, right subtree height) + 1
        // (+1 is for the current node itself.)
        return Math.max(left, right) + 1;
    }
}