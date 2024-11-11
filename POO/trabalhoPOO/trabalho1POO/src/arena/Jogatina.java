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
        Arena arena = new Arena();

        GeradorDeGuerreiros gerador = new GeradorDeGuerreiros();

        gerador.gerarESalvarGuerreirosParaArena(1); // Gregos e Nórdicos (lado 1)
        gerador.gerarESalvarGuerreirosParaArena(2); // Atlantes e Egípcios (lado 2)


        LeitorDeGuerreiros leitor = new LeitorDeGuerreiros(arena);
        leitor.montarArena(); // Lê todos os arquivos e monta as filas de cada lado na arena

        System.out.println("====================================================================");
        System.out.println("Calculando a soma dos pesos dos guerreiros...");
        arena.exibirSomaDosPesosDosGuerreiros();

 
        System.out.println("Identificando o guerreiro mais velho de cada lado...");
        arena.exibirGuerreiroMaisVelho(1); // Lado 1 (Gregos e Nórdicos)
        arena.exibirGuerreiroMaisVelho(2); // Lado 2 (Atlantes e Egípcios)

        System.out.println("====================================================================");
        System.out.println("Exibindo guerreiros da arena:");
        arena.exibirGuerreirosDeCadaLado();


        System.out.println("Iniciando o combate...");
        arena.iniciarCombate();
    }
}
