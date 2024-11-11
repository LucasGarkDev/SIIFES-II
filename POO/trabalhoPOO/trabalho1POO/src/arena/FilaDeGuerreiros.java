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

    private boolean deveSerAtacada = false; // Flag para indicar se a fila deve ser atacada prioritariamente
    private LinkedList<Guerreiro> guerreiros;

    // Construtor que inicializa a lista de guerreiros
    public FilaDeGuerreiros() {
        guerreiros = new LinkedList<>();
    }

    // Adicionar um guerreiro em qualquer posição da lista
    public void adicionarGuerreiro(Guerreiro guerreiro, int posicao) {
        if (posicao >= 0 && posicao <= guerreiros.size()) {
            guerreiros.add(posicao, guerreiro);
        } else {
            System.out.println("Posição inválida!");
        }
    }

    // Adicionar um guerreiro no final da lista
    public void adicionarGuerreiroNoFinal(Guerreiro guerreiro) {
        guerreiros.addLast(guerreiro);
    }

    // Remover um guerreiro de qualquer posição da lista
    public void removerGuerreiro(int posicao) {
        if (posicao >= 0 && posicao < guerreiros.size()) {
            guerreiros.remove(posicao);
        } else {
            System.out.println("Posição inválida para remoção!");
        }
    }

    // Encontrar o índice de um guerreiro na lista
    public int encontrarGuerreiro(Guerreiro guerreiro) {
        return guerreiros.indexOf(guerreiro);
    }

    // Verificar se a lista está vazia
    public boolean estaVazia() {
        return guerreiros.isEmpty();
    }

    // Retornar o primeiro guerreiro da lista
    public Guerreiro getPrimeiroGuerreiro() {
        return guerreiros.peekFirst();
    }

    // Retornar o último guerreiro da lista
    public Guerreiro getUltimoGuerreiro() {
        return guerreiros.peekLast();
    }

    public boolean isDeveSerAtacada() {
        return deveSerAtacada;
    }

    public void setDeveSerAtacada(boolean deveSerAtacada) {
        this.deveSerAtacada = deveSerAtacada;
    }
    
    // Retornar um guerreiro em uma posição específica
    public Guerreiro getGuerreiro(int posicao) {
        if (posicao >= 0 && posicao < guerreiros.size()) {
            return guerreiros.get(posicao);
        }
        System.out.println("Posição inválida!");
        return null;
    }

    // Listar todos os guerreiros presentes na lista
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

    // Mover o primeiro guerreiro para o final da lista
    public void moverPrimeiroParaUltimo() {
        if (!guerreiros.isEmpty()) {
            Guerreiro primeiro = guerreiros.removeFirst();
            guerreiros.addLast(primeiro);
        }
    }

    // Trocar de posição dois guerreiros da mesma fila
    public void trocarGuerreiros(int posicao1, int posicao2) {
        if (posicao1 >= 0 && posicao1 < guerreiros.size() && posicao2 >= 0 && posicao2 < guerreiros.size()) {
            Guerreiro guerreiro1 = guerreiros.get(posicao1);
            Guerreiro guerreiro2 = guerreiros.get(posicao2);
            guerreiros.set(posicao1, guerreiro2);
            guerreiros.set(posicao2, guerreiro1);
            System.out.println(guerreiro1.getNome() + " trocou de lugar com " + guerreiro2.getNome());
        } else {
            System.out.println("Posições inválidas para troca.");
        }
    }

    // Mover um guerreiro para uma nova posição e mover os guerreiros que estavam atrás dele para a frente
    public void moverGuerreiroParaFrente(int posicaoAtual, int novaPosicao) {
        if (posicaoAtual >= 0 && posicaoAtual < guerreiros.size() && novaPosicao >= 0 && novaPosicao <= guerreiros.size()) {
            Guerreiro guerreiro = guerreiros.remove(posicaoAtual);
            guerreiros.add(novaPosicao, guerreiro);
            System.out.println(guerreiro.getNome() + " foi movido da posição " + posicaoAtual + " para a posição " + novaPosicao);
        } else {
            System.out.println("Posições inválidas para movimento.");
        }
    }

    // Método para encontrar a posição de um guerreiro Y dado um guerreiro X na mesma fila
    public void interagirComGuerreiros(int posicaoX, Guerreiro guerreiroY) {
        if (posicaoX >= 0 && posicaoX < guerreiros.size()) {
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
        if (posicaoX >= 0 && posicaoX < guerreiros.size()) {
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

    // Método para obter o próximo guerreiro vivo na fila (caso o primeiro esteja morto)
    public Guerreiro getProximoGuerreiroVivo() {
        for (Guerreiro guerreiro : guerreiros) {
            if (!guerreiro.isEstaMorto()) {
                return guerreiro;  // Retorna o primeiro guerreiro vivo encontrado
            }
        }
        return null;  // Retorna null se todos estiverem mortos
    }

    // Retornar a lista de guerreiros
    public LinkedList<Guerreiro> getLista() {
        return guerreiros;
    }

    // Método para definir o alvo prioritário a ser atacado em uma fila adversária
    public Guerreiro definirAlvoPrioritario(FilaDeGuerreiros filaAdversaria) {
        return filaAdversaria.getProximoGuerreiroVivo();  // Retorna o próximo guerreiro vivo da fila adversária
    }

}
