#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

typedef struct tipoElemento {
    long long int valor;
    char nome[101];
    int removido; // Campo para marcar elementos removidos
} TElemento;

typedef struct tipoTabelaHash {
    TElemento *vetor;
    int tamanho;
} TabelaHash;

// Função hash principal
int funcaoHash(long long int k, int tamanho) {
    return k % tamanho;
}

// Função de re-hash
int funcaoHash2(long long int k, int tamanho) {
    return 1 + (k % (tamanho - 1));
}

// Função de re-hashing
int reHash(int i, long long int k, int tamanho) {
    return (i + funcaoHash2(k, tamanho)) % tamanho;
}

// Inicializa a tabela hash
void inicializarTabelaHash(TabelaHash *tabela, int tamanho) {
    tabela->tamanho = tamanho;
    tabela->vetor = (TElemento *)malloc(tamanho * sizeof(TElemento));
    for (int i = 0; i < tamanho; i++) {
        tabela->vetor[i].valor = 0;
        tabela->vetor[i].removido = 0;
        strcpy(tabela->vetor[i].nome, "");
    }
}

// Inserir na tabela hash
void inserirTabelaHash(TabelaHash *tabela, long long int valor, char *nome) {
    int indice = funcaoHash(valor, tabela->tamanho);
    int i = 0;

    // Tenta encontrar uma posição usando re-hashing
    while (tabela->vetor[indice].valor != 0 && !tabela->vetor[indice].removido) {
        if (tabela->vetor[indice].valor == valor) {
            printf("Erro: A matrícula %lld já está presente na tabela.\n", valor);
            return;
        }
        indice = reHash(indice, valor, tabela->tamanho);
        i++;
        if (i >= tabela->tamanho) {
            printf("Erro: Tabela cheia, não foi possível inserir.\n");
            return;
        }
    }

    // Insere o elemento
    tabela->vetor[indice].valor = valor;
    strcpy(tabela->vetor[indice].nome, nome);
    tabela->vetor[indice].removido = 0; // Marca como ativo
    printf("Matrícula %lld inserida com sucesso.\n", valor);
}

// Buscar na tabela hash
int buscarTabelaHash(TabelaHash *tabela, long long int valor) {
    int indice = funcaoHash(valor, tabela->tamanho);
    int i = 0;

    // Tenta encontrar o elemento usando re-hashing
    while (tabela->vetor[indice].valor != 0 || tabela->vetor[indice].removido) {
        if (tabela->vetor[indice].valor == valor && !tabela->vetor[indice].removido) {
            printf("Matrícula %lld encontrada. Nome: %s\n", valor, tabela->vetor[indice].nome);
            return 1; // Encontrado
        }
        indice = reHash(indice, valor, tabela->tamanho);
        i++;
        if (i >= tabela->tamanho) {
            break;
        }
    }

    printf("Matrícula %lld não encontrada na tabela.\n", valor);
    return 0; // Não encontrado
}

// Remover da tabela hash
void removerTabelaHash(TabelaHash *tabela, long long int valor) {
    int indice = funcaoHash(valor, tabela->tamanho);
    int i = 0;

    // Tenta encontrar o elemento para remoção usando re-hashing
    while (tabela->vetor[indice].valor != 0 || tabela->vetor[indice].removido) {
        if (tabela->vetor[indice].valor == valor && !tabela->vetor[indice].removido) {
            tabela->vetor[indice].removido = 1; // Marca como removido
            printf("Matrícula %lld removida com sucesso.\n", valor);
            return;
        }
        indice = reHash(indice, valor, tabela->tamanho);
        i++;
        if (i >= tabela->tamanho) {
            break;
        }
    }

    printf("Erro: A matrícula %lld não foi encontrada para remoção.\n", valor);
}

// Imprime a tabela hash
void imprimirTabelaHash(TabelaHash *tabela) {
    printf("\n===| Exibição Completa da Tabela Hash |===\n");
    for (int i = 0; i < tabela->tamanho; i++) {
        if (tabela->vetor[i].valor != 0 && !tabela->vetor[i].removido) {
            printf("Índice %d: Matrícula %lld, Nome: %s\n", i, tabela->vetor[i].valor, tabela->vetor[i].nome);
        } else {
            printf("Índice %d: (vazio)\n", i);
        }
    }
    printf("========================================\n");
}

// Função principal
int main() {
    TabelaHash tabela;
    int tamanhoTabela = 11; // Exemplo de tamanho de tabela
    inicializarTabelaHash(&tabela, tamanhoTabela);

    // Teste de inserção
    inserirTabelaHash(&tabela, 123456789, "Aluno 1");
    inserirTabelaHash(&tabela, 987654321, "Aluno 2");
    inserirTabelaHash(&tabela, 111111111, "Aluno 3");

    // Teste de busca
    buscarTabelaHash(&tabela, 123456789);
    buscarTabelaHash(&tabela, 111111111);
    buscarTabelaHash(&tabela, 555555555); // Não existente

    // Teste de remoção
    removerTabelaHash(&tabela, 123456789);
    buscarTabelaHash(&tabela, 123456789); // Deverá indicar como não encontrado

    // Imprimir tabela
    imprimirTabelaHash(&tabela);

    free(tabela.vetor);
    return 0;
}
