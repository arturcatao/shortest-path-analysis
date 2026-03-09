import dijkstra_graphs as dg

CAMINHO = "experiments/data/"

def main():
    

    tamanhos =  [100, 500, 1000]
    densidades = [0.1, 0.3, 0.5, 0.7, 0.9]
    AMOSTRAS = 100

    graph_id = 0
    for num_vertices in tamanhos:
        for densidade in densidades:
            for _ in range(AMOSTRAS):
                csv = CAMINHO + f"graphs_{int(densidade * 100)}.csv"

                graph_id += 1

                graph = dg.gerar_grafos(num_vertices, densidade, graph_id)
                salvar(graph, graph_id, num_vertices, densidade, csv)



def salvar(graph,id ,num_vertices, densidade, csv):
    with open(csv, "a") as arquivo:
        for edge in graph:
            arquivo.write(str(id) + "," + str(num_vertices) + "," + ",".join(map(str, edge)) + "\n")    

main() 
