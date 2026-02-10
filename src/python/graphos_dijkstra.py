import random

def main():
    tamanhos = [5, 10, 20, 50, 100, 250, 500, 750, 1000, 1500]

    for i in range(len(tamanhos) - 1):
        for j in range(random.randint(20, 30)):
            print(gerar_grafo(tamanhos[i], tamanhos[i + 1]))

def gerar_grafo(start, stop):
    MAX_VALOR = 100
    MIN_VALOR = 1

    tamanho = random.randint(start, stop)

    grafo = ""
    for i in range(tamanho):
        grafo += str(random.randint(MIN_VALOR, MAX_VALOR)) + " "
    
    return grafo.strip()

main()