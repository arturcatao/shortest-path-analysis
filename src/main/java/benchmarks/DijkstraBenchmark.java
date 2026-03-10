package benchmarks;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Timeout;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import algorithms.DijkstraAlgorithmLista;
import algorithms.Edge;
import heaps.BinaryHeap;
import heaps.FibonacciHeap;
import heaps.MyPriorityQueue;
import heaps.PairingHeap;

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
@Timeout(time = 30, timeUnit = TimeUnit.MINUTES)
@Measurement(iterations = 5)
@Fork(1)

public class DijkstraBenchmark {
    
    // ---------------------------
    //< PARÂMETROS DO EXPERIMENTO >
    // ---------------------------
    
    @Param({"10","30","50","70","90"})
    private int density;
    
    // Número de vértices
    @Param({"100", "500", "1000"})
    private int size;
    
    // Tipo de heap
    @Param({"PAIRING", "BINARY", "FIBONACCI"})
    private String heapType;

    //---------------------
    //< VARIÁVEIS INTERNAS >
    //---------------------
 
    private List<List<Edge>[]> graphs;

    //-----------------------------------------
    // SETUP (executa 1 vez por rodada de teste)
    //-----------------------------------------

    @Setup(Level.Trial)
    public void setup() {

        String path = "experiments/data/graphs_" + density + ".csv";

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

                case "FIBONACCI":
                    pq = new FibonacciHeap();
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