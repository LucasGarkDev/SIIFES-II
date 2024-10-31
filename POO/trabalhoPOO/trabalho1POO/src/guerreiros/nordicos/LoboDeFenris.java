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
public class LoboDeFenris extends GuerreiroNordico {
    
    public LoboDeFenris(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.dano = 40; // Dano inicial base de 40
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        // Calcula o bônus de dano antes de atacar
        int bonusDeDano = calcularBonusDeDano(arena);
        int danoTotal = this.dano + bonusDeDano;
        
        if(adversario != null){
            // O LoboDeFenris ataca o adversário com o dano calculado
            adversario.sofrerDano(danoTotal,arena);
            System.out.println(this.getNome() + "(Lobo de Fenris) atacou " + adversario.getNome() + " causando " + danoTotal + " de dano.");
        }
    }

    // Método para calcular o bônus de dano com base nos LobosDeFenris atrás do atacante
    private int calcularBonusDeDano(Arena arena) {
        int bonusDeDano = 0;
        FilaDeGuerreiros filaDoLobo = arena.getFila(1, arena.encontrarFilaDeGuerreiro(this)); // Lado 1, fila do LoboDeFenris
        int posicaoLobo = filaDoLobo.encontrarGuerreiro(this);

        // Verifica os guerreiros atrás do LoboDeFenris
        for (int i = posicaoLobo + 1; i < filaDoLobo.getLista().size(); i++) {
            Guerreiro guerreiroAtras = filaDoLobo.getGuerreiro(i);
            if (guerreiroAtras instanceof LoboDeFenris) {
                bonusDeDano += 8; // Adiciona 8 de dano por cada LoboDeFenris consecutivo
            } else {
                // Se encontrar um guerreiro que não seja LoboDeFenris, para de somar o bônus
                break;
            }
        }

        return bonusDeDano;
    }
   
}

