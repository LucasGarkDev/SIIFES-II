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

    public GiganteDePedra(String nome, int idade, int peso) {
        super(nome, idade, peso);
    }

    @Override
    public void atacar(Arena arena, int ladoAtacante) {
        // Obtém os defensores do lado oponente
        FilaDeGuerreiros[] defensores = arena.obterDefensores(ladoAtacante);
        Guerreiro adversario = arena.obterAlvoDisponivel(defensores, arena.obterPosicaoFila(ladoAtacante, this));

        if (adversario != null && adversario.estaVivo()) {
            // Ataca o adversário
            System.out.println(getNome() + " (Gigante de Pedra) ataca com um golpe poderoso!");
            adversario.receberDano(30);

            // Se o Gigante de Pedra for o primeiro a atacar no lado 1, ele força os adversários a atacá-lo
            if (ladoAtacante == 1 && arena.ePrimeiroAtaqueDoTurno(this)) {
                System.out.println(getNome() + " forçou todos os adversários a atacá-lo!");
                arena.definirAlvoForcado(this, ladoAtacante); // Define o Gigante como alvo forçado para os adversários
            }
        } else {
            System.out.println(getNome() + " não encontrou adversários para atacar.");
        }
    }
}

