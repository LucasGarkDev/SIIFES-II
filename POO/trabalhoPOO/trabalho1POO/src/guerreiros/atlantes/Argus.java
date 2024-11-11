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
        this.energia = 100;  // Define a energia inicial do Argos
        this.dano = 0;  // Argos não precisa de dano tradicional, já que mata o adversário direto
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        this.arena = arena;  // Armazena a arena em que o Argos está
        if (adversario != null && !adversario.isEstaMorto()) {
            System.out.println(getNome() + " ataca " + adversario.getNome() + " e mata instantaneamente!");
            adversario.sofrerDano(adversario.getEnergia(), arena);  // Reduz os pontos de vida a 0
        }
    }

    @Override
    public void sofrerDano(int quantidade, Arena arena) {
        this.arena = arena;  // Armazena a arena em que o Argos está
        super.sofrerDano(quantidade, arena);  // Chama o método da superclasse
    }
    
    @Override
    public String getNome() {
        return nome + "(Argus)";
    }
}

