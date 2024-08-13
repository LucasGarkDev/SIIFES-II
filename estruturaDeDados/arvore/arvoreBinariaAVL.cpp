#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct tipoNo {
    char nome[100];
    int nivelProfundidade;
    struct tipoNo *esq;
    struct tipoNo *dir;
    struct tipoNo *raiz;
} TNo;

TNo *raiz; // raiz inicial da arvore
//=============================================================================
int altura(TNo *N) {
    if (N == NULL) {
        return 0;
    } else {
        return N->nivelProfundidade;
    }
}
//=============================================================================
int max(int a, int b) {
    if (a > b) {
        return a;
    } else {
        return b;
    }
}
//=============================================================================
int getBalanco(TNo *N) {
    if (N == NULL) {
        return 0;
    }
    return altura(N->esq) - altura(N->dir);
}
//=============================================================================
TNo *criaNo(char *nome, TNo *raiz) {
    TNo *novo = (TNo *)malloc(sizeof(TNo));
    strcpy(novo->nome, nome);
    novo->nivelProfundidade = 1;
    novo->raiz = raiz;
    novo->esq = NULL;
    novo->dir = NULL;
    return novo;
}
//=============================================================================
void inicializa(TNo **raiz, char *nome) {
    *raiz = criaNo(nome, NULL);
}
//=============================================================================
TNo *rotacaoDireita(TNo *y) {
    TNo *x = y->esq;
    TNo *T2 = x->dir;

    x->dir = y;
    y->esq = T2;

    if (T2 != NULL) {
        T2->raiz = y;
    }
    x->raiz = y->raiz;
    y->raiz = x;

    y->nivelProfundidade = max(altura(y->esq), altura(y->dir)) + 1;
    x->nivelProfundidade = max(altura(x->esq), altura(x->dir)) + 1;

    return x;
}
//=============================================================================
TNo *rotacaoEsquerda(TNo *x) {
    TNo *y = x->dir;
    TNo *T2 = y->esq;

    y->esq = x;
    x->dir = T2;

    if (T2 != NULL) {
        T2->raiz = x;
    }
    y->raiz = x->raiz;
    x->raiz = y;

    x->nivelProfundidade = max(altura(x->esq), altura(x->dir)) + 1;
    y->nivelProfundidade = max(altura(y->esq), altura(y->dir)) + 1;

    return y;
}
//=============================================================================
TNo *balancearNo(TNo *no) {
    int balanco = getBalanco(no);

    if (balanco > 1) {
        if (getBalanco(no->esq) >= 0) {
            no = rotacaoDireita(no);
        } else {
            no->esq = rotacaoEsquerda(no->esq);
            no = rotacaoDireita(no);
        }
    } else if (balanco < -1) {
        if (getBalanco(no->dir) <= 0) {
            no = rotacaoEsquerda(no);
        } else {
            no->dir = rotacaoDireita(no->dir);
            no = rotacaoEsquerda(no);
        }
    }

    return no;
}
//=============================================================================
TNo *insere(TNo *no, char *nome) {
    if (no == NULL) {
        return criaNo(nome, no);
    }

    if (strcmp(nome, no->nome) < 0) {
        no->esq = insere(no->esq, nome);
        no->esq->raiz = no;
    } else if (strcmp(nome, no->nome) > 0) {
        no->dir = insere(no->dir, nome);
        no->dir->raiz = no;
    } else {
        return no;
    }

    no->nivelProfundidade = 1 + max(altura(no->esq), altura(no->dir));
    return balancearNo(no);
}
//=============================================================================
TNo *minValueNode(TNo *no) {
    TNo *atual = no;
    while (atual->esq != NULL) {
        atual = atual->esq;
    }
    return atual;
}
//=============================================================================
TNo *exclui(TNo *no, char *nome) {
    if (no == NULL) {
        return no;
    }

    if (strcmp(nome, no->nome) < 0) {
        no->esq = exclui(no->esq, nome);
    } else if (strcmp(nome, no->nome) > 0) {
        no->dir = exclui(no->dir, nome);
    } else {
        if (no->esq == NULL || no->dir == NULL) {
            TNo *temp = no->esq;
            if (temp == NULL) {
                temp = no->dir;
            }

            if (temp == NULL) {
                temp = no;
                no = NULL;
            } else {
                *no = *temp;
            }
            free(temp);
        } else {
            TNo *temp = minValueNode(no->dir);
            strcpy(no->nome, temp->nome);
            no->dir = exclui(no->dir, temp->nome);
        }
    }

    if (no == NULL) {
        return no;
    }

    no->nivelProfundidade = 1 + max(altura(no->esq), altura(no->dir));
    return balancearNo(no);
}
//=============================================================================
void caminhamentoEmOrdem(TNo *raiz) {
    if (raiz != NULL) {
        caminhamentoEmOrdem(raiz->esq);
        printf("%s, ", raiz->nome);
        caminhamentoEmOrdem(raiz->dir);
    }
}
//=============================================================================
void caminhamentoPreOrdem(TNo *raiz) {
    if (raiz != NULL) {
        printf("%s, ", raiz->nome);
        caminhamentoPreOrdem(raiz->esq);
        caminhamentoPreOrdem(raiz->dir);
    }
}
//=============================================================================
void caminhamentoPosOrdem(TNo *raiz) {
    if (raiz != NULL) {
        caminhamentoPosOrdem(raiz->esq);
        caminhamentoPosOrdem(raiz->dir);
        printf("%s, ", raiz->nome);
    }
}
//=============================================================================
void imprimeArvore(TNo *no) {
    if (no == NULL) {
        return;
    }

    printf("Nó: %s, Antecedente: %s, Profundidade: %d\n",
           no->nome,
           no->raiz != NULL ? no->raiz->nome : "NULL",
           no->nivelProfundidade);

    imprimeArvore(no->esq);
    imprimeArvore(no->dir);
}
//=============================================================================
int main() {
    inicializa(&raiz, "Zilian");
    raiz = insere(raiz, "Asdrubal");
    raiz = insere(raiz, "Julia");
    raiz = insere(raiz, "Anakin");
    raiz = insere(raiz, "Jack");
    raiz = insere(raiz, "Ortencio");
    raiz = insere(raiz, "Kleiton");
    raiz = insere(raiz, "Xuxa");

    caminhamentoEmOrdem(raiz);
    printf("\t\t caminhamento em ordem \t\n");
    printf("Impressão da árvore com antecedente e profundidade:\n");
    imprimeArvore(raiz);

    raiz = exclui(raiz, "Ortencio");

    caminhamentoEmOrdem(raiz);
    printf("\t\t caminhamento em ordem após exclusão de Ortencio \t\n");
    printf("Impressão da árvore com antecedente e profundidade após exclusão:\n");
    imprimeArvore(raiz);

    return 0;
}

