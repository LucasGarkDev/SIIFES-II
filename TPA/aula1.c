#include <stdio.h>
#include <stdlib.h>

// int main(){
//     int *p;
//     p = 516;
//     *p = 32;

//     printf("%d",*p);
// }

int main(){
    int *p = (int*)malloc(sizeof(int));
    int *q;
    *p = 516;
    q = p;
    *q = 32;
    printf("%d %d\n",*p,*q);
    // 32 32
}



