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
    public TreeNode sortedArrayToBST(int[] nums) {
        // Handle edge case where the input array is null or empty
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        // Start recursive divide-and-conquer construction over the entire array
        return constructBST(nums, 0, nums.length - 1);
    }

    /**
     * Recursive helper method to build a height-balanced BST from a sorted subarray.
     *
     * @param nums  The sorted integer array in ascending order.
     * @param left  The starting index of the current subarray.
     * @param right The ending index of the current subarray.
     * @return      The root node of the constructed balanced subtree.
     */
    private TreeNode constructBST(int[] nums, int left, int right) {
        // Base case: When the left pointer crosses the right pointer,
        // there are no elements left in this subarray to form a node.
        if (left > right) {
            return null;
        }

        // 1. Choose the middle element as the root to maintain height balance.
        // Using `left + (right - left) / 2` prevents potential integer overflow.
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        // 2. Recursively build the left subtree using the left half of the subarray
        // (all elements strictly smaller than nums[mid]).
        root.left = constructBST(nums, left, mid - 1);

        // 3. Recursively build the right subtree using the right half of the subarray
        // (all elements strictly greater than nums[mid]).
        root.right = constructBST(nums, mid + 1, right);

        return root;
    }
}