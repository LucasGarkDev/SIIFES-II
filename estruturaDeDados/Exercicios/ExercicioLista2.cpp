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
    struct tipoElemento *prox;	
}TElemento;

typedef struct tipoLista {
	TElemento *inicio;
	TElemento *fim;
	int total;
    int k;
}Tlista;

void inicializarCentroides(Tlista *lista) {
    TElemento *atual = lista->inicio;
    int grupo = 0;

    while (atual != NULL) {
        atual->grupo = grupo;
        grupo = (grupo + 1) % lista->k;
        atual = atual->prox;
    }
}

void recolheEnderecos(TElemento** enderecoVetor, Tlista *lista){
    TElemento *atual = lista->inicio;
    int i = 0;
    while (atual != NULL){
        enderecoVetor[i] = atual;
        i++;
        atual = atual->prox;
    }  
}


float calcularDistancia(TElemento *elemento1, TElemento *elemento2) {
    return sqrt(pow(elemento1->idade - elemento2->idade, 2) + pow(elemento1->altura - elemento2->altura, 2) + pow(elemento1->peso - elemento2->peso, 2));
}

void procuraMenorDistancia(float *distanciasDeJ, Tlista *lista, int tamDistancia, int indi){
    int i, indiceLista = 0;
    float menor = 999;
    TElemento *atual = lista->inicio;
    for (i = 0; i < tamDistancia; i++){
        if (distanciasDeJ[i] < menor){
            menor = distanciasDeJ[i];
            indiceLista = i;
        }
    }
    int aux = 0;
    while (atual->prox != NULL){
        if (aux == indiceLista){
            atual->grupo = indi;
        }
        aux++;
        atual = atual->prox;  // Adicione esta linha para avançar para o próximo elemento
    }
}

void escolheCentroides(TElemento** enderecoVetor, int* flags, int tam) {
    srand((unsigned int)time(NULL));

    size_t escolhido = rand() % tam;

    // Marcar o centro escolhido
    flags[escolhido] = 1;

    // Utilizar o endereço do elemento escolhido (enderecoVetor[escolhido]) conforme necessário
    printf("Centro escolhido. Dado: %p\n", (void*)enderecoVetor[escolhido]);
}

void kmeans(Tlista *lista){
    int tamList = lista->total;
    int numCentros = lista->k;
    int *flags = (int *)calloc(tamList, sizeof(int));
    TElemento** enderecoVetor = (TElemento**)malloc(tamList * sizeof(TElemento*));
    recolheEnderecos(enderecoVetor, lista);
    inicializarCentroides(lista);
    
    for (int j = 0; j < numCentros; j++) {  // Ajuste aqui
        escolheCentroides(enderecoVetor, flags, tamList);
        float *distancia = (float *)calloc(tamList - 1, sizeof(float));
        int i = 0;  // Adicione essa linha para redefinir i antes do loop interno
        int k = 0;

        while (i != tamList) {
            if (flags[i] == 1 && j != i) {
                distancia[k] = calcularDistancia(enderecoVetor[j], enderecoVetor[i]);
                k++;
            }
            i++;
        }

        procuraMenorDistancia(distancia, lista, tamList - 1, j);
        free(distancia);  // Libera a memória alocada para distancia
    }

    free(flags);
    free(enderecoVetor);
}


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
    // inserir(lista,1.80,23,81,"Pedro");
    // inserir(lista,1.76,21,67,"Anna");
    // inserir(lista,1.72,21,61,"Cinthia");
    // inserir(lista,1.72,17,72,"Jose");
    // inserir(lista,1.77,35,96,"Silvano");
    // inserir(lista,1.68,28,69,"Evelyn");
}

void inserir(Tlista *lista){
    int inseriu = 0;
    TElemento *novo = (TElemento *)malloc(sizeof(TElemento));
    digitarDados(novo);
    novo->prox = NULL;
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
        printf("\n%s\n", RESULTADO);
        printf("Numero do %d da lista e: (%s) (%.2f) Grupo: %d\n", ++cont, atual->nome, atual->imc, atual->grupo);
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
            if (lista->inicio == lista->fim){
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
        atual = atual->prox;// move para o proximo elemento
    }
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

Tlista lista; //variavel global

//=================================================
int main(){
    // int op, numInseri;
    char nome[40];
    int op;
    int repete = 0;
    inicializa(&lista);
    do{
        op = pedirOpcao();
        switch (op){
        case 1:
            // numInseri = pedirNum();
            inserir(&lista);
            break;
        case 2:
            kmeans(&lista);
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
