/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.gregos;

import arena.Arena;
import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class Manticora extends GuerreiroGrego{
    
    public Manticora(String nome, int idade, int peso) {
        super(nome, idade, peso);
    }

     @Override
    public void atacar(Arena arena, int ladoAtacante) {
        System.out.println(this.getNome() + " (Mantícora) ataca os guerreiros na sua frente e nas proximidades!");

        int filaAtual = 1; // Posição da fila (atualizar conforme a posição real)

        Guerreiro adversario = arena.obterAlvoDisponivel(arena.obterDefensores(ladoAtacante), filaAtual);
        if (adversario != null) {
            adversario.receberDano(30); // Causa 30 de dano no inimigo da mesma linha
            System.out.println(adversario.getNome() + " na mesma linha recebeu 30 de dano!");
        }

        // Ataca as filas adjacentes com 15 de dano
        if (filaAtual > 0) {
            Guerreiro adversarioAdjacente1 = arena.obterAlvoDisponivel(arena.obterDefensores(ladoAtacante), filaAtual - 1);
            if (adversarioAdjacente1 != null) {
                adversarioAdjacente1.receberDano(15);
                System.out.println(adversarioAdjacente1.getNome() + " na fila adjacente acima recebeu 15 de dano!");
            }
        }

        if (filaAtual < 3) {
            Guerreiro adversarioAdjacente2 = arena.obterAlvoDisponivel(arena.obterDefensores(ladoAtacante), filaAtual + 1);
            if (adversarioAdjacente2 != null) {
                adversarioAdjacente2.receberDano(15);
                System.out.println(adversarioAdjacente2.getNome() + " na fila adjacente abaixo recebeu 15 de dano!");
            }
        }
    }
    
}
