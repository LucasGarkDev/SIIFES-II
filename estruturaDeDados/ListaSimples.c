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


int main(){
    
    printf("\n%s\n", RESULTADO);

    printf("%s\n", CORTE);
    return 0;
}