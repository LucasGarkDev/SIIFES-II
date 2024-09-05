#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "TabelaHash.h"

//Feito por: Lucas Garcia de Souza

//=================================================
FILE * abrirArquivo(char * nomeArq, char * modo) {
    FILE * arq;
    arq = fopen( nomeArq, modo );
    if ( arq == NULL) {
        printf("ERRO ao abrir o arquivo.");
        exit(-1);
    }
    return arq;
}
//=================================================
void construirListaDoZero(TLista *lista){
    lista->inicio = NULL;
    lista->fim = NULL;
    lista->total = 0;
}
//=================================================
void lerArquivo(TLista *lista, FILE *arquivoLista) {
    int matricula;
    char nome[100];  
    while (fscanf(arquivoLista, "%d\n", &matricula) != EOF) {
        fgets(nome, sizeof(nome), arquivoLista);  
        nome[strcspn(nome, "\n")] = 0;  
        inserir(lista, matricula, nome);  
    }
}
//=================================================
void inicializa(TLista *lista, FILE *arquivoLista) {
    construirListaDoZero(lista);
    fseek(arquivoLista, 0, SEEK_END);
    long tamanho = ftell(arquivoLista);
    if (tamanho != 0) {
        fseek(arquivoLista, 0, SEEK_SET);  
        lerArquivo(lista, arquivoLista);
    }
}
//=================================================
void gravarListaEmArquivo(TLista *lista, FILE *arquivoLista) {
    arquivoLista = abrirArquivo("lista_matricula.txt", "w");
    TElemento *atual = lista->inicio;
    while (atual != NULL) {
        fprintf(arquivoLista, "%d\n%s\n", atual->valor, atual->nome); 
        atual = atual->prox;
    }
    fclose(arquivoLista);
}
//=================================================
int pesquisarMatricula2(TLista *lista, int matriculaBusca) {
    TElemento *atual = lista->inicio;
    while (atual != NULL) {
        if (atual->valor == matriculaBusca) {
            printf("Matrícula %d encontrada. Nome: %s\n", matriculaBusca, atual->nome);
            return 1;  // Encontrado
        }
        atual = atual->prox;
    }
    printf("Matrícula %d não encontrada na lista.\n", matriculaBusca);
    return 0;  // Não encontrado
}
//=================================================
int pesquisarMatricula(TLista *lista) {
    int matriculaBusca;
    printf("Digite a matrícula que deseja buscar: ");
    scanf("%d", &matriculaBusca);
    TElemento *atual = lista->inicio;
    while (atual != NULL) {
        if (atual->valor == matriculaBusca) {
            return matriculaBusca;
        }
        atual = atual->prox;
    }
    printf("Matrícula %d não encontrada na lista.\n", matriculaBusca);
    return matriculaBusca;
}
//=================================================
void inserir(TLista *lista, int valor, char *nome) {
    TElemento *novo = (TElemento *)malloc(sizeof(TElemento));
    novo->valor = valor;
    strcpy(novo->nome, nome); 
    novo->prox = NULL;

    if (lista->inicio == NULL) {
        lista->inicio = novo;
        lista->fim = novo;

    } else {
        lista->fim->prox = novo;
        lista->fim = novo;
    }
    lista->total++;
}
//=================================================
int pedirOpcao3() {
    int opcao;
    
    printf("Escolha o tamanho da tabela hash em relação ao número total de matrículas:\n");
    printf("1 - 100%% do número de matrículas\n");
    printf("2 - 120%% do número de matrículas\n");
    printf("3 - 150%% do número de matrículas\n");
    printf("Digite sua opção (1, 2 ou 3): ");
    scanf("%d", &opcao);
    return opcao;
    
}

//=================================================
void exibeLista(TLista lista) {
    TElemento *atual = lista.inicio;
    int cont = 0;
    printf("\n\n\n\t\t===| EXIBE LISTA COMPLETA |===\n\n");
    while (atual != NULL) {
        printf("Número %d da lista: Matrícula (%d), Nome: %s\n", ++cont, atual->valor, atual->nome);
        atual = atual->prox;
    }
}

