import random

#retorna a lista de adjacencia
def gerar_grafos(num_vertices, densidade):
    PESO_MIN = 1
    PESO_MAX = 100

    graph = []
    existing_eges = set()

    vertex = list(range(num_vertices))
    random.shuffle(vertex)
    
    for i in range(len(vertex) - 1):
        vertexA = vertex[i]
        vertexB = vertex[i + 1]

        peso = random.randint(PESO_MIN, PESO_MAX)

        edge = (vertexA, vertexB, peso)
        graph.append(edge)
        existing_eges.add((min(vertexA, vertexB), max(vertexA, vertexB)))

    for i in range(num_vertices):
        for j in range(i + 1, num_vertices) :
            pair = (i, j)
            if pair not in existing_eges and random.randint(1, 100) <= densidade * 100:
                peso = random.randint(PESO_MIN, PESO_MAX)
                edge = (i, j, peso)
                graph.append(edge)
                existing_eges.add((i, j))
    
    return graph


def gerar_grafos_esparsos(num_vertices):
    grafos = densidade = 0.1
    return gerar_grafos(num_vertices, densidade)


def gerar_grafos_medios(num_vertices):
    densidade = 0.5
    return gerar_grafos(num_vertices, densidade)


def gerar_grafos_densos(num_vertices):
    densidade = 0.9
    return gerar_grafos(num_vertices, densidade)