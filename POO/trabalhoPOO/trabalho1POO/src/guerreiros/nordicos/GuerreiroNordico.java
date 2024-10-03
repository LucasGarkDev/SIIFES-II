/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.nordicos;

import arena.Arena;
import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class GuerreiroNordico extends Guerreiro {

    public GuerreiroNordico(String nome, int idade, int peso) {
        super(nome, idade, peso);
    }

    @Override
    public void atacar(Arena arena, int ladoAtacante) {
        System.out.println(this.getNome() + " (Nórdico) ataca o guerreiro à frente!");
        // Lógica genérica de ataque: busca o adversário na frente
        Guerreiro adversario = arena.obterAlvoDisponivel(arena.obterDefensores(ladoAtacante), 0);
        if (adversario != null) {
            adversario.receberDano(25); // Dano genérico
            System.out.println(adversario.getNome() + " foi atacado e recebeu 25 de dano!");
        } else {
            System.out.println("Nenhum adversário disponível para atacar.");
        }
    }

}
