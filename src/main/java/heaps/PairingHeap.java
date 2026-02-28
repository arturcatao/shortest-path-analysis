package heaps;

public class PairingHeap {
    HeapNode root;

    public PairingHeap() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int top() {
        return root.key;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    public void delete() {
        root = delete(root);
    }

    public void join(PairingHeap other) {
        root = merge(root, other.root);
    }

    // Helper functions
    private HeapNode insert(HeapNode node, int key) {
        return merge(node, new HeapNode(key, null, null));
    }

    private HeapNode delete(HeapNode node) {
        return twoPassMerge(node.leftChild);
    }

    private HeapNode merge(HeapNode a, HeapNode b) {
        // If any of the two nodes is null,
        // return the not null node
        if (a == null)
            return b;
        if (b == null)
            return a;

        // To maintain the min heap condition compare
        // the nodes and node with minimum value become
        // parent of the other node
        if (a.key < b.key) {
            a.addChild(b);
            return a;
        } else {
            b.addChild(a);
            return b;
        }
    }

    private HeapNode twoPassMerge(HeapNode node) {
        if (node == null || node.nextSibling == null)
            return node;
        else {
            HeapNode a, b, newNode;
            a = node;
            b = node.nextSibling;
            newNode = node.nextSibling.nextSibling;

            a.nextSibling = null;
            b.nextSibling = null;

            return merge(merge(a, b), twoPassMerge(newNode));
        }
    }
}

class HeapNode{
    int key;
    HeapNode leftChild;
    HeapNode nextSibling;

    public HeapNode() {
        leftChild = null;
        nextSibling = null;
    }

    // creates a new node
    public HeapNode(int key_, HeapNode leftChild_, HeapNode nextSibling_) {
        key = key_;
        leftChild = leftChild_;
        nextSibling = nextSibling_;
    }

    // Adds a child and sibling to the node
    public void addChild(HeapNode node) {
        if (leftChild == null)
            leftChild = node;
        else {
            node.nextSibling = leftChild;
            leftChild = node;
        }
    }
}
