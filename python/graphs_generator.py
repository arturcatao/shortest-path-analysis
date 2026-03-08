import os
import dijkstra_graphs as dg

def main():
    csv_densos = "experiments/data/dense_graphs.csv"
    csv_esparsos = "experiments/data/sparse_graphs.csv"
    csv_medios = "experiments/data/medium_graphs.csv"       

    # 1. Arrays independentes para cada tipo!
    # Esparsos aguentam ser gigantes. Densos devem ser menores.
    tamanhos_esparsos = [1000, 5000, 10000, 50000, 100000]
    tamanhos_medios   = [1000, 2500, 5000, 10000, 20000]
    tamanhos_densos   = [500, 1000, 1500, 2000, 2500]
    
    # 2. Reduzindo as amostras para não estourar a memória RAM do Java
    AMOSTRAS = 10 

    # Limpa os arquivos antes de começar (para evitar duplicatas se rodar 2 vezes)
    open(csv_densos, "w").close()
    open(csv_medios, "w").close()
    open(csv_esparsos, "w").close()

    print("Gerando grafos esparsos...")
    graph_id = 0
    for num_vertices in tamanhos_esparsos:
        for _ in range(AMOSTRAS):
            graph_id += 1
            esparso = dg.gerar_grafos_esparsos(num_vertices, graph_id)
            salvar(esparso, graph_id, num_vertices, csv_esparsos)

    print("Gerando grafos médios...")
    graph_id = 0
    for num_vertices in tamanhos_medios:
        for _ in range(AMOSTRAS):
            graph_id += 1
            medio = dg.gerar_grafos_medios(num_vertices, graph_id)
            salvar(medio, graph_id, num_vertices, csv_medios)

    print("Gerando grafos densos...")
    graph_id = 0
    for num_vertices in tamanhos_densos:
        for _ in range(AMOSTRAS):
            graph_id += 1
            denso = dg.gerar_grafos_densos(num_vertices, graph_id)
            salvar(denso, graph_id, num_vertices, csv_densos)
            
    print("Todos os grafos foram gerados com sucesso!")


def salvar(graph, id, num_vertices, csv):
    # O modo "a" (append) vai adicionando as linhas no final do arquivo
    with open(csv, "a") as arquivo:
        for edge in graph:
            arquivo.write(str(id) + "," + str(num_vertices) + "," + ",".join(map(str, edge)) + "\n")    

if __name__ == "__main__":
    main()