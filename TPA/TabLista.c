

// int main(){
//     int *p;
//     p = 516;
//     *p = 32;

//     printf("%d",*p);
// }

// int main(){
//     int *p = (int*)malloc(sizeof(int));
//     int *q;
//     *p = 516;
//     q = p;
//     *q = 32;
//     printf("%d %d\n",*p,*q);
//     // 32 32
// }

// int main(){
//     int *p = (int*)malloc(20 * sizeof(int));
//     int i;
//     for (i = 0; i < 20; i++)
//         p[i] = i;

//     for (i = 0; i < 20; i++){
//         printf("%d ",*p);
//         p++;
//     }
    
// }


#include <stdio.h>
#include <stdlib.h>
#include "TabLista.h"

void insere(TEstr *e, int i){
    TNo *n = (TNo*)malloc(sizeof(TNo));
    n->valor = i;
    n->prox = e->prim;
    if(e->prim == NULL){
        e->prim = n;
        (*e).ult = n;
    }else{
        e->prim = n;
    }
}

TNo *remova(TEstr *e){
    TNo *n = NULL;
    if (e->prim != NULL){
        n = e->prim;
        e->prim = e->prim->prox;
        if (e->prim == NULL){
            e->ult = NULL;
        }
        return n;
    }
   
}
//============================================
int main(){
    TEstr *lista;
    insere(lista,12);
}