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
public class HomemEscorpiao extends GuerreiroEgipcio {

    public HomemEscorpiao(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.energia = 100;  // Energia inicial do Homem Escorpião
        this.dano = 20;      // Dano de ataque padrão do Homem Escorpião
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        if (adversario != null && !adversario.isEstaMorto()) {
            // Causa 20 pontos de dano
            System.out.println(getNome() + " ataca " + adversario.getNome() + " causando " + dano + " de dano.");
            adversario.sofrerDano(dano, arena);

            // Aplica o veneno se o adversário ainda não estiver envenenado
            if (!adversario.isEnvenenado()) {
                adversario.envenenar();
                System.out.println(adversario.getNome() + " foi envenenado e sofrerá 5 pontos de dano toda vez que atacar.");
            }
        }
    }
    
    @Override
    public String getNome() {
        return nome + "(Homem Escorpião)";
    }
}
