package algorithms;

public interface MinPriorityQueue{
    
    //todas as filas de prioridade precisam ter um insert
    //recebendo os mesmos parâmetros: vertex (qual vertice
    //estamos colocando) e priority (a distância atual
    //dele) 
    void insert(int vertex, int priority);
    
    //o algoritmo sempre precisa saber qual é o vértice
    //com menor distância ainda não processado, por isso o
    //extractMin remove e retorna o vertice com menor
    //prioridade
    int extractMin();

    //é quando voce descobre um caminho melhor para um
    //vertice e precisa atualizar a prioridade dele na
    //fila
    void decreaseKey(int vertex, int newPriority);

    //condição de parada
    boolean isEmpty();
}
