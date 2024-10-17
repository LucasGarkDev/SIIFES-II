/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.nordicos;

import arena.Arena;
import arena.FilaDeGuerreiros;
import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class GiganteDePedra extends GuerreiroNordico {

    private boolean habilidadeAtivada = false;  // Flag para saber se a habilidade foi ativada no turno

    public GiganteDePedra(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.energia = 300; // Gigante de Pedra começa com 300 de energia
        this.dano = 30;     // Dano base é 30
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        
    }

    
}
