/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.gregos;

import campoDeBatalha.FilaDeGuerreiros;
import campoDeBatalha.Guerreiro;
import campoDeBatalha.Guerreiro;
import campoDeBatalha.Guerreiro;

/**
 *
 * @author lucas
 */
public class Ciclope extends Guerreiro{
    
    public Ciclope(String nome, int idade, int peso) {
        super(nome, idade, peso);
    }
    
    @Override
    public void atacar(Guerreiro adversario) {
        System.out.println(this.nome + " ataca " + adversario.getNome() + " com força de um Ciclope!");
        adversario.receberDano(35);

        // A lógica para mover o adversário para o final da fila se ele ainda estiver vivo
        // deve ser implementada na classe que gerencia a fila (Jogo ou FilaDeGuerreiros).
    }
    
}
