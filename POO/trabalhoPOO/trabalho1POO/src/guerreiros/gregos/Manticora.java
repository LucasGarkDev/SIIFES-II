/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.gregos;

import campoDeBatalha.Guerreiro;

/**
 *
 * @author lucas
 */
public class Manticora extends Guerreiro{
    
    public Manticora(String nome, int idade, int peso) {
        super(nome, idade, peso);
    }

    @Override
    public void atacar(Guerreiro[] adversarios, int linhaAtual) {
        System.out.println(this.getNome() + " ataca com os espinhos da Mantícora!");

        // Ataca o inimigo da mesma linha
        if (adversarios[linhaAtual] != null) {
            adversarios[linhaAtual].receberDano(30);
            System.out.println(adversarios[linhaAtual].getNome() + " recebeu 30 de dano!");
        }

        // Ataca os inimigos das filas adjacentes
        if (linhaAtual > 0 && adversarios[linhaAtual - 1] != null) {
            adversarios[linhaAtual - 1].receberDano(15);
            System.out.println(adversarios[linhaAtual - 1].getNome() + " recebeu 15 de dano!");
        }

        if (linhaAtual < adversarios.length - 1 && adversarios[linhaAtual + 1] != null) {
            adversarios[linhaAtual + 1].receberDano(15);
            System.out.println(adversarios[linhaAtual + 1].getNome() + " recebeu 15 de dano!");
        }
    }

    
    
}
