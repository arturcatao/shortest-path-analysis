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

Escreva aqui.

## Hipotese Teórica

Escreva aqui.

## Análise dos resultados

Escreva aqui.

## Ameaças à validade

## Conclusão

## Referências

