#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TAMANHO 24

typedef char string[20];
typedef int individuo[TAMANHO];

typedef struct grafo{
	int caminhos[TAMANHO][TAMANHO];
	string vertices[TAMANHO];
    int estimativas[TAMANHO];
    int precedente[TAMANHO];
    int finalizado[TAMANHO];
}TGrafo;

TGrafo dado;

void inicializa(TGrafo *d);
void exibeCidades(TGrafo d);
void exibeSaidas(TGrafo d);
int menu();
void exibeParcial(TGrafo d, int lin);
void iniciaBuscaDeCaminho(TGrafo *d);
void iniciaBusca(TGrafo **d, int origem, int destino);
int encontreMenorEstimativa(TGrafo *d);
void exibe(TGrafo *d);
void geraCaminho(TGrafo *d, int origem, int destino);

//========================================================================
int main(){
	int opcao;
	
	inicializa(&dado);
	
	do{
		opcao = menu();
		
	    switch(opcao){
	    	case 1: exibeCidades(dado); break;
	    	case 2: exibeSaidas(dado); break;
	    	case 3: iniciaBuscaDeCaminho(&dado); break;
	    }
	} while (opcao != 0);
}
//=========================================================================
void inicializa(TGrafo *d){
	int l,c;
	
	strcpy(d->vertices[0],"VITORIA");
	strcpy(d->vertices[1],"DOMINGOS MARTINS");
	strcpy(d->vertices[2],"MARECHAL FLORIANO");
	strcpy(d->vertices[3],"PEDRA AZUL");
	strcpy(d->vertices[4],"VENDA NOVA");
	strcpy(d->vertices[5],"VILA VELHA");
	strcpy(d->vertices[6],"CARIACICA");
	strcpy(d->vertices[7],"VIANA");
	strcpy(d->vertices[8],"GUARAPARI");
	strcpy(d->vertices[9],"SERRA");
	strcpy(d->vertices[10],"FUNDAO");
	strcpy(d->vertices[11],"IBIRACU");
	strcpy(d->vertices[12],"JOAO NEIVA");
	strcpy(d->vertices[13],"ARACRUZ");
	strcpy(d->vertices[14],"LINHARES");
	strcpy(d->vertices[15],"JAGUARE");
	strcpy(d->vertices[16],"SAO MATEUS");
	strcpy(d->vertices[17],"CONCEICAO DA BARRA");
	strcpy(d->vertices[18],"MARILANDIA");
	strcpy(d->vertices[19],"COLATINA");
	strcpy(d->vertices[20],"BAIXO GUANDU");
	strcpy(d->vertices[21],"SAO ROQUE");
	strcpy(d->vertices[22],"SANTA TERESA");
	strcpy(d->vertices[23],"ANCHIETA");

	for(l= 0; l < TAMANHO; l++){
	   for(c=0; c < TAMANHO; c++){
	    	d->caminhos[l][c] = 0;
	   }//for
	   d->estimativas[l] = -1;
	   d->finalizado[l] = 0;
	   d->precedente[l] - -1;
    }//for
        
    d->caminhos[0][5] = 11; //Vitoria X Vila Velha (dist?ncia 11 km)
    d->caminhos[0][6] = 15; //Vitoria X Cariacica (dist?ncia 15 km)
    d->caminhos[0][9] = 30; //Vitoria X Serra (dist?ncia 30 km)
    
    d->caminhos[5][6] = 27; //Vila Velha X Cariacica 
    d->caminhos[5][7] = 19; //Vila Velha X Viana 
    d->caminhos[5][8] = 52; //Vila Velha X Guarapari
    
    d->caminhos[6][0] = 15; // Cariacica X Vitoria
	d->caminhos[6][5] = 27; // Cariacica X Vila Velha
    d->caminhos[6][7] = 15; // Cariacica X Viana
    
    d->caminhos[7][1] = 37; // Viana X Domingos Martins
    d->caminhos[7][5] = 37; // Viana X Vila Velha
    d->caminhos[7][6] = 15; // Viana X Cariacica

    d->caminhos[8][23] = 26;// Guarapari a Anchieta
    d->caminhos[8][5] = 52;// Guarapari a Anchieta
    
    d->caminhos[1][2] = 10; // Domingos Martins X Marechal Floriano
    d->caminhos[1][7] = 37; // Domingos Martins X Viana
    
    d->caminhos[2][1] = 10; // Marechal Floriano X Domingos Martins
    d->caminhos[2][3] = 45; // Marechal Floriano X Pedra Azul
    
    d->caminhos[3][2] = 45; // Pedra Azul X Marechal Floriano
    d->caminhos[3][4] = 15; // Pedra Azul X Venda Nova
    
    d->caminhos[4][3] = 15; // Venda Nova X Pedra Azul
    
    d->caminhos[9][0] = 30; // Serra X Vit?ria
    d->caminhos[9][10] = 29; // Serra X Fund?o
	d->caminhos[9][13] = 59; // Serra X Aracruz 
	
	d->caminhos[10][9] = 29; //Fund?o X Serra
	d->caminhos[10][11] = 17; //Fund?o X Ibrira?u
	d->caminhos[10][13] = 30; //Fund?o X Aracruz
	d->caminhos[10][22] = 28; //Fund?o X Santa Teresa
	
	
	d->caminhos[11][10] = 17; //Ibira?u X Fund?o
	d->caminhos[11][12] = 10; //Ibira?u X Jo?o Neiva
	d->caminhos[11][13] = 13; //Ibira?u X Aracruz
	
	d->caminhos[13][9] = 59; // Aracruz X Serra
	d->caminhos[13][10] = 13; // Aracruz X Fund?o
	d->caminhos[13][11] = 13; // Aracruz X Ibira?u
	
	d->caminhos[12][11] = 10;// Jo?o Neiva X Ibira?u
	d->caminhos[12][13] = 23;// Jo?o Neiva X Aracruz
	d->caminhos[12][14] = 57;// Jo?o Neiva X Linhares
	d->caminhos[12][19] = 23;// Jo?o Neiva X Colatina
	
	d->caminhos[14][12] = 57;// Linhares X Jaguar?
	d->caminhos[14][15] = 68;// Linhares X Jaguar?
	d->caminhos[14][18] = 71;// Linhares X Marilandia
	
	d->caminhos[15][14] = 68;// Jaguar? X Linhares
	d->caminhos[15][16] = 40;// Jaguar? X S?o Mateus
	
	d->caminhos[16][17] = 37;// S?o Mateus X Concei??o da Barra
	d->caminhos[16][15] = 40;// S?o Mateus X Jaguar?
	
	d->caminhos[18][14] = 71;// Maril?ndia X Linhares
	d->caminhos[18][19] = 27;// Maril?ndia X Colatina
	
	d->caminhos[19][18] = 27;// Colatina X Maril?ndia
	d->caminhos[19][20] = 50;// Colatina X Baixo GuANDU
	d->caminhos[19][21] = 30;// Colatina X S?o Roque
	d->caminhos[19][12] = 23;// Colatina X Jo?o Neiva
	
	d->caminhos[21][19] = 30;// S?o Roque X Colatina
	d->caminhos[21][22] = 32;// S?o Roque X Santa Teresa
	
	d->caminhos[20][19] = 50;// Baixo Gaundu X Colatina
	
	d->caminhos[23][8] = 26;// Anchieta X Guarapari
	
}
//=========================================================================
void exibeCidades(TGrafo d){
	int cont;
	printf("\n\n\t=====| CIDADES |=====\n\n");
	
	for(cont=0; cont < TAMANHO; cont++){
		printf("\n(%d) - %s.",cont,d.vertices[cont]);
	}//for
	printf("\n\n");
	system("PAUSE");
}
//========================================================================
void exibeSaidas(TGrafo d){
	int i, c;
	printf("\n\n\t\t=====| SAIDAS de CIDADES |=====\n\n");
	
	for(i = 0; i < TAMANHO; i++){
	   printf("\n\n(%d) - %s:", i, d.vertices[i]);
	   
	   for(int c=0; c < TAMANHO; c++){
	      if(d.caminhos[i][c] != 0){
	      	 printf("\n\t - %s [%d].",d.vertices[c], d.caminhos[i][c]);
		  }	//if
	   }//for	
	}//for
	
	system("PAUSE");
	
}
//========================================================================
int menu(){
	int op;
	do{
		system("CLS");
    	printf("\n\n\t\t=====| MENU Djikstra |=====\n\n");
	    printf("\t0 - Sair (Encerrar Aplicacao).\n\n");
	    printf("\t1 - Listar Cidades.\n");
	    printf("\t2 - Listar Saidas de Cidades.\n");
	    printf("\t3 - buscar melhor rota.\n\n");
	    printf("\t\tInforme OPCAO: ");		
	    scanf("%d",&op);
	}while((op < 0) || (op > 3));

	return op;
}
//========================================================================
void exibeParcial(TGrafo d, int lin){
    int col;
	printf("\n\n\t\t=====| RELATORIO DA SITUACAO |=====\n\n");
    
    printf("\tLOCAL: %d - %s ...:\n\n",lin, d.vertices[lin]);
    printf("\t(Estimativas: %d)  ",d.estimativas[lin]);
    if(d.precedente[lin] >= 0) printf("(Precedente: %d - %s)  ", d.precedente[lin],d.vertices[lin]);
    printf("(Finalizado: %d)\n",d.finalizado[lin]);
	
	printf("\n\n\tSAIDAS: \n");
	    
    for(col = 0; col < TAMANHO; col++){
    	if(d.caminhos[lin][col] != 0){
    		printf("\t\t%s :  %d km\n", d.vertices[col], d.caminhos[lin][col]);
    		printf("(Estimativas: %d)  ",d.estimativas[col]);
    		if(d.precedente[col] >= 0) printf("(Precedente: %d - %s)  ", d.precedente[col],d.vertices[col]);
    		printf("(Finalizado: %d)\n",d.finalizado[lin]);
    	}//if
    }//for
    printf("\n");
    system("PAUSE");
    
}
//========================================================================
void iniciaBuscaDeCaminho(TGrafo *d){
	int origem, destino;
	
	printf("\n\n\t\t=====| PESQUISA DO MELHOR CAMINHO |======\n\n");
	printf("\n\tInforme LOCAL de ORIGEM: ");
	scanf("%d",&origem);
	printf("\n\tInforme LOCAL de DESTINO: ");
	scanf("%d",&destino);
	printf("\n\n");
	
	d->estimativas[origem] = 0;
	d->precedente[origem] = -1;
	d->finalizado[origem] = 1;
	
	iniciaBusca(&d, origem, destino);
}
//=========================================================================
void iniciaBusca(TGrafo **d, int origem, int destino){
   int ultimo = origem;
   int col;
   int menorVert;
   
   while(ultimo != destino){
      printf("\nVisitando vertice %s com ESTIMATIVA %d kms.\n",(*d)->vertices[ultimo], (*d)->estimativas[ultimo]);
      
      for(col = 0; col < TAMANHO; col++){
   		if((*d)->finalizado[col] == 0) {
   			if((*d)->caminhos[ultimo][col] > 0){
   				if(origem == (*d)->precedente[col]){
   					(*d)->estimativas[col] = (*d)->caminhos[ultimo][col];
   				} else {
   					(*d)->estimativas[col] = (*d)->caminhos[ultimo][col] + (*d)->estimativas[ultimo];
   				}//if  ... else
   				(*d)->precedente[col] = ultimo;
   			}//if
   		}//if   		
      }//for
      
      menorVert = encontreMenorEstimativa(*d);
      
      ultimo = menorVert;
      (*d)->finalizado[ultimo] = 1;
      
      exibe(*d);
   }//while
   geraCaminho(*d, origem, destino);
}
//=========================================================================
int encontreMenorEstimativa(TGrafo *d){
	int menorVert = -1, menor, col, flag = 0;
	
	for(col  = 0; col < TAMANHO; col++)	{
		if (d->finalizado[col] == 0){
			if(d->estimativas[col] > -1){
				if(( flag == 0) || (d->estimativas[col] < menor) ){
					menor = d->estimativas[col];
					menorVert = col;
					flag = 1;
				}//if
			}//if
		}//if
	}//for
	return menorVert;
}
//=========================================================================
void geraCaminho(TGrafo *d, int origem, int destino){
	int atual = destino;
	
	printf("\n\n\t\t======| RESULTADO FINAL |======\n\n");
	printf("\t\t(CAMINHO ENCONTRADO)\n\n");
	
	do{
		printf("\t%s", d->vertices[atual]);
		if(atual == destino){
			printf("   (%d  kms)\n", d->estimativas[atual]);
		} else {
			printf("\n");
		}
		atual = d->precedente[atual];
	}while(atual > -1);
	printf("\n\n\n");
	system("PAUSE");
}
//=========================================================================
void exibe(TGrafo *d){
	int atual = 0;
	
	printf("\n\n\t\t=====| EXIBE CONDICAO |======\n\n");
	
	while(atual < TAMANHO){
		printf("\t(%d) - %s\n", atual, d->vertices[atual]);
		printf("\tESTIMATIVA: %d   PRECEDENTE: %d - %s\n",d->estimativas[atual], d->precedente[atual],d->vertices[atual]);
		printf("\tFINALIZADO: %d\n",d->finalizado[atual]);
		printf("+--------------------------------------------------------------------------------------------------+\n");
		atual++;
	}
	printf("\n\n");
	system("PAUSE");
}
//===========================================================================
