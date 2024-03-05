#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <math.h>
#include <time.h>

#define INICIO "------------INICIO------------" 
#define RESULTADO "------------RESULTADO------------"
#define CORTE "------------------------"

typedef char string[40];

typedef struct tipoElemento {
    float idade;
	float altura;
	float peso;
	float imc;
	string nome;
    int grupo;
    int centroide;
    float distancia;
    struct tipoElemento *prox;	
}TElemento;

typedef struct tipoLista {
	TElemento *inicio;
	TElemento *fim;
	int total;
    int k;
}Tlista;

void digitarDados(TElemento *elementoNovo){
    printf("Digite o nome da pessoa: ");
    scanf(" %39[^\n]s", elementoNovo->nome);
    printf("Digite a idade do individuo: ");
    scanf("%f", &elementoNovo->idade);
    printf("Digite a altura do individuo: ");
    scanf("%f", &elementoNovo->altura);
    printf("Digite o peso do individuo: ");
    scanf("%f", &elementoNovo->peso);
    elementoNovo->imc = (elementoNovo->peso/(elementoNovo->altura*elementoNovo->altura));

}

void pedirNome(char *nome){
    printf("Digite o nome do individuo para excluir: ");
    scanf(" %39[^\n]s", nome);
}

void inicializa(Tlista *lista){
    lista->inicio = NULL;
    lista->fim = NULL;
    lista->total = 0;
    printf("\n\n\tInforme quantidade de Grupos: ");
    scanf("%d", &lista->k);
}

// int jaEscolhidoComoCentroide(const int *centroides, int tamanho, int indice) {
//     for (int i = 0; i < tamanho; ++i) {
//         if (centroides[i] == indice) {
//             return 1; 
//         }
//     }
//     return 0; 
// }

// void escolheCentroides(Tlista *lista) {
//     int centroidesEscolhidos[lista->k];
//     int i, j;
//     int jaFoiEscolhido;
//     TElemento *atual = lista->inicio;
//     for (i = 0; i < lista->k; ++i) {
//         int indice;
//         do {
//             indice = rand() % lista->total;
//             jaFoiEscolhido = jaEscolhidoComoCentroide(centroidesEscolhidos,i,indice);
//         } while (jaFoiEscolhido != 0);
//         centroidesEscolhidos[i] = indice;
//         for (j = 0; j < indice; ++j) {
//             atual = atual->prox;
//         }
//         atual->centroide = i + 1;
//     }
// }

void insere(Tlista *lista, float altura, float idade, float peso, char *nome){
    int inseriu = 0;
    TElemento *novo = (TElemento *)malloc(sizeof(TElemento));
    novo->altura = altura;
    novo->idade = idade;
    novo->peso = peso;
    strcpy(novo->nome,nome);
    novo->prox = NULL;
    novo->centroide = 0;
    novo->imc = (peso/(altura*altura));
    if (lista->inicio == NULL){
        //Lista encontra-se vazia.
        //Inserir o primeiro e unico elemento da lista ate agora
        lista->inicio = novo;
        lista->fim = novo;
        lista->total = 1;
        inseriu = 1;
    }else{
        //Lista ja possui pelo menos 1 elemento
        TElemento *atual = lista->inicio;
        TElemento *anterior = NULL;
        while (atual != NULL){
            if (strcmp(atual->nome,novo->nome) == 1){
                if (atual == lista->inicio){
                    //Inserir novo no inicio da lista
                    novo->prox = atual;
                    lista->inicio = novo;
                }else{
                    //Inserir novo no meio da lista
                    novo->prox = atual;
                    anterior->prox = novo;
                }
                inseriu = 1;
                lista->total++;
                break;
            }
            anterior = atual;
            atual = atual->prox; //move para o próximo elemento
        }
        if (!inseriu){
            //Inserir elemento no fim da lista
            lista->fim->prox = novo;
            lista->fim = novo;
            lista->total++;
        }
        lista->total++;
    }
}

// void digitarDados(TElemento *elementoNovo){
//     printf("Digite o nome da pessoa: ");
//     scanf(" %39[^\n]s", elementoNovo->nome);
//     printf("Digite a idade do individuo: ");
//     scanf("%f", &elementoNovo->idade);
//     printf("Digite a altura do individuo: ");
//     scanf("%f", &elementoNovo->altura);
//     printf("Digite o peso do individuo: ");
//     scanf("%f", &elementoNovo->peso);
//     elementoNovo->imc = (elementoNovo->peso/(elementoNovo->altura*elementoNovo->altura));

// }

// void pedirNome(char *nome){
//     printf("Digite o nome do individuo para excluir: ");
//     scanf(" %39[^\n]s", nome);
// }

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

int pedirNum(){
    int num;
    do{
        printf("Digite um numero para ser inserido: ");
        scanf("%d", &num);
    } while (num < 0);
    return num;
}

