/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;

/**
 *
 * @author lucas
 */
public class Iniciativa {
    public static int rolarIniciativa(Personagem p) {
        return RolagemDados.rolarD20() + p.calcularModificador(p.getDestreza());
    }
}

