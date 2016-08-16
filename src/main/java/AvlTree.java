public class AvlTree {
    public static class Node {
        public Node left;
        public Node right;
        public int height;
        public final int data;

        Node(int val) {
            data = val;
            height = 1;
        }
    }

    private Node _root;

    public Node getRoot() {
        return _root;
    }

    public void insert(int data) {
        _root = insert(_root, data);
    }

    private static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }

        if (data < root.data) root.left = insert(root.left, data);
        else if (data > root.data) root.right = insert(root.right, data);

        root = balance(root);
        updateHeight(root);

        return root;
    }

    private static Node balance(Node root) {
        int bFactor = balanceFactor(root);

        if (bFactor < -1) {
            if (balanceFactor(root.left) > 0) {
                root.left = rotateLeft(root.left);
            }

            return rotateRight(root);
        } else if (bFactor > 1) {
            if (balanceFactor(root.right) < 0) {
                root.right = rotateRight(root.right);
            }

            return rotateLeft(root);
        }

        return root;
    }

    private static int balanceFactor(Node n) {
        if (n == null) return 0;
        return (n.right != null ? n.right.height : 0) -
                (n.left != null ? n.left.height : 0);
    }

    private static Node rotateRight(Node root) {
        Node pivot = root.left;

        root.left = root.left.right;
        pivot.right = root;

        updateHeight(root);
        return pivot;
    }

    private static Node rotateLeft(Node root) {
        Node pivot = root.right;
        root.right = pivot.left;
        pivot.left = root;

        updateHeight(root);
        return pivot;
    }

    private static void updateHeight(Node root) {
        root.height = Math.max(root.left != null ? root.left.height : 0,
                root.right != null ? root.right.height : 0) + 1;
    }
}
