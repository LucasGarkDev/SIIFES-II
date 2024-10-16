/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.atlantes;

import arena.Arena;
import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class GuerreiroAtlante extends Guerreiro{
    
    // Construtor da classe GuerreiroAtlante
    public GuerreiroAtlante(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.dano = 10; // Ajustando o dano padrão para 10
    }

    // Implementação do método atacar
    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        if (adversario != null && !adversario.isEstaMorto()) {
            System.out.println(getNome() + " ataca " + adversario.getNome() + " causando " + dano + " de dano.");
            adversario.sofrerDano(dano);
        }
    }
    
}
