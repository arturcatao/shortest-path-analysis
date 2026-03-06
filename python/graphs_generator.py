import os
import random
import dijkstra_graphs as dg

CSV = "experiments/data/graphs.csv"

def main():
    

    tamanhos =  [100, 500, 1000, 1500]
    densidades = [0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9]
    AMOSTRAS = 50 #100

    graph_id = 0
    for num_vertices in tamanhos:
        for densidade in densidades:
            for _ in range(AMOSTRAS):
                graph_id += 1

                graph = dg.gerar_grafos(num_vertices, densidade, graph_id)
                salvar(graph, graph_id, num_vertices, densidade)



def salvar(graph,id ,num_vertices, densidade):
    with open(CSV, "a") as arquivo:
        for edge in graph:
            arquivo.write(str(id) + ","  + str(densidade) + "," + str(num_vertices) + "," + ",".join(map(str, edge)) + "\n")    

main()