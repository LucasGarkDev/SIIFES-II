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
public class RolagemDados {

    /**
     * Rola um dado de 20 lados (d20).
     * @return um número entre 1 e 20.
     */
    public static int rolarD20() {
        return (int) (Math.random() * 20) + 1;
    }

    /**
     * Rola um dado com o número de lados especificado.
     * @param lados o número de lados do dado (ex: 6 para um d6).
     * @return um número entre 1 e o número de lados, ou -1 se o número de lados for inválido.
     */
    public static int rolarDado(int lados) {
        if (lados < 1) {
            System.out.println("Número inválido de lados: " + lados);
            return -1;  // Retorna -1 se o número de lados for inválido
        }
        return (int) (Math.random() * lados) + 1;
    }

    /**
     * Faz uma rolagem com vantagem, retornando o maior valor entre duas rolagens de d20.
     * @return o maior valor entre duas rolagens de d20.
     */
    public static int rolarComVantagem() {
        return Math.max(rolarD20(), rolarD20());
    }

    /**
     * Faz uma rolagem com desvantagem, retornando o menor valor entre duas rolagens de d20.
     * @return o menor valor entre duas rolagens de d20.
     */
    public static int rolarComDesvantagem() {
        return Math.min(rolarD20(), rolarD20());
    }

    /**
     * Rola vários dados e retorna a soma das rolagens.
     * @param quantidade a quantidade de dados a serem rolados.
     * @param lados o número de lados dos dados a serem rolados.
     * @return a soma das rolagens ou -1 se a quantidade ou o número de lados forem inválidos.
     */
    public static int rolarMultiplosDados(int quantidade, int lados) {
        if (quantidade < 1 || lados < 1) {
            System.out.println("Número inválido de dados ou lados.");
            return -1;  // Retorna -1 se a quantidade ou número de lados forem inválidos
        }

        int soma = 0;
        for (int i = 0; i < quantidade; i++) {
            soma += rolarDado(lados);
        }
        return soma;
    }

    /**
     * Rola o dano de uma arma, baseado no dado de dano da arma e aplica o modificador de atributo.
     * @param ladosDadoDano o número de lados do dado da arma (ex: d6, d8).
     * @param modificador o modificador de Força ou Destreza (dependendo da arma).
     * @return o valor total do dano causado.
     */
    public static int rolarDanoArma(int ladosDadoDano, int modificador) {
        int rolagemDano = rolarDado(ladosDadoDano);
        if (rolagemDano == -1) {
            return -1;  // Dado inválido
        }
        return rolagemDano + modificador;
    }

    /**
     * Rola o dano de um feitiço, baseado no dado de dano do feitiço e aplica o modificador de atributo.
     * @param quantidadeDados a quantidade de dados de dano.
     * @param ladosDadoDano o número de lados do dado de dano do feitiço (ex: d6, d8).
     * @param modificador o modificador de Inteligência, Sabedoria ou Carisma (dependendo do feitiço).
     * @return o valor total do dano causado pelo feitiço.
     */
    public static int rolarDanoFeitico(int quantidadeDados, int ladosDadoDano, int modificador) {
        int rolagemDano = rolarMultiplosDados(quantidadeDados, ladosDadoDano);
        if (rolagemDano == -1) {
            return -1;  // Dados inválidos
        }
        return rolagemDano + modificador;
    }
}


