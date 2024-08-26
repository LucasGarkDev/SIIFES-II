/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;

/**
 *
 * @author lucas
 */
public class Monstro extends Personagem {
    private int pontosDeVida;

    public Monstro(String nome, int pontosDeVida) {
        super(nome);  // Chama o construtor da classe Personagem
        this.pontosDeVida = pontosDeVida;
    }

    public int getPontosDeVida() {
        return pontosDeVida;
    }
}
