/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.gregos;

import arena.Arena;
import arena.FilaDeGuerreiros;
import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class Hidra extends GuerreiroGrego{
    
    private int numeroDeCabecas = 1;
    
    public Hidra(String nome, int idade, int peso) {
        super(nome, idade, peso);
    }

    @Override
    public void atacar(Arena arena, int ladoAtacante) {
        // Obtém os defensores do lado oponente
        FilaDeGuerreiros[] defensores = arena.obterDefensores(ladoAtacante);

        // A Hidra ataca o primeiro guerreiro disponível nas filas inimigas
        for (int i = 0; i < defensores.length; i++) {
            Guerreiro adversario = arena.obterAlvoDisponivel(defensores, i);
            if (adversario != null && adversario.estaVivo()) {
                // Atacar o guerreiro
                System.out.println(getNome() + " (Hidra) ataca ferozmente!");
                adversario.receberDano(50 + (5 * (numeroDeCabecas - 1))); // Ataque aumenta com cada cabeça

                // Se o adversário foi morto, ganha uma nova cabeça e recupera energia
                if (!adversario.estaVivo()) {
                    numeroDeCabecas++;
                    System.out.println(getNome() + " ganhou uma nova cabeça! Agora possui " + numeroDeCabecas + " cabeças.");
                    recuperarEnergia();
                }
                break; // Hidra ataca apenas um guerreiro por turno
            }
        }
    }

    private void recuperarEnergia() {
        int novaEnergia = Math.min(100, getEnergia() + 20); // Recupera até o máximo de 100
        setEnergia(novaEnergia);
        System.out.println(getNome() + " recuperou 20 de energia e agora tem " + novaEnergia + " de energia!");
    }
}
