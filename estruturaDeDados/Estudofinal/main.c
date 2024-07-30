#include <stdio.h>
#include "lista_encadeada.c"
// #include "pilha.h"
// #include "avl.h"

int main() {
    // Inicializa a lista encadeada
    TLista *lista;
    inicializaLista(&lista);
   

    // Testando a Lista Encadeada
    inserirTarefa(lista, 1, "Implementar a interface", 2);
    inserirTarefa(lista, 2, "Corrigir bugs", 1);
    inserirTarefa(lista, 3, "Escrever documentação", 3);
    printf("Lista de Tarefas:\n");
    exibirTarefas(lista);
    excluirTarefa(lista, 2);
    printf("Lista de Tarefas após excluir a tarefa com ID 2:\n");
    exibirTarefas(lista);
    buscarTarefa(lista, 3);

    // Testando a Pilha (Histórico de Operações)
    // TPilha pilha;
    // inicializaPilha(&pilha);
    // empilhaOperacao(&pilha, "Inseriu tarefa com ID 1");
    // empilhaOperacao(&pilha, "Inseriu tarefa com ID 2");
    // empilhaOperacao(&pilha, "Inseriu tarefa com ID 3");
    // printf("Histórico de Operações:\n");
    // exibirHistorico(&pilha);
    // desempilhaOperacao(&pilha);
    // printf("Histórico de Operações após desempilhar:\n");
    // exibirHistorico(&pilha);

    // Testando a Árvore AVL
    // TNoAVL *raiz = NULL;
    // raiz = insereAVL(raiz, 1, "Implementar a interface", 2);
    // raiz = insereAVL(raiz, 2, "Corrigir bugs", 1);
    // raiz = insereAVL(raiz, 3, "Escrever documentação", 3);
    // printf("Árvore AVL de Tarefas (Em Ordem):\n");
    // caminhamentoEmOrdemAVL(raiz);
    // buscaAVL(raiz, 2);

    return 0;
}
