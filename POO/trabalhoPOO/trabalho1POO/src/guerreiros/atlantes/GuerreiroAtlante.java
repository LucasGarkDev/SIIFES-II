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

    public GuerreiroAtlante(String nome, int idade, int peso) {
        super(nome, idade, peso);
        this.energia = 100;
    }

    @Override
    public void atacar(Arena arena, int ladoAtacante) {
        System.out.println(this.getNome() + " (Atlante) ataca o guerreiro à frente!");
        Guerreiro adversario = arena.obterAlvoDisponivel(arena.obterDefensores(ladoAtacante), 0);
        if (adversario != null) {
            adversario.receberDano(10); // Dano genérico
            System.out.println(adversario.getNome() + " foi atacado e recebeu 30 de dano!");
        } else {
            System.out.println("Nenhum adversário disponível para atacar.");
        }
    }
    
}
