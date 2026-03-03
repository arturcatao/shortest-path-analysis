package benchmarks;

import algorithms.DijkstraAlgorithm;
import algorithms.DijkstraAlgorithmLista;
import heaps.MyPriorityQueue;
import heaps.PairingHeap;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@Fork(1)
public class AnaliseDijkstra {

    private DijkstraAlgorithm dijkstraHeap;
    private DijkstraAlgorithmLista dijkstraLista;

    //completar
}
