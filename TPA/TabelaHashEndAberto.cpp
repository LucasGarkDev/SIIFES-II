//Feito por: Lucas Garcia E Luis Augusto
#include "TabelaHashEndAberto.h"

// Funções hash e reHash necessárias
long long int hash(long long int k, int tamVetor) {
    return k % tamVetor;
}

long long int hash2(long long int k, int tamVetor) {
    return 1 + (k % (tamVetor - 1));
}

long long int reHash(long long int i, int k, int tamVetor) {
    return (i + hash2(k, tamVetor)) % tamVetor;
}

//=================================================
FILE * abrirArquivo(char * nomeArq, char * modo) {
    FILE * arq;
    arq = fopen(nomeArq, modo);
    if (arq == NULL) {
        printf("ERRO ao abrir o arquivo.\n");
        exit(-1);
    }
    printf("INFO: Arquivo Aberto! Bom uso.\n");
    return arq;
}

//=================================================
void calcularTempo(double ini,double fim){
    double tempo_decorrido = (double)(fim - ini) / CLOCKS_PER_SEC;

    printf("Tempo de execucao: %f segundos\n", tempo_decorrido);
}
//=================================================
float aleatorio(int n){
    return (rand() % (n+1));
}
//=================================================
void lerArquivo(TLista *lista, FILE *arquivoLista) {
    char nome[100];
    char matriculaStr[20];  
    long long int matricula;

    while (fgets(nome, sizeof(nome), arquivoLista) != NULL) {
        nome[strcspn(nome, "\n")] = 0;  // Remover a quebra de linha do nome
        if (fgets(matriculaStr, sizeof(matriculaStr), arquivoLista) != NULL) {
            matricula = strtol(matriculaStr, NULL, 10);  // Converter string para long long int
            inserir(lista, matricula, nome);
        }
    }
}
//=================================================
void inicializa(TLista *lista, FILE *arquivoLista) {
    construirListaDoZero(lista);
    fseek(arquivoLista, 0, SEEK_END);
    long tamanho = ftell(arquivoLista);
    if (tamanho != 0) {
        fseek(arquivoLista, 0, SEEK_SET);  // Retorna ao início do arquivo
        lerArquivo(lista, arquivoLista);
    }
}

//=================================================
void salvarDadosNoArquivo(TabelaHash *tabela, FILE *arquivoLista) {
    arquivoLista = fopen("nomes_matriculas.txt", "w");
    if (arquivoLista == NULL) {
        printf("ERRO ao abrir o arquivo para gravação.\n");
        return;
    }

    for (int i = 0; i < tabela->tamanho; i++) {
        if (tabela->vetor[i].flag == 1) { // Apenas elementos ocupados
            fprintf(arquivoLista, "%s\n%lld\n", tabela->vetor[i].nome, tabela->vetor[i].valor);
        }
    }

    fclose(arquivoLista);
    printf("INFO: Dados salvos com sucesso no arquivo.\n");
}

