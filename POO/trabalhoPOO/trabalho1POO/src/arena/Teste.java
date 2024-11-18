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
        // Criação da arena
        Arena arena = new Arena();

        // Criação de guerreiros do lado 2 (Atlantes e Egípcios)
        Prometeano prometeano = new Prometeano("Prometeano Guardião", 180, 300.0, 100);
        HomemEscorpiao homemEscorpiao = new HomemEscorpiao("Escorpião Rei", 140, 110.0);

        // Adição ao lado 2
        arena.getFila(2, 0).adicionarGuerreiroNoFinal(prometeano);
        arena.getFila(2, 0).adicionarGuerreiroNoFinal(homemEscorpiao);

        // Criação de guerreiros do lado 1 (Gregos e Nórdicos)
        Ciclope ciclope = new Ciclope("Polifemo", 200, 500.0);
        Hidra hidra = new Hidra("Hidra de Lerna", 100, 350.0);
        Valquiria valquiria = new Valquiria("Valquíria Branca", 150, 90.0);

        // Adição ao lado 1
        arena.getFila(1, 0).adicionarGuerreiroNoFinal(ciclope);
        arena.getFila(1, 0).adicionarGuerreiroNoFinal(hidra);
        arena.getFila(1, 0).adicionarGuerreiroNoFinal(valquiria);

        // Exibir estado inicial da arena
        System.out.println("Estado inicial da arena:");
        arena.exibirGuerreirosDeCadaLado();

        // Sortear o lado que inicia o turno
        int ladoSorteado = arena.sortearLado();
        System.out.println("\nLado sorteado para começar o turno: Lado " + ladoSorteado);

        // Executar múltiplos turnos para garantir a morte do Prometeano
        for (int i = 0; i < 3; i++) {
            System.out.println("\nTurno " + (i + 1) + ":");
            arena.executarTurno(ladoSorteado);
            arena.exibirGuerreirosDeCadaLado();

            // Verificar se o Prometeano morreu
            if (prometeano.isEstaMorto()) {
                break;
            }
        }

        // Exibir estado final da arena
        System.out.println("\nEstado final da arena após os turnos:");
        arena.exibirGuerreirosDeCadaLado();
    }
}
