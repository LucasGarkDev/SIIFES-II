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

void inicializa(TPilha *P){
	P->base = NULL;
	P->topo = NULL;	
}

void empilhar(TPilha *P, int valor){
	TElemento *novo = (TElemento *)malloc(sizeof(TElemento));
	
	novo->ante = NULL;
	novo->prox = NULL;
	novo->digito = valor;
	
	if(P->topo == NULL){
		//Pilha VAZIA...
		P->base = novo;
		P->topo = novo;
	} else {
		P->topo->prox = novo;
		novo->ante = P->topo;
		P->topo = novo;
	}//if
}

void desmembrar(TPilha *P, int numero){
	int quoc = numero;
	
	do{
		printf("\n>>> %d em %d\n", quoc%10, quoc);
		
		empilhar(P, (quoc % 10));
		quoc = quoc / 10;			
	}while(quoc > 0);
	
}

int desempilhar(TPilha *P){
	TElemento *atual;
	int resultado;
	
	if(P->topo != NULL){
		atual = P->topo;
		P->topo = P->topo->ante;
		if(P->topo != NULL) {
			P->topo->prox = NULL;
			P->base = NULL;
		}
		resultado = atual->digito;
		free(atual);
	} else {
		resultado = -666;
	}//if

	return resultado;
	
}

int remontar(TPilha *P){
	int valor = 0;
	int fator = 1;
	
	while(P->topo != NULL){
		valor = valor + (desempilhar(P) * fator);
		fator = fator * 10;
		
		printf("\n\n valor= %d    fator= %d",valor, fator);
	}//while
	
	return valor;
}

Tpilha pilha; //variavel global

//=================================================
int main(){
    int num = 1991, reverso;
    inicializa(&pilha);
    desmembrar(&pilha,num);
    reverso = remontar(&pilha);
    printf("Comparando o numero %d com %d\n",num,reverso);
    if (num == reverso){
        printf("O numero e palindromo\n");
    }else{
        printf("O numero nao e palindromo\n");
    }
    return 0;
}