int pedirNum2(){
    int num;
    do{
        printf("Digite um numero para ser excluido: ");
        scanf("%d", &num);
    } while (num < 0);
    return num;
}


void inserir(Tlista *lista){
    int inseriu = 0;
    TElemento *novo = (TElemento *)malloc(sizeof(TElemento));
    digitarDados(novo);
    novo->prox = NULL;
    novo->centroide = 0;
    if (lista->inicio == NULL){
        //Lista encontra-se vazia.
        //Inserir o primeiro e unico elemento da lista ate agora
        lista->inicio = novo;
        lista->fim = novo;
        lista->total = 1;
        inseriu = 1;
    }else{
        //Lista ja possui pelo menos 1 elemento
        TElemento *atual = lista->inicio;
        TElemento *anterior = NULL;
        while (atual != NULL){
            if (strcmp(atual->nome,novo->nome) == 1){
                if (atual == lista->inicio){
                    //Inserir novo no inicio da lista
                    novo->prox = atual;
                    lista->inicio = novo;
                }else{
                    //Inserir novo no meio da lista
                    novo->prox = atual;
                    anterior->prox = novo;
                }
                inseriu = 1;
                lista->total++;
                break;
            }
            anterior = atual;
            atual = atual->prox; //move para o próximo elemento
        }
        if (!inseriu){
            //Inserir elemento no fim da lista
            lista->fim->prox = novo;
            lista->fim = novo;
            lista->total++;
        }
        lista->total++;
    }
}

void exibeLista(Tlista lista) {
    TElemento *atual = lista.inicio;
    int cont = 0;
    printf("\n\n\n\t\t===| EXIBE LISTA COMPLETA |===\n\n");
    while (atual != NULL) {
        if (atual->grupo == -1){
            printf("\n%s\n", RESULTADO);
            printf("Numero do %d da lista e: (%s) (%.2f) Sem Grupo\n", ++cont, atual->nome, atual->imc);
        }else{
            printf("\n%s\n", RESULTADO);
            if (atual->centroide != 0){
                printf("Numero do %d da lista e: (%s) (%.2f) E o centroid do grupo: %d\n", ++cont, atual->nome, atual->imc, atual->centroide);
            }else{
                printf("Numero do %d da lista e: (%s) (%.2f) Grupo: %d\n", ++cont, atual->nome, atual->imc, atual->grupo);
            }
        }
        atual = atual->prox;
    }
    printf("%s\n", CORTE);
}

void excluirLista(Tlista *lista, string nome){
    TElemento *atual = lista->inicio;
    int cont = 0;
    TElemento *anterior = NULL;
    while (atual != NULL){
        if (cont == 0){
            printf("\n\n\tPessoa nao encontrada\n");
            cont++;
        }else if (strcmp(atual->nome,nome) == 0){
            //Encontra o elemento a ser excluido
            if(lista->inicio == lista->fim){
                //Exclusao do unico elemento da lista
                printf("\n\n\tExcluindo o PESSOA %s ...\n", atual->nome);
                printf("\n\n\tExcluindo o ELEMENTO %.2f ...\n", atual->imc);
                lista->inicio = NULL;
                lista->fim = NULL;
            }else if (atual == lista->inicio){
                //Esclui o primeiro elemento da lista
                printf("\n\n\tExcluindo o PESSOA %s ...\n", atual->nome);
                printf("\n\n\tExcluindo o ELEMENTO %.2f ...\n", atual->imc);
                lista->inicio = atual->prox; //lista->inicio = lista->inicio->prox;
            }else if (atual == lista->fim){
                //Excluindo o ultimo cara da lista
                printf("\n\n\tExcluindo o PESSOA %s ...\n", atual->nome);
                printf("\n\n\tExcluindo o ELEMENTO %.2f ...\n", atual->imc);
                lista->fim = anterior;
                lista->fim->prox = NULL;
            }else if ((atual != lista->inicio)&&(atual != lista->fim)){
                //Excluindo alguem que nao esta nem no fim e nem no inicio
                printf("\n\n\tExcluindo o PESSOA %s ...\n", atual->nome);
                printf("\n\n\tExcluindo o ELEMENTO %.2f ...\n", atual->imc);
                anterior->prox = atual->prox;
            }
            free(atual);
            lista->total--;
            break;
        }
        anterior = atual;
        atual = atual->prox;
    }
}

