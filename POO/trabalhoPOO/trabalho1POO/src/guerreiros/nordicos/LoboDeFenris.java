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
public class LoboDeFenris extends GuerreiroNordico {
    
    public LoboDeFenris(String nome, int idade, int peso) {
        super(nome, idade, peso);
    }

   @Override
    public void atacar(Arena arena, int ladoAtacante) {
        // Obtém os defensores do lado oponente
        FilaDeGuerreiros[] defensores = arena.obterDefensores(ladoAtacante);
        int posicao = arena.obterPosicaoFila(ladoAtacante, this);
        Guerreiro adversario = arena.obterAlvoDisponivel(defensores, posicao);

        // Verifica se há um adversário disponível
        if (adversario != null && adversario.estaVivo()) {
            // Calcula o bônus de ataque baseado nos lobos atrás
            int bonus = calcularBonusLobosAtras(arena, ladoAtacante);
            int danoTotal = 40 + bonus;

            // Ataca o adversário
            System.out.println(getNome() + " (Lobo de Fenris) ataca com " + danoTotal + " de dano!");
            adversario.receberDano(danoTotal);
        } else {
            System.out.println(getNome() + " não encontrou adversários para atacar.");
        }
    }

    // Calcula o bônus com base nos lobos que estão atrás na fila
    private int calcularBonusLobosAtras(Arena arena, int ladoAtacante) {
        int bonus = 0;
        int posicao = arena.obterPosicaoFila(ladoAtacante, this); // Obtém a posição do Lobo na fila

        // Percorre os guerreiros atrás na fila e soma o bônus para cada Lobo de Fenris
        for (int i = posicao + 1; i < 4; i++) {
            Guerreiro atras = arena.obterGuerreiroAtras(ladoAtacante, posicao, i - posicao); // Obtém o guerreiro atrás
            if (atras instanceof LoboDeFenris && atras.estaVivo()) {
                bonus += 8; // Adiciona 8 pontos de bônus para cada Lobo de Fenris atrás
            }
        }

        return bonus;
    }
}

