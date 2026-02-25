Análise Experimental de Estruturas de Heap no Desempenho do
Algoritmo de Dijkstra

Projeto desenvolvido para a disciplina de **Estrutura de Dados e
Algoritmos** do curso de **Ciência da Computação da UFCG**.

## 📌 Sobre o projeto

Este trabalho tem como objetivo analisar, na prática, o impacto
de diferentes estruturas de fila de prioridade no desempenho do
algoritmo de Dijkstra, utilizado para resolver problemas de
caminho mínimo em grafos.

Em vez de considerar apenas a análise assintótica tradicional, o
projeto busca investigar como fatores práticos influenciam a
execução do algoritmo, como o tipo de heap utilizada, o formato
dos grafos de entrada e o comportamento real do código em
ambiente controlado.

Serão comparadas três variações de estruturas de dados:

- Binary Heap  
- Fibonacci Heap  
- Bucket Heap  

Cada uma delas será integrada ao algoritmo de Dijkstra e
avaliada por meio de experimentos controlados.

## 🔬 Metodologia geral

O projeto segue uma abordagem experimental, composta por:

1. Adaptação de implementações existentes das estruturas de heap
   em Java  
   2. Integração dessas estruturas ao algoritmo de Dijkstra  
   3. Geração de grafos com diferentes características
      (esparsos, densos, aleatórios e escala-livre)  
      4. Execução dos experimentos e coleta de métricas como
         tempo de execução, uso de memória e número de operações
         relevantes  
         5. Comparação dos resultados obtidos com as
            expectativas teóricas

            Os resultados serão apresentados por meio de
            gráficos e análises comparativas.

            ## 📊 Objetivo

            O principal objetivo do projeto é aproximar teoria e
            prática na análise de algoritmos, mostrando que o
            desempenho real de uma solução pode variar de acordo
            com as estruturas de dados utilizadas e as
            características do problema.

            ---

            *(Este README será atualizado ao longo do
            desenvolvimento do projeto, incluindo instruções de
            execução, descrição dos experimentos e resultados
            obtidos.)*
