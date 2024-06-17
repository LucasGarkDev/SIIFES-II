//pilha....17/06/2024
#include <stdio.h>
#include <stdlib.h>
#define INICIO "------------INICIO------------" 
#define RESULTADO "------------RESULTADO------------"
#define CORTE "------------------------"

typedef struct TElemento{
    int digito;
    struct TElemento *prox;
    struct TElemento *ante;
}TElemento;

typedef struct TPilha{
    TElemento *topo;
    TElemento *base;   
}Tpilha;

void inicializa(Tpilha *pilha){
    pilha->base = NULL;
    pilha->topo = NULL;
}
void empilhar(Tpilha *pilha, int valor){
    TElemento *novo = (TElemento*)malloc(sizeof(TElemento));
    novo->ante = NULL;
    novo->prox = NULL;
    novo->digito = valor;

    if (pilha->topo == NULL){
        //pilha vazia
        pilha->base = novo;
        pilha->topo = novo;
    }else{
        pilha->topo->prox = novo;
        novo->ante = pilha->topo;
        pilha->topo = novo;
    }
    
}

void desmembra(Tpilha *pilha, int numero){
    int resto, quociente;
    do{
        empilhar(pilha,(quociente % 10));
        quociente = quociente / 10;
    } while (quociente > 0);
    
}

int desempilhar(Tpilha *pilha){
    TElemento *atual;
    int resultado;
    if (pilha->topo != NULL){
        atual = pilha->topo;
        pilha->topo = pilha->topo->ante;
        pilha->topo->prox = NULL;
        resultado = pilha->topo->digito;
        free(atual);
    }else{
        resultado = -646;
    }
    
    return resultado;    
}

int remontar(Tpilha *pilha){
    int valor = 0;
    int fator = 1;
    while (pilha->topo != NULL){
        valor = valor + (desempilhar(pilha)*fator);
        fator = fator * 10;
    }
    
}

Tpilha pilha; //variavel global

//=================================================
int main(){
    int num = 1991, reverso;
    inicializa(&pilha);
    desmembra(&pilha,num);
    reverso = remontar(&pilha);
    printf("Comparando o numero %d com %d\n",num,reverso);
    if (num == reverso){
        printf("O numero e palindromo\n");
    }else{
        printf("O numero nao e palindromo\n");
    }
    return 0;
}
