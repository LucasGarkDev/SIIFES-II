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
    insereAtor(L,"Tobey Maguire");
	insereAtor(L,"Christen Dusten");
	insereAtor(L,"Charlton Heston");
	insereAtor(L,"Sophia Loren");
	insereAtor(L,"Kirk Douglas");
	insereAtor(L,"Michael Douglas");
	insereAtor(L,"Mira Sorvino");
	insereAtor(L,"Tom Cruise");
	insereAtor(L,"Jennifer Connelly");
	insereAtor(L,"Kelly McGillis");
	insereAtor(L,"Kathleen Turner");
	insereAtor(L,"Danny DeVito");
	insereAtor(L,"Sylvester Stallone");
	insereAtor(L,"Talia Shire");
	insereAtor(L,"Hayley Atwell");
	insereAtor(L,"Chris Evans");
	insereAtor(L,"Sebastian Stan");
	insereAtor(L,"Sidney Potier");
	insereAtor(L,"Charlie Chaplin");
	insereAtor(L,"Paulette Goddard");
	insereAtor(L,"Bruce Willis");
	insereAtor(L,"Cybill Shepherd");
	insereAtor(L,"Genevieve Page");
	insereAtor(L,"Roy Scheider");
	insereAtor(L,"Richard Dreyfuss");
	insereAtor(L,"Edna Purviance");
	insereAtor(L,"Jackie Coogan");
	insereAtor(L,"Lulu");
	insereAtor(L,"Judy Geeson");
	
	insereFilme(L,"O Garoto", 1921);
	insereFilme(L,"Tempos Modernos", 1936);
	insereFilme(L,"SPARTACUS", 1958);
	insereFilme(L,"El Cid", 1961);
	insereFilme(L,"To Sir With Love", 1967);
	insereFilme(L,"Tubarao", 1976);
	insereFilme(L,"O Poderoso Chefao", 1972);
	insereFilme(L,"A Joia do NILO", 1986);
	insereFilme(L,"Moonlighting", 1985);
	insereFilme(L,"Tudo por uma Esmeralda", 1985);
	
	criaElenco(L,"El Cid","Charlton Heston");
	criaElenco(L,"El Cid","Sophia Loren");
	criaElenco(L,"El Cid","Genevieve Page");
	criaElenco(L,"Tubarao","Roy Scheider");
	criaElenco(L,"Tubarao","Richard Dreyfuss");
	criaElenco(L,"A Joia do NILO","Michael Douglas");
	criaElenco(L,"A Joia do NILO","Kathleen Turner");
	criaElenco(L,"A Joia do NILO","Danny DeVito");
	criaElenco(L,"O Garoto","Charlie Chaplin");
	criaElenco(L,"O Garoto","Edna Purviance");
	criaElenco(L,"O Garoto","Jackie Coogan");
	criaElenco(L,"To Sir With Love","Sidney Potier");
	criaElenco(L,"To Sir With Love", "Judy Geeson");
	criaElenco(L,"To Sir With Love", "Lulu");
	criaElenco(L,"Moonlighting", "Cybill Shepherd");
	criaElenco(L,"Moonlighting", "Bruce Willis");
	criaElenco(L,"Tudo por uma Esmeralda", "Michael Douglas");
	criaElenco(L,"Tudo por uma Esmeralda", "Kathleen Turner");
	criaElenco(L,"Tempos Modernos", "Charlie Chaplin");
	criaElenco(L,"Tempos Modernos", "Paulette Goddard");
}

