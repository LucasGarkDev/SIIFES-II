/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arena;

import arena.Arena;

// Importar todas as classes do pacote guerreiros.gregos
import guerreiros.gregos.*;

// Importar todas as classes do pacote guerreiros.nordicos
import guerreiros.nordicos.*;

// Importar todas as classes do pacote guerreiros.atlantes
import guerreiros.atlantes.*;

// Importar todas as classes do pacote guerreiros.egipcios
import guerreiros.egipcios.*;

/**
 *
 * @author lucas
 */
public class Teste {

    public static void main(String[] args) {
        // Criando a arena
        Arena arena = new Arena();

        // Criando o Argos
        Argus argos = new Argus("Argos", 45, 120.0, 100);
        GuerreiroAtlante atlante = new GuerreiroAtlante("Guerreiro Atlante 1", 30, 80.0);

        // Criando guerreiros gregos
        GuerreiroGrego grego1 = new GuerreiroGrego("Guerreiro Grego 1", 30, 80.0);
        GuerreiroGrego grego2 = new GuerreiroGrego("Guerreiro Grego 2", 32, 85.0);
        GuerreiroGrego grego3 = new GuerreiroGrego("Guerreiro Grego 3", 28, 78.0);
        GuerreiroGrego grego4 = new GuerreiroGrego("Guerreiro Grego 4", 35, 90.0);

        // Adicionando Argos na primeira fila do lado 1
        arena.getFila(2, 0).adicionarGuerreiroNoFinal(argos);
        arena.getFila(2,0).adicionarGuerreiroNoFinal(atlante);

        // Adicionando os Guerreiros Gregos na primeira fila do lado 2
        arena.getFila(1, 0).adicionarGuerreiroNoFinal(grego1);
        arena.getFila(1, 0).adicionarGuerreiroNoFinal(grego2);
        arena.getFila(1, 0).adicionarGuerreiroNoFinal(grego3);
        arena.getFila(1, 0).adicionarGuerreiroNoFinal(grego4);

        // Exibindo o estado inicial da arena
        System.out.println("Estado inicial da Arena:");
        arena.exibirGuerreirosDeCadaLado();

        // Iniciando o combate
        System.out.println("\nIniciando o combate...");
        arena.iniciarCombate();

        // Exibindo o estado final da arena
        System.out.println("\nResultado final da Arena:");
        arena.exibirGuerreirosDeCadaLado();
    }
}
