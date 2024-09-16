/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campoDeBatalha;

import java.util.LinkedList;

/**
 *
 * @author lucas
 */
public class FilaDeGuerreiros {
    private LinkedList<Guerreiro> guerreiros;

    public FilaDeGuerreiros() {
        guerreiros = new LinkedList<>();
    }

    // Adiciona um guerreiro no final da fila
    public void adicionarGuerreiro(Guerreiro guerreiro) {
        guerreiros.addLast(guerreiro);
    }

    // Retorna o primeiro guerreiro da fila
    public Guerreiro obterPrimeiroGuerreiro() {
        return guerreiros.peekFirst();
    }

    // Remove e retorna o primeiro guerreiro da fila
    public Guerreiro removerPrimeiroGuerreiro() {
        return guerreiros.pollFirst();
    }

    // Move o primeiro guerreiro para o final da fila, se ainda estiver vivo
    public void moverParaFinalSeVivo() {
        Guerreiro primeiro = guerreiros.pollFirst();
        if (primeiro != null && primeiro.estaVivo()) {
            guerreiros.addLast(primeiro);
        }
    }

    // Verifica se a fila ainda possui guerreiros vivos
    public boolean temGuerreiros() {
        return !guerreiros.isEmpty();
    }

    // Exibe os guerreiros presentes na fila
    public void exibirGuerreiros() {
        for (Guerreiro guerreiro : guerreiros) {
            System.out.println(guerreiro);
        }
    }
}
