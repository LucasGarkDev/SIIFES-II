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
    public void atacar(Guerreiro adversario) {
        // Dano padrão ao adversário
        System.out.println(this.nome + " ataca " + adversario.getNome() + " na mesma fila com seus espinhos!");
        adversario.receberDano(30);

        // Assumindo que o adversário está em uma estrutura que a Manticora pode acessar as filas adjacentes,
        // aplicaremos o dano aos guerreiros adjacentes indiretamente. Isso será feito na classe que gerencia as filas.
    }
    
}
