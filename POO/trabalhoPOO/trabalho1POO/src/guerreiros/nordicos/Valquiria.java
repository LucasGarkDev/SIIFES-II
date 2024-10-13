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
public class Valquiria extends GuerreiroNordico {

    public Valquiria(String nome, int idade, int peso) {
        super(nome, idade, peso);
    }

    public void recuperarEnergia(Guerreiro aliado) {
        if (aliado != null && aliado.estaVivo()) {
            int novaEnergia = aliado.getEnergia() + 20; // Recupera 20 pontos de energia
            aliado.setEnergia(novaEnergia); // Define a nova energia do aliado
            System.out.println(aliado.getNome() + " teve sua energia aumentada para " + aliado.getEnergia() + " pela Valquíria.");
        }
    }

    @Override
    public void atacar(Arena arena, int ladoAtacante) {
        // Obtém os defensores do lado oponente
        FilaDeGuerreiros[] defensores = arena.obterDefensores(ladoAtacante);
        int posicaoNaFila = arena.obterPosicaoFila(ladoAtacante, this);
        Guerreiro adversario = arena.obterAlvoDisponivel(defensores, posicaoNaFila);

        if (adversario != null && adversario.estaVivo()) {
            // Ataca o adversário
            System.out.println(getNome() + " (Valquíria) ataca o adversário!");
            adversario.receberDano(20);

            // Cura o guerreiro que está atrás dela na fila
            Guerreiro aliadoAtras = arena.obterGuerreiroAtras(ladoAtacante, posicaoNaFila, posicaoNaFila);
            if (aliadoAtras != null && aliadoAtras.estaVivo()) {
                recuperarEnergia(aliadoAtras); // Recupera 20 pontos de energia
                System.out.println(getNome() + " cura " + aliadoAtras.getNome() + " em 20 pontos de energia.");
            }
        } else {
            System.out.println(getNome() + " não encontrou adversários para atacar.");
        }
    }

}
