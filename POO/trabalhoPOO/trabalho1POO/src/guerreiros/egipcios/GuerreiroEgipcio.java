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
public class GuerreiroEgipcio extends Guerreiro{

    public GuerreiroEgipcio(String nome, int idade, int peso) {
        super(nome, idade, peso);
    }

    @Override
    public void atacar(Arena arena, int ladoAtacante) {
        System.out.println(this.getNome() + " (Egípcio) ataca o guerreiro à frente!");
        Guerreiro adversario = arena.obterAlvoDisponivel(arena.obterDefensores(ladoAtacante), 0);
        if (adversario != null) {
            adversario.receberDano(20); // Dano genérico
            System.out.println(adversario.getNome() + " foi atacado e recebeu 20 de dano!");
        } else {
            System.out.println("Nenhum adversário disponível para atacar.");
        }
    }
    
}