//=================================================
int pesquisarMatricula2(TLista *lista, long long int matriculaBusca) {
    TElemento *atual = lista->inicio;
    while (atual != NULL) {
        if (atual->valor == matriculaBusca) {
            printf("Matrícula %lld encontrada. Nome: %s\n", matriculaBusca, atual->nome);
            return 1;  // Encontrado
        }
        atual = atual->prox;
    }
    printf("Matrícula %lld não encontrada na lista.\n", matriculaBusca);
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
void inserir(TLista *lista, long long int valor, char *nome) {
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
    printf("1 - 120%% do número de matrículas\n");
    printf("2 - 150%% do número de matrículas\n");
    printf("3 - 180%% do número de matrículas\n");
    printf("Digite sua opção (1, 2 ou 3): ");
    scanf("%d", &opcao);
    return opcao;
    
}
//=================================================

//=================================================

//=================================================
long long int pedirOpcao(){
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
long long int pedirNum(int caminhoASerEscolhido) {
    long long int num;
    if (caminhoASerEscolhido == 0){
        printf("Digite um numero para ser inserido: ");
        scanf("%lld", &num);  // Usar %lld para long long int
    }else{
        printf("Digite um numero para ser excluido: ");
        scanf("%lld", &num);  // Usar %lld para long long int
    }
    return num;
}

//================================================
void imprimirTabelaHash(TabelaHash *tabela) {
    printf("\n\n===| Exibição Completa da Tabela Hash |===\n\n");    
    for (int i = 0; i < tabela->tamanho; i++) {
        if (tabela->vetor[i].flag == 1) { // Apenas elementos ocupados
            printf("Índice %d: Matrícula: %lld, Nome: %s\n", i, tabela->vetor[i].valor, tabela->vetor[i].nome);
        } else {
            printf("Índice %d: (vazio)\n", i);
        }
    }
    printf("\n========================================\n");
}


//================================================
void menuPrincipal(TabelaHash *tabelaHash, int funcaoHashEscolhida) {
    long long int op;
    long long int numInseri;
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
                inserirTabelaHash(tabelaHash, numInseri, nomeInseri, funcaoHashEscolhida);
                break;
            case 2:
                // Exibir a Tabela Hash
                exibeTabelaHash(tabelaHash);
                break;
            case 3:
                // Excluir da Tabela Hash
                numInseri = pedirNum(1);
                excluirTabelaHash(tabelaHash, numInseri, funcaoHashEscolhida);
                break;
            case 4:
                // Pesquisar uma matrícula na Tabela Hash
                numInseri = pedirNum(0);
                pesquisarTabelaHash(tabelaHash, numInseri, funcaoHashEscolhida);
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
int funcaoHashMultiplicacao(long long int matricula, int tamanho) {
    double A = 0.6180339887 * 0.5;  // Usar double para maior precisão
    double hashValue = matricula * A;
    int indice = (int)(tamanho * (hashValue - (long long int)hashValue));
    printf("Depuração - Valor de A: %f, Matricula: %lld, Índice Calculado: %d\n", A, matricula, indice);
    // Garantir que o índice seja positivo e dentro dos limites da tabela
    if (indice < 0) {
        indice = -indice;
    }
    return indice % tamanho;  
}
//================================================
int funcaoHashRestoDivisao(long long int matricula, int tamanho) {
    return matricula % tamanho;
}
//================================================
int escolherFuncaoHash() {
    int opcao;
    printf("Escolha a função hash:\n");
    printf("1 - Resto da Divisão\n");
    printf("2 - Método da Multiplicação\n");
    printf("Digite sua opção: ");
    scanf("%d", &opcao);
    return opcao;
}
//================================================
int contarMatriculas(FILE *arquivoLista) {
    char linha[100];
    int totalMatriculas = 0;
    while (fgets(linha, sizeof(linha), arquivoLista) != NULL) {
        totalMatriculas++;
    }
    // Como cada matrícula ocupa duas linhas (nome e matrícula), dividimos por 2
    return totalMatriculas / 2;
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
    tabela->vetor = (TElemento *)malloc(tamanho * sizeof(TElemento));
    for (int i = 0; i < tamanho; i++) {
        tabela->vetor[i].valor = 0;  // Valor zero indica posição vazia
        tabela->vetor[i].flag = 0;   // 0 para vazio, 1 para ocupado, 2 para removido
        strcpy(tabela->vetor[i].nome, "");
    }
}

//================================================
void inicializarTabela(TabelaHash *tabelaHash, FILE *arquivoLista) {
    int totalMatriculas = contarMatriculas(arquivoLista);
    int opcaoPorcentagem = pedirOpcao3();
    int tamanhoTabela;
    
    switch (opcaoPorcentagem) {
        case 1:
            tamanhoTabela = acharProximoPrimo((int)(totalMatriculas * 1.2));
            break;
        case 2:
            tamanhoTabela = acharProximoPrimo((int)(totalMatriculas * 1.5));
            break;
        case 3:
            tamanhoTabela = acharProximoPrimo((int)(totalMatriculas * 1.8));
            break;
        default:
            printf("Opção inválida\n");
            exit(1);
    }

    inicializarTabelaHash(tabelaHash, tamanhoTabela);
}
//================================================
void lerEInserirMatriculas(TabelaHash *tabelaHash, FILE *arquivoLista) {
    rewind(arquivoLista);  // Reposicionar para o início do arquivo
    long long int matricula;
    char nome[100];

    while (fgets(nome, sizeof(nome), arquivoLista) != NULL) {  // Ler o nome
        nome[strcspn(nome, "\n")] = 0;  // Remover o '\n' do nome
        if (fscanf(arquivoLista, "%lld\n", &matricula) != EOF) {  // Ler a matrícula como long long int
            inserirTabelaHash(tabelaHash, matricula, nome);  // Inserir na tabela hash
        }
    }
}
//=================================================
int pedirOpcao3() {
    int opcao;
    printf("Escolha o tamanho da tabela hash em relação ao número total de matrículas:\n");
    printf("1 - 120%% do número de matrículas\n");
    printf("2 - 150%% do número de matrículas\n");
    printf("3 - 180%% do número de matrículas\n");
    printf("Digite sua opção (1, 2 ou 3): ");
    scanf("%d", &opcao);
    return opcao;
}

//=================================================
long long int pedirOpcao() {
    int op;
    printf("\n%s\n", INICIO);
    do {
        printf("1 - Inserir na Tabela Hash\n");
        printf("2 - Exibir a Tabela Hash\n");
        printf("3 - Excluir da Tabela Hash\n");
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
long long int pedirNum(int caminhoASerEscolhido) {
    long long int num;
    if (caminhoASerEscolhido == 0) {
        printf("Digite um numero para ser inserido: ");
        scanf("%lld", &num);  // Usar %lld para long long int
    } else {
        printf("Digite um numero para ser excluido: ");
        scanf("%lld", &num);  // Usar %lld para long long int
    }
    return num;
}

//================================================
void imprimirTabelaHash(TabelaHash *tabela) {
    printf("\n\n===| Exibição Completa da Tabela Hash |===\n\n");    
    for (int i = 0; i < tabela->tamanho; i++) {
        if (tabela->vetor[i].flag == 1) { // Apenas elementos ocupados
            printf("Índice %d: Matrícula: %lld, Nome: %s\n", i, tabela->vetor[i].valor, tabela->vetor[i].nome);
        } else {
            printf("Índice %d: (vazio)\n", i);
        }
    }
    printf("\n========================================\n");
}

//================================================
void menuPrincipal(TabelaHash *tabelaHash, int funcaoHashEscolhida) {
    long long int op;
    long long int numInseri;
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
                imprimirTabelaHash(tabelaHash);
                break;
            case 3:
                // Excluir da Tabela Hash
                numInseri = pedirNum(1);
                excluirTabelaHash(tabelaHash, numInseri);
                break;
            case 4:
                // Pesquisar uma matrícula na Tabela Hash
                numInseri = pedirNum(0);
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
int contarMatriculas(FILE *arquivoLista) {
    char linha[100];
    int totalMatriculas = 0;
    while (fgets(linha, sizeof(linha), arquivoLista) != NULL) {
        totalMatriculas++;
    }
    // Como cada matrícula ocupa duas linhas (nome e matrícula), dividimos por 2
    return totalMatriculas / 2;
}

//================================================
int contarTotalMatriculas(TabelaHash *tabela) {
    int total = 0;
    for (int i = 0; i < tabela->tamanho; i++) {
        if (tabela->vetor[i].flag == 1) {
            total++;
        }
    }
    return total;
}

//================================================
int ehPrimo(int num) {
    if (num <= 1) return 0; // Números menores ou iguais a 1 não são primos
    if (num == 2) return 1; // 2 é primo
    if (num % 2 == 0) return 0; // Números pares não são primos
    // Verificar divisibilidade por números ímpares até a raiz quadrada de num
    for (int i = 3; i <= sqrt(num); i += 2) {
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
    tabela->vetor = (TElemento *)malloc(tamanho * sizeof(TElemento));
    for (int i = 0; i < tamanho; i++) {
        tabela->vetor[i].valor = 0;  // Valor zero indica posição vazia
        tabela->vetor[i].flag = 0;   // 0 para vazio, 1 para ocupado, 2 para removido
        strcpy(tabela->vetor[i].nome, "");
    }
}

//================================================
void inicializarTabela(TabelaHash *tabelaHash, FILE *arquivoLista) {
    int totalMatriculas = contarMatriculas(arquivoLista);
    int opcaoPorcentagem = pedirOpcao3();
    int tamanhoTabela;
    
    switch (opcaoPorcentagem) {
        case 1:
            tamanhoTabela = acharProximoPrimo((int)(totalMatriculas * 1.2));
            break;
        case 2:
            tamanhoTabela = acharProximoPrimo((int)(totalMatriculas * 1.5));
            break;
        case 3:
            tamanhoTabela = acharProximoPrimo((int)(totalMatriculas * 1.8));
            break;
        default:
            printf("Opção inválida\n");
            exit(1);
    }

    inicializarTabelaHash(tabelaHash, tamanhoTabela);
}

//================================================
void lerEInserirMatriculas(TabelaHash *tabelaHash, FILE *arquivoLista) {
    rewind(arquivoLista);  // Reposicionar para o início do arquivo
    long long int matricula;
    char nome[100];

    while (fgets(nome, sizeof(nome), arquivoLista) != NULL) {  // Ler o nome
        nome[strcspn(nome, "\n")] = 0;  // Remover o '\n' do nome
        if (fscanf(arquivoLista, "%lld\n", &matricula) != EOF) {  // Ler a matrícula como long long int
            inserirTabelaHash(tabelaHash, matricula, nome);  // Inserir na tabela hash
        }
    }
}

//================================================
void executarMenu(TabelaHash *tabelaHash, int funcaoHashEscolhida) {
    menuPrincipal(tabelaHash, funcaoHashEscolhida);
}

//================================================
int pesquisarTabelaHash(TabelaHash *tabela, long long int matricula) {
    int i = hash(matricula, tabela->tamanho);
    int tentativas = 0;

    while (tabela->vetor[i].flag != 0 && tentativas < tabela->tamanho) {
        if (tabela->vetor[i].valor == matricula && tabela->vetor[i].flag == 1) {
            printf("Matrícula %lld encontrada. Nome: %s\n", matricula, tabela->vetor[i].nome);
            return 1; // Encontrado
        }
        i = reHash(i, matricula, tabela->tamanho);
        tentativas++;
    }

    printf("Matrícula %lld não encontrada na tabela.\n", matricula);
    return 0; // Não encontrado
}

//================================================
void inserirTabelaHash(TabelaHash *tabela, long long int matricula, char *nome) {
    int i = hash(matricula, tabela->tamanho);
    int tentativas = 0;

    while (tabela->vetor[i].flag == 1 && tentativas < tabela->tamanho) {
        if (tabela->vetor[i].valor == matricula) {
            printf("Erro: A matrícula %lld já está presente na tabela.\n", matricula);
            return;
        }
        i = reHash(i, matricula, tabela->tamanho);
        tentativas++;
    }

    if (tentativas < tabela->tamanho) {
        tabela->vetor[i].valor = matricula;
        strcpy(tabela->vetor[i].nome, nome);
        tabela->vetor[i].flag = 1; // Marca como ocupado
        printf("Matrícula %lld inserida com sucesso.\n", matricula);
    } else {
        printf("Erro: Tabela cheia, não foi possível inserir.\n");
    }
}

//================================================
void excluirTabelaHash(TabelaHash *tabela, long long int matricula) {
    int i = hash(matricula, tabela->tamanho);
    int tentativas = 0;

    while (tabela->vetor[i].flag != 0 && tentativas < tabela->tamanho) {
        if (tabela->vetor[i].valor == matricula && tabela->vetor[i].flag == 1) {
            tabela->vetor[i].flag = 2; // Marca como removido
            printf("Matrícula %lld removida com sucesso.\n", matricula);
            return;
        }
        i = reHash(i, matricula, tabela->tamanho);
        tentativas++;
    }

    printf("Erro: A matrícula %lld não foi encontrada para remoção.\n", matricula);
}

//================================================
void liberarTabelaHash(TabelaHash *tabela) {
    free(tabela->vetor);
}

//================================================
void executarMenu(TabelaHash *tabelaHash, int funcaoHashEscolhida) {
    menuPrincipal(tabelaHash, funcaoHashEscolhida);
}
//================================================
int pesquisarTabelaHash(TabelaHash *tabela, long long int matricula) {
    int i = hash(matricula, tabela->tamanho);
    int tentativas = 0;

    while (tabela->vetor[i].flag != 0 && tentativas < tabela->tamanho) {
        if (tabela->vetor[i].valor == matricula && tabela->vetor[i].flag == 1) {
            printf("Matrícula %lld encontrada. Nome: %s\n", matricula, tabela->vetor[i].nome);
            return 1; // Encontrado
        }
        i = reHash(i, matricula, tabela->tamanho);
        tentativas++;
    }

    printf("Matrícula %lld não encontrada na tabela.\n", matricula);
    return 0; // Não encontrado
}


//================================================
void inserirTabelaHash(TabelaHash *tabela, long long int matricula, char *nome) {
    int i = hash(matricula, tabela->tamanho);
    int tentativas = 0;

    while (tabela->vetor[i].flag == 1 && tentativas < tabela->tamanho) {
        if (tabela->vetor[i].valor == matricula) {
            printf("Erro: A matrícula %lld já está presente na tabela.\n", matricula);
            return;
        }
        i = reHash(i, matricula, tabela->tamanho);
        tentativas++;
    }

    if (tentativas < tabela->tamanho) {
        tabela->vetor[i].valor = matricula;
        strcpy(tabela->vetor[i].nome, nome);
        tabela->vetor[i].flag = 1; // Marca como ocupado
        printf("Matrícula %lld inserida com sucesso.\n", matricula);
    } else {
        printf("Erro: Tabela cheia, não foi possível inserir.\n");
    }
}


//================================================
void excluirTabelaHash(TabelaHash *tabela, long long int matricula) {
    int i = hash(matricula, tabela->tamanho);
    int tentativas = 0;

    while (tabela->vetor[i].flag != 0 && tentativas < tabela->tamanho) {
        if (tabela->vetor[i].valor == matricula && tabela->vetor[i].flag == 1) {
            tabela->vetor[i].flag = 2; // Marca como removido
            printf("Matrícula %lld removida com sucesso.\n", matricula);
            return;
        }
        i = reHash(i, matricula, tabela->tamanho);
        tentativas++;
    }

    printf("Erro: A matrícula %lld não foi encontrada para remoção.\n", matricula);
}

//================================================

//================================================

//================================================
void liberarTabelaHash(TabelaHash *tabela) {
    for (int i = 0; i < tabela->tamanho; i++) {
        liberarLista(&tabela->vetorListas[i]); 
    }
    free(tabela->vetorListas);
}
//================================================
int main() {
    FILE *arquivoLista = abrirArquivo("nomes_matriculas.txt", "r");
    // Contar o número de matrículas no arquivo
    int totalMatriculas = contarMatriculas(arquivoLista);
    printf("Total de matrículas no arquivo: %d\n", totalMatriculas);
    // Reposicionar o ponteiro para o início do arquivo para leitura
    rewind(arquivoLista);

    TabelaHash tabelaHash;
    inicializarTabela(&tabelaHash, arquivoLista);

    int funcaoHashEscolhida = escolherFuncaoHash();
    printf("Função Hash escolhida: %d\n", funcaoHashEscolhida);  // Depuração

    processTime inicio = clock();
    lerEInserirMatriculas(&tabelaHash, arquivoLista, funcaoHashEscolhida);

    processTime fim = clock();
    calcularTempo(inicio,fim);
    executarMenu(&tabelaHash, funcaoHashEscolhida);

    salvarDadosNoArquivo(&tabelaHash, arquivoLista);

    liberarTabelaHash(&tabelaHash);
    
    return 0;
}