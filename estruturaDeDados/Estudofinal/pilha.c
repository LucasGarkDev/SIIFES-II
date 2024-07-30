#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Estrutura da pilha
typedef struct tipoElemento {
    char operacao[100];
    struct tipoElemento *abaixo;
} TElemento;

typedef struct tipoPilha {
    TElemento *topo;
    TElemento *base;
} TPilha;

// Função de inicializar a pilha
void inicializaPilha(TPilha *p){
    p->topo = NULL;
    p->base = NULL;
}

// Função de verificar se a pilha esta vazia
int pilhaVazia(TPilha *p){
    if (p->topo == NULL){
        return 1;
    }else{
        return 0;
    }
}

// Função para empilhar a operação
void empilhaOperacao(TPilha *p, char *operacao){
    TElemento *novo = (TElemento*)malloc(sizeof(TElemento));
    strcpy(novo->operacao,operacao);
    if (pilhaVazia(p) == 1){
        p->topo = novo;
        p->base = novo;
    }else{
        novo->abaixo = p->topo;
        p->topo = novo;
    }
}

// Função para exibir o historico
void exibirHistorico(TPilha *p){
    TElemento *atual = p->topo;
    while (atual != NULL){
        printf("%s -> ", atual->operacao);
        atual = atual->abaixo;   
    }
    printf("NULL\n");
}

//Função para desemplihar 
void desempilhaOperacao(TPilha *p){
    TElemento *removendo = p->topo;
    if (pilhaVazia(p) != 1){
        printf("Operação desempilhada: %s\n", removendo->operacao);
        p->topo = p->topo->abaixo;
        free(removendo);
    }
    
}