package heaps;

public class PairingHeap implements MyPriorityQueue{

    private HeapNode root;
    private HeapNode[] nodes; // acesso direto aos vértices

    public PairingHeap(int maxVertices) {
        root = null;
        nodes = new HeapNode[maxVertices];
    }

    //Alteração de métodos
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void insert(int vertex, int priority) {
        HeapNode newNode = new HeapNode(vertex, priority);

        nodes[vertex] = newNode;
        root = merge(root, newNode);
    }

    @Override
    public int extractMin() {
        if (root == null) return -1;

        int minVertex = root.vertex;

        nodes[minVertex] = null;
        root = twoPassMerge(root.leftChild);

        if (root != null) root.parent = null;

        return minVertex;
    }

    @Override
    public void decreaseKey(int vertex, int newPriority) {
        HeapNode node = nodes[vertex];

        if (node == null) return;

        if (node.priority <= newPriority) return;

        node.priority = newPriority;

        if (node != root) {
            cut(node);
            root = merge(root, node);
        }
    }

   
    //Metodo auxiliares
    private void cut(HeapNode node) {
        if (node.parent != null) {
            if (node.parent.leftChild == node) {
                node.parent.leftChild = node.nextSibling;
            } else {
                HeapNode sibling = node.parent.leftChild;   
                while (sibling.nextSibling != node) {
                    sibling = sibling.nextSibling;
                }
                sibling.nextSibling = node.nextSibling;
            }
        }

        node.parent = null;
        node.nextSibling = null;
    }

    private HeapNode merge(HeapNode a, HeapNode b) {
        if (a == null) return b;
        if (b == null) return a;

        if (a.priority < b.priority) {
            b.nextSibling = a.leftChild;
            if (a.leftChild != null) a.leftChild.parent = b;
            a.leftChild = b;
            b.parent = a;
            return a;
        } else {
            a.nextSibling = b.leftChild;
            if (b.leftChild != null) b.leftChild.parent = a;
            b.leftChild = a;
            a.parent = b;
            return b;
        }
    }

    private HeapNode twoPassMerge(HeapNode node) {
        if (node == null || node.nextSibling == null)
            return node;

        HeapNode a = node;
        HeapNode b = node.nextSibling;
        HeapNode rest = b.nextSibling;

        a.nextSibling = null;
        b.nextSibling = null;

        return merge(merge(a, b), twoPassMerge(rest));
    }
}

class HeapNode{
     int vertex;
    int priority;

    HeapNode leftChild;
    HeapNode nextSibling;
    HeapNode parent;

    public HeapNode(int vertex, int priority) {
        this.vertex = vertex;
        this.priority = priority;
        this.leftChild = null;
        this.nextSibling = null;
        this.parent = null;
    }
}
