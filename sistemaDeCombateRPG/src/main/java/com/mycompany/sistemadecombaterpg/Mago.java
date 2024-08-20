/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;

/**
 *
 * @author lucas
 */
public class Mago extends Conjurador{
    public void recuperacaoArcana(){
        for (Magia magia : listaDeMagias.getListaDeNos()) {
            int novoValor = no.getValor() + 1; // Aumenta o valor em 1
            no.setValor(novoValor); // Define o novo valor no nó
        }
    }
}
