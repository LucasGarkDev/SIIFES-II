/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arena;

import geradores.GeradorDeGuerreiros;

/**
 *
 * @author lucas
 */
public class Jogatina {
    public static void main(String[] args) {
        Arena arena = new Arena();

//        GeradorDeGuerreiros gerador = new GeradorDeGuerreiros();
//
//        gerador.gerarESalvarGuerreirosParaArena(1); // Gregos e Nórdicos (lado 1)
//        gerador.gerarESalvarGuerreirosParaArena(2); // Atlantes e Egípcios (lado 2)

        LeitorDeGuerreiros leitor = new LeitorDeGuerreiros(arena);
        leitor.montarArena(); // Lê todos os arquivos e monta as filas de cada lado na arena

        System.out.println("====================================================================");
        System.out.println("a) Exibindo guerreiros da arena:");
        arena.exibirGuerreirosDeCadaLado();
        
        System.out.println("b) Calculando a soma dos pesos dos guerreiros...");
        arena.exibirSomaDosPesosDosGuerreiros();
 
        System.out.println("c) Identificando o guerreiro mais velho...");
        arena.exibirGuerreiroMaisVelhoDaArena();

//        System.out.println("Iniciando o combate...");
        arena.iniciarCombate();
        System.out.println("====================================================================");
    }
}
