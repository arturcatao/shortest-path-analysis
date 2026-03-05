import os
import random
import dijkstra_graphs as dg

def main():
    
    csv_densos = "experiments/data/dense_graphs.csv"
    csv_esparsos = "experiments/data/sparse_graphs.csv"
    csv_medios = "experiments/data/medium_graphs.csv"       

    tamanhos =  [100, 500, 1000, 1500]
    AMOSTRAS = 100

    graph_id = 0
    for num_vertices in tamanhos:
        for _ in range(AMOSTRAS):
            graph_id += 1

            denso = dg.gerar_grafos_densos(num_vertices, graph_id)
            salvar(denso, graph_id, num_vertices, csv_densos)

            esparso = dg.gerar_grafos_esparsos(num_vertices, graph_id)
            salvar(esparso, graph_id, num_vertices, csv_esparsos)

            medio = dg.gerar_grafos_medios(num_vertices, graph_id)
            salvar(medio, graph_id, num_vertices, csv_medios)



def salvar(graph,id ,num_vertices, csv):
    with open(csv, "a") as arquivo:
        for edge in graph:
            arquivo.write(str(id) + "," + str(num_vertices) + "," + ",".join(map(str, edge)) + "\n")    

main()