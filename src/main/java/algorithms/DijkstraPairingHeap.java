package algorithms;

import java.util.Arrays;
import java.util.List;

import heaps.MyPriorityQueue;
import heaps.PairingHeap;       

public class DijkstraPairingHeap {

    public static int[] dijkstra(List<Edge>[] graph, int source) {
    int n = graph.length;

    int[] dist = new int[n];
    boolean[] visited = new boolean[n];

    // infinito
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[source] = 0;

    MyPriorityQueue pq = new PairingHeap(n);

    // insere todos os vértices
    for (int i = 0; i < n; i++) {
        pq.insert(i, dist[i]);
    }

    while (!pq.isEmpty()) {
        int u = pq.extractMin();

        if (visited[u]) continue;
        visited[u] = true;

        for (Edge e : graph[u]) {
            int v = e.to;
            int weight = e.weight;

            if (!visited[v] && dist[u] + weight < dist[v]) {
                dist[v] = dist[u] + weight;

                // 🔥 AQUI entra o Pairing Heap
                pq.decreaseKey(v, dist[v]);
            }
        }
    }

    return dist;
}
}
