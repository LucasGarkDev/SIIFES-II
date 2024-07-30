#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Estrutura de um nó da lista encadeada
typedef struct tipoNoLista {
    int id;
    char descricao[100];
    int prioridade;
    struct tipoNoLista *prox, *ante;
} TNoLista;

typedef struct tipoLista {
    TNoLista *inicio, *fim;
    int totalDeElementos;
} TLista;

void inicializaLista(TLista **lista);
void inserirTarefa(TLista *lista, int id, char *descricao, int prioridade);
void excluirTarefa(TLista *lista, int id);
int buscarTarefa(TLista *lista, int id);
void exibirTarefas(TLista *lista);

// Função para inicializar a lista
void inicializaLista(TLista **lista) {
    *lista = (TLista *)malloc(sizeof(TLista));
    (*lista)->inicio = NULL;
    (*lista)->fim = NULL;
    (*lista)->totalDeElementos = 0;
}

// Função de iniciar elemento tarefa nova
void criaTarefa(TNoLista *tarefa, int id, char *descricao, int prioridade){
    tarefa->id = id;
    tarefa->prioridade = prioridade;
    strcpy(tarefa->descricao,descricao);
    tarefa->prox = NULL;
    tarefa->ante = NULL;
}

// Função para inserir uma tarefa na lista ordenada por prioridade
void inserirTarefa(TLista *lista, int id, char *descricao, int prioridade){
    TNoLista *novo = (TNoLista *)malloc(sizeof(TNoLista));
    criaTarefa(novo,id,descricao,prioridade);
    if (lista->inicio == NULL) {
        lista->inicio = novo;
        lista->fim = novo;
    } else {
        TNoLista *atual = lista->inicio;
        while (atual != NULL) {
            if (atual->prioridade >= novo->prioridade) {
                novo->prox = atual;
                novo->ante = atual->ante;
                if (atual->ante != NULL) {
                    atual->ante->prox = novo;
                } else {
                    lista->inicio = novo;
                }
                atual->ante = novo;
                lista->totalDeElementos++;
                return;
            }
            if (atual->prox == NULL) {
                break;
            }
            atual = atual->prox;
        }
        atual->prox = novo;
        novo->ante = atual;
        lista->fim = novo;
    }
    lista->totalDeElementos++;
}

// Função para excluir uma tarefa da lista


// Função para buscar uma tarefa na lista


// Função para exibir a lista de tarefas
void exibirTarefas(TLista *lista) {
    TNoLista *atual = lista->inicio;
    while (atual != NULL) {
        printf("\nID=%d, Descrição=%s, Prioridade=%d ->", atual->id, atual->descricao, atual->prioridade);
        atual = atual->prox;
    }
    printf("NULL\n");
}



