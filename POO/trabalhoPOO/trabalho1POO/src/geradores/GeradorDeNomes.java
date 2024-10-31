/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package geradores;

import java.util.Random;

/**
 *
 * @author lucas
 */
public class GeradorDeNomes {
    private static final String[] PREFIXOS = {"Val", "Fen", "Dro", "Kar", "Bel", "Thal", "Mar", "Zen", "Or", "Lun"};
    private static final String[] SUFIXOS = {"mir", "dor", "kan", "ron", "tis", "gar", "nor", "thos", "los", "ros"};

    // Gera um nome aleatório combinando um prefixo e um sufixo
    public static String gerarNomeAleatorio() {
        Random random = new Random();
        String prefixo = PREFIXOS[random.nextInt(PREFIXOS.length)];
        String sufixo = SUFIXOS[random.nextInt(SUFIXOS.length)];
        return prefixo + sufixo;
    }
}
