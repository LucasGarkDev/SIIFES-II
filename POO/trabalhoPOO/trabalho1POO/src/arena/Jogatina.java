/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arena;

import geradores.GeradorDeGuerreiros;
import arena.Arena;

// Importar todas as classes do pacote guerreiros.gregos
import guerreiros.gregos.*;

// Importar todas as classes do pacote guerreiros.nordicos
import guerreiros.nordicos.*;

// Importar todas as classes do pacote guerreiros.atlantes
import guerreiros.atlantes.*;

// Importar todas as classes do pacote guerreiros.egipcios
import guerreiros.egipcios.*;

import geradores.*;

/**
 *
 * @author lucas
 */
public class Jogatina {
    public static void main(String[] args) {
        // Criar uma instância da arena
        Arena arena = new Arena();

        // Criar uma instância do GeradorDeGuerreiros para gerar arquivos de guerreiros
        GeradorDeGuerreiros gerador = new GeradorDeGuerreiros();

        // Gerar guerreiros aleatórios e salvar nos arquivos para os dois lados
        gerador.gerarESalvarGuerreirosParaArena(1); // Gregos e Nórdicos (lado 1)
        gerador.gerarESalvarGuerreirosParaArena(2); // Atlantes e Egípcios (lado 2)

        // Criar uma instância do LeitorDeGuerreiros para ler os guerreiros dos arquivos e adicionar à arena
        LeitorDeGuerreiros leitor = new LeitorDeGuerreiros(arena);
        leitor.montarArena(); // Lê todos os arquivos e monta as filas de cada lado na arena

        // Exibir a soma dos pesos dos guerreiros de cada lado
        System.out.println("Calculando a soma dos pesos dos guerreiros...");
        arena.exibirSomaDosPesosDosGuerreiros();

        // Exibir o guerreiro mais velho de cada lado
        System.out.println("Identificando o guerreiro mais velho de cada lado...");
        arena.exibirGuerreiroMaisVelho(1); // Lado 1 (Gregos e Nórdicos)
        arena.exibirGuerreiroMaisVelho(2); // Lado 2 (Atlantes e Egípcios)

        // Exibir os guerreiros da arena para verificar a configuração
        System.out.println("Exibindo guerreiros da arena:");
        arena.exibirGuerreirosDeCadaLado();

        // Opcional: iniciar o combate
        System.out.println("Iniciando o combate...");
        arena.iniciarCombate();
    }
}
