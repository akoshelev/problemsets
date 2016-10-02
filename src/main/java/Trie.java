import java.util.HashMap;

public class Trie {

    private static class Node {
        HashMap<Character, Node> children;
        boolean isComplete;

        Node() {
            children = new HashMap<>();
            isComplete = false;
        }

        void markComplete() {
            isComplete = true;
        }
    }

    private Node _root;

    public Trie() {
        _root = new Node();
    }

    public void add(String item) {

        Node parent = _root;
        int i = 0;
        while (i < item.length()) {
            Character c = item.charAt(i);
            Node current = parent.children.getOrDefault(c, null);

            // create a node if not exists
            if (current == null) {
                current = new Node();
                parent.children.put(c, current);
            }

            // mark as a word if its completed
            if (i == item.length() - 1) {
                current.markComplete();
            }

            // traverse further
            i++;
            parent = current;
        }
    }

    public int find(String prefix) {
        Node n = findNodeByPrefix(_root, prefix);
        if (n == null) return 0;

        // this is most generic case where we allow duplicates in Trie
        // if its not an issue then we may count number of words for every node during word's insertion
        int result = getWordsFrom(n);
        return result;
    }

    private Node findNodeByPrefix(Node root, String prefix) {
        if (root == null) return null;
        if (prefix.length() == 0) return root;

        Node child = root.children.getOrDefault(prefix.charAt(0), null);

        return findNodeByPrefix(child, prefix.substring(1));
    }

    private int getWordsFrom(Node n) {
        if (n == null) return 0;

        // must count itself if there is a word ends in it.
        int count = n.isComplete ? 1 : 0;

        // check children
        for (Node child : n.children.values()) {
            count += getWordsFrom(child);
        }

        return count;
    }
}
