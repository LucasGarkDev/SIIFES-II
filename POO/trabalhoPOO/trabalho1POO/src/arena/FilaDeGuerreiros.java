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

    private boolean deveSerAtacada = false; 
    private LinkedList<Guerreiro> guerreiros;

    // Construtor que inicializa a lista de guerreiros
    public FilaDeGuerreiros() {
        guerreiros = new LinkedList<>();
    }

    public void adicionarGuerreiro(Guerreiro guerreiro, int posicao) {
        if ((posicao >= 0) && (posicao <= guerreiros.size())) {
            guerreiros.add(posicao, guerreiro);
        } else {
            System.out.println("Posição inválida!");
        }
    }

    public void adicionarGuerreiroNoFinal(Guerreiro guerreiro) {
        guerreiros.addLast(guerreiro);
    }

    public void removerGuerreiro(int posicao) {
        if ((posicao >= 0) && (posicao < guerreiros.size())) {
            guerreiros.remove(posicao);
        } else {
            System.out.println("Posição inválida para remoção!");
        }
    }

    public int encontrarGuerreiro(Guerreiro guerreiro) {
        return guerreiros.indexOf(guerreiro);
    }

    public boolean estaVazia() {
        return guerreiros.isEmpty();
    }

    public Guerreiro getPrimeiroGuerreiro() {
        return guerreiros.peekFirst();
    }

    public Guerreiro getUltimoGuerreiro() {
        return guerreiros.peekLast();
    }

    public boolean isDeveSerAtacada() {
        return deveSerAtacada;
    }

    public void setDeveSerAtacada(boolean deveSerAtacada) {
        this.deveSerAtacada = deveSerAtacada;
    }
    
    public Guerreiro getGuerreiro(int posicao) {
        if (posicao >= 0 && posicao < guerreiros.size()) {
            return guerreiros.get(posicao);
        }
        System.out.println("Posição inválida!");
        return null;
    }

    public void listarGuerreiros() {
        if (guerreiros.isEmpty()) {
            System.out.println("A lista de guerreiros está vazia.");
        } else {
            for (int i = 0; i < guerreiros.size(); i++) {
                Guerreiro guerreiro = guerreiros.get(i);
                System.out.println(i + " - " + guerreiro);
            }
        }
    }

    public void moverPrimeiroParaUltimo() {
        if (!guerreiros.isEmpty()) {
            Guerreiro primeiro = guerreiros.removeFirst();
            guerreiros.addLast(primeiro);
        }
    }

    public void trocarGuerreiros(int posicao1, int posicao2) {
        if ((posicao1 >= 0) && (posicao1 < guerreiros.size()) && (posicao2 >= 0) && (posicao2 < guerreiros.size())) {
            Guerreiro guerreiro1 = guerreiros.get(posicao1);
            Guerreiro guerreiro2 = guerreiros.get(posicao2);
            guerreiros.set(posicao1, guerreiro2);
            guerreiros.set(posicao2, guerreiro1);
            //System.out.println(guerreiro1.getNome() + " trocou de lugar com " + guerreiro2.getNome());
        } else {
            System.out.println("Posições inválidas para troca.");
        }
    }

    public void moverGuerreiroParaFrente(int posicaoAtual, int novaPosicao) {
        if ((posicaoAtual >= 0) && (posicaoAtual < guerreiros.size()) && (novaPosicao >= 0) && (novaPosicao <= guerreiros.size())) {
            Guerreiro guerreiro = guerreiros.remove(posicaoAtual);
            guerreiros.add(novaPosicao, guerreiro);
            //System.out.println(guerreiro.getNome() + " foi movido da posição " + posicaoAtual + " para a posição " + novaPosicao);
        } else {
            System.out.println("Posições inválidas para movimento.");
        }
    }

    public void interagirComGuerreiros(int posicaoX, Guerreiro guerreiroY) {
        if ((posicaoX >= 0) && (posicaoX < guerreiros.size())) {
            Guerreiro guerreiroX = guerreiros.get(posicaoX);
            int posicaoY = guerreiros.indexOf(guerreiroY);

            if (posicaoY != -1) {
                System.out.println(guerreiroX.getNome() + " (na posição " + posicaoX + ") está interagindo com " + guerreiroY.getNome() + " (na posição " + posicaoY + ").");
            } else {
                System.out.println(guerreiroY.getNome() + " não está na mesma fila.");
            }
        } else {
            System.out.println("Posição inválida para o guerreiro X.");
        }
    }

    // Sobrecarga para interação entre guerreiros de diferentes filas
    public void interagirComGuerreiros(int posicaoX, FilaDeGuerreiros outraFila, Guerreiro guerreiroY) {
        if ((posicaoX >= 0) && (posicaoX < guerreiros.size())) {
            Guerreiro guerreiroX = guerreiros.get(posicaoX);
            int posicaoY = outraFila.encontrarGuerreiro(guerreiroY);

            if (posicaoY != -1) {
                System.out.println(guerreiroX.getNome() + " (na posição " + posicaoX + ") está interagindo com " + guerreiroY.getNome() + " (na posição " + posicaoY + " da outra fila).");
            } else {
                System.out.println(guerreiroY.getNome() + " não está na fila passada como parâmetro.");
            }
        } else {
            System.out.println("Posição inválida para o guerreiro X.");
        }
    }

    public Guerreiro getProximoGuerreiroVivo() {
        for (Guerreiro guerreiro : guerreiros) {
            if (!guerreiro.isEstaMorto()) {
                return guerreiro; 
            }
        }
        return null;  
    }

    public LinkedList<Guerreiro> getLista() {
        return guerreiros;
    }

    public Guerreiro definirAlvoPrioritario(FilaDeGuerreiros filaAdversaria) {
        return filaAdversaria.getProximoGuerreiroVivo();  // Retorna o próximo guerreiro vivo da fila adversária
    }

}
