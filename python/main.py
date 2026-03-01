import os
import random
import dijkstra_graphs as dg

def main():

    csv_densos = "../experiments/data/dense_graphs.csv"
    csv_espasos = "../experiments/data/sparse_graphs.csv"
    csv_medios = "../experiments/data/medium_graphs.csv"

    tamanhos = [100, 500, 1000, 5000, 10000]
    AMOSTRAS = 100

    for num_vertices in tamanhos:
        for i in range(AMOSTRAS):
            denso = dg.gerar_grafos_densos(num_vertices)
            salvar(denso, num_vertices, csv_densos)

            esparso = dg.gerar_grafos_esparsos(num_vertices)
            salvar(esparso, num_vertices, csv_espasos)

            medio = dg.gerar_grafos_medios(num_vertices)
            salvar(medio, num_vertices, csv_medios)


def salvar(grafo,num_vertices, csv):
    with open(csv, "a") as arquivo:
        arquivo.write(str(num_vertices) + "," + grafo + "\n")
    

main()