void defineElenco(Tlista *L){
	Tfilme *f;
	Tator *a;
	string titulo;
	string nome;
	
	printf("\n\n\t\t======| DEFINE ELENCO DE FILME |======\n\n");
	printf("\tInforme TITULO de FILME: ");
	fflush(stdin);
	scanf(" %39[^\n]s",titulo);
	
	f = localizaFilme(L, titulo);
	
	if(f == NULL){
		printf("\n\n\tERRO: filme NAO encontrado.\n\tTITULO: %s.\n\n",f->titulo);
		system("PAUSE");
	} else {
    	printf("\n\n\tInforme NOME de ATOR: ");
	    fflush(stdin);
	    scanf(" %39[^\n]s",nome);
			
	    a = localizaAtor(L, nome);
		if(a == NULL){
			printf("\n\n\tERRO: ator NAO encontrado!\n\tNOME: %s.\n\n", a->nome);
		} else {
			Telenco *novo = (Telenco *)malloc(sizeof(Telenco));
			novo->atorFilme = a;
			novo->prox = NULL;
            if (f->elenco == NULL){
                // filme esta sem elenco ainda
                f->elenco = novo;
            }else{
                //filme possui pelo menos 1 ator no elenco
                Telenco *e = f->elenco;
                while (e->prox != NULL){
                    e = e->prox;
                }
                e->prox = novo;
            }
		}//if
	}//if
	
}
//=======================================================================
Tfilme *localizaFilme(Tlista *L, string titulo){
	Tfilme *atual = L->inicioF;
	
	while (atual != NULL){
		if(strcmp(atual->titulo, titulo) == 0){
			break;
		}//if
		atual = atual->prox;
	}//while
	return atual;
}
//=======================================================================
Tator *localizaAtor(Tlista *L, string nome){
	Tator *atual = L->inicioA;
	
	while(atual != NULL)	{
		if(strcmp(atual->nome, nome) == 0) {
			break;
		}//if
		atual = atual->prox;
	}//while
	return atual;
}
//=======================================================================
void relacionarFilmeAtor(Tfilme *f, Tator *a){
	Telenco *novo = (Telenco *)malloc(sizeof(Telenco));
	
	novo->prox = NULL;
	novo->atorFilme = a;
	
	if(f->elenco != NULL){
	   Telenco *atual = f->elenco;
	   while(atual->prox != NULL){
			atual = atual->prox;
       }//while
	   atual->prox = novo;		
	} else {
	   f->elenco = novo;
	}//if
}
//=======================================================================
void criaElenco(Tlista *L, string titulo, string nomeAtor){
	Tfilme *f = localizaFilme(L,titulo);
	if(f != NULL){
		Tator *a = localizaAtor(L, nomeAtor);
		
		if(a != NULL)	{
			relacionarFilmeAtor(f,a);
		}//if
	}//if	
}
//=======================================================================

void cadastraFilme(Tfilme *elementoNovo){
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
    cadastraFilme(novo);
    novo->prox = NULL;
    novo->ante = NULL;
    novo->elenco = NULL;
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
                
                if (atual->ante == NULL){
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
        printf("1 - Inserir Filme na lista\n");
        printf("2 - Exibe Lista de Filmes\n");
        printf("3 - Excluir da Filme da lista\n");
        printf("4 - Ativar menu dos atores\n");
        printf("5 - Sair\n");
        printf("Digite a opção: ");
        scanf("%d", &op);
        printf("%s\n", CORTE);
    } while ((op < 1)||(op > 5));
    return op;
}

int menu(){
    int op;
    printf("\n|---------------|MENU LISTA DE ATORES|---------------|\n");
    do{
        printf("1 - Cadastrar Ator\n");
        printf("2 - Exibe Lista de Atores\n");
        printf("3 - Sair\n");
        printf("Digite a opção: ");
        scanf("%d", &op);
        printf("%s\n", CORTE);
    } while ((op < 1)||(op > 3));
    return op;
}

