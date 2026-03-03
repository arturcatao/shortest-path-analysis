import random

#retorna a lista de adjacencia
def gerar_grafos(num_vertices, densidade):
    PESO_MIN = 1
    PESO_MAX = 100

    grafo = []
    random.seed(13)

    vertex = range(num_vertices)
    random.shuffle(vertex)
    
    for i in range(len(vertex) - 1):
        vertexA = vertex[i]
        vertexB = vertex[i + 1]
        peso = random.randint(PESO_MIN, PESO_MAX)

        edge = (vertexA, vertexB, peso)
        grafo.append(edge)

    for i in range(num_vertices):
        for j in range(i + 1, num_vertices) :
            