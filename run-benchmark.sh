#!usr/bin/bash

set -e # interrompe caso tenha erro

python3 python/graphs_generator.py

mvn clean package

java -jar target/benchmarks.jar -rf csv -rff temp.csv #executa os benchmarks

mkdir -p experiments/results

if [ -f experiments/results/dijkstra_results.csv ]; then 
    tail -n +2 temp.csv >> experiments/results/djikstra_results.csv
else 
    mv temp.csv experiments/results/dijkstra_results.csv
fi

rm -f temp.csv