void exibe(Tlista lista){
    Tfilme *atual = lista.inicioF;
    Telenco *cursor;
    Tator *ator;
    int cont = 0;
    printf("\n\n\t\t========| EXIBE TODOS OS FILMES |========\n\n");
    while (atual != NULL){
        printf("\t(%d) - %s [%d].\n",cont+1,atual->titulo,atual->anoProducao);
        if (atual->elenco != NULL){
            printf("\n");
            cursor = atual->elenco;
            while (cursor != NULL){
                printf("\t\t%s\n", cursor->atorFilme->nome);
                cursor->atorFilme = cursor->atorFilme->prox;
            }
        }
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

int atoresSelect(Tlista *lista){
    int op;
    int repete = 0;
    do{
        op = menu();
        switch (op){
        case 1:
            cadastraAtor(lista);
            break;
        case 2: 
            exibeAtores(lista);
            break;
        case 3:
            repete = 1;
            break;
        default:
            break;
        }
    } while (repete == 0); 
    return 0;
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
            atoresSelect(&lista);
            break;
        case 5:
            repete = 1;
            break;
        default:
            break;
        }
    } while (repete == 0); 
    return 0;
}

//================================================

// /* 
//    Lista Encadeada Aninhada 25/03/2024.
//  */
// #include <stdio.h>
// #include <stdlib.h>
// #include <string.h>

// typedef char string[40];

// typedef struct tipoAtor {
// 	string nome;
// 	tipoAtor *prox;	
// }TAtor;

// typedef struct tipoElenco {
// 	TAtor *ator;
// 	tipoElenco *prox;
// }TElenco;

// typedef struct tipoFilme {
// 	string titulo;
// 	int anoProducao;
// 	tipoFilme *ante, *prox;
// 	TElenco *elenco;
// }TFilme;

// typedef struct tipoLista {
// 	TFilme *inicioF;
// 	TFilme *fimF;
// 	TAtor *inicioA;
// 	TAtor *fimA;
// }TLista;

// TLista listas;

// void inicializa(TLista *L);
// void insereAtor(TLista *L, const string nome);
// void cadastraAtor(TLista *L);
// void exibeAtores(TLista *L);
// int menu();
// void cadastraFilme(TLista *L);
// void insereFilme(TLista *L, string titulo, int ano);
// void exibeFilmes(TLista L);
// void defineElenco(TLista *L);
// TFilme *localizaFilme(TLista *L, string titulo);
// TAtor *localizaAtor(TLista *L, string nome);
// void relacionarFilmeAtor(TFilme *f, TAtor *a);
// void criaElenco(TLista *L, string titulo, string nomeAtor);

// int main(){
// 	int opcao;
	
// 	inicializa(&listas);
	
// 	do{
// 		opcao = menu();
		
// 		switch(opcao)	{
// 			case 1: cadastraAtor(&listas); break;
// 			case 2: exibeAtores(&listas); break;
// 			case 3: cadastraFilme(&listas); break;
// 			case 4: exibeFilmes(listas); break;
// 			case 5: defineElenco(&listas); break;
// 		}//switch case
		
// 	}while(opcao != 0);
// }
// //=================================================================
// void inicializa(TLista *L){
// 	L->inicioF = NULL;
// 	L->inicioA = NULL;
// 	L->fimF = NULL;
// 	L->fimA = NULL;
	
// 	insereAtor(L,"Tobey Maguire");
// 	insereAtor(L,"Christen Dusten");
// 	insereAtor(L,"Charlton Heston");
// 	insereAtor(L,"Sophia Loren");
// 	insereAtor(L,"Kirk Douglas");
// 	insereAtor(L,"Michael Douglas");
// 	insereAtor(L,"Mira Sorvino");
// 	insereAtor(L,"Tom Cruise");
// 	insereAtor(L,"Jennifer Connelly");
// 	insereAtor(L,"Kelly McGillis");
// 	insereAtor(L,"Kathleen Turner");
// 	insereAtor(L,"Danny DeVito");
// 	insereAtor(L,"Sylvester Stallone");
// 	insereAtor(L,"Talia Shire");
// 	insereAtor(L,"Hayley Atwell");
// 	insereAtor(L,"Chris Evans");
// 	insereAtor(L,"Sebastian Stan");
// 	insereAtor(L,"Sidney Potier");
// 	insereAtor(L,"Charlie Chaplin");
// 	insereAtor(L,"Paulette Goddard");
// 	insereAtor(L,"Bruce Willis");
// 	insereAtor(L,"Cybill Shepherd");
// 	insereAtor(L,"Genevieve Page");
// 	insereAtor(L,"Roy Scheider");
// 	insereAtor(L,"Richard Dreyfuss");
// 	insereAtor(L,"Edna Purviance");
// 	insereAtor(L,"Jackie Coogan");
// 	insereAtor(L,"Lulu");
// 	insereAtor(L,"Judy Geeson");
	
