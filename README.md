# Análise Experimental de Diferentes Estruturas de Heap no Desempenho do Algoritmo de Dijkstra

Projeto desenvolvido para a disciplina de **Estrutura de Dados e
Algoritmos** do curso de **Ciência da Computação da UFCG**.

## Introdução

O algoritmo de Dijkstra é um algoritmo de menor caminho para grafos com pesos
não negativos, sendo aplicado em sistemas de navegação, roteamento de redes e outros problemas de otimização. O desempenho desse algoritmo depende diretamente da estrutura de dados utilizada para implementar sua fila de prioridade. Diferentes implementações podem apresentar comportamentos distintos na prática, mesmo quando a análise teórica sugere vantagens assintóticas.
Neste projeto realizamos uma **análise experimental do algoritmo de Dijkstra utilizando diferentes estruturas de heap**, buscando comparar seu desempenho em grafos com diferentes tamanhos e densidades.
    
## Contextualização

Uma etapa fundamental do algoritmo de Dijkstra é a utilização de uma **fila de prioridade**, responsável por selecionar o vértice com menor distância estimada. Durante sua execução, duas operações são particularmente importantes:

- **extractMin**: remove o vértice com menor distância.
- **decreaseKey**: atualiza a distância estimada de um vértice.

A eficiência dessas operações depende diretamente da estrutura de dados utilizada para implementar a fila de prioridade. Neste projeto analisamos experimentalmente três estruturas de heap:

- **Binary Heap**
- **Pairing Heap**
- **Fibonacci Heap**

Cada uma apresenta diferentes complexidades assintóticas para essas operações:

| Estrutura        | Complexidade Extract-Min | Complexidade Decrease-Key | Complexidade Total Dijkstra |
|------------------|--------------------------|----------------------------|------------------------------|
| Binary Heap      | O(log V)                 | O(log V)                   | O((V + E) log V)             |
| Pairing Heap     | O(log V) amortizado      | O(log V) amortizado        | O((V + E) log V)             |
| Fibonacci Heap   | O(log V) amortizado      | O(1) amortizado            | O(V log V + E)               |

Consequentemente, a complexidade do algoritmo de Dijkstra varia de acordo com a estrutura utilizada:

- **Binary Heap / Pairing Heap:** `O((V + E) log V)`
- **Fibonacci Heap:** `O(V log V + E)`


## Objetivo

        Estudar e analisar o comportamento das diferentes estruturas de dados na 
        fila de prioridade do Dijkstra, e verificar se o seu comportamento assintótico 
        segue na prática quando testado com diferentes tipos de grafos.


## Rodar os experimentos:
```
./run-benchmark.sh
```
## Contextualização

Escreva aqui.

## Metodologia

A pesquisa adotou uma abordagem experimental controlada, seguindo esses passos:

Primeiro Passo: Implementação das estruturas e adaptações do algoritmo de Dijkstra

        Foram implementadas diferentes estruturas de prioridade, Binary Heap, Fibonacci Heap e Pairing Heap, utilizando da interface MyPriorityQueue para formar o contrato dos métodos “Insert”, “decreaseKey” e “extractMin”. As estruturas foram modificadas, para garantir que elas pudessem ser utilizadas corretamente na análise, utilizando as mesmas entradas.
        Após a implementação dessas estruturas de dados, elas foram utilizadas no algoritmo de Dijkstra, adaptando-o para receber cada estrutura devidamente. Essa abordagem permite que a lógica do algoritmo permaneça inalterada e garante que as diferenças no experimento sejam apenas ligadas às diferenças estruturais e operacionais de cada variação. 

Segundo Passo:  Geração de entradas

        Foram geradas as entradas, através de um script na linguagem de programação Python, para a criação de grafos usando diferentes perfis estruturais, como grafos esparsos e grafos densos.
        Densidades: 10%, 30%, 50%, 70%, 90%
        Tamanhos: 100, 500, 1.000, 1.500 vértices

	Os pesos das arestas foram gerados aleatoriamente e os grafos são garantidamente conectados, para assegurar que haja caminhos possíveis para qualquer par de vértices. 


Terceiro Passo: configuração do ambiente de testes e análise dos resultados
	
	Os testes foram realizados por meio de um Benchmark, utilizando a biblioteca JMH (Java Microbenchmark Harness), que mediu o tempo médio para a execução do algoritmo de Dijkstra, variando o tamanho, densidade e tipo de estrutura implementada. Para garantir resultados mais precisos, o Java executou cada configuração três vezes, como aquecimento, seguidos de cinco execuções em uma JVM separada. Os resultados foram processados e apresentados em gráficos comparativos, que foram gerados pela biblioteca matplotlib, do Python. Nos parâmetros utilizados nesse experimento, foi utilizado 1 fork onde são realizadas 3 execuções de aquecimento seguidas de 5 ciclos de medição, com o tempo contado em milissegundos. As variáveis testadas foram o tipo de grafo (esparso, médio e denso), o tamanho (100, 500, 1.000 e 1.500 vértices) e o tipo de heap (Binary, Fibonacci e Pairing).


## Hipotese Teórica

Escreva aqui.

## Análise dos resultados

Contagem de DecreaseKeys:

No cenário do algoritmo de Dijkstra para diferentes filas de prioridade, a contagem de operações de decreaseKeys torna-se importante para garantir que o experimento é justo, no caso, são usados os mesmos grafos para testar o algoritmo com os três tipos de Heap. Além disso, o fato da contagem ser a mesma para as três significa que o Dijkstra encontrou o mesmo caminho em todos os casos, ou seja, o que vai diferenciar os resultados é o custo interno do método, que é seguido pelo contrato formado na interface MyPriorityQueue.

![contador para 1000 vertices](static/DecreaseKey/chart_decrease_keys_1000.png)

Como apresentado no gráfico acima, as três estruturas apresentaram contagens praticamente idênticas para um mesmo grafo, confirmando a hipótese 4. Observa-se também que o número de chamadas ao decreaseKey cresce proporcionalmente à densidade do grafo, pois grafos mais densos possuem mais arestas e, consequentemente, mais oportunidades de encontrar caminhos melhores durante a execução do algoritmo.
Em casos pontuais, pode haver uma diferença mínima na contagem, em decorrência das diferentes formas como cada estrutura trata os vértices processados, mas isso não tem impacto significativo nos resultados.

![contador para 100 vertices](static/DecreaseKey/chart_decrease_keys_100.png)

## Ameaças à validade

## Conclusão

## Referências

