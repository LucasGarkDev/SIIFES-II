/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.egipcios;

import arena.Arena;
import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class MortoVivo extends GuerreiroEgipcio{
    
    public MortoVivo(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.energia = 100;
        this.dano = 5; // Dano padrão do MortoVivo
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        // MortoVivo ataca com 5 de dano
        if (adversario != null && !adversario.isEstaMorto()) {
            System.out.println(getNome() + " (MortoVivo) ataca " + adversario.getNome() + " causando " + dano + " de dano.");
            adversario.sofrerDano(dano,arena);
        }
    }
}
