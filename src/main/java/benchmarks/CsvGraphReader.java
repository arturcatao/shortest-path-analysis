package benchmarks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algorithms.Edge;

public class CsvGraphReader {

    @SuppressWarnings("unchecked")
    public static List<List<Edge>[]> readAllGraphsOfSize(String path, int numVertices) {

        // Map: idGrafo -> lista de arestas
        Map<Integer, List<int[]>> edgesByGraph = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                int vertices = Integer.parseInt(parts[1]);

                // filtrando apenas grafos do tamanho desejado
                if (vertices != numVertices)
                    continue;

                int v1 = Integer.parseInt(parts[2]);
                int v2 = Integer.parseInt(parts[3]);
                int weight = Integer.parseInt(parts[4]);

                edgesByGraph
                        .computeIfAbsent(id, k -> new ArrayList<>())
                        .add(new int[]{v1, v2, weight});
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Agora convertemos cada conjunto de arestas
        // para lista de adjacência

        List<List<Edge>[]> graphs = new ArrayList<>();

        for (List<int[]> edges : edgesByGraph.values()) {

            List<Edge>[] graph = new List[numVertices];

            for (int i = 0; i < numVertices; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int[] edge : edges) {

                int v1 = edge[0];
                int v2 = edge[1];
                int weight = edge[2];

                // Grafo não-direcionado
                graph[v1].add(new Edge(v2, weight));
                graph[v2].add(new Edge(v1, weight));
            }

            graphs.add(graph);
        }

        return graphs;
    }
}
