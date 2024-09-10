/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;

/**
 *
 * @author lucas
 */
public class Condicao {
    private String nome;
    private int duracao;

    public Condicao(String nome, int duracao) {
        this.nome = nome;
        this.duracao = duracao;
    }

    public void aplicarCondicao(Personagem alvo) {
        // Lógica para aplicar a condição, como reduzir movimentos, evitar ataques, etc.
    }

    public void removerCondicao(Personagem alvo) {
        // Remover condição
    }

    // Getters e setters
}
