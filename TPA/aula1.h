typedef struct no{
    int valor;
    struct no *prox;
}TNo;

typedef struct estrutura{
    TNo *prim, *ult;
}TEstr;

void insere(TEstr *e, int i);