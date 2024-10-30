/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.egipcios;

import arena.Arena;
import arena.FilaDeGuerreiros;
import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class Anubita extends GuerreiroEgipcio{
    
    public Anubita(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.dano = 15;
    }
    
    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        if ((adversario != null) && (!adversario.isEstaMorto())) {
            System.out.println(getNome() + " ataca " + adversario.getNome() + " causando " + dano + " de dano.");
            adversario.sofrerDano(dano,arena);
            FilaDeGuerreiros filaAdversaria = null;
            int indiceFilaAdversaria = arena.encontrarFilaDeGuerreiro(adversario);
            filaAdversaria = arena.getFila(1, indiceFilaAdversaria);
            Guerreiro ultimoFila = filaAdversaria.getUltimoGuerreiro();
            System.out.println(getNome() + " tambem ataca " + ultimoFila.getNome() + " causando " + dano + " de dano.");
            ultimoFila.sofrerDano(dano, arena);
        }
    }
    
   
    
}
