/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;

import java.util.Random;

/**
 *
 * @author lucas
 */
public class Magia {
    private int nivel;
    private int dadoDano;
    private int numeroAlvosMaximo;
    private String nome;
    private String descricao;

    public int getNivel() {
        return nivel;
    }

    public void setDadoDano(String dadoEscolhido) {
        RolagemDados rolagemDados = new RolagemDados();
        this.dadoDano = rolagemDados.rolagem(dadoEscolhido); 
    }

    public int getDadoDano() {
        return dadoDano;
    }
    
    public int getNumeroAlvosMaximo() {
        return numeroAlvosMaximo;
    }

    public void setNumeroAlvosMaximo(int numeroAlvosMaximo) {
        this.numeroAlvosMaximo = numeroAlvosMaximo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
