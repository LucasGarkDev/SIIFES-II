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
public class GiganteDePedra extends GuerreiroNordico {

    private boolean habilidadeAtivada = false;

    public GiganteDePedra(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.energia = 300;
        this.dano = 30;
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        int lado = arena.getLado(this);
        FilaDeGuerreiros[] ladoAliado = (lado == 1) ? arena.getLado1() : arena.getLado2();

        if (!habilidadeAtivada) {
            arena.resetarFlagsDeAtaque(ladoAliado);
            FilaDeGuerreiros minhaFila = arena.getFila(lado, arena.encontrarFilaDeGuerreiro(this));
            minhaFila.setDeveSerAtacada(true); // Ativa o flag para que apenas esta fila seja atacada
            habilidadeAtivada = true;
            System.out.println(this.getNome() + " ativou sua habilidade para atrair todos os ataques para sua fila.");
        }

        adversario.sofrerDano(this.dano, arena);
        System.out.println(this.getNome() + " atacou " + adversario.getNome() + " causando " + this.dano + " de dano.");
    }

    public void resetarHabilidade() {
        habilidadeAtivada = false;
    }

    @Override
    public String getNome() {
        return nome + "(Gigante de Pedra)";
    }
}
