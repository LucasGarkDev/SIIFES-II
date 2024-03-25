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

typedef struct tipoAtor{
    string nome; 
    tipoAtor *prox;   
}Tator;

typedef struct tipoElenco{
    Tator *atorFilme;
    tipoElenco *prox;    
}Telenco;

typedef struct tipoFilme {
    int anoProducao;
    string titulo;
    tipoFilme *ante; //ponteiro para o filme anterior
    tipoFilme *prox; //ponteiro para o proximo filme
    Telenco *elenco;
}Tfilme;

typedef struct tipoLista{
    Tfilme *inicioF; //inicio
    Tfilme *fimF; //fim
    Tator *inicioA;
    Tator *fimA;
    int total; //Quantidade de registros na lista
}Tlista;

Tlista lista; //variavel global

void insereAtor(Tlista *lista, string nome){
    Tator *novo = (Tator *)malloc(sizeof(Tator));
    novo->prox = NULL;
    strcpy(novo->nome,nome);
    int flag = 0;
    if (lista->inicioA == NULL){
        //lista de Ator encontra-se vazia
        lista->inicioA = novo;
        lista->fimA = novo;
    }else{
        Tator *atual = lista->inicioA;
        Tator *anterior = NULL;
        while (atual != NULL){
            if (strcmp(atual->nome,novo->nome) > 0){
                //momento de inserir na lista
                flag = 1;
                if (atual == lista->inicioA){
                    //insere 'novo' como primeiro da lista
                    novo->prox = atual;
                    lista->inicioA = novo;
                }else{
                    //insere 'novo' antes do 'atual'(no meio da lista)
                    novo->prox = anterior->prox;
                    anterior->prox = novo;
                }
                break; // Importante adicionar o break aqui para evitar que continue percorrendo a lista desnecessariamente
            }
            anterior = atual;
            atual = atual->prox;
        }
        if (!flag){
            //insere 'novo' no fim da lista
            lista->fimA->prox = novo;
            lista->fimA = novo;
        }
    }
}


void inicializa(Tlista *lista){
    lista->inicioF = NULL;
    lista->inicioA = NULL;
    lista->fimF = NULL;
    lista->fimA = NULL;
    lista->total = 0;
    insereAtor(lista,"Tobey Maguire");
	insereAtor(lista,"Christen Dusten");
	insereAtor(lista,"Charlton Heston");
	insereAtor(lista,"Sofia Loren");
	insereAtor(lista,"Kirk Douglas");
	insereAtor(lista,"Michael Douglas");
	insereAtor(lista,"Mira Sorvino");
	insereAtor(lista,"Tom Cruise");
	insereAtor(lista,"Jennifer Connelly");
	insereAtor(lista,"Kelly McGillis");
	insereAtor(lista,"Kathleen Turner");
	insereAtor(lista,"Danny DeVito");
	insereAtor(lista,"Sylvester Stallone");
	insereAtor(lista,"Talia Shire");
	insereAtor(lista,"Hayley Atwell");
	insereAtor(lista,"Chris Evans");
	insereAtor(lista,"Sebastian Stan");
	insereAtor(lista,"Sidney Potier");
	insereAtor(lista,"Charlie Chaplin");
	insereAtor(lista,"Paulette Goddard");
	insereAtor(lista,"Bruce Willis");
	insereAtor(lista,"Cybill Shepherd");
}

void digitarDados(Tfilme *elementoNovo){
    printf("Digite o titulo do filme: ");
    fflush(stdin);
    scanf(" %39[^\n]s", elementoNovo->titulo);
    printf("Digite o ano de produção do final: ");
    fflush(stdin);
    scanf("%d", &elementoNovo->anoProducao);
}

void cadastraAtor(Tlista *lista){
	string nome;
	
	printf("\n\n\t\t=====| INSERE ATOR |=====\n\n");
	printf("\tInforme NOME de novo ATOR: ");
	fflush(stdin);
	scanf(" %39[^\n]s",nome);
	
	insereAtor(lista, nome);
}