typedef struct tipoNo {
    char nome[100];
    int nivelProfundidade;
    struct tipoNo *esq;
    struct tipoNo *dir;
    struct tipoNo *raiz;
} TNo;

TNo *raiz; // raiz inicial da arvore

// Função para criar os conteudos de um No
TNo *criaNoAVL(char *nome, TNo *raiz){
    TNo *novo = (TNo *)malloc(sizeof(TNo));
    strcpy(novo->nome,nome);
    novo->nivelProfundidade = 1;
    novo->raiz = raiz;
    novo->esq = NULL;
    novo->dir = NULL;
    return novo;
}

int nivelProfundidade(TNo *N) {
    if (N == NULL) {
        return 0;
    }
    return N->nivelProfundidade;
}

int max(int a, int b) {
    if (a > b) {
        return a;
    }
        return b;
}

int getBalanco(TNo *N) {
    if (N == NULL) {
        return 0;
    }
    return nivelProfundidade(N->esq) - nivelProfundidade(N->dir);
}

TNo *minValueNodeAVL(TNo *no) {
    TNo *atual = no;
    while (atual->esq != NULL)
        atual = atual->esq;
    return atual;
}

TNo *rotacaoDireita(TNo *y) {
    TNo *x = y->esq;
    TNo *T2 = x->dir;

    x->dir = y;
    y->esq = T2;

    if (T2 != NULL) {
        T2->raiz = y;
    }
    x->raiz = y->raiz;
    y->raiz = x;

    y->nivelProfundidade = max(nivelProfundidade(y->esq), nivelProfundidade(y->dir)) + 1;
    x->nivelProfundidade = max(nivelProfundidade(x->esq), nivelProfundidade(x->dir)) + 1;

    return x;
}


TNo *rotacaoEsquerda(TNo *x) {
    TNo *y = x->dir;
    TNo *T2 = y->esq;

    y->esq = x;
    x->dir = T2;

    if (T2 != NULL) {
        T2->raiz = x;
    }
    y->raiz = x->raiz;
    x->raiz = y;

    x->nivelProfundidade = max(nivelProfundidade(x->esq), nivelProfundidade(x->dir)) + 1;
    y->nivelProfundidade = max(nivelProfundidade(y->esq), nivelProfundidade(y->dir)) + 1;

    return y;
}

