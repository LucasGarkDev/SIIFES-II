#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#define INICIO "------------INICIO------------" 
#define RESULTADO "------------RESULTADO------------"
#define CORTE "------------------------"

void func(int *n1, int *n2){
    if (*n1 > *n2){
        printf("\n\n%s\n\n",RESULTADO);
        printf("%d \n", *n1);
        printf("%s\n",CORTE);
    }else{
        printf("\n\n%s\n\n",RESULTADO);
        printf("%d \n", *n2);
        printf("%s\n",CORTE);
    }
}

int main(){
    int *p1, *p2, a ,b;
    a = 2;
    b = 5;
    p2 = &a;
    p1 = &b;
    func(p1,p2);
    return 0;
}