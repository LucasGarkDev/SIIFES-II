/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arena;
import java.util.Random;

/**
 *
 * @author lucas
 */
public class Arena {
    private FilaDeGuerreiros[] lado1; // Gregos e Nórdicos
    private FilaDeGuerreiros[] lado2; // Atlantes e Egípcios
    private Random random;

    public Arena() {
        // Inicializando as filas para cada lado
        lado1 = new FilaDeGuerreiros[4];
        lado2 = new FilaDeGuerreiros[4];

        for (int i = 0; i < 4; i++) {
            lado1[i] = new FilaDeGuerreiros();
            lado2[i] = new FilaDeGuerreiros();
        }

        random = new Random();
    }

    // Método para executar um turno completo de ataque
    public void realizarTurno() {
        System.out.println("\n=== Iniciando o turno ===");

        boolean lado1AtacaPrimeiro = sortearLadoInicia();

        if (lado1AtacaPrimeiro) {
            System.out.println("\nLado 1 (Gregos e Nórdicos) atacando:");
            executarAtaques(1); // Lado 1 ataca
            System.out.println("\nLado 2 (Atlantes e Egípcios) atacando:");
            executarAtaques(2); // Lado 2 ataca
        } else {
            System.out.println("\nLado 2 (Atlantes e Egípcios) atacando:");
            executarAtaques(2); // Lado 2 ataca
            System.out.println("\nLado 1 (Gregos e Nórdicos) atacando:");
            executarAtaques(1); // Lado 1 ataca
        }

        // Verificar se um lado foi derrotado
        if (ladoDerrotado(1)) {
            System.out.println("\nLado 1 foi derrotado!");
        } else if (ladoDerrotado(2)) {
            System.out.println("\nLado 2 foi derrotado!");
        } else {
            System.out.println("\nO turno foi concluído. Nenhum lado foi derrotado.");
        }
    }

    // Método para obter a fila com base no lado e na posição
    public FilaDeGuerreiros obterFila(int lado, int posicaoFila) {
        if (lado == 1) {
            return lado1[posicaoFila];
        } else if (lado == 2) {
            return lado2[posicaoFila];
        }
        return null;
    }

    // Método que define qual lado inicia o ataque
    private boolean sortearLadoInicia() {
        return random.nextBoolean();
    }

    // Executa os ataques para o lado especificado
    private void executarAtaques(int ladoAtacante) {
        FilaDeGuerreiros[] atacantes = ladoAtacante == 1 ? lado1 : lado2;
        FilaDeGuerreiros[] defensores = ladoAtacante == 1 ? lado2 : lado1;

        for (int i = 0; i < 4; i++) {
            Guerreiro atacante = atacantes[i].obterPrimeiroGuerreiro();

            if (atacante != null) {
                Guerreiro alvo = obterAlvoDisponivel(defensores, i);
                if (alvo != null) {
                    System.out.println(atacante.getNome() + " está atacando " + alvo.getNome());
                    atacante.atacar(this, ladoAtacante); // Passa a arena para o ataque
                } else {
                    System.out.println(atacante.getNome() + " não encontrou inimigos na frente.");
                }
            } else {
                System.out.println("Nenhum guerreiro disponível na fila " + (i + 1) + " para atacar.");
            }
        }
    }

    // Obtém o primeiro alvo disponível na fila adversária
    public Guerreiro obterAlvoDisponivel(FilaDeGuerreiros[] defensores, int linhaAtacante) {
        for (int i = linhaAtacante; i < defensores.length; i++) {
            if (defensores[i].temGuerreiros()) {
                return defensores[i].obterPrimeiroGuerreiro();
            }
        }
        // Se não houver guerreiros à frente, verifica desde o início
        for (int i = 0; i < linhaAtacante; i++) {
            if (defensores[i].temGuerreiros()) {
                return defensores[i].obterPrimeiroGuerreiro();
            }
        }
        return null; // Nenhum alvo disponível
    }

    // Verifica se o lado foi derrotado (se todas as filas estiverem vazias)
    public boolean ladoDerrotado(int lado) {
        FilaDeGuerreiros[] filas = lado == 1 ? lado1 : lado2;
        for (FilaDeGuerreiros fila : filas) {
            if (fila.temGuerreiros()) {
                return false;
            }
        }
        return true; // Todas as filas estão vazias
    }

    // Obter o início e o fim de uma fila de guerreiros de um lado específico
    public Guerreiro obterPrimeiroGuerreiro(int lado, int fila) {
        return lado == 1 ? lado1[fila].obterPrimeiroGuerreiro() : lado2[fila].obterPrimeiroGuerreiro();
    }

    public Guerreiro obterUltimoGuerreiro(int lado, int fila) {
        return lado == 1 ? lado1[fila].obterUltimoGuerreiro() : lado2[fila].obterUltimoGuerreiro();
    }

    // Interação com os guerreiros atrás do atacante em sua própria fila
    public Guerreiro obterGuerreiroAtras(int lado, int fila, int posicao) {
        if (lado == 1) {
            return lado1[fila].obterGuerreiroNaPosicao(posicao + 1);
        } else {
            return lado2[fila].obterGuerreiroNaPosicao(posicao + 1);
        }
    }

    // Obter as filas defensivas com base no lado do atacante
    public FilaDeGuerreiros[] obterDefensores(int ladoAtacante) {
        return ladoAtacante == 1 ? lado2 : lado1;
    }
}
