/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;

import java.util.LinkedList;

/**
 *
 * @author lucas
 */
public class Combate implements Acoes{
    LinkedList<Personagem> listaDeIniciativa = new LinkedList<>();
    
   
    public boolean combateAcabou(LinkedList<Personagem> listaDeIniciativa) {
        for (Personagem inimigo : listaDeIniciativa) {
            if (inimigo instanceof Monstro) {
                return false; // Há pelo menos um inimigo, então o combate ainda não acabou
            }
        }
        return true; // Não há inimigos, o combate acabou
    }

    public void ordenarLista(LinkedList<Personagem> listaDePersonagens){
        
    }
    
    public 
    
    public void iniciarCombate(LinkedList<Personagem> listaDeIniciativa){
        ordenarLista(listaDeIniciativa);
        boolean encerrarCombate = false;
        do{
            for (Personagem atual : listaDeIniciativa){
                
            }
            encerrarCombate = combateAcabou(listaDeIniciativa);
        }while(encerrarCombate != true);
    }

}
