import random
import argparse

def gerar_grafo_aleatorio(num_vertices, densidade=0.5, peso_min=1, peso_max=100, simetrico=False):
    """
    Gera um grafo aleatório como matriz de adjacência
    Args:
        num_vertices: número de vértices do grafo
        densidade: probabilidade de existir uma aresta (0.0 a 1.0)
        peso_min: peso mínimo das arestas
        peso_max: peso máximo das arestas
        simetrico: se True, gera grafo não-direcionado (simétrico)
    """
    grafo = [[0] * num_vertices for _ in range(num_vertices)]
    
    for i in range(num_vertices):
        for j in range(num_vertices):
            if i == j:
                continue
            
            # Se já foi definido (grafo simétrico), pula
            if simetrico and grafo[j][i] != 0:
                grafo[i][j] = grafo[j][i]
                continue
            
            if random.random() < densidade:
                peso = random.randint(peso_min, peso_max)
                grafo[i][j] = peso
    
    # Converte para formato de espaços (compatível com Java)
    flat = []
    for linha in grafo:
        flat.extend(linha)
    return ' '.join(map(str, flat))


def main():
    parser = argparse.ArgumentParser(description='Gera grafos aleatórios para análise de Dijkstra')
    parser.add_argument('--tamanhos', nargs='+', type=int, default=[5, 10, 20, 50, 100],
                        help='Lista de tamanhos de grafos')
    parser.add_argument('--densidade', type=float, default=0.3,
                        help='Densidade do grafo (0.0 a 1.0)')
    parser.add_argument('--amostras', type=int, default=3,
                        help='Número de grafos por tamanho')
    parser.add_argument('--simetrico', action='store_true',
                        help='Gera grafos não-direcionados (simétricos)')
    parser.add_argument('--peso-min', type=int, default=1,
                        help='Peso mínimo das arestas')
    parser.add_argument('--peso-max', type=int, default=100,
                        help='Peso máximo das arestas')
    
    args = parser.parse_args()
    
    for tamanho in args.tamanhos:
        for _ in range(args.amostras):
            grafo = gerar_grafo_aleatorio(
                tamanho, 
                args.densidade, 
                args.peso_min, 
                args.peso_max,
                args.simetrico
            )
            print(grafo)


if __name__ == "__main__":
    main()