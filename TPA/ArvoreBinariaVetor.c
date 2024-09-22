//Feito por: Lucas Garcia E Luis Augusto
#include "ArvoreBinariaVetor.h"

//=================================================
// Função para ler o arquivo
FILE *abrirArquivo(char *nomeArq, char *modo) {
    FILE *arq = fopen(nomeArq, modo);
    if (arq == NULL) {
        printf("ERRO ao abrir o arquivo.\n");
        exit(-1);
    }
    printf("INFO: Arquivo Aberto! Bom uso.\n");
    return arq;
}

//=================================================
// Função que calcula o tempo em segundos 
void calcularTempo(double ini, double fim) {
    double tempoDecorrido = (double)(fim - ini) / CLOCKS_PER_SEC;
    printf("Tempo de execucao: %f segundos\n", tempoDecorrido);
}
//=================================================
// Função para salvar os dados no arquivo
void salvarDadosNoArquivo(TabelaHash *tabela, FILE *arquivoLista) {
    arquivoLista = fopen("nomes_matriculas.txt", "w");
    if (arquivoLista == NULL) {
        printf("ERRO ao abrir o arquivo para gravação.\n");
        return;
    }
    int i;
    // Percorre a tabela e pega os dados dentro dela e escreve os nomes e matriculas de volta no arquivo
    for (i = 0; i < tabela->tamanho; i++) {
        if (tabela->vetor[i].flag == 1) { // Apenas elementos ocupados
            fprintf(arquivoLista, "%s\n%lld\n", tabela->vetor[i].nome, tabela->vetor[i].valor);
        }
    }

    fclose(arquivoLista);
    printf("INFO: Dados salvos com sucesso no arquivo.\n");
}
//=================================================
// Função menu para perguntar o tamanho do vetor
// int pedirOpcao3() {
//     int opcao;
//     printf("Escolha o tamanho da tabela hash em relação ao número total de matrículas:\n");
//     printf("1 - 120%% do número de matrículas\n");
//     printf("2 - 150%% do número de matrículas\n");
//     printf("3 - 180%% do número de matrículas\n");
//     printf("Digite sua opção (1, 2 ou 3): ");
//     scanf("%d", &opcao);
//     return opcao;
// }
//=================================================
// Menu de açoes para realizar com a tabela hash
long long int pedirOpcao() {
    int op;
    printf("\n--- Menu Principal ---\n");
    do {
        printf("1 - Inserir na Arvore\n");
        printf("2 - Exibir a Arvore\n");
        printf("3 - Excluir da Arvore\n");
        printf("4 - Pesquisar na Arvore\n");
        printf("5 - Total de Matriculas\n");
        printf("6 - Percorrer toda a Arvore\n");
        printf("7 - Sair\n");
        printf("Digite a opção: ");
        scanf("%d", &op);
    } while ((op < 1) || (op > 7));
    return op;
}
//=================================================
// Funçao para ler dados do usuario(teclado)
long long int pedirNum(int caminhoASerEscolhido) {
    long long int num;
    if (caminhoASerEscolhido == 0) {
        printf("Digite um numero para ser inserido: ");
        scanf("%lld", &num);
    } else {
        printf("Digite um numero para ser excluido: ");
        scanf("%lld", &num);
    }
    return num;
}

//================================================
// Função para exibir todos os elementos presentes na tabela
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
// Funçao para sustentar o menu de ações do programa
void menuPrincipal(TabelaHash *tabelaHash) {
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
// Função para contar as matriculas enquanto ainda estao no arquivo
int contarMatriculas(FILE *arquivoLista) {
    char linha[100];
    int totalMatriculas = 0;
    // Loop para ler as linhas das do arquivo e contabilidar o numero de linhas
    while (fgets(linha, sizeof(linha), arquivoLista) != NULL) {
        totalMatriculas++;
    }
    // Como cada matrícula ocupa duas linhas (nome e matrícula), dividimos por 2
    return totalMatriculas / 2;
}
//================================================
// Função para contar as matriculas quando elas ja estao na tabela
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

//================================================

//================================================

//================================================
// Função que le as matriculas do arquivo e coloca na tabela
void lerEInserirMatriculas(TabelaHash *tabelaHash, FILE *arquivoLista) {
    rewind(arquivoLista);  // Reposicionar para o início do arquivo
    long long int matricula;
    char nome[100];

    // Loop que passa pelo arquivo, ele interrompe o loop quando o conteudo da leitura for NULL
    while (fgets(nome, sizeof(nome), arquivoLista) != NULL) {  // Ler o nome
        nome[strcspn(nome, "\n")] = 0;  // Remover o '\n' do nome
        if (fscanf(arquivoLista, "%lld\n", &matricula) != EOF) {  // Ler a matrícula como long long int
            inserirTabelaHash(tabelaHash, matricula, nome);  // Inserir na tabela hash
        }
    }
}
//================================================

//================================================

//================================================

//================================================

//================================================
int main() {
    FILE *arquivoLista = abrirArquivo("nomes_matriculas.txt", "r");
    int totalMatriculas = contarMatriculas(arquivoLista);
    printf("Total de matrículas no arquivo: %d\n", totalMatriculas);
    rewind(arquivoLista); // organiza o cursor do arquivo

    //Inicializar a arvore;

    double inicio = clock();
    //lerEInserirMatriculas(&tabelaHash, arquivoLista);
    double fim = clock();
    calcularTempo(inicio, fim);

    //menuPrincipal(&tabelaHash);
    //salvarDadosNoArquivo(&tabelaHash, arquivoLista);


    return 0;
}
