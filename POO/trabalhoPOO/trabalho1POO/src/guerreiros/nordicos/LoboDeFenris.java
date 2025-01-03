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
        this.dano = 40; 
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        int bonusDeDano = calcularBonusDeDano(arena);
        int danoTotal = this.dano + bonusDeDano;
        
        if(adversario != null){
            adversario.sofrerDano(danoTotal,arena);
            //System.out.println(this.getNome() + "atacou " + adversario.getNome() + " causando " + danoTotal + " de dano.");
        }
    }

    // Método para calcular o bônus de dano com base nos LobosDeFenris atrás do atacante
    private int calcularBonusDeDano(Arena arena) {
        int bonusDeDano = 0;
        FilaDeGuerreiros filaDoLobo = arena.getFila(1, arena.encontrarFilaDeGuerreiro(this)); 
        int posicaoLobo = filaDoLobo.encontrarGuerreiro(this);

        // Verifica os guerreiros atrás do LoboDeFenris
        for (int i = posicaoLobo + 1; i < filaDoLobo.getLista().size(); i++) {
            Guerreiro guerreiroAtras = filaDoLobo.getGuerreiro(i);
            if (guerreiroAtras instanceof LoboDeFenris) {
                bonusDeDano += 8; 
            } else {
                break;
            }
        }

        return bonusDeDano;
    }
   

}

