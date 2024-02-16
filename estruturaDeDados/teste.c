#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#define INICIO "------------INICIO------------" 
#define RESULTADO "------------RESULTADO------------"
#define CORTE "------------------------"

int main(){
    int x, y, *p; y = 1;
    p = &y;
    x = *p;
    x = 4;
    (*p)++;
    --x;
    (*p) += x;
    printf("\n\n%s\n", RESULTADO);
    printf("O valor de X e: %d\n", x);
    printf("O valor de Y e: %d\n", y);
    printf("O valor de P e: %d\n", *p);
    printf("%s\n\n", CORTE);
    return 0;
}