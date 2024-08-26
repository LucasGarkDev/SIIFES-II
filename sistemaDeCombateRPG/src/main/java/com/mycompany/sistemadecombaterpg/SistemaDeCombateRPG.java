/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemadecombaterpg;

import java.util.LinkedList;

/**
 *
 * @author lucas
 */
public class SistemaDeCombateRPG {

    public static void main(String[] args) {
        Combate campoDebatalha = new Combate();
        campoDebatalha.listaDeIniciativa.add(new Personagem("Herói"));
        campoDebatalha.listaDeIniciativa.add(new Monstro("Orc", 100));
        campoDebatalha.listaDeIniciativa.add(new Personagem("Mago"));
        campoDebatalha.listaDeIniciativa.add(new Monstro("Dragão", 200));
        
        
        campoDebatalha.iniciarCombate(campoDebatalha.listaDeIniciativa);
        
    }
}
