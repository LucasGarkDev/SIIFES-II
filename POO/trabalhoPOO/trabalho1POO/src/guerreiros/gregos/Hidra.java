/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.gregos;

import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class Hidra extends GuerreiroGrego{
    
    public Hidra(String nome, int idade, int peso) {
        super(nome, idade, peso);
    }

    @Override
    public void atacar(arena.Arena arena, int ladoAtacante) {
        System.out.println(this.getNome() + " (Hidra) ataca o guerreiro à frente e se regenera!");
        Guerreiro adversario = arena.obterAlvoDisponivel(arena.obterDefensores(ladoAtacante), 0);
        if (adversario != null) {
            adversario.receberDano(20); // Hidra causa um dano moderado
            System.out.println(adversario.getNome() + " foi atacado e recebeu 20 de dano!");

            // Hidra se regenera após o ataque
            this.setEnergia(Math.min(100, this.getEnergia() + 10));
            System.out.println(this.getNome() + " se regenerou e agora tem " + this.getEnergia() + " de energia!");
        } else {
            System.out.println("Nenhum adversário disponível para atacar.");
        }
    }
}
