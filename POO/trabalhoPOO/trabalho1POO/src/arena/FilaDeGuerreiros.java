/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arena;

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

    private Guerreiro alvoForcado;

    public void definirAlvoForcado(Guerreiro alvo) {
        this.alvoForcado = alvo;
    }

    public Guerreiro obterAlvoForcado() {
        return this.alvoForcado;
    }

    public void limparAlvoForcado() {
        this.alvoForcado = null;
    }

    // Retorna a lista de guerreiros na fila
    public LinkedList<Guerreiro> getGuerreiros() {
        return guerreiros;
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

    // Obter o último guerreiro da fila
    public Guerreiro obterUltimoGuerreiro() {
        if (!guerreiros.isEmpty()) {
            return guerreiros.getLast();
        }
        return null;
    }

    // Retorna o guerreiro em uma posição específica
    public Guerreiro obterGuerreiroNaPosicao(int posicao) {
        if (posicao >= 0 && posicao < guerreiros.size()) {
            return guerreiros.get(posicao);
        }
        return null;
    }

    // Exibe os guerreiros presentes na fila
    public void exibirGuerreiros() {
        for (Guerreiro guerreiro : guerreiros) {
            System.out.println(guerreiro);
        }
    }

    public int obterPosicaoGuerreiro(Guerreiro guerreiro) {
        return guerreiros.indexOf(guerreiro); // Retorna a posição do guerreiro na lista ou -1 se não encontrado
    }

    // Remove um guerreiro específico da fila
    public void removerGuerreiro(Guerreiro guerreiro) {
        if (guerreiros.contains(guerreiro)) {
            guerreiros.remove(guerreiro);
            System.out.println(guerreiro.getNome() + " foi removido da fila, pois sua energia chegou a 0.");
        } else {
            System.out.println("Guerreiro " + guerreiro.getNome() + " não encontrado na fila.");
        }
    }

}
