/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.nordicos;

import arena.Arena;
import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class GuerreiroNordico extends Guerreiro {

    // Construtor da classe GuerreiroNordico
    public GuerreiroNordico(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.dano = 20; // Definindo o dano de ataque do Guerreiro Nórdico
    }

    // Implementação do método atacar
    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        if (adversario != null && !adversario.isEstaMorto()) {
            System.out.println(getNome() + " ataca " + adversario.getNome() + " causando " + dano + " de dano.");
            adversario.sofrerDano(dano,arena);
        }
    }

}
