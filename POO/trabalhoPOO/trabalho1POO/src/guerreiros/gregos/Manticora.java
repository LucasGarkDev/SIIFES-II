/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.gregos;

import arena.Arena;
import arena.FilaDeGuerreiros;
import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class Manticora extends GuerreiroGrego{
    
    public Manticora(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.dano = 30;  // A Manticora causa 30 de dano no ataque principal
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        adversario.sofrerDano(this.dano,arena);

        int filaAdversario = arena.encontrarFilaDeGuerreiro(adversario);

        if (filaAdversario == -1) {
            // O adversário não foi encontrado, então não é possível continuar
            System.out.println("Adversário não encontrado em nenhuma fila.");
            return;
        }

        FilaDeGuerreiros filaAcima = null;
        FilaDeGuerreiros filaAbaixo = null;

        if (filaAdversario == 0) { // Fila 1
            filaAbaixo = arena.getFila(2, filaAdversario + 1); // Fila abaixo (fila 2)
        } else if (filaAdversario == 3) { // Fila 4
            filaAcima = arena.getFila(2, filaAdversario - 1); // Fila acima (fila 3)
        } else {
            filaAcima = arena.getFila(2, filaAdversario - 1); // Fila acima
            filaAbaixo = arena.getFila(2, filaAdversario + 1); // Fila abaixo
        }

        // Atacar guerreiros nas filas adjacentes
        if (filaAcima != null) {
            Guerreiro guerreiroAcima = filaAcima.getPrimeiroGuerreiro();
            if (guerreiroAcima != null) {
                guerreiroAcima.sofrerDano(15,arena);
                System.out.println(this.getNome() + " também causou 15 de dano a " + guerreiroAcima.getNome() + " (fila acima).");
            }
        }

        if (filaAbaixo != null) {
            Guerreiro guerreiroAbaixo = filaAbaixo.getPrimeiroGuerreiro();
            if (guerreiroAbaixo != null) {
                guerreiroAbaixo.sofrerDano(15,arena);
                System.out.println(this.getNome() + " também causou 15 de dano a " + guerreiroAbaixo.getNome() + " (fila abaixo).");
            }
        }

        System.out.println(this.getNome() + " atacou " + adversario.getNome() + " causando 30 de dano.");
    }
    
    @Override
    public String getNome() {
        return nome + "(Manticora)";
    }
}
