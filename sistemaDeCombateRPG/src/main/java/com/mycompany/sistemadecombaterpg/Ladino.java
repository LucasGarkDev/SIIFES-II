/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;

/**
 *
 * @author lucas
 */
public class Ladino extends Personagem{
    private String dadoSneakAttack = "d6";
    public int quantidadeDadoSneakAttack = 1;
    
    public int sneakAttack(){
        int danoAcumulado = 0;
        int cont = 1;
        while (cont <= this.quantidadeDadoSneakAttack){
            danoAcumulado += rolagem(dadoSneakAttack);
        }
        return danoAcumulado;
    }
}
