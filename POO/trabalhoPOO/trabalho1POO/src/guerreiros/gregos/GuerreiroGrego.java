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

    public GuerreiroGrego(String nome, int idade, int peso) {
        super(nome, idade, peso);
    }

    @Override
    public void atacar(Arena arena, int ladoAtacante) {
        System.out.println(this.getNome() + " (Grego) ataca o guerreiro à frente!");
        Guerreiro adversario = arena.obterAlvoDisponivel(arena.obterDefensores(ladoAtacante), 0);
        if (adversario != null) {
            adversario.receberDano(15); 
            System.out.println(adversario.getNome() + " foi atacado e recebeu 15 de dano!");
        } else {
            System.out.println("Nenhum adversário disponível para atacar.");
        }
    }
    
    @Override
    public void receberDano(int dano) {
        super.receberDano(dano);
        
        if (this.getEnergia() > 100) {
            this.setEnergia(100);
        }
    }
    
}
