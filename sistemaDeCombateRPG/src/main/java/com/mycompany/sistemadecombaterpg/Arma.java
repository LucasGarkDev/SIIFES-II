/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;

/**
 *
 * @author lucas
 */
public class Arma {
    private String nome;
    private int ladosDadoDano;
    private String tipoDeAtributo;  // Força ou Destreza, dependendo da arma

    public Arma(String nome, int ladosDadoDano, String tipoDeAtributo) {
        this.nome = nome;
        this.ladosDadoDano = ladosDadoDano;
        this.tipoDeAtributo = tipoDeAtributo;
    }

    // Método para calcular o dano com base no modificador do atributo
    public int calcularDano(int modificadorAtributo) {
        return RolagemDados.rolarDanoArma(ladosDadoDano, modificadorAtributo);
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLadosDadoDano() {
        return ladosDadoDano;
    }

    public void setLadosDadoDano(int ladosDadoDano) {
        this.ladosDadoDano = ladosDadoDano;
    }

    public String getTipoDeAtributo() {
        return tipoDeAtributo;
    }

    public void setTipoDeAtributo(String tipoDeAtributo) {
        this.tipoDeAtributo = tipoDeAtributo;
    }
}
