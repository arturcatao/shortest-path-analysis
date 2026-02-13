import random

def main():
    #tamanhos = [5, 10, 20, 50, 100, 250, 500, 750, 1000, 1500]
    tamanhos = [5, 10, 20, 50]
    
    for i in range(len(tamanhos) - 1):
        for j in range(random.randint(20, 30)):
            num_vertices = random.randint(tamanhos[i], tamanhos[i + 1])
            print(gerar_grafo(num_vertices))

def gerar_grafo(num_vertices, densidade=0.3, peso_min=1, peso_max=100):
    """
    Gera um grafo conectado válido
    
    Args:
        num_vertices: número de vértices
        densidade: probabilidade de existir aresta (0.0 a 1.0)
        peso_min: peso mínimo das arestas
        peso_max: peso máximo das arestas
    
    Returns:
        String com matriz em formato linear
    """
    # Criar matriz zerada
    grafo = [[0 for _ in range(num_vertices)] for _ in range(num_vertices)]
    
    # 1. GARANTIR CONECTIVIDADE - criar caminho mínimo
    vertices = list(range(num_vertices))
    random.shuffle(vertices)
    
    for i in range(len(vertices) - 1):
        v1 = vertices[i]
        v2 = vertices[i + 1]
        peso = random.randint(peso_min, peso_max)
        grafo[v1][v2] = peso
        grafo[v2][v1] = peso  # simétrico
    
    # 2. ADICIONAR ARESTAS ALEATÓRIAS (respeitando densidade)
    for i in range(num_vertices):
        for j in range(i + 1, num_vertices):
            # Se já não existe aresta e passa no teste de densidade
            if grafo[i][j] == 0 and random.random() < densidade:
                peso = random.randint(peso_min, peso_max)
                grafo[i][j] = peso
                grafo[j][i] = peso  # simétrico
    
    # 3. CONVERTER MATRIZ PARA STRING LINEAR
    resultado = []
    for linha in grafo:
        resultado.extend(linha)
    
    return ' '.join(map(str, resultado))

main()