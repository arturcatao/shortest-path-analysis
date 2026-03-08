package heaps;

import java.util.ArrayList;
import java.util.List;

public class PairingHeap implements MyPriorityQueue {

    private HeapNode root;
    private HeapNode[] nodes;

    public PairingHeap(int maxVertices) {
        root = null;
        nodes = new HeapNode[maxVertices];
    }

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
        nodes[minVertex] = null; // Remove do controle
        
        root = twoPassMerge(root.leftChild);

        if (root != null) {
            root.parent = null;
            root.prevSibling = null;
        }

        return minVertex;
    }

    @Override
    public void decreaseKey(int vertex, int newPriority) {
        ContadorDeOperacoes.incrementaDecreaseKey();
        HeapNode node = nodes[vertex];

        if (node == null || node.priority <= newPriority) return;

        node.priority = newPriority;

        if (node != root) {
            cut(node);
            root = merge(root, node);
        }
    }

    // --- Métodos Auxiliares ---

    // Agora é O(1) graças ao prevSibling
    private void cut(HeapNode node) {
        if (node.parent == null) return; // Já é raiz

        if (node.prevSibling != null) {
            node.prevSibling.nextSibling = node.nextSibling;
        } else {
            // Se não tem irmão anterior, ele é o filho esquerdo do pai
            node.parent.leftChild = node.nextSibling;
        }

        if (node.nextSibling != null) {
            node.nextSibling.prevSibling = node.prevSibling;
        }

        node.parent = null;
        node.nextSibling = null;
        node.prevSibling = null;
    }

    private HeapNode merge(HeapNode a, HeapNode b) {
        if (a == null) return b;
        if (b == null) return a;

        if (a.priority <= b.priority) {
            b.nextSibling = a.leftChild;
            if (a.leftChild != null) {
                a.leftChild.prevSibling = b;
            }
            a.leftChild = b;
            b.parent = a;
            b.prevSibling = null; // b vira o primeiro filho
            return a;
        } else {
            a.nextSibling = b.leftChild;
            if (b.leftChild != null) {
                b.leftChild.prevSibling = a;
            }
            b.leftChild = a;
            a.parent = b;
            a.prevSibling = null; // a vira o primeiro filho
            return b;
        }
    }

    private HeapNode twoPassMerge(HeapNode node) {
        if (node == null) return null;

        List<HeapNode> treeList = new ArrayList<>();

        // 1ª passada: merge em pares
        while (node != null) {
            HeapNode a = node;
            HeapNode b = node.nextSibling;
            node = (b != null) ? b.nextSibling : null;

            // Limpa as conexões laterais para evitar vazamentos/ciclos
            a.nextSibling = null;
            a.prevSibling = null;
            a.parent = null;

            if (b != null) {
                b.nextSibling = null;
                b.prevSibling = null;
                b.parent = null;
                treeList.add(merge(a, b));
            } else {
                treeList.add(a);
            }
        }

        if (treeList.isEmpty()) return null;

        // 2ª passada: merge da direita pra esquerda
        HeapNode result = treeList.get(treeList.size() - 1);
        for (int i = treeList.size() - 2; i >= 0; i--) {
            result = merge(treeList.get(i), result);
        }

        return result;
    }
}

class HeapNode {
    int vertex;
    int priority;

    HeapNode leftChild;
    HeapNode nextSibling;
    HeapNode prevSibling; // Essencial para O(1) no método cut()
    HeapNode parent;

    public HeapNode(int vertex, int priority) {
        this.vertex = vertex;
        this.priority = priority;
        this.leftChild = null;
        this.nextSibling = null;
        this.prevSibling = null;
        this.parent = null;
    }
}
