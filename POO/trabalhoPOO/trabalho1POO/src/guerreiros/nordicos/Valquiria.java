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

    public Valquiria(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.dano = 20; // Dano fixo de 20
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        // A Valquiria ataca o adversário com 20 de dano
        adversario.sofrerDano(this.dano,arena);
        System.out.println(this.getNome() + " atacou " + adversario.getNome() + " causando " + this.dano + " de dano.");

        // Agora interagir com o guerreiro logo atrás dela na fila
        FilaDeGuerreiros filaDaValquiria = arena.getFila(1, arena.encontrarFilaDeGuerreiro(this));
        int posicaoValquiria = filaDaValquiria.encontrarGuerreiro(this);

        // Verificar se existe um guerreiro atrás da Valquiria na mesma fila
        if (posicaoValquiria < filaDaValquiria.getLista().size() - 1) {
            Guerreiro guerreiroAtras = filaDaValquiria.getGuerreiro(posicaoValquiria + 1);
            if (guerreiroAtras != null && !guerreiroAtras.isEstaMorto()) {
                // Conceder 20 de energia ao guerreiro que está atrás dela
                guerreiroAtras.setEnergia(guerreiroAtras.getEnergia() + 20);
                System.out.println(this.getNome() + " concedeu 20 de energia a " + guerreiroAtras.getNome() + ".");
            }
        }
    }
}
