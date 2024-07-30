#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Estrutura da arvore
typedef struct tipoNoAVL {
    int id;
    char descricao[100];
    int prioridade;
    struct tipoNoAVL *esq;
    struct tipoNoAVL *dir;
    int altura;
} TNoAVL;

TNoAVL *rotacaoDireita(TNoAVL *y);
TNoAVL *rotacaoEsquerda(TNoAVL *x);
int buscaAVL(TNoAVL* no, int id);


// Função para descobrir a altura
int altura(TNoAVL *N){
    if (N == NULL){
        return 1;
    }
    return N->altura;
}

// Função para fazer a rotação direita
TNoAVL *rotacaoDireita(TNoAVL *y){
    TNoAVL *x = y->esq;
    TNoAVL *t2 = x->dir;

    x->dir = y;
    y->esq = t2;

    y->altura = max(altura(y->esq), altura(y->dir)) + 1;
    x->altura = max(altura(x->esq), altura(x->dir)) + 1;

    return x;
}

// Função para realizar uma rotação à esquerda
TNoAVL *rotacaoEsquerda(TNoAVL *x) {
    TNoAVL *y = x->dir;
    TNoAVL *t2 = y->esq;

    y->esq = x;
    x->dir = t2;

    x->altura = max(altura(x->esq), altura(x->dir)) + 1;
    y->altura = max(altura(y->esq), altura(y->dir)) + 1;

    return y;
}


// Função para saber se a e maior que b
int max(int a, int b){
    if (a > b){
        return a;
    }else{
        return b;
    }
}

// Função para saber o fator de balanceamento
int getBalanco(TNoAVL *N){
    if (N == NULL)
        return 0;
    return altura(N->esq) - altura(N->dir);
}

// Função para criar um novo No para a arvore
TNoAVL* novoNoAVL(int id, char *descricao, int prioridade){
    TNoAVL *novo = (TNoAVL*)malloc(sizeof(TNoAVL));
    novo->id = id;
    strcpy(novo->descricao,descricao);
    novo->prioridade = prioridade;
    novo->dir = NULL;
    novo->esq = NULL;
    return novo;
}

// Função para balancear a arvore
TNoAVL* balancearNo(TNoAVL* no, int id) {
    int estaBalanceado = getBalanco(no);
    if (estaBalanceado > 1) {
        // Desbalanceada para a esquerda
        if (getBalanco(no->esq) >= 0) {
            // Rotaçao simples à direita
            no = rotacaoDireita(no);
        } else {
            // Rotaçao dupla à direita (esquerda-direita)
            no->esq = rotacaoEsquerda(no->esq);
            no = rotacaoDireita(no);
        }
    } else if (estaBalanceado < -1) {
        // Desbalanceada para a direita
        if (getBalanco(no->dir) <= 0) {
            // Rotaçao simples à esquerda
            no = rotacaoEsquerda(no);
        } else {
            // Rotaçao dupla à esquerda (direita-esquerda)
            no->dir = rotacaoDireita(no->dir);
            no = rotacaoEsquerda(no);
        }
    }
    return no;
}

// Função para inserir um novo No na arvore
TNoAVL* insereAVL(TNoAVL* no, int id, char *descricao, int prioridade){
    if (no == NULL){
        return novoNoAVL(id,descricao,prioridade);
    }
    if (prioridade > no->prioridade){
        no->dir = insereAVL(no->dir,id,descricao,prioridade);
    }else if (prioridade < no->prioridade){
        no->esq = insereAVL(no->esq,id,descricao,prioridade);
    }else{
        return no;
    }
    
    no->altura = 1 + max(altura(no->dir),altura(no->esq)); 

    return balancearNo(no,id);
    
}

// Função para fazer o caminhamento em ordem na arvore
void caminhamentoEmOrdemAVL(TNoAVL *raiz){
    if (raiz != NULL){
        caminhamentoEmOrdemAVL(raiz->esq);
        printf("ID=%d, Descrição=%s, Prioridade=%d\n", raiz->id, raiz->descricao, raiz->prioridade);
        caminhamentoEmOrdemAVL(raiz->dir);
    }
}

