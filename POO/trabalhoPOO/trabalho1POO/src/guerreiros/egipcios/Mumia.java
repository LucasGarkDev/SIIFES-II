/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.egipcios;

import arena.Arena;
import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class Mumia extends GuerreiroEgipcio {

    // Construtor
    public Mumia(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.energia = 100;
        this.dano = 50; 
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        if (adversario != null && !adversario.isEstaMorto()) {
            //System.out.println(getNome() + " ataca " + adversario.getNome() + " causando " + dano + " de dano.");
            adversario.sofrerDano(dano, arena);

            if (adversario.isEstaMorto()) {
                //System.out.println(getNome() + " matou " + adversario.getNome() + ".");
                criarMortoVivo(arena, adversario);
            }
        }
    }

    @Override
    public void sofrerDano(int quantidade, Arena arena) {
        if (this.energia - quantidade <= 0) {
            criarAnubitas(arena);
        }

        super.sofrerDano(quantidade, arena);
    }

    private void criarMortoVivo(Arena arena, Guerreiro adversarioMorto) {
        MortoVivo mortoVivo = new MortoVivo(adversarioMorto.getNome(), adversarioMorto.getIdade(), adversarioMorto.getPeso());

        int indiceFila = arena.encontrarFilaDeGuerreiro(this);
        arena.getFila(2, indiceFila).adicionarGuerreiroNoFinal(mortoVivo);

        //System.out.println("Um Morto-Vivo surge com o nome " + adversarioMorto.getNome() + " no final da fila.");
    }

    private void criarAnubitas(Arena arena) {
        //System.out.println(this.getNome() + " morreu, mas 4 Anubitas surgem em seu lugar!");

        int indiceFila = arena.encontrarFilaDeGuerreiro(this);
        for (int i = 0; i < 4; i++) {
            Anubita anubita = new Anubita(this.getNome(), 0, 60.0);
            arena.getFila(2, indiceFila).adicionarGuerreiroNoFinal(anubita);
        }
    }
    

}
