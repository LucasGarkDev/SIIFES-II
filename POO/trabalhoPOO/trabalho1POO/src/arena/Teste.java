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

import geradores.*;

public class Teste {

    public static void main(String[] args) {
        // Inicializa a arena
        Arena arena = new Arena();

        // Configura o Lado 1: Gigante de Pedra e outros aliados
        GiganteDePedra gigante1 = new GiganteDePedra("Thor", 150, 500.0);
        GiganteDePedra gigante2 = new GiganteDePedra("Odin", 200, 520.0);
        Guerreiro aliado1 = new Valquiria("Valquiria", 30, 60);
        Guerreiro aliado2 = new LoboDeFenris("Lobo de Fenris", 25, 70);

        // Configura o Lado 1: Gigante de Pedra e outros aliados
        arena.getFila(1, 0).adicionarGuerreiro(gigante1, 0); // Gigante de Pedra na primeira posição da Fila 1
        arena.getFila(1, 1).adicionarGuerreiro(gigante2, 0); // Segundo Gigante na primeira posição da Fila 2
        arena.getFila(1, 2).adicionarGuerreiro(aliado1, 0);  // Aliado na primeira posição da Fila 3
        arena.getFila(1, 3).adicionarGuerreiro(aliado2, 0);  // Aliado na primeira posição da Fila 4

        // Configura o Lado 2: Adiciona guerreiros para o combate
        Guerreiro adversario1 = new HomemEscorpiao("Homem Escorpiao", 40, 80);
        Guerreiro adversario2 = new Mumia("Mumia", 60, 100);
        Guerreiro adversario3 = new Anubita("Anubita", 50, 90);
        Guerreiro adversario4 = new Prometeano("Prometeano", 70, 85, 100);

        // Configura o Lado 2: Adiciona guerreiros para o combate
        arena.getFila(2, 0).adicionarGuerreiro(adversario1, 0); // Inimigo na primeira posição da Fila 1
        arena.getFila(2, 1).adicionarGuerreiro(adversario2, 0); // Inimigo na primeira posição da Fila 2
        arena.getFila(2, 2).adicionarGuerreiro(adversario3, 0); // Inimigo na primeira posição da Fila 3
        arena.getFila(2, 3).adicionarGuerreiro(adversario4, 0); // Inimigo na primeira posição da Fila 4

        // Exibe estado inicial da arena
        System.out.println("Estado inicial da Arena:");
        arena.exibirGuerreirosDeCadaLado();

        // Executa combate controlado para analisar o comportamento do Gigante de Pedra
        for (int i = 0; i < 5; i++) {
            System.out.println("-------------------------------------------------");
            System.out.println("Turno " + (i + 1) + ":");
            int ladoSorteado = arena.sortearLado();
            System.out.println("Lado " + ladoSorteado + " começa o turno.");
            arena.executarTurno(ladoSorteado);

            // Exibe o estado da arena após o turno
            arena.exibirGuerreirosDeCadaLado();

            // Verifica se um dos lados foi eliminado e encerra o combate
            if (arena.todosGuerreirosMortos(1)) {
                System.out.println("Lado 2 (Atlantes e Egípcios) venceu!");
                break;
            } else if (arena.todosGuerreirosMortos(2)) {
                System.out.println("Lado 1 (Gregos e Nórdicos) venceu!");
                break;
            }
        }

        // Exibe o último guerreiro morto e o guerreiro que causou sua morte, se houver
        try {
            arena.exibirUltimoGuerreiroMorto();
        } catch (UltimoGuerreiroNaoIdentificadoException e) {
            System.out.println("Erro ao exibir o último guerreiro morto: " + e.getMessage());
        }
    }
}
