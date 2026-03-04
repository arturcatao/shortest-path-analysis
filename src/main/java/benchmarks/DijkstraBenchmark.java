package benchmarks;

import algorithms.DijkstraAlgorithmLista;
import algorithms.DijkstraAlgorithmLista.Edge;
import heaps.MyPriorityQueue;
import heaps.PairingHeap;
import heaps.BinaryHeap;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.concurrent.TimeUnit;

// Isso é um benchmark JMH, ele mede o tempo médio de execução do
// algoritmo de dijkstra, que varia o tipo de grafo, o tamanho e
// o heap. Para cada uma das proximas 6 configurações, vou
// explicar sua implicação:
//
// 1. cada thread de benchmark terá sua própria instância da classe
// 2. está medindo o tempo médio de execução por chamada do método
// 3. significa que o tempo será contado em milisegundos.
// 4. o java vai executar o benchmark 3 vezes antes de medir
// 5. depois, ele mede 5 vezes
// 6. o benchmark roda em uma JVM separada.

@State(Scope.Thread) 
@BenchmarkMode(Mode.AverageTime) 
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@Fork(1)

public class DijkstraBenchmark {
    
    // ---------------------------
    //< PARÂMETROS DO EXPERIMENTO >
    // ---------------------------

    // Tipo de grafo
    @Param({"DENSE", "MEDIUM", "SPARSE"})
    private String graphType;

    // Número de vértices
    @Param({"100", "500", "1000", "5000", "10000"})
    private int size;
    
    /**
    // Amostra do grafo (ID no CSV)
    @Param({
            "1","2","3","4","5","6","7","8","9","10"
            // podemos expandir
    })
    private int sample;
    */

    // Tipo de heap
    @Param({"PAIRING", "BINARY"})
    private String heapType;

    //---------------------
    //< VARIÁVEIS INTERNAS >
    //---------------------
 
    private List<List<Edge>[]> graphs;
    //private List<Edge>[] graph;

    //-----------------------------------------
    // SETUP (executa 1 vez por rodada de teste)
    //-----------------------------------------

    @Setup(Level.Trial)
    public void setup() {

        String path;

        switch (graphType) {
            case "DENSE":
                path = "experiments/data/dense_graphs.csv";
                break;

            case "MEDIUM":
                path = "experiments/data/medium_graphs.csv";
                break;

            case "SPARSE":
                path = "experiments/data/sparse_graphs.csv";
                break;

            default:
                throw new IllegalArgumentException("Tipo de grafo inválido");
        }

        //graph = CsvGraphReader.readGraph(path, size, sample);
        graphs = CsvGraphReader.readAllGraphsOfSize(path, size);

    }

    //----------
    // BENCHMARK
    //----------

    @Benchmark
    public void runDijkstra(Blackhole blackhole) {
    
        for (List<Edge>[] graph: graphs) {
            
            MyPriorityQueue pq;

            switch (heapType) {
                case "PAIRING":
                    pq = new PairingHeap(size);
                    break;

                case "BINARY":
                    pq = new BinaryHeap(size);
                    break;

                default:
                    throw new IllegalArgumentException("Heap inválida");
            }

            int[] result =
                DijkstraAlgorithmLista.dijkstraUniversal(graph, 0, pq);

            blackhole.consume(result);
            //obs: o blackhole é o objeto que usamos para impedir que
            //o compilador otimize o código e elimine partes dele
        }
    }
}
