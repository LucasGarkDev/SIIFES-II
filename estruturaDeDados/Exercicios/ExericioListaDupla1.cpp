#include <stdio.h>
#include <stdlib.h>
#include <string.h>
// #include <ctype.h>
// #include <math.h>
// #include <time.h>

#define INICIO "------------INICIO------------" 
#define RESULTADO "------------RESULTADO------------"
#define CORTE "------------------------"

typedef char string[40];

typedef struct tipoFilme {
    int anoProducao;
    string titulo;
    tipoFilme *ante; //ponteiro para o filme anterior
    tipoFilme *prox; //ponteiro para o proximo filme
}Tfilme;

typedef struct tipoLista{
    Tfilme *primeiro; //inicio
    Tfilme *ultimo; //fim
    int total; //Quantidade de registros na lista
}Tlista;

Tlista lista; //variavel global

void inicializa(Tlista *lista){
    lista->primeiro = NULL;
    lista->ultimo = NULL;
    lista->total = 0;
}

void digitarDados(Tfilme *elementoNovo){
    printf("Digite o titulo do filme: ");
    fflush(stdin);
    scanf(" %39[^\n]s", elementoNovo->titulo);
    printf("Digite o ano de produção do final: ");
    fflush(stdin);
    scanf("%d", &elementoNovo->anoProducao);
}

// void insere(Tlista *lista, string titulo, int ano){
//     Tfilme *novo = (Tfilme *)malloc(sizeof(Tfilme));
//     Tfilme *atual;
//     int flag = 0;
//     novo->anoProducao = ano;
//     strcpy(novo->titulo,titulo);
//     novo->prox = NULL;
//     novo->ante = NULL;

//     if (lista->primeiro == NULL){
//         //Lista encontra-se vazia.
//         //Inserir o primeiro e unico elemento da lista ate agora
//         lista->primeiro = novo;
//         lista->ultimo = novo;
//         flag = 1;
//     }else{
//         //Lista ja possui pelo menos 1 elemento
//         atual = lista->primeiro;
//         while (atual != NULL){
//             if (strcmp(atual->titulo,novo->titulo) > 0){
//                 //encontrada a posiçao para a inserçao do novo Tfilme
//                 flag = 1;
                
//                 if (atual == lista->primeiro){
//                     //Inserir novo no inicio da lista
//                     novo->prox = atual;
//                     atual->ante = novo;
//                     lista->primeiro = novo;
//                 }else{
//                     //Inserir novo no meio da lista
//                     novo->prox = atual;
//                     novo->ante = atual->ante;
//                     atual->ante->prox = novo;
//                     atual->ante = novo;
//                 }
//                 break;
//             }
//             atual = atual->prox; //move para o próximo elemento
//         }
//         if (flag == 0){
//             //inserir o novo como o ultimo Tfilme da lista
//             lista->ultimo->prox = novo;
//             novo->ante = lista->ultimo;
//             lista->ultimo = novo;
//         }
//     }
//     lista->total++;
// }

void insere(Tlista *lista){
    Tfilme *novo = (Tfilme *)malloc(sizeof(Tfilme));
    Tfilme *atual;
    int flag = 0;
    digitarDados(novo);
    novo->prox = NULL;
    novo->ante = NULL;
    if (lista->primeiro == NULL){
        //Lista encontra-se vazia.
        //Inserir o primeiro e unico elemento da lista ate agora
        lista->primeiro = novo;
        lista->ultimo = novo;
        flag = 1;
    }else{
        //Lista ja possui pelo menos 1 elemento
        atual = lista->primeiro;
        while (atual != NULL){
            if (strcmp(atual->titulo,novo->titulo) > 0){
                //encontrada a posiçao para a inserçao do novo Tfilme
                flag = 1;
                
                if (atual == lista->primeiro){
                    //Inserir novo no inicio da lista
                    novo->prox = atual;
                    atual->ante = novo;
                    lista->primeiro = novo;
                }else{
                    //Inserir novo no meio da lista
                    novo->prox = atual;
                    novo->ante = atual->ante;
                    atual->ante->prox = novo;
                    atual->ante = novo;
                }
                break;
            }
            atual = atual->prox; //move para o próximo elemento
        }
        if (flag == 0){
            //inserir o novo como o ultimo Tfilme da lista
            lista->ultimo->prox = novo;
            novo->ante = lista->ultimo;
            lista->ultimo = novo;
        }
    }
    lista->total++;
}