// 	insereFilme(L,"O Garoto", 1921);
// 	insereFilme(L,"Tempos Modernos", 1936);
// 	insereFilme(L,"SPARTACUS", 1958);
// 	insereFilme(L,"El Cid", 1961);
// 	insereFilme(L,"To Sir With Love", 1967);
// 	insereFilme(L,"Tubarao", 1976);
// 	insereFilme(L,"O Poderoso Chefao", 1972);
// 	insereFilme(L,"A Joia do NILO", 1986);
// 	insereFilme(L,"Moonlighting", 1985);
// 	insereFilme(L,"Tudo por uma Esmeralda", 1985);
	
// 	criaElenco(L,"El Cid","Charlton Heston");
// 	criaElenco(L,"El Cid","Sophia Loren");
// 	criaElenco(L,"El Cid","Genevieve Page");
// 	criaElenco(L,"Tubarao","Roy Scheider");
// 	criaElenco(L,"Tubarao","Richard Dreyfuss");
// 	criaElenco(L,"A Joia do NILO","Michael Douglas");
// 	criaElenco(L,"A Joia do NILO","Kathleen Turner");
// 	criaElenco(L,"A Joia do NILO","Danny DeVito");
// 	criaElenco(L,"O Garoto","Charlie Chaplin");
// 	criaElenco(L,"O Garoto","Edna Purviance");
// 	criaElenco(L,"O Garoto","Jackie Coogan");
// 	criaElenco(L,"To Sir With Love","Sidney Potier");
// 	criaElenco(L,"To Sir With Love", "Judy Geeson");
// 	criaElenco(L,"To Sir With Love", "Lulu");
// 	criaElenco(L,"Moonlighting", "Cybill Shepherd");
// 	criaElenco(L,"Moonlighting", "Bruce Willis");
// 	criaElenco(L,"Tudo por uma Esmeralda", "Michael Douglas");
// 	criaElenco(L,"Tudo por uma Esmeralda", "Kathleen Turner");
// 	criaElenco(L,"Tempos Modernos", "Charlie Chaplin");
// 	criaElenco(L,"Tempos Modernos", "Paulette Goddard");
	
// }
// //=================================================================
// void insereAtor(TLista *L, const string nome){
// 	TAtor *novo	= (TAtor *)malloc(sizeof(TAtor));
// 	int flag = 0;
	
// 	novo->prox = NULL;
// 	strcpy(novo->nome, nome);
	
// 	if(L->inicioA == NULL){
// 		//Lista de Atores encontra-se vazia
// 		L->inicioA = novo;
// 		L->fimA = novo;
// 	} else {
// 		TAtor *atual = L->inicioA;
// 		TAtor *anterior = NULL;
		
// 		while(atual != NULL){
// 			if (strcmp(atual->nome,novo->nome) > 0)	{
// 				//Momento de inserir na lista.
// 				flag = 1;
// 				if(atual == L->inicioA)	{
// 					//insere NOVO como primeiro da Lista...
// 					novo->prox = atual;
// 					L->inicioA = novo;	
// 				} else {
// 					//insere NOVO antes do ATUAL (no MEIO da LISTA)
// 					novo->prox = anterior->prox;
// 					anterior->prox = novo;
// 				}//if
				
// 				break;
// 			}//if
// 		    anterior = atual;
// 			atual = atual->prox;
// 		}//while
		
// 		if(!flag){
// 			//Insere NOVO no FIM da LISTA.
// 			L->fimA->prox = novo;
// 			L->fimA = novo;
// 		}//if
// 	}//if
// }
// //=================================================================
// void cadastraAtor(TLista *L){
// 	string nome;
	