//=================================================
void excluirLista(TLista *lista, int valor){
    TElemento *atual = lista->inicio;
    TElemento *anterior = NULL;
    while (atual != NULL) {
        if (atual->valor == valor) {
            // Encontra o elemento a ser excluído
            if (atual == lista->inicio) {
                // Exclusão do primeiro elemento da lista
                printf("\n\n\tExcluindo o ELEMENTO %d ...\n", atual->valor);
                lista->inicio = atual->prox;
                if (lista->inicio == NULL) {
                    // A lista tinha apenas um elemento
                    lista->fim = NULL;
                }
            } else if (atual == lista->fim) {
                // Exclusão do último elemento da lista
                printf("\n\n\tExcluindo o ELEMENTO %d ...\n", atual->valor);
                lista->fim = anterior;
                lista->fim->prox = NULL;
            } else {
                // Exclusão de um elemento do meio da lista
                printf("\n\n\tExcluindo o ELEMENTO %d ...\n", atual->valor);
                anterior->prox = atual->prox;
            }
            free(atual);
            lista->total--;
            return; // Sai da função após excluir o elemento
        }
        anterior = atual;
        atual = atual->prox; // move para o próximo elemento
    }
    printf("Elemento %d não encontrado na lista.\n", valor);
}
//=================================================
int pedirOpcao(){
    int op;
    printf("\n%s\n", INICIO);
    do {
        printf("1 - Inserir na Lista\n");
        printf("2 - Exibe Lista\n");
        printf("3 - Excluir da Lista\n");
        printf("4 - Pesquisar Matricula\n");
        printf("5 - Total de Matriculas\n");
        printf("6 - Imprimir toda a Tabela Hash\n");
        printf("7 - Sair\n");
        printf("Digite a opção: ");
        scanf("%d", &op);
        printf("%s\n", CORTE);
    } while ((op < 1) || (op > 7));
    return op;
}
//=================================================
int pedirNum(int caminhoASerEscolhido){
    int num;
    if (caminhoASerEscolhido == 0){
        printf("Digite um numero para ser inserido: ");
        scanf("%d", &num);
    }else{
        printf("Digite um numero para ser excluido: ");
        scanf("%d", &num);
    }
    return num;
}
//================================================
void imprimirTabelaHash(TabelaHash *tabela) {
    printf("\n\n===| Exibição Completa da Tabela Hash |===\n\n");    
    for (int i = 0; i < tabela->tamanho; i++) {
        printf("Índice %d:\n", i);
        
        TElemento *atual = tabela->vetorListas[i].inicio;
        
        if (atual == NULL) {
            printf("  (vazio)\n");
        } else {
            while (atual != NULL) {
                printf("  Matrícula: %d\n", atual->valor);
                atual = atual->prox;
            }
        }
    }
    printf("\n========================================\n");
}
//================================================
void menuPrincipal(TabelaHash *tabelaHash) {
    int op, numInseri;
    char nomeInseri[100];  
    int repete = 0;
    do {
        op = pedirOpcao();  
        switch (op) {
            case 1:
                // Inserir na Tabela Hash
                numInseri = pedirNum(0);  
                printf("Digite o nome associado à matrícula: ");
                getchar();  // Para evitar problemas com a leitura do '\n' residual
                fgets(nomeInseri, sizeof(nomeInseri), stdin);
                nomeInseri[strcspn(nomeInseri, "\n")] = 0;  // Remover a quebra de linha
                inserirTabelaHash(tabelaHash, numInseri, nomeInseri);
                break;
            case 2:
                // Exibir a Tabela Hash
                exibeTabelaHash(tabelaHash);
                break;
            case 3:
                // Excluir da Tabela Hash
                numInseri = pedirNum(1);  
                excluirTabelaHash(tabelaHash, numInseri);
                break;
            case 4:
                // Pesquisar uma matrícula na Tabela Hash
                numInseri = pedirNum(0);  // Pedir matrícula para buscar
                pesquisarTabelaHash(tabelaHash, numInseri);
                break;
            case 5:
                // Exibir total de matrículas na Tabela Hash
                printf("O total de matrículas na tabela é: %d\n", contarTotalMatriculas(tabelaHash));
                break;
            case 6:
                // Imprimir toda a tabela hash
                imprimirTabelaHash(tabelaHash);
                break;
            case 7:
                // Sair
                repete = 1;
                break;
            default:
                printf("Opção inválida. Tente novamente.\n");
                break;
        }
    } while (repete == 0);
}

