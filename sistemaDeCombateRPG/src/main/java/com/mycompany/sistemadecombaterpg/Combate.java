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
    
    public int escolherAcao(Personagem personagem){
        int opcaoEscolhida = 0;
        System.out.println("Turno de :" + personagem);
        System.out.println("1 - Atacar");
        System.out.println("2 - Magia");
        System.out.println("3 - Usar Habilidade");
        System.out.println("4 - Esquivar");
        System.out.println("5 - Guardar Ação");
        return opcaoEscolhida;
    }
    
    public void realizarAcoes(Personagem personagem, Personagem alvo){
        int opcaoEscolhida = escolherAcao(personagem);
        switch (opcaoEscolhida) {
            case 1:
                System.out.println("Qual deve ser o alvo do ataque:");
                
                personagem.atacar("d20", personagem.calcularModificador(personagem.forca), alvo);
                break;
            case 2:
                
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                break;
        }
        
    }
    
    public void iniciarCombate(LinkedList<Personagem> listaDeIniciativa){
        ordenarLista(listaDeIniciativa);
        boolean encerrarCombate = false;
        do{
            for (Personagem atual : listaDeIniciativa){
                realizar
            }
            encerrarCombate = combateAcabou(listaDeIniciativa);
        }while(encerrarCombate != true);
    }

}
