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
public class GuerreiroGrego extends Guerreiro{

    // Construtor da classe GuerreiroGrego
    public GuerreiroGrego(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.dano = 15; // Definindo o dano de ataque do Guerreiro Grego
    }

    // Sobrescrevendo o método de aumento de energia para garantir que não ultrapasse 100
    @Override
    public void setEnergia(int energia) {
        if (energia > 100) {
            this.energia = 100;
        } else {
            this.energia = energia;
        }
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