void exibeAtores(Tlista *L){
	Tator *atual = L->inicioA;
	int cont = 0;
	printf("\n\n");
	printf("+----------------------------------------------------+\n");
	while (atual != NULL)	{
		printf("\t(%d) - %s.\n", cont+1, atual->nome);
        cont++;
		atual = atual->prox;
	}//while
	printf("+----------------------------------------------------+\n\n\n");
}

void insere(Tlista *lista){
    Tfilme *novo = (Tfilme *)malloc(sizeof(Tfilme));
    Tfilme *atual;
    int flag = 0;
    digitarDados(novo);
    novo->prox = NULL;
    novo->ante = NULL;
    if (lista->inicioF == NULL){
        //Lista encontra-se vazia.
        //Inserir o primeiro e unico elemento da lista ate agora
        lista->inicioF = novo;
        lista->fimF = novo;
        flag = 1;
    }else{
        //Lista ja possui pelo menos 1 elemento
        atual = lista->inicioF;
        while (atual != NULL){
            if (strcmp(atual->titulo,novo->titulo) > 0){
                //encontrada a posiçao para a inserçao do novo Tfilme
                flag = 1;
                
                if (atual == lista->inicioF){
                    //Inserir novo no inicio da lista
                    novo->prox = atual;
                    atual->ante = novo;
                    lista->inicioF = novo;
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
            lista->fimF->prox = novo;
            novo->ante = lista->fimF;
            lista->fimF = novo;
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
    Tfilme *atual = lista.inicioF;
    int cont = 0;
    printf("\n\n\t\t========| EXIBE TODOS OS FILMES |========\n\n");
    while (atual != NULL){
        printf("\t(%d) - %s [%d].\n",cont+1,atual->titulo,atual->anoProducao);
        cont++;
        atual = atual->prox;
    }
    printf("\n\n");
}

int pesquisarNaLista(Tlista *lista,string nome){
    Tfilme *atual = lista->inicioF;
    while (atual != NULL){
        if (strcmp(atual->titulo,nome) == 0){
            printf("\nnome existe\n");
            return 1;
        }
        atual = atual->prox;
    }
    return 0;
}

void exclui(Tlista *lista, string titulo){
    Tfilme *atual = lista->inicioF;
    while (atual != NULL){
        if (strcmp(atual->titulo,titulo) == 0){
            //Encontra o elemento a ser excluido
            if(lista->inicioF == lista->fimF){
                //Exclusao do unico elemento da lista
                printf("\n\n\tExcluindo o FILME %s ...\n", atual->titulo);
                lista->inicioF = NULL;
                lista->fimF = NULL;
            }else if (atual == lista->inicioF){
                //Exclui o primeiro elemento da lista
                printf("\n\n\tExcluindo o FILME %s ...\n", atual->titulo);
                lista->inicioF = atual->prox; //lista->inicio = lista->inicio->prox;
                lista->inicioF->ante = NULL;
            }else if (atual == lista->fimF){
                //Excluindo o ultimo cara da lista
                printf("\n\n\tExcluindo o FILME %s ...\n", atual->titulo);
                lista->fimF = atual->ante;
                lista->fimF->prox = NULL;
            }else if ((atual != lista->inicioF)&&(atual != lista->fimF)){
                //Excluindo alguem que nao esta nem no fim e nem no inicio
                printf("\n\n\tExcluindo o FILME %s ...\n", atual->titulo);
                atual->ante->prox = atual->prox;
                atual->prox->ante = atual->ante; 
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
        fflush(stdin);
        scanf(" %39[^\n]s",tituloSelecionado);
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
            // insere(&lista);
            break;
        case 2:
            // exibe(lista); 
            exibeAtores(&lista);
            break;
        case 3:
            // selecionaFilmeExclusao(&lista);
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