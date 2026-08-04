/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // ---------------------------------------------------------
    // SERIALIZE
    // Convert Tree -> String
    // ---------------------------------------------------------
    public String serialize(TreeNode root) {

        // StringBuilder is used because repeatedly concatenating
        // strings is expensive.
        StringBuilder sb = new StringBuilder();

        serializeHelper(root, sb);

        return sb.toString();
    }

    // Preorder Traversal
    // Root -> Left -> Right
    private void serializeHelper(TreeNode node, StringBuilder sb) {

        // If node is null, store "#"
        // This is necessary to preserve the tree structure.
        if (node == null) {
            sb.append("#,");
            return;
        }

        // Store current node value.
        sb.append(node.val).append(",");

        // Serialize left subtree.
        serializeHelper(node.left, sb);

        // Serialize right subtree.
        serializeHelper(node.right, sb);
    }

    // ---------------------------------------------------------
    // DESERIALIZE
    // Convert String -> Tree
    // ---------------------------------------------------------
    public TreeNode deserialize(String data) {

        // Split the serialized string using comma.
        String[] values = data.split(",");

        // Queue helps us process values one by one
        // in the same order they were serialized.
        Queue<String> queue = new LinkedList<>();

        for (String value : values) {
            queue.offer(value);
        }

        return deserializeHelper(queue);
    }

    // Builds the tree recursively.
    private TreeNode deserializeHelper(Queue<String> queue) {

        // Read the next value.
        String value = queue.poll();

        // "#" means this node was null.
        if (value.equals("#")) {
            return null;
        }

        // Create current node.
        TreeNode node = new TreeNode(Integer.parseInt(value));

        // Build left subtree.
        node.left = deserializeHelper(queue);

        // Build right subtree.
        node.right = deserializeHelper(queue);

        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));