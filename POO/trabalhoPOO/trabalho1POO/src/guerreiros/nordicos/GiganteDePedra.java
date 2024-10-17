/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.nordicos;

import arena.Arena;
import arena.FilaDeGuerreiros;
import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class GiganteDePedra extends GuerreiroNordico {

    private boolean habilidadeAtivada = false;  // Flag para saber se a habilidade foi ativada no turno

    public GiganteDePedra(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.energia = 300; // Gigante de Pedra começa com 300 de energia
        this.dano = 30;     // Dano base é 30
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        // Verifica se o lado do Gigante foi sorteado para começar atacando
        if (!habilidadeAtivada) {
            // O Gigante de Pedra redireciona o ataque dos guerreiros inimigos para ele
            habilidadeAtivada = true;  // Ativa a habilidade no turno
            System.out.println(this.getNome() + " ativou sua habilidade! Todos os inimigos atacam " + this.getNome() + " neste turno.");

            // Redirecionar os ataques dos guerreiros adversários para o Gigante
            for (int i = 0; i < 4; i++) {
                FilaDeGuerreiros filaInimiga = arena.getFila(2, i); // Lado 2 é o lado inimigo
                Guerreiro primeiroInimigo = filaInimiga.getPrimeiroGuerreiro();

                // Se houver um guerreiro vivo na fila inimiga, redirecione o ataque para o Gigante
                if (primeiroInimigo != null && !primeiroInimigo.isEstaMorto()) {
                    System.out.println(primeiroInimigo.getNome() + " foi forçado a atacar " + this.getNome() + "!");
                    primeiroInimigo.atacar(arena, this); // Forçar o ataque no GiganteDePedra
                }
            }
        }

        // O Gigante ataca normalmente após redirecionar os ataques
        adversario.sofrerDano(this.dano);
        System.out.println(this.getNome() + " atacou " + adversario.getNome() + " causando " + this.dano + " de dano.");
    }

    // Método para resetar a habilidade no final do turno
    public void resetarHabilidade() {
        habilidadeAtivada = false;  // Reseta a habilidade para o próximo turno
    }
}
