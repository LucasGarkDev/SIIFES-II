/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arena;

import guerreiros.atlantes.GuerreiroAtlante;
import guerreiros.egipcios.GuerreiroEgipcio;
import guerreiros.gregos.GuerreiroGrego;
import guerreiros.nordicos.GuerreiroNordico;
import java.util.LinkedList;

/**
 *
 * @author lucas
 */
public class Teste {
    public static void main(String[] args) {
        Arena arena = new Arena();

        
        arena.obterFila(1, 0).adicionarGuerreiro(new GuerreiroGrego("Guerreiro Grego", 25, 80));
        arena.obterFila(1, 1).adicionarGuerreiro(new GuerreiroNordico("Guerreiro Nórdico", 30, 85));

        
        arena.obterFila(2, 0).adicionarGuerreiro(new GuerreiroEgipcio("Guerreiro Egípcio", 28, 75));
        arena.obterFila(2, 2).adicionarGuerreiro(new GuerreiroAtlante("Guerreiro Atlante", 35, 90));

        
        arena.realizarTurno();
    
    }
}