//================================================
int funcaoHash(int matricula, int tamanho) {
    return matricula % tamanho;
}
//================================================
int contarMatriculas(FILE *arquivoLista) {
    int matricula;
    int totalMatriculas = 0;
    while (fscanf(arquivoLista, "%d", &matricula) != EOF) {
        totalMatriculas++;
    }
    return totalMatriculas;
}
//================================================
int contarTotalMatriculas(TabelaHash *tabela) {
    int total = 0;
    for (int i = 0; i < tabela->tamanho; i++) {
        total += tabela->vetorListas[i].total;  // Somar o total de cada lista encadeada
    }
    return total;
}
//================================================
int ehPrimo(int num) {
    if (num <= 1) return 0; // Números menores ou iguais a 1 não são primos
    if (num == 2) return 1; // 2 é primo
    if (num % 2 == 0) return 0; // Números pares não são primos
    // Verificar divisibilidade por números ímpares até a raiz quadrada de num
    for (int i = 3; i <= num*0.5; i += 2) {
        if (num % i == 0) {
            return 0; // Não é primo
        }
    }
    return 1; // É primo
}
//================================================
int acharProximoPrimo(int num) {
    // Continuar incrementando até encontrar um número primo
    while (!ehPrimo(num)) {
        num++;
    }
    return num;
}
//================================================
void inicializarTabelaHash(TabelaHash *tabela, int tamanho) {
    tabela->tamanho = tamanho;
    tabela->vetorListas = (TLista *)malloc((size_t)tamanho * sizeof(TLista));
    // Inicializar todas as listas encadeadas em cada posição do vetor
    for (int i = 0; i < tamanho; i++) {
        construirListaDoZero(&tabela->vetorListas[i]);
    }
}
//================================================
void inicializarTabela(TabelaHash *tabelaHash, FILE *arquivoLista) {
    int totalMatriculas = contarMatriculas(arquivoLista);
    int opcaoPorcentagem = pedirOpcao3();
    int tamanhoTabela;
    
    switch (opcaoPorcentagem) {
        case 1:
            tamanhoTabela = acharProximoPrimo((int)(totalMatriculas * 1.0));
            break;
        case 2:
            tamanhoTabela = acharProximoPrimo((int)(totalMatriculas * 1.2));
            break;
        case 3:
            tamanhoTabela = acharProximoPrimo((int)(totalMatriculas * 1.5));
            break;
        default:
            printf("Opção inválida\n");
            exit(1);
    }

    inicializarTabelaHash(tabelaHash, tamanhoTabela);
}
//================================================
void lerEInserirMatrículas(TabelaHash *tabelaHash, FILE *arquivoLista) {
    rewind(arquivoLista);  // Reposicionar para o início do arquivo
    int matricula;
    char nome[100];
    while (fscanf(arquivoLista, "%d\n", &matricula) != EOF) {
        fgets(nome, sizeof(nome), arquivoLista);
        nome[strcspn(nome, "\n")] = 0;  // Remover o '\n'
        inserirTabelaHash(tabelaHash, matricula, nome);
    }
}
//================================================
void executarMenu(TabelaHash *tabelaHash) {
    menuPrincipal(tabelaHash);
}
//================================================
int pesquisarTabelaHash(TabelaHash *tabela, int matricula) {
    int indice = funcaoHash(matricula, tabela->tamanho);
    if (pesquisarMatricula2(&tabela->vetorListas[indice], matricula)) {
        printf("Matrícula %d encontrada na tabela hash.\n", matricula);
        return 1;  // Encontrado
    } else {
        printf("Erro: A matrícula %d não foi encontrada na tabela.\n", matricula);
        return 0;  // Não encontrado
    }
}
//================================================
void inserirTabelaHash(TabelaHash *tabela, int matricula, char *nome) {
    int indice = funcaoHash(matricula, tabela->tamanho);
    if (pesquisarMatricula2(&tabela->vetorListas[indice], matricula)) {
        printf("Erro: A matrícula %d já está presente na tabela.\n", matricula);
        return;
    }
    inserir(&tabela->vetorListas[indice], matricula, nome);
    printf("Matrícula %d inserida com sucesso.\n", matricula);
}

//================================================
void excluirTabelaHash(TabelaHash *tabela, int matricula) {
    int indice = funcaoHash(matricula, tabela->tamanho);
    // Verifica se a matrícula está na lista antes de tentar removê-la
    if (pesquisarMatricula2(&tabela->vetorListas[indice], matricula)) {
        excluirLista(&tabela->vetorListas[indice], matricula);
        printf("Matrícula %d removida com sucesso da tabela hash.\n", matricula);
    } else {
        printf("Erro: A matrícula %d não foi encontrada para remoção.\n", matricula);
    }
}
//================================================
void exibeTabelaHash(TabelaHash *tabela) {
    for (int i = 0; i < tabela->tamanho; i++) {
        printf("Índice %d: ", i);
        exibeLista(tabela->vetorListas[i]);
    }
}
//================================================
void liberarLista(TLista *lista) {
    TElemento *atual = lista->inicio;
    TElemento *prox;
    while (atual != NULL) {
        prox = atual->prox;  
        free(atual);         
        atual = prox;        
    }
    lista->inicio = NULL;
    lista->fim = NULL;
    lista->total = 0;
}
//================================================
void liberarTabelaHash(TabelaHash *tabela) {
    for (int i = 0; i < tabela->tamanho; i++) {
        liberarLista(&tabela->vetorListas[i]);  // Função que libera cada lista
    }
    free(tabela->vetorListas);
}
//================================================
int main() {
    FILE *arquivoLista = abrirArquivo("matricula.txt", "r");
    TabelaHash tabelaHash;
    inicializarTabela(&tabelaHash, arquivoLista);
    lerEInserirMatrículas(&tabelaHash, arquivoLista);
    fclose(arquivoLista);
    executarMenu(&tabelaHash);
    liberarTabelaHash(&tabelaHash);
    return 0;
}