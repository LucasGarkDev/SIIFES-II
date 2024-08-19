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
    public int rolagem(String dadoEscolhido) {
        int resultadoRolagem = 0;
        Random dado = new Random();
   
        if (dadoEscolhido.equals("d20")) {
            resultadoRolagem = dado.nextInt(20) + 1;
        }else if(dadoEscolhido.equals("d12")){
            resultadoRolagem = dado.nextInt(12) + 1;
        }else if(dadoEscolhido.equals("d10")){
            resultadoRolagem = dado.nextInt(10) + 1;
        }else if(dadoEscolhido.equals("d6")){
            resultadoRolagem = dado.nextInt(6) + 1;
        }else if (dadoEscolhido.equals("d4")){
            resultadoRolagem = dado.nextInt(4) + 1;
        }else {
            resultadoRolagem = 10;
        }  
        return resultadoRolagem;
    }
}
