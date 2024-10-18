#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TAM_NOME 100
#define TAM_INICIAL 20  // Capacidade inicial maior para evitar realocações frequentes

// Estrutura que representa um vizinho de uma cidade
typedef struct tipoVizinho {
    char nome[TAM_NOME];         // Nome da cidade vizinha
    double distancia;            // Distância entre as cidades
    struct tipoVizinho *prox;    // Ponteiro para o próximo vizinho (lista encadeada)
} TVizinho;

// Estrutura que representa uma cidade
typedef struct tipoCidade {
    char nome[TAM_NOME];         // Nome da cidade
    TVizinho *vizinhos;          // Lista encadeada de vizinhos
} TCidade;

// Estrutura que representa o grafo de cidades
typedef struct tipoGrafo {
    TCidade *cidades;            // Vetor de cidades
    int numCidades;              // Número de cidades no grafo
    int capacidade;              // Capacidade do vetor de cidades
} TGrafo;

// Função para criar um novo grafo
TGrafo* criarGrafo(int capacidade) {
    TGrafo *grafo = (TGrafo *)malloc(sizeof(TGrafo));
    if (grafo == NULL) {
        printf("Erro ao alocar memória para o grafo!\n");
        exit(1);
    }
    grafo->cidades = (TCidade *)malloc(capacidade * sizeof(TCidade));
    if (grafo->cidades == NULL) {
        printf("Erro ao alocar memória para as cidades!\n");
        exit(1);
    }
    grafo->numCidades = 0;
    grafo->capacidade = capacidade;
    return grafo;
}

// Função para adicionar uma cidade ao grafo
void adicionarCidade(TGrafo *grafo, char *nome) {
    // Verificar se precisamos realocar mais memória
    if (grafo->numCidades >= grafo->capacidade) {
        grafo->capacidade *= 2;
        TCidade *novoCidades = (TCidade *)realloc(grafo->cidades, grafo->capacidade * sizeof(TCidade));
        if (novoCidades == NULL) {
            printf("Erro ao realocar memória para as cidades!\n");
            exit(1);
        }
        grafo->cidades = novoCidades;
    }
    // Adicionar nova cidade
    strcpy(grafo->cidades[grafo->numCidades].nome, nome);
    grafo->cidades[grafo->numCidades].vizinhos = NULL;
    grafo->numCidades++;
}

// Função para encontrar o índice de uma cidade no grafo
int encontrarCidade(TGrafo *grafo, char *nome) {
    for (int i = 0; i < grafo->numCidades; i++) {
        if (strcmp(grafo->cidades[i].nome, nome) == 0) {
            return i;
        }
    }
    return -1; // Cidade não encontrada
}

// Função para adicionar um vizinho a uma cidade
void adicionarVizinho(TCidade *cidade, char *nomeVizinho, double distancia) {
    TVizinho *novoVizinho = (TVizinho *)malloc(sizeof(TVizinho));
    if (novoVizinho == NULL) {
        printf("Erro ao alocar memória para o vizinho!\n");
        exit(1);
    }
    strcpy(novoVizinho->nome, nomeVizinho);
    novoVizinho->distancia = distancia;
    novoVizinho->prox = cidade->vizinhos;
    cidade->vizinhos = novoVizinho;
}

// Função para ler o arquivo no novo formato
void lerArquivo(TGrafo *grafo, const char *nomeArquivo) {
    FILE *arquivo = fopen(nomeArquivo, "r");
    if (arquivo == NULL) {
        printf("Erro ao abrir o arquivo!\n");
        return;
    }

    char linha[TAM_NOME * 10];  // Buffer maior para ler cada linha completa
    while (fgets(linha, sizeof(linha), arquivo) != NULL) {
        // Remove o '\n'
        linha[strcspn(linha, "\n")] = '\0';

        // Verifica se é o delimitador ":"
        if (strcmp(linha, ":") == 0) {
            continue;  // Pule para a próxima linha
        }

        // Quebra a linha em tokens separados por vírgula
        char *token = strtok(linha, ",");
        char cidade1[TAM_NOME], cidade2[TAM_NOME];
        double distancia;

        // Pega o primeiro nome de cidade
        if (token != NULL) {
            strcpy(cidade1, token);
            if (encontrarCidade(grafo, cidade1) == -1) {
                adicionarCidade(grafo, cidade1);
            }
        }

        // Processa pares de cidade2 e distância
        while ((token = strtok(NULL, ",")) != NULL) {
            strcpy(cidade2, token);

            token = strtok(NULL, ",");  // Pega a distância
            if (token != NULL) {
                distancia = atof(token);

                // Adiciona a vizinhança bidirecional
                int idxCidade1 = encontrarCidade(grafo, cidade1);
                int idxCidade2 = encontrarCidade(grafo, cidade2);

                adicionarVizinho(&grafo->cidades[idxCidade1], cidade2, distancia);
                adicionarVizinho(&grafo->cidades[idxCidade2], cidade1, distancia);
            }

            // Agora cidade2 passa a ser cidade1 para o próximo par
            strcpy(cidade1, cidade2);
        }
    }

    fclose(arquivo);
}

// Função para imprimir o grafo
void imprimirGrafo(TGrafo *grafo) {
    for (int i = 0; i < grafo->numCidades; i++) {
        printf("Cidade: %s\n", grafo->cidades[i].nome);
        TVizinho *viz = grafo->cidades[i].vizinhos;
        while (viz != NULL) {
            printf("  Vizinho: %s, Distância: %.2lf\n", viz->nome, viz->distancia);
            viz = viz->prox;
        }
    }
}

// Função para liberar a memória alocada
void liberarGrafo(TGrafo *grafo) {
    for (int i = 0; i < grafo->numCidades; i++) {
        TVizinho *viz = grafo->cidades[i].vizinhos;
        while (viz != NULL) {
            TVizinho *temp = viz;
            viz = viz->prox;
            free(temp);
        }
    }
    free(grafo->cidades);
    free(grafo);
}

// Função principal
int main() {
    TGrafo *grafo = criarGrafo(TAM_INICIAL);

    // Nome do arquivo de entrada
    char nomeArquivo[] = "cidades.txt"; // Altere o nome conforme necessário

    lerArquivo(grafo, nomeArquivo);
    imprimirGrafo(grafo);

    // Liberação de memória após a execução
    liberarGrafo(grafo);

    return 0;
}
