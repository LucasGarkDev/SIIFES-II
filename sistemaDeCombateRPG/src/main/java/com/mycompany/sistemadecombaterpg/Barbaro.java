/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;

/**
 *
 * @author lucas
 */
public class Barbaro extends Personagem {
    private boolean furiaAtiva;

    public Barbaro(int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma, int nivel) {
        super(forca, destreza, constituicao, inteligencia, sabedoria, carisma, nivel);
        this.furiaAtiva = false;
    }

    public void ativarFuria() {
        this.furiaAtiva = true;
    }

    @Override
    public void atacar(Personagem alvo) {
        int rolagemAtaque = RolagemDados.rolarD20() + calcularModificador(forca);
        if (rolagemAtaque >= alvo.getClasseDeArmadura()) {
            int dano = RolagemDados.rolarDado(12) + calcularModificador(forca);
            if (furiaAtiva) {
                dano += 2;  // Bônus de fúria
            }
            alvo.receberDano(dano);
        }
    }

    @Override
    public int calcularPontosDeVida() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

