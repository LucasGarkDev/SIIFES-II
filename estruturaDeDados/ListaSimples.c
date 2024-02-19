#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#define INICIO "------------INICIO------------" 
#define RESULTADO "------------RESULTADO------------"
#define CORTE "------------------------"

//Lista simplesmente encadeada
//19/02/2024
struct TipoElemento{
    int valor;
    Telemento *prox;
};
typedef struct TipoElemento Telemento;

struct TipoLista{
    Telemento *inicio;
    Telemento *fim;
    int total;
};
typedef struct TipoLista Tlista;
//=================================================
void inicializa(Tlista *lista){
    lista->inicio = NULL;
    lista->fim = NULL;
    lista->total = 0;
}
//=================================================
void inserir(int valor, Tlista *lista){
    Telemento *novo = (Telemento *)malloc(sizeof(Telemento));
    novo->valor = valor;
    novo->prox = NULL;
    if (lista->inicio == NULL){
        //lista encontras-se vazia
        lista->inicio = novo;
        lista->fim = novo;
        lista->total = 1;
    }else{
        //Lista poissui pelo menos 1 elemento
        Telemento *atual = lista->inicio;
        Telemento *anterior = NULL;
        while (atual != NULL){
            if (atual->valor >= novo->valor){
                if (anterior == NULL){
                    //Inserir novo como primeiro da Lista
                    novo->prox = atual;
                    lista->inicio = novo;
                }else{
                    //Inserir no meio da Lista
                    novo->prox = atual;
                    anterior->prox = novo;
                }
                break;
            }
            anterior = atual;
            atual = atual->prox; //move para o proximo elemento
        }//while
        lista->total++;
    }//if
    
}

//=================================================

//=================================================

//=================================================
int main(){
    Tlista lista; //variavel global
    printf("\n%s\n", INICIO);
    inicializa(&lista);
    inserir(35,&lista);
    printf("%s\n", CORTE);
    return 0;
}