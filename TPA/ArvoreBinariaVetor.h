//Feito por: Lucas Garcia E Luis Augusto
#include <math.h>
#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <time.h>

typedef char string[101];
typedef clock_t processTime;

#define FATOR_SEGURANCA 1.5 // Define um fator de segurança para a alocação dinâmica (150% do tamanho original)

typedef struct {
    long long int matricula;
    string nome;
    int ocupado; // 0 para vazio, 1 para ocupado
} NoArvore;

typedef struct {
    NoArvore *elementos; // Agora é um ponteiro para permitir alocação dinâmica
    int tamanho; // Número de elementos ocupados
    int capacidade; // Capacidade atual do vetor
} ArvoreBinaria;

// Funções principais
void inicializarArvore(ArvoreBinaria *arvore);
void inserirAluno(ArvoreBinaria *arvore, long long int matricula, char *nome);
void buscarAluno(ArvoreBinaria *arvore, long long int matricula);
void removerAluno(ArvoreBinaria *arvore, long long int matricula);
void imprimirEmOrdem(ArvoreBinaria *arvore, int i);
void salvarDadosNoArquivo(ArvoreBinaria *arvore, FILE *arquivoLista);
void lerEInserirMatriculas(ArvoreBinaria *arvore, FILE *arquivoLista);
FILE *abrirArquivo(char *nomeArq, char *modo);
void calcularTempo(double ini, double fim);
void menuPrincipal(ArvoreBinaria *arvore);