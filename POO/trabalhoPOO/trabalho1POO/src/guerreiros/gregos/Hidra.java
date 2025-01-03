/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.gregos;

import arena.Arena;
import arena.FilaDeGuerreiros;
import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class Hidra extends GuerreiroGrego{
    
     private int numeroDeCabecas;

    public Hidra(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.dano = 50; // Dano inicial
        this.numeroDeCabecas = 1; // Começa com 1 cabeça
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        adversario.sofrerDano(this.dano,arena);

        // Verificar se o adversário morreu
        if (adversario.isEstaMorto()) {
            numeroDeCabecas++;
            this.dano = 50 + (numeroDeCabecas - 1) * 5;
            //System.out.println(this.getNome() + " matou " + adversario.getNome() + " e agora tem " + numeroDeCabecas + " cabeças e " + this.dano + " de dano.");
            this.energia = Math.min(this.energia + 20, 100);
            //System.out.println(this.getNome() + " recuperou 20 de energia e agora tem " + this.energia + " pontos de energia.");
        } else {
            //System.out.println(this.getNome() + " atacou " + adversario.getNome() + " causando " + this.dano + " de dano.");
        }    
    }
    

}
