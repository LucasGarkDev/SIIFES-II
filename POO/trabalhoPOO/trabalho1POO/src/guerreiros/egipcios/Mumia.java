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
        this.dano = 50; // Dano padrão da Mumia
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        // Define a arena para a Mumia
        this.arena = arena;

        // A Mumia ataca o adversário e verifica se o adversário morreu
        if (adversario != null && !adversario.isEstaMorto()) {
            System.out.println(getNome() + "(Mumia) ataca " + adversario.getNome() + " causando " + dano + " de dano.");
            adversario.sofrerDano(dano, arena);

            // Se o adversário morrer, cria um MortoVivo e adiciona ao final da fila
            if (adversario.isEstaMorto()) {
                System.out.println(getNome() + " matou " + adversario.getNome() + ".");
                criarMortoVivo(arena, adversario);
            }
        }
    }

    @Override
    public void sofrerDano(int quantidade, Arena arena) {
        // Define a arena quando a Mumia sofre dano
        this.arena = arena;

        // Verifica se a Mumia vai morrer com este dano
        if (this.energia - quantidade <= 0) {
            // Se o dano for fatal, a Mumia cria 4 Anubitas antes de morrer
            criarAnubitas();
        }

        // Aplicar o dano à Mumia
        super.sofrerDano(quantidade, arena);
    }

    private void criarMortoVivo(Arena arena, Guerreiro adversarioMorto) {
        // Cria um MortoVivo com os atributos do adversário morto
        MortoVivo mortoVivo = new MortoVivo(adversarioMorto.getNome(), adversarioMorto.getIdade(), adversarioMorto.getPeso());

        // Encontra a fila onde a Mumia está e adiciona o MortoVivo ao final da fila
        int indiceFila = arena.encontrarFilaDeGuerreiro(this);
        arena.getFila(2, indiceFila).adicionarGuerreiroNoFinal(mortoVivo);

        System.out.println("Um MortoVivo surge com o nome " + adversarioMorto.getNome() + " no final da fila.");
    }

    private void criarAnubitas() {
        // A Mumia cria 4 Anubitas ao morrer
        System.out.println(this.getNome() + " morreu, mas 4 Anubitas surgem em seu lugar!");

        // Encontra a fila onde a Mumia estava
        int indiceFila = arena.encontrarFilaDeGuerreiro(this);

        for (int i = 0; i < 4; i++) {
            // Cada Anubita herda o nome da Mumia, mas com idade = 0 e peso = 60
            Anubita anubita = new Anubita(this.getNome(), 0, 60.0);
            arena.getFila(2, indiceFila).adicionarGuerreiroNoFinal(anubita);
        }
    }
}
