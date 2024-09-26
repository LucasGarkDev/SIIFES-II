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
    public void atacar(Guerreiro adversario, FilaDeGuerreiros filaAdversaria) {
        System.out.println(this.nome + " ataca " + adversario.getNome() + " com força de um Ciclope!");
        adversario.receberDano(35);

        // Se o adversário ainda estiver vivo e o Ciclope for o primeiro a atacar,
        // ele jogará o adversário para o final da fila adversária
        if (adversario.estaVivo()) {
            filaAdversaria.moverParaFinal(adversario);
            System.out.println(adversario.getNome() + " foi movido para o final da fila pela força do Ciclope!");
        }
    }
    
    
}
