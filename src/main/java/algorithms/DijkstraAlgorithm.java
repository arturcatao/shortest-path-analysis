/**Esse código utiliza a implementação do Dijkstra encontrada no repositório:
* https://github.com/TheAlgorithms/Java/tree/master/src/main/java/com/thealgorithms/datastructures/graphs
* Com algumas modificações para utilizar os grafos em formato de
* listas de adjacência.
*/

package algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import heaps.MyPriorityQueue;

public class DijkstraAlgorithm {

    // Dijkstra Universal(Com a classe Edge funciona para tudo) Ela so auxilia
    public static int[] dijkstraUniversal(List<Edge>[] graph, int source, MyPriorityQueue pq) {
        int n = graph.length;

        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int i = 0; i < n; i++) {
            pq.insert(i, dist[i]);
        }

        while (!pq.isEmpty()) {
            int u = pq.extractMin();

            if (u == -1)
                break;
            if (visited[u])
                continue;

            visited[u] = true;

            if (dist[u] == Integer.MAX_VALUE)
                break;

            for (Edge e : graph[u]) {
                int v = e.to;
                int weight = e.weight;

                if (!visited[v] && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.decreaseKey(v, dist[v]);
                }
            }
        }

        return dist;
    }

    // Se nao quer usar a classe Edge
    public static int[] dijkstra(List<int[]>[] grafo, int S) {
        int n = grafo.length;

        int[] dist = new int[n];
        boolean[] mark = new boolean[n];

        int INF = Integer.MAX_VALUE / 2;
        Arrays.fill(dist, INF);
        dist[S] = 0;

        PriorityQueue<int[]> fila = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        fila.offer(new int[] { 0, S });

        while (!fila.isEmpty()) {
            int[] topo = fila.poll();
            int u = topo[1];

            if (mark[u])
                continue;

            mark[u] = true;

            for (int[] vizinho : grafo[u]) {
                int v = vizinho[0];
                int w = vizinho[1];

                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;

                    fila.offer(new int[] { dist[v], v });
                }
            }
        }

        return dist;
    }

    // Como ler os resultado da classe sem Edge, para ler com Edge na hora de passar o peso e o vertice cria-se o objeto Edge e adiciona na lista
    /*Exemplo sem Edge
     * int n = 1000;
     * List<int[]>[] grafo = new ArrayList[n];
     * for (int i = 0; i < n; i++) {
     * grafo[i] = new ArrayList<>();
     * }
     * 
     * // Adicionando uma aresta (exemplo: do vértice 0 para o vértice 5 com peso
     * 10)
     * grafo[0].add(new int[]{5, 10});
     * 
     * // Adicionando outra aresta (exemplo: do vértice 0 para o vértice 8 com peso
     * 25)
     * grafo[0].add(new int[]{8, 25});
     * 
     */
}