// Função para balancear a arvore
TNo *balancearNo(TNo *no) {
    int balanco = getBalanco(no);

    if (balanco > 1) {
	// Desbalanceada para a esquerda
        if (getBalanco(no->esq) >= 0) {
		// Rotaçao simples à direita
            no = rotacaoDireita(no);
        } else {
		// Rotaçao dupla à direita (esquerda-direita)
            no->esq = rotacaoEsquerda(no->esq);
            no = rotacaoDireita(no);
        }
    } else if (balanco < -1) {
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
TNo *insereAVL(TNo *no, char *nome){
    // Caso seja o primeiro no da arvore
    if (no == NULL) {
        return criaNoAVL(nome, no);
    }
    // Verifica se o nome e maior
    if (strcmp(nome, no->nome) < 0) {
        // Se o nome for maior ele insere na esquerda
        no->esq = insereAVL(no->esq, nome);
        no->esq->raiz = no;
    } else if (strcmp(nome, no->nome) > 0) {
        // Se for menor ele insere na direita
        no->dir = insereAVL(no->dir, nome);
        no->dir->raiz = no;
    } else {
        // Achou o lugar do No
        return no;
    }
    // Atualiza a altura
    no->nivelProfundidade = 1 + max(nivelProfundidade(no->esq), nivelProfundidade(no->dir));
    // Chama a função para balancear a arvore
    return balancearNo(no);
}

TNo *excluiAVL(TNo *no, char *nome) {
    if (no == NULL) {
        return no;
    }

    if (strcmp(nome, no->nome) < 0) {
        no->esq = excluiAVL(no->esq, nome);
    } else if (strcmp(nome, no->nome) > 0) {
        no->dir = excluiAVL(no->dir, nome);
    } else {
        if (no->esq == NULL || no->dir == NULL) {
            TNo *temp = no->esq;
            if (temp == NULL) {
                temp = no->dir;
            }

            if (temp == NULL) {
                temp = no;
                no = NULL;
            } else {
                *no = *temp;
            }
            free(temp);
        } else {
            TNo *temp = minValueNodeAVL(no->dir);
            strcpy(no->nome, temp->nome);
            no->dir = excluiAVL(no->dir, temp->nome);
        }
    }

    if (no == NULL) {
        return no;
    }

    no->nivelProfundidade = 1 + max(nivelProfundidade(no->esq), nivelProfundidade(no->dir));
    return balancearNo(no);
}

void imprimeArvore(TNo *no) {
    if (no == NULL) {
        return;
    }

    printf("\n\t\t| %d \t\t| Nó: %s \t| Antecedente: %s \t|",
           no->nivelProfundidade, no->nome, no->raiz ? no->raiz->nome : "NULL");

    imprimeArvore(no->esq);
    imprimeArvore(no->dir);
}

void criaTabelaImpressaArvore(TNo *raiz){
    printf("\n \t\t| Profundidade \t| Nó \t\t| Antecedente   \t| ");
    imprimeArvore(raiz);
    printf("\n \t\t=========================================================\n ");
}


#include <windows.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "functions.c"

TNo *raiz;

void inicializa(TNo **R){
	printf("\n\n\t\t=====| ARVORE BINARIA AVL |=====\n");
    raiz = criaNoAVL("Zilian",raiz);
    raiz = insereAVL(raiz,"Astolfo");
    raiz = insereAVL(raiz,"Julia");
    raiz = insereAVL(raiz,"Anakin");
    raiz = insereAVL(raiz,"Jack");
    raiz = insereAVL(raiz,"Ortencio");
    raiz = insereAVL(raiz,"Kleiton");
    raiz = insereAVL(raiz,"Xuxa");

    printf("\n\t\t caminhamento em ordem \t\n");
    caminhamentoEmOrdemAVL(raiz);
    printf("\n\nImpressão da árvore com antecedente e profundidade:\n");
    criaTabelaImpressaArvore(raiz);

    system("PAUSE");
    raiz = excluiAVL(raiz, "Ortencio");

    caminhamentoEmOrdemAVL(raiz);

    printf("\n\n\t\t caminhamento em ordem após exclusão de Ortencio \t\n");
    printf("\nImpressão da árvore com antecedente e profundidade após exclusão:\n");
    criaTabelaImpressaArvore(raiz);
    system("PAUSE");
}

//===| Programa Principal |===========================
int main(){
	SetConsoleOutputCP(65001);
	inicializa(&raiz);
}