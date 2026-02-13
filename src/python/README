# Gerador de Grafos para Teste do Algoritmo de Dijkstra

## Descrição

Este script Python gera grafos aleatórios válidos no formato de matriz de adjacência linear, específico para testar implementações do algoritmo de Dijkstra. Os grafos gerados são **não-direcionados**, **conectados** e utilizam pesos positivos nas arestas.

## Características dos Grafos Gerados

### ✅ Propriedades Garantidas

- **Formato Correto**: Cada linha contém exatamente N² elementos (para matriz N×N)
- **Diagonal Zero**: `grafo[i][i] = 0` (sem auto-loops)
- **Simetria**: `grafo[i][j] = grafo[j][i]` (grafo não-direcionado)
- **Conectividade**: Todos os vértices são alcançáveis a partir de qualquer outro vértice
- **Pesos Positivos**: Todas as arestas têm peso entre 1 e 100 (configurável)

### Estrutura do Grafo

Cada grafo é representado como uma matriz de adjacência em formato linear:
```
Entrada: 0 10 0 30 10 0 50 0 0 50 0 20 30 0 20 0
         └─────────────────┬─────────────────┘
                    16 elementos = 4² 

Representa matriz 4×4:
    0   1   2   3
0 [ 0  10   0  30 ]
1 [10   0  50   0 ]
2 [ 0  50   0  20 ]
3 [30   0  20   0 ]
```

## Como Usar

### Execução Básica
```bash
# Gerar grafos e exibir no terminal
python3 graphos_dijkstra.py

# Salvar grafos em arquivo
python3 graphos_dijkstra.py > dijkstra_graphs.csv

# Salvar em diretório específico
python3 graphos_dijkstra.py > ../../data/dijkstra_graphs.csv
```

### Saída

O script gera múltiplos grafos de diferentes tamanhos. Cada linha da saída é um grafo completo:
```
0 45 0 45 0 67 0 67 0    # Grafo 3×3 (9 elementos)
0 10 0 30 10 0 50 0 0 50 0 20 30 0 20 0    # Grafo 4×4 (16 elementos)
...
```

## Configuração

### Tamanhos de Grafos

O script gera grafos em faixas de tamanho definidas:
```python
tamanhos = [5, 10, 20, 50, 100, 250, 500, 750, 1000, 1500]
```

Para cada par de tamanhos consecutivos, gera entre 20-30 grafos aleatórios:
- 20-30 grafos entre 5 e 10 vértices
- 20-30 grafos entre 10 e 20 vértices
- 20-30 grafos entre 20 e 50 vértices
- ... e assim por diante

**Total aproximado**: 180-270 grafos

### Parâmetros da Função `gerar_grafo()`
```python
def gerar_grafo(num_vertices, densidade=0.3, peso_min=1, peso_max=100):
```

| Parâmetro | Descrição | Valor Padrão |
|-----------|-----------|--------------|
| `num_vertices` | Número de vértices no grafo | Obrigatório |
| `densidade` | Probabilidade de existir aresta (0.0 a 1.0) | 0.3 (30%) |
| `peso_min` | Peso mínimo das arestas | 1 |
| `peso_max` | Peso máximo das arestas | 100 |

### Personalizando a Geração

#### Grafos Mais Esparsos (Menos Arestas)
```python
def gerar_grafo_esparso(num_vertices):
    return gerar_grafo(num_vertices, densidade=0.1)  # 10% de arestas
```

#### Grafos Mais Densos (Mais Arestas)
```python
def gerar_grafo_denso(num_vertices):
    return gerar_grafo(num_vertices, densidade=0.7)  # 70% de arestas
```

#### Pesos Maiores
```python
grafo = gerar_grafo(100, peso_min=10, peso_max=1000)
```

## Algoritmo de Geração

O script segue três etapas principais:

### 1. Inicialização
```python
# Cria matriz zerada N×N
grafo = [[0 for _ in range(num_vertices)] for _ in range(num_vertices)]
```

### 2. Garantir Conectividade
```python
# Cria caminho aleatório passando por todos os vértices
vertices = list(range(num_vertices))
random.shuffle(vertices)

for i in range(len(vertices) - 1):
    v1 = vertices[i]
    v2 = vertices[i + 1]
    peso = random.randint(peso_min, peso_max)
    grafo[v1][v2] = peso
    grafo[v2][v1] = peso  # Simetria
```

### 3. Adicionar Arestas Aleatórias
```python
# Adiciona arestas extras respeitando a densidade
for i in range(num_vertices):
    for j in range(i + 1, num_vertices):
        if grafo[i][j] == 0 and random.random() < densidade:
            peso = random.randint(peso_min, peso_max)
            grafo[i][j] = peso
            grafo[j][i] = peso
```

### 4. Conversão para Formato Linear
```python
# Converte matriz 2D para string linear
resultado = []
for linha in grafo:
    resultado.extend(linha)
return ' '.join(map(str, resultado))
```

## Validação

Para verificar se um grafo gerado é válido:
```python
def validar_grafo(grafo_str):
    elementos = list(map(int, grafo_str.split()))
    
    # Verifica se é quadrado perfeito
    import math
    n = int(math.sqrt(len(elementos)))
    if n * n != len(elementos):
        return False, f"Não é quadrado perfeito: {len(elementos)} elementos"
    
    # Converte para matriz
    matriz = [elementos[i*n:(i+1)*n] for i in range(n)]
    
    # Verifica diagonal zero
    for i in range(n):
        if matriz[i][i] != 0:
            return False, f"Diagonal não zero na posição [{i}][{i}]"
    
    # Verifica simetria
    for i in range(n):
        for j in range(i+1, n):
            if matriz[i][j] != matriz[j][i]:
                return False, f"Não simétrico"
    
    return True, "Grafo válido"
```

## Integração com Java

Os grafos gerados podem ser consumidos diretamente pelo script Java:
```bash
# Gerar grafos
python3 graphos_dijkstra.py > grafos.txt

# Processar com Java
java AnaliseDijkstra < grafos.txt
```

O script Java:
1. Lê cada linha
2. Calcula `N = √(número_de_elementos)`
3. Reconstrói a matriz N×N
4. Executa Dijkstra e mede o tempo

## Exemplo de Uso Completo
```bash
# 1. Gerar grafos de teste
python3 graphos_dijkstra.py > test_graphs.csv

# 2. Executar análise
java AnaliseDijkstra < test_graphs.csv > resultados.csv

# 3. Analisar resultados
# resultados.csv contém: Dijkstra,tempo(ns),vertices
```

## Complexidade

| Vértices | Elementos | Tempo Geração | Memória |
|----------|-----------|---------------|---------|
| 10       | 100       | < 1ms         | ~1KB    |
| 100      | 10.000    | ~10ms         | ~100KB  |
| 1000     | 1.000.000 | ~1s           | ~10MB   |
| 5000     | 25.000.000| ~30s          | ~250MB  |

## Limitações

- **Grafos muito grandes** (>5000 vértices) podem consumir muita memória
- A **densidade** afeta o tempo de geração (grafos densos são mais lentos)
- Não gera grafos com **arestas direcionadas** (sempre simétricos)
- Pesos são sempre **positivos** (adequado para Dijkstra)

## Requisitos

- Python 3.6 ou superior
- Biblioteca padrão (sem dependências externas)

## Autor

Script desenvolvido para teste de performance do algoritmo de Dijkstra.

## Licença

Código livre para uso educacional e acadêmico.