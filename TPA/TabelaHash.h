#include <stdio.h>
#include <stdlib.h>

#define INICIO "------------INICIO------------" 
#define RESULTADO "------------RESULTADO------------"
#define CORTE "------------------------"
typedef struct tipoElemento{
    int valor;
    struct tipoElemento *prox; 
}TElemento;

typedef struct tipoLista{
    TElemento *inicio, *fim;    
    int total;    
}TLista;

typedef struct tipoTabelaHash {
    TLista *vetorListas;  // Vetor de listas encadeadas
    int tamanho;          // Tamanho da tabela (número de posições no vetor)
}TabelaHash;


FILE * abrirArquivo(char * nomeArq, char * modo);
void construirListaDoZero(TLista *lista);
void lerArquivo(TLista *lista, FILE *arquivoLista);
void inicializa(TLista *lista, FILE *arquivoLista);
void gravarListaEmArquivo(TLista *lista, FILE *arquivoLista);
int pesquisarMatricula(TLista *lista);
void inserir(TLista *lista, int valor);
void exibeLista(TLista lista);
void excluirLista(TLista *lista, int valor);
int pedirOpcao();
int pedirNum(int caminhoASerEscolhido);
void menuPrincipal(TLista *listaEncadeada);