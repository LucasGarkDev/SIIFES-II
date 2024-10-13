/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arena;

import guerreiros.atlantes.GuerreiroAtlante;
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
        Arena arena = new Arena();

        // Adicionar Hidra no lado 1, na fila 2
        arena.obterFila(1, 0).adicionarGuerreiro(new GiganteDePedra("Atom", 30, 200));
        arena.obterFila(1, 1).adicionarGuerreiro(new GuerreiroNordico("Erick", 30, 200));
        arena.obterFila(1, 2).adicionarGuerreiro(new GuerreiroNordico("Soluço", 30, 200));
        
        // Adicionar dois guerreiros egípcios na fila à frente da Hidra (fila 1 e 2, lado 2)
        arena.obterFila(2, 0).adicionarGuerreiro(new GuerreiroEgipcio("Pin salabin", 28, 75));
        arena.obterFila(2, 1).adicionarGuerreiro(new GuerreiroEgipcio("Artaku", 28, 75));
        arena.obterFila(2, 2).adicionarGuerreiro(new GuerreiroEgipcio("Ralapin", 28, 75));
        
        // Executar a batalha até que um dos lados seja derrotado
        arena.realizarBatalhaCompleta();
    }
}
