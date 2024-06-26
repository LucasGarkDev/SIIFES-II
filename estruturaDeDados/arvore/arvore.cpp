#include <stdio.h>
#include <stdlib.h>

typedef struct tipoNo{
    int valor;
    struct tipoNo *esq;
    struct tipoNo *dir;
}TNo;

TNo *raiz; // raiz inicial da arvore

//===============================================================
void inicializa(TNo **raiz){
    *raiz = NULL;
}
//===============================================================
TNo *criaNo(int valor){
    TNo *novo = (TNo *)malloc(sizeof(TNo));
    novo->valor = valor;
    novo->esq = NULL;
    novo->dir = NULL;
    return novo;
}
//===============================================================
void insere(TNo **raiz, int valor){
    if(*raiz == NULL){
        // arvore vazia
        // printf("Primeiro no da arvore\n");
        *raiz = criaNo(valor);
    }else if (valor >= (*raiz)->valor){
        // insere na direita
        if ((*raiz)->dir == NULL){
            (*raiz)->dir = criaNo(valor);
            // printf("Foi inserido na direita\n");
        }else{
            // printf("chama a funçao para a direita\n");
            insere(&(*raiz)->dir,valor);
        }   
    }else{
        // insere na esquerda
        if ((*raiz)->esq == NULL){
            // printf("Foi inserido na esquerda\n");
            (*raiz)->esq = criaNo(valor);
        }else{
            // printf("chama a funçao para a esquerda\n");
            insere(&(*raiz)->esq,valor);
        }
    }   
}
//===============================================================
void caminhamentoEmOrdem(TNo *raiz){
    if(raiz != NULL){
        caminhamentoEmOrdem(raiz->esq);
        printf("%d, ",raiz->valor);
        caminhamentoEmOrdem(raiz->dir);
    }    
}
//===============================================================
void caminhamentoPreOrdem(TNo *raiz){
    if(raiz != NULL){
        printf("%d, ",raiz->valor);
        caminhamentoEmOrdem(raiz->esq);
        caminhamentoEmOrdem(raiz->dir);
    }    
}
//===============================================================

//===============================================================

//===============================================================
int main(){
    inicializa(&raiz);
    insere(&raiz,56);
    insere(&raiz,41);
    insere(&raiz,99);
    insere(&raiz,32);
    insere(&raiz,35);
    insere(&raiz,92);
    insere(&raiz,86);
    caminhamentoEmOrdem(raiz);
    printf("\t\t caminhamento em ordem \t\n");
    caminhamentoPreOrdem(raiz);
    printf("\t\t caminhamento pre ordem \t\n");
    return 0;
}