// 	printf("\n\n\t\t=====| INSERE ATOR |=====\n\n");
// 	printf("\tInforme NOME de novo ATOR: ");
// 	fflush(stdin);
// 	gets(nome);
	
// 	insereAtor(L, nome);
// }
// //=================================================================
// void exibeAtores(TLista *L){
// 	TAtor *atual = L->inicioA;
// 	int cont = 0;
	
// 	printf("\n\n");
// 	printf("+----------------------------------------------------+\n");
// 	while (atual != NULL)	{
// 		printf("\t(%d) - %s.\n", ++cont, atual->nome);
// 		atual = atual->prox;
// 	}//while
// 	printf("+----------------------------------------------------+\n\n\n");
// 	system("PAUSE");
// }
// //=========================================================================
// int menu(){
// 	int opcao;
	
// 	system("CLS");
// 	printf("\n\n\t\t==========| MENU |==========\n\n");
// 	printf("\t0 - SAIR (Encerrar Aplicacao).\n\n");
// 	printf("\t1 - Cadastrar Atores.\n");
// 	printf("\t2 - Exibir Atores.\n");
// 	printf("\t3 - Cadastrar Filmes.\n");
// 	printf("\t4 - Exibir Filmes.\n");
// 	printf("\t5 - Define Elenco de Filme.\n");
	
// 	printf("\n\tInforme OPCAO desejada: ");
// 	fflush(stdin);
// 	scanf("%d", &opcao);
	
// 	if((opcao < 0) || (opcao > 5)){
// 		printf("\n\n\tERRO: Opcao invalida!\nTente outra vez!\n\n");
// 		system("PAUSE");
// 	}
// 	return opcao;
// }
// //=========================================================================
// void cadastraFilme(TLista *L){
// 	string titulo;
// 	int anoProducao;
	
// 	printf("\n\n\t\t=====| CADASTRO DE FILMES |=====\n\n");
// 	printf("\tTITULO: ");
// 	fflush(stdin);
// 	gets(titulo);
// 	printf("\n\n\tANO DE PRODUCAO: ");
// 	fflush(stdin);
// 	scanf("%d",&anoProducao);
	
// 	insereFilme(L, titulo, anoProducao);
// }
// //========================================================================
// void insereFilme(TLista *L, string titulo, int ano){
// 	TFilme *novo = (TFilme *)malloc(sizeof(TFilme));
// 	TFilme *anterior, *atual;
// 	int flag;
	
// 	novo->prox = NULL;
// 	novo->ante = NULL;
// 	novo->elenco = NULL;
// 	strcpy(novo->titulo, titulo);
// 	novo->anoProducao = ano;
	
// 	if(L->inicioF == NULL){
// 		//Inserção em Lista VAZIA.
// 		L->inicioF = novo;
// 		L->fimF = novo;
// 	} else {
// 		//A Lista possui pelo menos um Filme.
// 		atual = L->inicioF;
// 		flag = 0;
		
// 		while(atual != NULL){
// 			if(strcmp(atual->titulo, novo->titulo) > 0){
// 				//Encontrada posição de inserção na Lista...
// 				anterior = atual->ante;
// 				flag = 1;
				
// 				novo->prox = atual;
// 				atual->ante = novo;
				
// 				if(anterior == NULL){
// 					//insere NOVO antes do PRIMEIRO Filme da Lista.	
// 					L->inicioF = novo;	
// 				} else {
// 					//insere NOVO no MEIO da Lista.
// 					anterior->prox = novo;
// 					novo->ante = anterior;
// 				}//if
// 				break;
// 			}//if
// 			atual = atual->prox;	
// 		}//while
		
// 		if(!flag){
// 			//Insere após o ÚLTIMO Filme da Lista.
// 			L->fimF->prox = novo;
// 			novo->ante = L->fimF;
// 			L->fimF = novo;
// 		}//if
		
