/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campoDeBatalha;
import java.util.Random;

/**
 *
 * @author lucas
 */
public class Jogo {
    private FilaDeGuerreiros[] lado1; // Gregos e Nórdicos
    private FilaDeGuerreiros[] lado2; // Atlantes e Egípcios
    private Random random;

    public Jogo() {
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
        boolean lado1AtacaPrimeiro = sortearLadoInicia();

        if (lado1AtacaPrimeiro) {
            executarAtaques(lado1, lado2);
            executarAtaques(lado2, lado1);
        } else {
            executarAtaques(lado2, lado1);
            executarAtaques(lado1, lado2);
        }
    }

    // Método que define qual lado inicia
    private boolean sortearLadoInicia() {
        return random.nextBoolean();
    }

    // Executa os ataques de todas as filas de um lado
    private void executarAtaques(FilaDeGuerreiros[] atacantes, FilaDeGuerreiros[] defensores) {
        for (int i = 0; i < 4; i++) {
            Guerreiro atacante = atacantes[i].obterPrimeiroGuerreiro();

            if (atacante != null) {
                // Encontrar o alvo na mesma linha
                Guerreiro alvo = obterAlvo(defensores, i);
                if (alvo != null) {
                    atacante.atacar(alvo);
                }
            }
        }
    }

    // Método que encontra o alvo apropriado
    private Guerreiro obterAlvo(FilaDeGuerreiros[] defensores, int linha) {
        // Prioridade para a fila da mesma linha
        if (defensores[linha].temGuerreiros()) {
            return defensores[linha].obterPrimeiroGuerreiro();
        }

        // Caso a fila da mesma linha esteja vazia, procura nas filas adjacentes
        for (int offset = 1; offset < 4; offset++) {
            int proximaLinha = (linha + offset) % 4;
            if (defensores[proximaLinha].temGuerreiros()) {
                return defensores[proximaLinha].obterPrimeiroGuerreiro();
            }
        }

        return null; // Nenhum alvo disponível
    }
}
