import random

def main():
    tamanhos = [5, 10, 20, 50, 100, 250, 500, 750] #, 1000, 1500]
    PESO_MIN = 1
    PESO_MAX = 100
    DENSIDADE = 0.3

    for i in range(len(tamanhos) - 1):
        for j in range(random.randint(30, 50)):
            num_vertices = random.randint(tamanhos[i], tamanhos[i + 1])
            print(gerar_grafos(num_vertices, PESO_MIN, PESO_MAX, DENSIDADE))

def gerar_grafos(num_vertices, peso_min, peso_max, densidade):
    grafo = []
    for i in range(num_vertices):
        linha = []
        for j in range(num_vertices):
            linha.append(0)
        grafo.append(linha)
    
    vertices = list(range(num_vertices))
    random.shuffle(vertices)

    for i in range(len(vertices) - 1):
        peso = random.randint(peso_min, peso_max)
        v1 = vertices[i]
        v2 = vertices[i + 1]

        grafo[v1][v2] = peso
        grafo[v2][v1] = peso 

    for i in range(len(grafo)):
        for j in range(i + 1, num_vertices):
            if grafo[i][j] == 0 and random.randint(1, 100) <= densidade * 100:
                grafo[i][j] = random.randint(peso_min, peso_max)
                grafo[j][i] = grafo[i][j]            


    saida = ""
    for linha in grafo:
        for i in range(len(linha)):
            saida += str(linha[i]) + " "

    return saida.strip()

main()