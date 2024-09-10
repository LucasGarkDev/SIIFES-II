/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;

/**
 *
 * @author lucas
 */
public class Guerreiro extends Personagem {

    public Guerreiro(int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma, int nivel) {
        super(forca, destreza, constituicao, inteligencia, sabedoria, carisma, nivel);
    }

    @Override
    public void atacar(Personagem alvo) {
        int rolagemAtaque = RolagemDados.rolarD20() + calcularModificador(forca);
        if (rolagemAtaque >= alvo.getClasseDeArmadura()) {
            int dano = RolagemDados.rolarDado(8) + calcularModificador(forca); // Exemplo: d8 de dano
            alvo.receberDano(dano);
        }
    }

    @Override
    public int calcularPontosDeVida() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


