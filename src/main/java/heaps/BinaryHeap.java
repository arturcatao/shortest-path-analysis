package heaps;

public class BinaryHeap implements MyPriorityQueue {
    private int[] minHeap;
    private int[] prioridade;
    private int[] posicao;
    private int size;

    public BinaryHeap(int capacidade) {
        minHeap = new int[capacidade];
        prioridade = new int[capacidade];
        posicao = new int[capacidade];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void insert(int vertex, int priority) {
        minHeap[size] = vertex;
        prioridade[vertex] = priority;
        posicao[vertex] = size - 1;
        size++;

        heapifyUp(size - 1);
    }

    @Override
    public int extractMin() {
        int min = minHeap[0];
        size--;
        minHeap[0] = minHeap[size];
        posicao[minHeap[0]] = 0;
        heapifyDown(0);

        return min;
    }

    @Override
    public void decreaseKey(int vertex, int newPriority) {
        contadorDeOperacoes.incrementaDecreaseKey();
        prioridade[vertex] = newPriority;
        heapifyUp(posicao[vertex]);
    }

    // métodos auxiliares

    private void heapifyUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (prioridade[minHeap[parent]] > prioridade[minHeap[i]]) {
                swap(i, parent);
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;

            if (left < size && prioridade[minHeap[left]] < prioridade[minHeap[smallest]])
                smallest = left;
            if (right < size && prioridade[minHeap[right]] < prioridade[minHeap[smallest]])
                smallest = right;

            if (smallest != i) {
                swap(i, smallest);
                i = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        posicao[minHeap[i]] = j;
        posicao[minHeap[j]] = i;
        
        int aux = minHeap[i];
        minHeap[i] = minHeap[j];
        minHeap[j] = aux;
    }
}
