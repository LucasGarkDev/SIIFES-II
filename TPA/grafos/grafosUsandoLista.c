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
// 1ª etapa: Lê o arquivo para coletar todas as cidades (primeiros nomes de cada bloco)
void lerCidades(TGrafo *grafo, FILE *arquivo) {
    char cidadeAtual[101];   // Nome da cidade principal

    while (fscanf(arquivo, "%s", cidadeAtual) != EOF) {
        // Se encontramos ":", indica o fim de um bloco
        if (strcmp(cidadeAtual, ":") == 0) {
            continue;  // Pula o delimitador de bloco ":"
        }

        // Insere a cidade atual no grafo
        inserirCidade(grafo, cidadeAtual);
    }
}

// 2ª etapa: Lê o arquivo novamente para associar vizinhos e distâncias
void lerVizinhos(TGrafo *grafo, FILE *arquivo) {
    char cidadeAtual[101];   // Nome da cidade principal
    char vizinhoAtual[101];  // Nome da cidade vizinha
    float distancia;

    while (fscanf(arquivo, "%s", cidadeAtual) != EOF) {
        // Se encontramos ":", indica o fim de um bloco
        if (strcmp(cidadeAtual, ":") == 0) {
            continue;
        }

        // Continuar lendo vizinhos e distâncias até encontrar o próximo ":"
        while (fscanf(arquivo, "%s", vizinhoAtual) != EOF && strcmp(vizinhoAtual, ":") != 0) {
            fscanf(arquivo, "%f", &distancia);  // Lê a distância associada ao vizinho
            inserirVizinho(grafo, cidadeAtual, vizinhoAtual, distancia);  // Insere o vizinho
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
