/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campoDeBatalha;

import java.util.LinkedList;

/**
 *
 * @author lucas
 */
public class Teste {
    public static void main(String[] args) {
        // Carregar e exibir guerreiros do Lado 1 (Gregos e Nórdicos)
        System.out.println("Guerreiros do Lado 1 (Gregos e Nórdicos):");
        for (int i = 1; i <= 4; i++) {
            FilaDeGuerreiros filaLado1 = new FilaDeGuerreiros();
            LinkedList<Guerreiro> guerreirosLado1 = LeitorDeArquivo.carregarGuerreirosDeArquivo("fila1" + i + ".txt", 1);
            
            for (Guerreiro guerreiro : guerreirosLado1) {
                filaLado1.adicionarGuerreiro(guerreiro);
            }
            
            System.out.println("Fila " + i + ":");
            filaLado1.exibirGuerreiros();
            System.out.println("----------------------------");
        }

        // Carregar e exibir guerreiros do Lado 2 (Atlantes e Egípcios)
        System.out.println("Guerreiros do Lado 2 (Atlantes e Egípcios):");
        for (int i = 1; i <= 4; i++) {
            FilaDeGuerreiros filaLado2 = new FilaDeGuerreiros();
            LinkedList<Guerreiro> guerreirosLado2 = LeitorDeArquivo.carregarGuerreirosDeArquivo("fila2" + i + ".txt", 2);
            
            for (Guerreiro guerreiro : guerreirosLado2) {
                filaLado2.adicionarGuerreiro(guerreiro);
            }
            
            System.out.println("Fila " + i + ":");
            filaLado2.exibirGuerreiros();
            System.out.println("----------------------------");
        }
    }
}
