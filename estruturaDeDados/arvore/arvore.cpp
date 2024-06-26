#include <stdio.h>
#include <stdlib.h>

typedef struct tipoNo{
    int valor;
    struct tipoNo *esq;
    struct tipoNo *dir;
}TNo;

TNo *raiz; // raiz inicial da arvore

//===============================================================
void inicializa(TNo *raiz){
    raiz = NULL;
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
void insere(TNo *raiz, int valor){
    if(raiz == NULL){
        // arvore vazia
        raiz = criaNo(valor);
    }else if (valor >= raiz->valor){
        // insere na direita
        if (raiz->dir == NULL){
            raiz->dir = criaNo(valor);
        }else{
            insere(raiz->dir,valor);
        }   
    }else{
        // insere na esquerda
        if (raiz->esq == NULL){
            raiz->esq = criaNo(valor);
        }else{
            insere(raiz->esq,valor);
        }
    }   
}
//===============================================================

//===============================================================

//===============================================================

//===============================================================

//===============================================================
int main(){
    inicializa(raiz);
    return 0;
}
