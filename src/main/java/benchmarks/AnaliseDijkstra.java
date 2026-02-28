package benchmarks;
import algorithms.DijkstraAlgorithm;
import java.io.*;
import java.util.Arrays;

public class AnaliseDijkstra{
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = "";
            
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                int[] flatGraph = Arrays.stream(tokens).mapToInt(Integer::parseInt).toArray();
                
                // Calcula o tamanho da matriz
                int numVertices = (int) Math.sqrt(flatGraph.length);
                
                // Converte array 1D para matriz 2D
                int[][] graph = new int[numVertices][numVertices];
                int index = 0;
                for (int i = 0; i < numVertices; i++) {
                    for (int j = 0; j < numVertices; j++) {
                        graph[i][j] = flatGraph[index++];
                    }
                }
                
                // Testa Dijkstra
                DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(numVertices);
                long start = System.nanoTime();
                // dijkstra.run(graph, 0); falta colocar o heap
                long end = System.nanoTime();
                long time = end - start;
                
                System.out.println("Dijkstra," + time + "," + numVertices);
            }
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}