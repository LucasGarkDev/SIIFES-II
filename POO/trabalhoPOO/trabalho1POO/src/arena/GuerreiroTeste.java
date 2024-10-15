/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arena;

/**
 *
 * @author lucas
 */
public class GuerreiroTeste extends Guerreiro{

    public GuerreiroTeste(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.dano = 10;  // Dano de teste
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        System.out.println(this.nome + " ataca " + adversario.getNome() + " causando " + this.dano + " de dano.");
        adversario.sofrerDano(this.dano);
    }
    
}
