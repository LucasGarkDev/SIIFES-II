#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "lista_encadeada.h"

// Função para inicializar a lista
void inicializaLista(TLista **lista){
    (*lista)->inicio == NULL;
    (*lista)->fim == NULL;
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
    if (lista->inicio == NULL){
        lista->inicio = novo;
        lista->fim = novo;
    }else{
        TNoLista *atual = (TNoLista *)malloc(sizeof(TNoLista));
        atual = lista->inicio;
        while (atual->prox != NULL){
            /* code */
        }
        
    }
    
    
}

// Função para excluir uma tarefa da lista


// Função para buscar uma tarefa na lista


// Função para exibir a lista de tarefas




