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
public class Hidra extends Guerreiro{
    
    private int cabecas = 1; // A Hidra começa com 1 cabeça
    
    public Hidra(String nome, int idade, int peso) {
        super(nome, idade, peso);
    }

    @Override
    public void atacar(Guerreiro adversario) {
        int dano = 50 + (5 * (cabecas - 1)); // Dano aumenta 5 por cabeça extra
        System.out.println(this.nome + " ataca " + adversario.getNome() + " com força de uma Hidra!");
        adversario.receberDano(dano);

        // Se a Hidra matar o adversário
        if (!adversario.estaVivo()) {
            cabecas++;
            // Atualizando a energia da Hidra usando o método setter
            int novaEnergia = Math.min(100, this.getEnergia() + 20);
            this.setEnergia(novaEnergia); // Use o método setter para atualizar a energia
            System.out.println(this.nome + " ganhou uma nova cabeça! Agora possui " + cabecas + " cabeças.");
        }
    }
    
}
