import pandas as pd
import matplotlib.pyplot as plt


df = pd.read_csv("../../data/resultado.csv")

tipos = df["Algo"].unique()

plt.figure(figsize=(10, 6))

for tipo in tipos:
    dados_tipo = df[df["Algo"] == tipo]

    dados_tipo = dados_tipo.sort_values("Size")
    plt.plot(
        dados_tipo["Size"],
        dados_tipo["Time"],
        marker="o",
        linestyle="-",
        label=tipo
    )

plt.xlabel('Tamanho (edges)')
plt.ylabel('Tempo (ns)')
plt.title('Tempo vs Tamanho')
plt.legend()
plt.grid(True, alpha=0.3)

plt.tight_layout()
plt.savefig('dijkstra', dpi=300, bbox_inches='tight')
plt.show()