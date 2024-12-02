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
public class Argus extends GuerreiroAtlante {

    // Construtor
    public Argus(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.energia = 100;  
        this.dano = 0;  
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) { 
        if (adversario != null && !adversario.isEstaMorto()) {
            //System.out.println(getNome() + " ataca " + adversario.getNome() + " e mata instantaneamente!");
            adversario.sofrerDano(adversario.getEnergia(), arena);  
        }
    }
    
}

