#ifndef LISTA_ENCADEADA_H
#define LISTA_ENCADEADA_H

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

#endif // LISTA_ENCADEADA_H

