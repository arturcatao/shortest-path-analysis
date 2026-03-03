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

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@Fork(1)
public class DijkstraBenchmark {

    // PARÂMETROS DO EXPERIMENTO

    // Tipo de grafo
    @Param({"DENSE", "MEDIUM", "SPARSE"})
    private String graphType;

    // Número de vértices
    @Param({"100", "500", "1000", "5000", "10000"})
    private int size;

    // Amostra do grafo (ID no CSV)
    @Param({
            "1","2","3","4","5","6","7","8","9","10"
            // depois você pode expandir até 100
    })
    private int sample;

    // Tipo de heap
    @Param({"PAIRING", "BINARY"})
    private String heapType;

    // VARIÁVEIS INTERNAS

    private List<Edge>[] graph;

    // SETUP (executa 1 vez por rodada de teste)

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

        graph = CsvGraphReader.readGraph(path, size, sample);
    }

    // BENCHMARK

    @Benchmark
    public void runDijkstra(Blackhole blackhole) {

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
    }
}
