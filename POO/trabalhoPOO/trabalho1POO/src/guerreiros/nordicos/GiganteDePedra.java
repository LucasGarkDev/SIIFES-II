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

    private int contadorDeGolpes = 0;
    private boolean habilidadeAtivada = false;

    // Construtor do GiganteDePedra sem receber a Arena diretamente
    public GiganteDePedra(String nome, int idade, double peso) {
        super(nome, idade, peso); // Chama o construtor da classe mãe
        this.energia = 300; // Energia inicial de 300 para o Gigante
        this.dano = 30; // Dano do Gigante de Pedra
    }

    // Método que será chamado sempre que o Gigante de Pedra for atacado
    @Override
    public void sofrerDano(int dano, Arena arena) {
        this.arena = arena;  // Define a arena ao sofrer dano
        super.sofrerDano(dano, arena); // Chama o método original para aplicar o dano
        contadorDeGolpes++; // Incrementa o contador de golpes recebidos
        System.out.println(this.getNome() + " foi atacado! Contador de golpes: " + contadorDeGolpes);

        // Verifica se ele já recebeu golpes de todos os guerreiros adversários
        int filasInimigasNaoVazias = contarFilasNaoVazias(this.arena, 2); // Conta as filas não vazias do lado inimigo

        // Se já recebeu golpes de todos os guerreiros adversários, reverte a camuflagem
        if (contadorDeGolpes >= filasInimigasNaoVazias) {
            reverterCamuflagem(this.arena); // Remove a camuflagem, ou seja, reanima os aliados
            habilidadeAtivada = false; // Reseta a habilidade para o próximo turno
            contadorDeGolpes = 0; // Reseta o contador de golpes
        }
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        this.arena = arena;  // Define a arena ao atacar
        // Verifica se o lado do Gigante foi sorteado para começar atacando
        if (!habilidadeAtivada) {
            System.out.println(this.getNome() + " ativou sua habilidade e deixou seus aliados 'mortos' temporariamente!");
            camuflarAliados(this.arena); // Deixa os aliados mortos temporariamente
            habilidadeAtivada = true; // Marca que a habilidade foi ativada
        }

        // O Gigante ataca normalmente após "camuflar" os aliados
        adversario.sofrerDano(this.dano, arena);
        System.out.println(this.getNome() + " atacou " + adversario.getNome() + " causando " + this.dano + " de dano.");
    }

    // Método para marcar os aliados como "mortos" temporariamente
    private void camuflarAliados(Arena arena) {
        for (int i = 0; i < 4; i++) {
            FilaDeGuerreiros filaAliada = arena.getFila(1, i); // Lado 1 é o lado aliado
            if (filaAliada != null && filaAliada.getPrimeiroGuerreiro() != null && !filaAliada.getPrimeiroGuerreiro().equals(this)) {
                Guerreiro aliado = filaAliada.getPrimeiroGuerreiro();
                aliado.setEstaMorto(true); // Marca os aliados como mortos
                System.out.println(aliado.getNome() + " está temporariamente 'morto'.");
            }
        }
    }

    // Método para reverter a morte dos aliados ao final do turno
    private void reverterCamuflagem(Arena arena) {
        System.out.println(this.getNome() + " está reanimando seus aliados...");
        for (int i = 0; i < 4; i++) {
            FilaDeGuerreiros filaAliada = arena.getFila(1, i); // Lado 1 é o lado aliado
            if (filaAliada != null && filaAliada.getPrimeiroGuerreiro() != null) {
                Guerreiro aliado = filaAliada.getPrimeiroGuerreiro();
                if (aliado.isEstaMorto()) {
                    aliado.setEstaMorto(false); // Reanima os aliados
                    System.out.println(aliado.getNome() + " foi reanimado.");
                }
            }
        }
    }

    private int contarFilasNaoVazias(Arena arena, int lado) {
        int contador = 0;
        for (int i = 0; i < 4; i++) {
            FilaDeGuerreiros fila = arena.getFila(lado, i);
            if (fila != null && !fila.estaVazia()) {
                contador++;
            }
        }
        return contador;
    }
}


