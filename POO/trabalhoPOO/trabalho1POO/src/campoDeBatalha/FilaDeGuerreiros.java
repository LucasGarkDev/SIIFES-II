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

    // Remove e retorna o primeiro guerreiro da fila
    public Guerreiro removerPrimeiroGuerreiro() {
        return guerreiros.pollFirst();
    }

    // Retorna o guerreiro na primeira posição sem removê-lo
    public Guerreiro obterPrimeiroGuerreiro() {
        return guerreiros.peekFirst();
    }

    // Verifica se a fila tem guerreiros
    public boolean temGuerreiros() {
        return !guerreiros.isEmpty();
    }

    // Retorna o número de guerreiros na fila
    public int tamanho() {
        return guerreiros.size();
    }

    // Obter um guerreiro em uma posição específica
    public Guerreiro obterGuerreiroNaPosicao(int posicao) {
        if (posicao >= 0 && posicao < guerreiros.size()) {
            return guerreiros.get(posicao);
        }
        return null;
    }

    // Obter o último guerreiro da fila
    public Guerreiro obterUltimoGuerreiro() {
        if (!guerreiros.isEmpty()) {
            return guerreiros.getLast();
        }
        return null;
    }

    // Exibe os guerreiros presentes na fila
    public void exibirGuerreiros() {
        for (Guerreiro guerreiro : guerreiros) {
            System.out.println(guerreiro);
        }
    }
}
