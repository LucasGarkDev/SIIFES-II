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

    // Construtor do Sátiro
    public Satiro(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.dano = 10;  
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        // Obtém a fila do adversário
        int filaAdversario = arena.encontrarFilaDeGuerreiro(adversario);

        if (filaAdversario != -1) { // Verifica se encontrou a fila do adversário
            FilaDeGuerreiros filaDefensora = arena.getFila(1, filaAdversario); // Lado 2 é o lado adversário (Atlantes e Egípcios)

            // Causa dano em toda a fila adversária
            System.out.println(this.getNome() + "(Satiro) está atacando toda a fila adversária " + (filaAdversario + 1) + ".");
            for (Guerreiro guerreiro : filaDefensora.getLista()) {
                if (guerreiro != null && !guerreiro.isEstaMorto()) {
                    guerreiro.sofrerDano(this.dano,arena); // Aplica o dano base do Sátiro
                    System.out.println(this.getNome() + " causou " + this.dano + " de dano a " + guerreiro.getNome() + ".");
                }
            }
        } else {
            System.out.println(this.getNome() + " não encontrou uma fila válida para atacar.");
        }
    }
}
