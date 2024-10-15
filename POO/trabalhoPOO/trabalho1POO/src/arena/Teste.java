/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arena;

import guerreiros.atlantes.GuerreiroAtlante;
import guerreiros.atlantes.Prometeano;
import guerreiros.egipcios.GuerreiroEgipcio;
import guerreiros.gregos.Ciclope;
import guerreiros.gregos.GuerreiroGrego;
import guerreiros.gregos.Hidra;
import guerreiros.gregos.Manticora;
import guerreiros.nordicos.GiganteDePedra;
import guerreiros.nordicos.GuerreiroNordico;
import guerreiros.nordicos.LoboDeFenris;
import guerreiros.nordicos.Valquiria;
import java.util.LinkedList;

/**
 *
 * @author lucas
 */
public class Teste {
    public static void main(String[] args) {
        // Criando guerreiros de teste para o lado 1 (Gregos e Nórdicos)
        Guerreiro guerreiro1L1 = new GuerreiroTeste("Guerreiro Grego A", 30, 80.5);
        Guerreiro guerreiro2L1 = new GuerreiroTeste("Guerreiro Grego B", 25, 75.3);
        Guerreiro guerreiro3L1 = new GuerreiroTeste("Guerreiro Nórdico A", 35, 85.0);
        Guerreiro guerreiro4L1 = new GuerreiroTeste("Guerreiro Nórdico B", 28, 90.0);

        // Criando guerreiros de teste para o lado 2 (Atlantes e Egípcios)
        Guerreiro guerreiro1L2 = new GuerreiroTeste("Guerreiro Atlante A", 32, 78.5);
        Guerreiro guerreiro2L2 = new GuerreiroTeste("Guerreiro Atlante B", 26, 73.3);
        Guerreiro guerreiro3L2 = new GuerreiroTeste("Guerreiro Egípcio A", 33, 84.0);
        Guerreiro guerreiro4L2 = new GuerreiroTeste("Guerreiro Egípcio B", 29, 82.0);

        // Criando a arena
        Arena arena = new Arena();

        // Adicionando os guerreiros nas filas do lado 1
        arena.getFila(1, 0).adicionarGuerreiroNoFinal(guerreiro1L1); // Fila 1 do lado 1
        arena.getFila(1, 1).adicionarGuerreiroNoFinal(guerreiro2L1); // Fila 2 do lado 1
        arena.getFila(1, 2).adicionarGuerreiroNoFinal(guerreiro3L1); // Fila 3 do lado 1
        arena.getFila(1, 3).adicionarGuerreiroNoFinal(guerreiro4L1); // Fila 4 do lado 1

        // Adicionando os guerreiros nas filas do lado 2
        arena.getFila(2, 0).adicionarGuerreiroNoFinal(guerreiro1L2); // Fila 1 do lado 2
        arena.getFila(2, 1).adicionarGuerreiroNoFinal(guerreiro2L2); // Fila 2 do lado 2
        arena.getFila(2, 2).adicionarGuerreiroNoFinal(guerreiro3L2); // Fila 3 do lado 2
        arena.getFila(2, 3).adicionarGuerreiroNoFinal(guerreiro4L2); // Fila 4 do lado 2

        // Exibindo o estado inicial da arena
        System.out.println("Estado inicial da Arena:");
        arena.exibirGuerreirosDeCadaLado();

        // Iniciando o combate
        System.out.println("\nIniciando o combate...");
        arena.iniciarCombate();

        // Exibindo o resultado final
        System.out.println("\nResultado final:");
        arena.exibirGuerreirosDeCadaLado();
    }
}
