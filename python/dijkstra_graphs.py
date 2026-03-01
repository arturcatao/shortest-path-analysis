import random


def gerar_grafos(num_vertices, densidade):
    PESO_MIN = 1
    PESO_MAX = 100

    random.seed(13)
    # Gera a matriz do grafo
    grafo = []
    for i in range(num_vertices):
        linha = []
        for j in range(num_vertices):
            linha.append(0)
        grafo.append(linha)
    
    # Embaralha os vertices para gerar arestas aleatorias
    vertices = list(range(num_vertices))
    random.shuffle(vertices)

    # Gera as arestas de A para B e B para A, garantindo que o grafo seja conexo
    for i in range(len(vertices) - 1):
        peso = random.randint(PESO_MIN, PESO_MAX)
        v1 = vertices[i]
        v2 = vertices[i + 1]

        grafo[v1][v2] = peso
        grafo[v2][v1] = peso 

    # Gera mais arestas para ficar de acordo com densidade
    for i in range(len(grafo)):
        for j in range(i + 1, num_vertices):
            if grafo[i][j] == 0 and random.randint(1, 100) <= densidade * 100:
                grafo[i][j] = random.randint(PESO_MIN, PESO_MAX)
                grafo[j][i] = grafo[i][j]            


    saida = ""
    for linha in grafo:
        for i in range(len(linha)):
            saida += str(linha[i]) + " "

    return saida.strip()


def gerar_grafos_esparsos(num_vertices):
    grafos = densidade = 0.1
    return gerar_grafos(num_vertices, densidade)


def gerar_grafos_medios(num_vertices):
    densidade = 0.5
    return gerar_grafos(num_vertices, densidade)


def gerar_grafos_densos(num_vertices):
    densidade = 0.9
    return gerar_grafos(num_vertices, densidade)