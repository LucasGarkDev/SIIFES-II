/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.atlantes;

import arena.Arena;
import arena.FilaDeGuerreiros;
import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class Satiro extends GuerreiroAtlante {

    public Satiro(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.dano = 10;  
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        int filaAdversario = arena.encontrarFilaDeGuerreiro(adversario);

        if (filaAdversario != -1) { 
            FilaDeGuerreiros filaDefensora = arena.getFila(1, filaAdversario); 

            //System.out.println(this.getNome() + " está atacando toda a fila adversária " + (filaAdversario + 1) + ".");
            for (Guerreiro guerreiro : filaDefensora.getLista()) {
                if (guerreiro != null && !guerreiro.isEstaMorto()) {
                    guerreiro.sofrerDano(this.dano,arena); 
                    //System.out.println(this.getNome() + " causou " + this.dano + " de dano a " + guerreiro.getNome() + ".");
                }
            }
        } else {
            //System.out.println(this.getNome() + " não encontrou uma fila válida para atacar.");
        }
    }
    

}
