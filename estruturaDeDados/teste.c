#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#define INICIO "------------INICIO------------" 
#define RESULTADO "------------RESULTADO------------"
#define CORTE "------------------------"

void maiorMenor(int vetor[], int *maximo, int *minimo){
    int i;
    for (i = 0; i < 12; i++){
        if (vetor[i] > *maximo){
            *maximo = vetor[i]; 
        }
        if (vetor[i] < *minimo){
            *minimo = vetor[i];
        }
    }
}

int main(){
    int maior = 0;
    int menor = 999;
    int vetor[12] = {3,5,12,675,2323,656,234,753,2,6,1,4};
    maiorMenor(vetor,&maior,&menor);
    printf("\n%s\n", RESULTADO);
    printf("O maior elemento desse vetor e: %d\n", maior);
    printf("O menor elemento desse vetor e: %d\n", menor);
    printf("%s\n", CORTE);
    return 0;
}