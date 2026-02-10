import subprocess


def executar_analise_java():
    # Executar o comando Java com redirecionamento
    subprocess.run(
        "javac ../java/Analise",
        shell=True
    )

    subprocess.run(
        "java ../java/Analise < ../../data/graphs.csv > ../../data/resultado.csv",
        shell=True
    )

def main():
    executar_analise_java()

main()