# Análise Experimental de Diferentes Estruturas de Heap no Desempenho do Algoritmo de Dijkstra

Projeto desenvolvido para a disciplina de **Estrutura de Dados e
Algoritmos** do curso de **Ciência da Computação da UFCG**.

## Introdução

O algoritmo de Dijkstra é um dos algoritmos mais conhecidos na
abordagem de algoritmos de menor caminho para grafos com pesos
não negativos, sendo utilizado em sistemas de GPS, roteamento de
dados e muitas outras utilizações que necessitam de otimização de
distâncias. Pensando nisso, decidimos mudar a estrutura de dados
utilizada na fila de prioridade para analisarmos os diferentes
comportamentos que o algoritmo apresenta, através de uma análise
experimental controlada.
    
A nossa experimentação utilizou 3 estruturas de Heap diferentes
para a fila de prioridade do algoritmo, sendo elas: Binary Heap,
Fibonacci Heap e o Pairing Heap, na qual utilizamos diferentes
tipos de grafos como entrada, com tamanhos variados de 100 a 1500
vértices, e densidade de 10% até 99%. Através da nossa análise
podemos verificar se o comportamento prático segue a análise
assintótica da estrutura, o que muitas vezes não é a realidade.
Além disso, produzimos dados para auxiliar na escolha de qual
estrutura de dados a ser utilizada baseado no grafo utilizado.


## Objetivo

            O principal objetivo do projeto é aproximar teoria e
            prática na análise de algoritmos, mostrando que o
            desempenho real de uma solução pode variar de acordo
            com as estruturas de dados utilizadas e as
            características do problema.

## Rodar os experimentos:

./run-benchmark.sh

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