int pedirOpcao(){
    int op;
    printf("\n%s\n", INICIO);
    do{
        printf("1 - Inserir na Lista\n");
        printf("2 - Exibe Lista\n");
        printf("3 - Excluir da Lista\n");
        printf("4 - Sair\n");
        printf("Digite a opção: ");
        scanf("%d", &op);
        printf("%s\n", CORTE);
    } while ((op < 1)||(op > 4));
    return op;
}

void exibe(Tlista lista){
    Tfilme *atual = lista.primeiro;
    int cont = 0;
    printf("\n\n\t\t========| EXIBE TODOS OS FILMES |========\n\n");
    while (atual != NULL){
        printf("\t(%d) - %s [%d].\n",++cont,atual->titulo,atual->anoProducao);
        cont++;
        atual = atual->prox;
    }
    printf("\n\n");
}

int pesquisarNaLista(Tlista *lista,string nome){
    Tfilme *atual = lista->primeiro;
    while (atual->prox != NULL){
        if (strcmp(atual->titulo,nome) == 0){
            return 1;
        }
        atual = atual->prox;
    }
    return 0;
}

void exclui(Tlista *lista, string titulo){
    Tfilme *atual = lista->primeiro;
    Tfilme *anterior = NULL;
    while (atual != NULL){
        if (strcmp(atual->titulo,titulo) == 0){
            printf("\nEncontrou para excluir\n");
            //Encontra o elemento a ser excluido
            if(lista->primeiro == lista->ultimo){
                printf("\nEra o unico da lista\n");
                //Exclusao do unico elemento da lista
                printf("\n\n\tExcluindo o FILME %s ...\n", atual->titulo);
                lista->primeiro = NULL;
                lista->ultimo = NULL;
            }else if (atual == lista->primeiro){
                printf("\nEra o primeiro da Lista\n");
                //Exclui o primeiro elemento da lista
                printf("\n\n\tExcluindo o FILME %s ...\n", atual->titulo);
                lista->primeiro = atual->prox; //lista->inicio = lista->inicio->prox;
                lista->primeiro->ante = NULL;
            }else if (atual == lista->ultimo){
                printf("\nEra o ultimo da lista\n");
                //Excluindo o ultimo cara da lista
                printf("\n\n\tExcluindo o FILME %s ...\n", atual->titulo);
                lista->ultimo = atual->ante;
                lista->ultimo->prox = NULL;
            }else if ((atual != lista->primeiro)&&(atual != lista->ultimo)){
                printf("\nEstava no meio da lista\n");
                //Excluindo alguem que nao esta nem no fim e nem no inicio
                printf("\n\n\tExcluindo o FILME %s ...\n", atual->titulo);
                anterior->prox = atual->prox;
                atual->prox->ante = anterior;
            }
            free(atual);
            lista->total--;
            break;
        }
        atual = atual->prox;
    }
}

void selecionaFilmeExclusao(Tlista *lista){
	string tituloSelecionado;
    do{
        printf("Digite o titulo do filme que deseja excluir: ");
        scanf(" %s",tituloSelecionado);
        printf("\n%s\n",tituloSelecionado);
    } while (pesquisarNaLista(lista,tituloSelecionado) != 1);
    exclui(lista,tituloSelecionado);
}

//=================================================
int main(){
    int op;
    // char nome[40];
    int repete = 0;
    inicializa(&lista);   
     do{
        op = pedirOpcao();
        switch (op){
        case 1:
            insere(&lista);
            break;
        case 2:
            exibe(lista); 
            break;
        case 3:
            selecionaFilmeExclusao(&lista);
            break;
        case 4:
            repete = 1;
            break;
        default:
            break;
        }
    } while (repete == 0); 
    return 0;
}

//================================================