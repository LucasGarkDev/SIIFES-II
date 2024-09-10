/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;

/**
 *
 * @author lucas
 */
public class Modificadores {

    // Método estático para calcular o modificador de atributos
    public static int calcularModificador(int valorDeAtributo) {
        return (valorDeAtributo - 10) / 2;
    }

    // Método estático para calcular o bônus de proficiência com base no nível
    public static int calcularBonusProficiencia(int nivel) {
        return (nivel - 1) / 4 + 2;
    }

    // Método estático para aplicar modificadores a rolagens de ataque
    public static int aplicarModificadorAoAtaque(int rolagem, int modificador) {
        return rolagem + modificador;
    }
}

