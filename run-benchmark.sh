#!/usr/bin/bash
set -e # interrompe caso tenha erro
# Gerar os grafos
echo "Gerando grafos..."
python3 python/graphs_generator.py
echo "Compilando projeto..."
mvn clean package
# Executa o benchmark com medição de memória
echo "Executando benchmarks..."
java -jar target/benchmarks.jar \
-rf csv \
-rff temp.csv \
-prof gc \
-jvmArgs "-Xmx20g"
echo "Salvando os resultados..."
mkdir -p experiments/results
if [ -f experiments/results/dijkstra_results.csv ]; then 
tail -n +2 temp.csv >> experiments/results/dijkstra_results.csv
else 
mv temp.csv experiments/results/dijkstra_results.csv
fi
rm -f temp.csv
echo "Processo finalizado com sucesso."