void escolheCentroides(Tlista *lista){
    //Função que escolhe randomicamente um centroide para cada grupo.
    //O usuário-final define a quantidade de grupos existentes (k) durante a execução
    //do programa.
    //Esta função sorteia um TElemento para Centróide de cada grupo   

    srand((unsigned int)time(NULL));
    int grupo = lista->k;
    int posicao = 0;
    int pos;
  
    TElemento *atual;
  
    while(grupo > 0){
	    posicao = rand() % 20;
	    atual = lista->inicio;
	    pos = 0;
	    while(atual != NULL){
		    if(pos == posicao){
			    //Encontrado TElemento a ser Transformado em Centróide	...
			    if(atual->centroide == 0){
			        atual->centroide = 1;
			        atual->grupo = grupo;
		           	printf("\n\n\tEscolhido CENTROIDE do GRUPO %d: %s ...",grupo, atual->nome);
		    	    break;
	    	    } else {
				    pos--;
    		    }//if
		    }//if
		    if(atual == lista->fim){
			atual = lista->inicio;
		    } else {
			    atual = atual->prox;
		    }//if
		    pos++;
	    }//while
	    grupo--;  			
    }//while
  
    printf("\n\n Processo de escolha dos CENTROIDES concluido!\n\n");
}
Tlista lista; //variavel global

float calcularDistancia(TElemento *elemento1, TElemento *elemento2) {
    return sqrt(pow(elemento1->idade - elemento2->idade, 2) + pow(elemento1->altura - elemento2->altura, 2) + pow(elemento1->peso - elemento2->peso, 2));
}

int pesquisar(TElemento *distancias, int tam){
    int i;
    float menor = 999.99;
    int resp;
    for (i = 0; i < tam; i++){
        if (distancias[i].distancia < menor){
            menor = distancias[i].distancia;
            resp = i;
        }
    }
    return resp;
}

void ordenaPorGrupo(Tlista *lista, TElemento *centroides){
//Reordena os elementos da Lista por GRUPO. Ao final do processo os ELementos da Lista
//que pertencerem ao mesmo grupo deverão estar agrupados de maneira contigua.
    int i;
    TElemento *atual = lista->inicio;
	for (i = 0; i < lista->k; i++){
        atual = lista->inicio;
        printf("\n|-----------------Grupo numero %d----------------|\n", i+1);
        printf("\n O centroid do grupo e: %s - %.2f - %d\n", centroides[i].nome, centroides[i].imc, centroides[i].grupo);
        while (atual != NULL){
            if (atual->grupo == centroides[i].grupo){
                printf("O individuo %s faz parte do grupo %d\n", atual->nome, atual->grupo);
            }
            atual = atual->prox;
        }
    }  
}

void distribuiElementos(Tlista *lista) {
    TElemento centroides[lista->k];
    int i = 0;
    TElemento *atual = lista->inicio;

    // Encontrar os centroides
    while (atual != NULL) {
        if (atual->centroide != 0) {
            centroides[i] = *atual;
            i++;
        }
        atual = atual->prox;
    }

    // Calcular distâncias e atribuir grupos
    TElemento distancias[lista->k];
    atual = lista->inicio;
    while (atual != NULL) {
        if (atual->centroide == 0) {
            for (i = 0; i < lista->k; i++) {
                distancias[i].distancia = calcularDistancia(&centroides[i], atual);
                distancias[i].grupo = centroides[i].grupo;
            }
            int indiceMenorDist = pesquisar(distancias, lista->k);
            atual->grupo = distancias[indiceMenorDist].grupo;
        }
        atual = atual->prox;
    }

    ordenaPorGrupo(lista,centroides);   
}


//=================================================
int main(){
    // int op, numInseri;
    char nome[40];
    int op;
    int repete = 0;
    // int primeiraVez = 1;
    inicializa(&lista);
    do{
        op = pedirOpcao();
        switch (op){
        case 1:
            // numInseri = pedirNum();
            // inserir(&lista);
            insere(&lista,1.80,23,81,"Pedro");
            insere(&lista,1.76,21,67,"Anna");
            insere(&lista,1.72,21,61,"Cinthia");
            insere(&lista,1.72,17,72,"Jose");
            insere(&lista,1.77,35,96,"Silvano");
            insere(&lista,1.68,28,69,"Evelyn");
            insere(&lista,1.77,56,94,"Asdrubal");
            insere(&lista,1.75,35,85,"Jambira");
            insere(&lista,1.65,20,100,"Desiderio");
            insere(&lista,1.82,46,102,"Ramiro");
            insere(&lista,1.70,38,69,"Paula");
            insere(&lista,1.65,39,68,"Claudia");
            insere(&lista,1.66,18,65,"Sofia");
            insere(&lista,1.86,58,99,"Astolfo");
            insere(&lista,1.80,52,120,"Demostenes");
            insere(&lista,1.70,38,69,"Josefino");
            escolheCentroides(&lista);
            distribuiElementos(&lista);
            break;
        case 2:
            exibeLista(lista);
            break;
        case 3:
            // numInseri = pedirNum2();
            pedirNome(nome);
            excluirLista(&lista,nome);
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

//=========================================