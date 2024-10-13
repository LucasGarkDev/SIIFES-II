/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.gregos;

import arena.Arena;
import arena.FilaDeGuerreiros;
import arena.Guerreiro;
import arena.Guerreiro;
import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class Ciclope extends GuerreiroGrego{
    
    public Ciclope(String nome, int idade, int peso) {
        super(nome, idade, peso);
    }

    @Override
    public void atacar(Arena arena, int ladoAtacante) {
        System.out.println(this.getNome() + " (Ciclope) ataca o guerreiro à frente com um golpe esmagador!");

        Guerreiro adversario = arena.obterAlvoDisponivel(arena.obterDefensores(ladoAtacante), 0);
        if (adversario != null) {
            adversario.receberDano(35); // Causa 35 de dano
            System.out.println(adversario.getNome() + " recebeu 35 de dano!");

            // Se for o primeiro da fila a atacar, move o adversário para o fim da fila
            if (arena.obterPrimeiroGuerreiro(ladoAtacante, 0) == this) {
                arena.moverGuerreiroParaFinal(2, 0); // Move o adversário para o final da fila inimiga
            }
        } else {
            System.out.println("Nenhum adversário disponível para atacar.");
        }
    }
}
