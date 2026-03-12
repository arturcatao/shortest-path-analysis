package algorithms;
import java.util.Arrays;

import heaps.MyPriorityQueue;

/**
 * Dijkstra's algorithm for finding the shortest path from a single source vertex to all other vertices in a graph.
 */
public class DijkstraAlgorithm {

    private final int vertexCount;

    /**
     * Constructs a Dijkstra object with the given number of vertices.
     *
     * @param vertexCount The number of vertices in the graph.
     */
    public DijkstraAlgorithm(int vertexCount) {
        this.vertexCount = vertexCount;
    }

    /**
     * Executes Dijkstra's algorithm on the provided graph to find the shortest paths from the source vertex to all other vertices.
     *
     * The graph is represented as an adjacency matrix where {@code graph[i][j]} represents the weight of the edge from vertex {@code i}
     * to vertex {@code j}. A value of 0 indicates no edge exists between the vertices.
     *
     * @param graph The graph represented as an adjacency matrix.
     * @param source The source vertex.
     * @return An array where the value at each index {@code i} represents the shortest distance from the source vertex to vertex {@code i}.
     * @throws IllegalArgumentException if the source vertex is out of range.
     */
    public int[] run(int[][] graph, int source, MyPriorityQueue pq) {
        if (source < 0 || source >= vertexCount) {
            throw new IllegalArgumentException("Incorrect source");
        }

        int[] distances = new int[vertexCount];
        boolean[] processed = new boolean[vertexCount];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(processed, false);

        distances[source] = 0;

        // Inserir TODOS os vértices na heap no início
        for (int i = 0; i < vertexCount; i++) {
            pq.insert(i, distances[i]);
        }


        while (!pq.isEmpty()) {
            
            int u = pq.extractMin();

            if (processed[u]) continue;

            processed[u] = true;

            for (int v = 0; v < vertexCount; v++) {
                
                if (!processed[v] && graph[u][v] != 0 &&
                distances[u] != Integer.MAX_VALUE) {

                    int newDist = distances[u] + graph[u][v];

                    if (newDist < distances[v]) {
                        distances[v] = newDist;

                        //atualizando prioridade na heap
                        pq.decreaseKey(v, newDist);
                    }
                }
            }
        }
        
        return distances;
    }


    /**
     * Prints the shortest distances from the source vertex to all other vertices.
     *
     * @param distances The array of shortest distances.
     */
    private void printDistances(int[] distances) {
        System.out.println("Vertex \t Distance");
        for (int i = 0; i < vertexCount; i++) {
            System.out.println(i + " \t " + distances[i]);
        }
    }
}
