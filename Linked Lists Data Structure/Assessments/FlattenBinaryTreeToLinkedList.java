class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class FlattenBinaryTreeToLinkedList {

    public static void flatten(TreeNode root) {
        // Task 1: Initialize `current` to the root
        TreeNode current = root;

        // Task 2: While `current` is not `null`
        while (current != null) {
            if (current.left != null) {
                TreeNode rightmost = current.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }
                rightmost.right = current.right;
                current.right = current.left;
                current.left = null;
            }

            // Task 3: Move `current` to its right child
            current = current.right;
        }
    }
    public static void printFlattenedLinkedList(TreeNode root) {
        TreeNode current = root;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.right;
        }
    }

    public static void main(String[] args) {
        // Example usage:
        // Construct a sample binary tree:
        //        1
        //       / \
        //      2   5
        //     / \   \
        //    3   4   6

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        flatten(root);

        // Print the flattened linked list
        System.out.println("Flattened Linked List:");
        printFlattenedLinkedList(root);
    }
}
