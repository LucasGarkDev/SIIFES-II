/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;

/**
 *
 * @author lucas
 */
public interface Acoes {
    public int atacar(String dado, int modificador, Personagem alvo);
    public int magia(Magia magiaEscolhida, int modificador, Personagem alvo);
    public int defender(Personagem alvo);
    public int esquivar(Personagem personagem);
    public void guardarAcao(Personagem personagem);
}
