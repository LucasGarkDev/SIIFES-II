/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;
/**
 *
 * @author lucas
 */
public abstract class Feitico {
    protected int quantidadeDados;
    protected int ladosDadoDano;
    protected int modificadorAtributo;

    public Feitico(int quantidadeDados, int ladosDadoDano, int modificadorAtributo) {
        this.quantidadeDados = quantidadeDados;
        this.ladosDadoDano = ladosDadoDano;
        this.modificadorAtributo = modificadorAtributo;
    }

    // Método abstrato para conjurar o feitiço
    public abstract void conjurar(Personagem alvo);

    // Método para calcular o dano total do feitiço usando a classe RolagemDados
    public int calcularDano() {
        return RolagemDados.rolarDanoFeitico(quantidadeDados, ladosDadoDano, modificadorAtributo);
    }
}


