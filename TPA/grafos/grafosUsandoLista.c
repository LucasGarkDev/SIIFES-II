#include "grafoUsandoListas.h"

//=================================================
// Função para abrir um arquivo no modo especificado
FILE *abrirArquivo(char *nomeArq, char *modo) {
    FILE *arquivo = fopen(nomeArq, modo);
    if (arquivo == NULL) {
        printf("Erro ao abrir o arquivo %s\n", nomeArq);
        exit(1);
    }
    return arquivo;
}
//=================================================
// Inicializa o grafo com uma capacidade inicial de cidades
void inicializarGrafo(TGrafo *grafo, int capacidade) {
    grafo->cidades = (TCidade*) malloc(capacidade * sizeof(TCidade));
    grafo->numCidades = 0;
    grafo->capacidade = capacidade;
}
//=================================================
// Insere uma cidade no grafo (adiciona um vértice)
void inserirCidade(TGrafo *grafo, string nomeCidade) {
    if (grafo->numCidades >= grafo->capacidade) {
        grafo->capacidade *= 2; // Dobra a capacidade se necessário
        grafo->cidades = realloc(grafo->cidades, grafo->capacidade * sizeof(TCidade));
    }

    strcpy(grafo->cidades[grafo->numCidades].nome, nomeCidade);
    grafo->cidades[grafo->numCidades].vizinhos = NULL; // Inicializa sem vizinhos
    grafo->numCidades++;
}
//=================================================
// Insere um vizinho em uma cidade
void inserirVizinho(TGrafo *grafo, string nomeCidade, string nomeVizinho, float distancia) {
    TCidade *cidade = buscarCidade(grafo, nomeCidade);
    if (cidade == NULL) return;

    // Cria um novo vizinho
    TVizinho *novoVizinho = (TVizinho*) malloc(sizeof(TVizinho));
    strcpy(novoVizinho->nome, nomeVizinho);
    novoVizinho->distancia = distancia;
    novoVizinho->prox = cidade->vizinhos; // Insere no início da lista
    cidade->vizinhos = novoVizinho;
}
//=================================================
// Busca uma cidade pelo nome
TCidade* buscarCidade(TGrafo *grafo, string nomeCidade) {
    for (int i = 0; i < grafo->numCidades; i++) {
        if (strcmp(grafo->cidades[i].nome, nomeCidade) == 0) {
            return &grafo->cidades[i];
        }
    }
    return NULL;
}
//=================================================

//=================================================

//=================================================

//=================================================
// Libera a memória alocada para o grafo
void destruirGrafo(TGrafo *grafo) {
    for (int i = 0; i < grafo->numCidades; i++) {
        // Libera a lista de vizinhos de cada cidade
        TVizinho *vizinho = grafo->cidades[i].vizinhos;
        while (vizinho != NULL) {
            TVizinho *temp = vizinho;
            vizinho = vizinho->prox;
            free(temp);
        }
    }

    // Libera o vetor de cidades
    free(grafo->cidades);
}
//=================================================
// 1ª etapa: Lê o arquivo e cria cada cidade no início de cada bloco
void lerCidades(TGrafo *grafo, FILE *arquivo) {
    char linha[101];  // Buffer para leitura de linha

    while (fgets(linha, sizeof(linha), arquivo)) {
        linha[strcspn(linha, "\n")] = '\0';  // Remove o '\n' no final da linha

        // Se encontramos ":", indica o fim de um bloco
        if (strcmp(linha, ":") == 0) {
            continue;  // Pula o delimitador de bloco ":"
        }

        // A primeira cidade em cada bloco é o nome da cidade a ser inserida
        inserirCidade(grafo, linha);  // Cria e insere a cidade no grafo
    }
}

// 2ª etapa: Lê o arquivo novamente para associar vizinhos e distâncias
void lerVizinhos(TGrafo *grafo, FILE *arquivo) {
    char linha[101];  // Buffer para leitura de linha
    char cidadeAtual[101];  // Nome da cidade principal
    char vizinhoAtual[101];  // Nome da cidade vizinha
    float distancia;

    while (fgets(linha, sizeof(linha), arquivo)) {
        linha[strcspn(linha, "\n")] = '\0';  // Remove o '\n'

        if (strcmp(linha, ":") == 0) {
            cidadeAtual[0] = '\0';  // Reseta a cidade atual para o próximo bloco
            continue;  // Pula o delimitador de bloco ":"
        }

        // Se não temos uma cidade atual, a linha lida é o nome da cidade principal do bloco
        if (cidadeAtual[0] == '\0') {
            strcpy(cidadeAtual, linha);  // Armazena o nome da cidade principal
        } else if (sscanf(linha, "%f", &distancia) == 1) {
            // Se a linha contém um número, ela é a distância para o vizinho
            inserirVizinho(grafo, cidadeAtual, vizinhoAtual, distancia);  // Insere o vizinho com a distância
        } else {
            // Caso contrário, a linha contém o nome de um vizinho
            strcpy(vizinhoAtual, linha);
        }
    }
}

// Exibe o grafo (as cidades e seus vizinhos)
void exibirGrafo(TGrafo *grafo) {
    for (int i = 0; i < grafo->numCidades; i++) {
        printf("Cidade: %s\n", grafo->cidades[i].nome);
        TVizinho *vizinho = grafo->cidades[i].vizinhos;
        while (vizinho != NULL) {
            printf("  -> Vizinho: %s, Distância: %.2f\n", vizinho->nome, vizinho->distancia);
            vizinho = vizinho->prox;
        }
        printf("%s\n", CORTE);
    }
}

// Função principal (main)
int main() {
    TGrafo grafo;
    inicializarGrafo(&grafo, 5);  // Inicializa com uma capacidade de 5 cidades

    // Abre o arquivo "cidades.txt"
    FILE *arquivo = abrirArquivo("cidades.txt", "r");

    // 1ª etapa: Ler todas as cidades e inseri-las no grafo
    lerCidades(&grafo, arquivo);

    // Volta ao início do arquivo para a 2ª etapa
    rewind(arquivo);

    // 2ª etapa: Ler novamente e associar os vizinhos
    lerVizinhos(&grafo, arquivo);

    // Fecha o arquivo
    fclose(arquivo);

    // Exibe o grafo
    exibirGrafo(&grafo);

    // Libera a memória
    destruirGrafo(&grafo);

    return 0;
}
