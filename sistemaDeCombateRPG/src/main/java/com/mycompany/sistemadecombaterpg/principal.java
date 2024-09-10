/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemadecombaterpg;

import java.util.LinkedList;

/**
 *
 * @author lucas
 */
import java.util.Arrays;

public class principal {
    public static void main(String[] args) {
        Guerreiro guerreiro = new Guerreiro(16, 14, 15, 10, 12, 8, 5);
        Barbaro barbaro = new Barbaro(18, 12, 16, 8, 10, 10, 5);

        Combate combate = new Combate(Arrays.asList(guerreiro, barbaro));
        combate.iniciarCombate();
    }
}

