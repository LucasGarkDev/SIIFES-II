#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#define INICIO "------------INICIO------------" 
#define RESULTADO "------------RESULTADO------------"
#define CORTE "------------------------"

int main(){
    int a, b, *p1, *p2;
    a = 4;
    b = 3;
    p1 = &a;
    p2 = p1;
    *p2 = *p1 + 3;
    b = b * (*p1);
    (*p2)++;
    p1 = &b;
    printf("\n\n%s\n\n",RESULTADO);
    printf("Valor: %d %d \n", *p1, *p2);
    printf("Valor: %d %d \n", a, b);
    printf("%s\n",CORTE);
    return 0;
}