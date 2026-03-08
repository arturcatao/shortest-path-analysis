import random
import math

#retorna a lista de adjacencia
def gerar_grafos(num_vertices, densidade, seed):
    random.seed(seed)
    
    PESO_MIN = 1
    PESO_MAX = 100

    graph = []
    existing_edges = set()

    # 1. Garante que o grafo é conexo (caminho base)
    vertex = list(range(num_vertices))
    random.shuffle(vertex)
    
    for i in range(len(vertex) - 1):
        vertexA = vertex[i]
        vertexB = vertex[i + 1]
        peso = random.randint(PESO_MIN, PESO_MAX)
        edge = (vertexA, vertexB, peso)
        graph.append(edge)
        # Salva o par ordenado para a busca no Set ser efetiva
        existing_edges.add((min(vertexA, vertexB), max(vertexA, vertexB)))

    # 2. Preenchimento de arestas otimizado!
    # Calcula quantas arestas no total esse grafo deve ter
    max_possible_edges = (num_vertices * (num_vertices - 1)) // 2
    target_edges = int(max_possible_edges * densidade)

    # Regra: Se o grafo for muito grande e muito esparso, fazemos por sorteio (MUITO mais rápido)
    if num_vertices >= 5000 and densidade < 0.1:
        while len(graph) < target_edges:
            u = random.randint(0, num_vertices - 1)
            v = random.randint(0, num_vertices - 1)
            if u != v:
                pair = (min(u, v), max(u, v))
                if pair not in existing_edges:
                    peso = random.randint(PESO_MIN, PESO_MAX)
                    graph.append((u, v, peso))
                    existing_edges.add(pair)
    
    # Regra: Se for denso ou pequeno, usamos o seu loop de varredura original (evita colisão)
    else:
        for i in range(num_vertices):
            for j in range(i + 1, num_vertices):
                pair = (i, j)
                # Dica: random.random() é um pouco mais rápido que randint para porcentagens!
                if pair not in existing_edges and random.random() <= densidade:
                    peso = random.randint(PESO_MIN, PESO_MAX)
                    edge = (i, j, peso)
                    graph.append(edge)
                    existing_edges.add(pair)
    
    return graph


def gerar_grafos_esparsos(num_vertices, seed):
    densidade = math.log(num_vertices) / num_vertices
    return gerar_grafos(num_vertices, densidade, seed)


def gerar_grafos_medios(num_vertices, seed):
    densidade = 1 / math.sqrt(num_vertices)
    return gerar_grafos(num_vertices, densidade, seed)


def gerar_grafos_densos(num_vertices, seed):
    densidade = 0.80 # 80% de todas as conexões possíveis
    return gerar_grafos(num_vertices, densidade, seed)