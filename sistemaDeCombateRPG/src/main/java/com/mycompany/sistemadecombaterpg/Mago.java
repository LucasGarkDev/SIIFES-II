/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;

/**
 *
 * @author lucas
 */
public class Mago extends Personagem {

    public Mago(int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma, int nivel) {
        super(forca, destreza, constituicao, inteligencia, sabedoria, carisma, nivel);
    }

    @Override
    public int calcularPontosDeVida() {
        int dadoDeVida = 6; // Mago usa um d6 para o cálculo de pontos de vida
        return dadoDeVida + calcularModificador(constituicao);
    }

    @Override
    public void atacar(Personagem alvo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