// 	}//if
// }
// //========================================================================
// void exibeFilmes(TLista L){
// 	int cont = 0;
// 	TFilme *atual = L.inicioF;
// 	TElenco *cursor;
	
// 	printf("\n\n\t\t=====| EXIBIR TODOS OS FLMES |=====\n\n");
// 	while(atual != NULL){
// 		printf("\n\t(%d) - %s [%d].\n", ++cont, atual->titulo, atual->anoProducao);
// 		if(atual->elenco != NULL) printf("\n");
// 		cursor = atual->elenco;
// 		while(cursor != NULL){
// 			printf("\t\t%s\n", cursor->ator->nome);
// 			cursor = cursor->prox;
// 		}//while
// 		atual = atual->prox;
// 	}//while
// 	printf("\n\n");
// 	system("PAUSE");
// }
// //=======================================================================
// void defineElenco(TLista *L){
// 	TFilme *f;
// 	TAtor *a;
// 	string titulo;
// 	string nome;
	
// 	printf("\n\n\t\t======| DEFINE ELENCO DE FILME |======\n\n");
// 	printf("\tInforme TITULO de FILME: ");
// 	fflush(stdin);
// 	gets(titulo);
	
// 	f = localizaFilme(L, titulo);
	
// 	if(f == NULL){
// 		printf("\n\n\tERRO: filme NAO encontrado.\n\tTITULO: %s.\n\n",f->titulo);
// 		system("PAUSE");
// 	} else {
//     	printf("\n\n\tInforme NOME de ATOR: ");
// 	    fflush(stdin);
// 	    gets(nome);
			
// 	    a = localizaAtor(L, nome);
// 		if(a == NULL){
// 			printf("\n\n\tERRO: ator NAO encontrado!\n\tNOME: %s.\n\n", a->nome);
// 			system("PAUSE");
// 		} else {
// 			TElenco *novo=(TElenco *)malloc(sizeof(TElenco));
// 			novo->ator = a;
// 			novo->prox = NULL;
			
// 			if(f->elenco == NULL){
// 				//Filme sem elenco ainda.
// 				f->elenco = novo;
// 			} else {
// 				//Filme já possui pelo menos um ator no elenco.
// 				TElenco *e = f->elenco;
// 				while(e->prox != NULL){
// 					e = e->prox;
// 				}//while
// 				e->prox = novo;
// 			}
// 		}//if
// 	}//if
	
// }
// //=======================================================================
// TFilme *localizaFilme(TLista *L, string titulo){
// 	TFilme *atual = L->inicioF;
	
// 	while (atual != NULL){
// 		if(strcmp(atual->titulo, titulo) == 0){
// 			break;
// 		}//if
// 		atual = atual->prox;
// 	}//while
// 	return atual;
// }
// //=======================================================================
// TAtor *localizaAtor(TLista *L, string nome){
// 	TAtor *atual = L->inicioA;
	
// 	while(atual != NULL)	{
// 		if(strcmp(atual->nome, nome) == 0) {
// 			break;
// 		}//if
// 		atual = atual->prox;
// 	}//while
// 	return atual;
// }
// //=======================================================================
// void relacionarFilmeAtor(TFilme *f, TAtor *a){
// 	TElenco *novo = (TElenco *)malloc(sizeof(TElenco));
	
// 	novo->prox = NULL;
// 	novo->ator = a;
	
// 	if(f->elenco != NULL){
// 	   TElenco *atual = f->elenco;
// 	   while(atual->prox != NULL){
// 			atual = atual->prox;
//        }//while
// 	   atual->prox = novo;		
// 	} else {
// 	   f->elenco = novo;
// 	}//if
// }
// //=======================================================================
// void criaElenco(TLista *L, string titulo, string nomeAtor){
// 	TFilme *f = localizaFilme(L,titulo);
// 	if(f != NULL){
// 		TAtor *a = localizaAtor(L, nomeAtor);
		
// 		if(a != NULL)	{
// 			relacionarFilmeAtor(f,a);
// 		}//if
// 	}//if	
// }
// //=======================================================================