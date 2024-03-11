#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <math.h>
#include <time.h>

#define INICIO "------------INICIO------------" 
#define RESULTADO "------------RESULTADO------------"
#define CORTE "------------------------"

typedef char string[40];

typedef struct tipoFilme {
    int anoProducao;
    tipoFilme *ante; //ponteiro para o filme anterior
    tipoFilme *prox; //ponteiro para o proximo filme
}Tfilme;

typedef struct tipoLista{
    Tfilme *primeiro; //inicio
    Tfilme *ultimo; //fim
    int total;
}Tlista;

Tlista lista; //variavel global


//=================================================
int main(){
    
    return 0;
}

//==========================================