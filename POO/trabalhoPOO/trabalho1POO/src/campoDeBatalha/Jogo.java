/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campoDeBatalha;

import guerreiros.gregos.Ciclope;
import guerreiros.gregos.Manticora;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author lucas
 */
public class Jogo {
    private FilaDeGuerreiros[] lado1;
    private FilaDeGuerreiros[] lado2;
    private Random random;

    public Jogo() {
        // Inicializa as 4 filas para cada lado
        lado1 = new FilaDeGuerreiros[4];
        lado2 = new FilaDeGuerreiros[4];

        for (int i = 0; i < 4; i++) {
            lado1[i] = new FilaDeGuerreiros();
            lado2[i] = new FilaDeGuerreiros();
        }
    }

    // Método para carregar os guerreiros de todos os arquivos
    public void carregarGuerreiros() {
        String[] arquivosLado1 = {"fila11.txt", "fila12.txt", "fila13.txt", "fila14.txt"};
        String[] arquivosLado2 = {"fila21.txt", "fila22.txt", "fila23.txt", "fila24.txt"};

        // Carrega os guerreiros para o lado 1
        for (int i = 0; i < 4; i++) {
            LinkedList<Guerreiro> guerreiros = LeitorDeArquivo.carregarGuerreirosDeArquivo(arquivosLado1[i], 1);
            for (Guerreiro guerreiro : guerreiros) {
                lado1[i].adicionarGuerreiro(guerreiro);
            }
        }

        // Carrega os guerreiros para o lado 2
        for (int i = 0; i < 4; i++) {
            LinkedList<Guerreiro> guerreiros = LeitorDeArquivo.carregarGuerreirosDeArquivo(arquivosLado2[i], 2);
            for (Guerreiro guerreiro : guerreiros) {
                lado2[i].adicionarGuerreiro(guerreiro);
            }
        }
    }
    
    // Método para sortear qual lado inicia
    private boolean sortearLadoInicia() {
        return random.nextBoolean(); // true para lado1 começar, false para lado2
    }

    // Método para iniciar o combate
    public void iniciarCombate() {
        boolean lado1AtacaPrimeiro = sortearLadoInicia();
        System.out.println(lado1AtacaPrimeiro ? "Lado 1 começa!" : "Lado 2 começa!");

        while (temGuerreirosVivos(lado1) && temGuerreirosVivos(lado2)) {
            if (lado1AtacaPrimeiro) {
                realizarTurno(lado1, lado2);
                realizarTurno(lado2, lado1);
            } else {
                realizarTurno(lado2, lado1);
                realizarTurno(lado1, lado2);
            }

            // Move os guerreiros para o final das filas se ainda estiverem vivos
            rodarFilas(lado1);
            rodarFilas(lado2);
        }

        // Verifica quem venceu
        if (temGuerreirosVivos(lado1)) {
            System.out.println("Lado 1 venceu!");
        } else {
            System.out.println("Lado 2 venceu!");
        }
    }

    // Realiza o ataque de cada fila do lado atacante na respectiva fila do lado defensor
    private void realizarTurno(FilaDeGuerreiros[] atacante, FilaDeGuerreiros[] defensor) {
        for (int i = 0; i < 4; i++) {
            Guerreiro guerreiroAtacante = atacante[i].obterPrimeiroGuerreiro();
            Guerreiro guerreiroDefensor = defensor[i].obterPrimeiroGuerreiro();

            if (guerreiroAtacante != null && guerreiroDefensor != null) {
                if (guerreiroAtacante instanceof Manticora) {
                    ((Manticora) guerreiroAtacante).atacar(defensor, i);
                } else if (guerreiroAtacante instanceof Ciclope) {
                    ((Ciclope) guerreiroAtacante).atacar(guerreiroDefensor, defensor[i]);
                } else {
                    guerreiroAtacante.atacar(guerreiroDefensor);
                }

                // Verifica se o guerreiro defensor morreu e precisa ser removido
                if (!guerreiroDefensor.estaVivo()) {
                    defensor[i].removerPrimeiroGuerreiro();
                    System.out.println(guerreiroDefensor.getNome() + " foi derrotado!");
                }
            }
        }
    }

    // Move os guerreiros das filas para o final, se ainda estiverem vivos
    private void rodarFilas(FilaDeGuerreiros[] lado) {
        for (FilaDeGuerreiros fila : lado) {
            fila.moverParaFinalSeVivo();
        }
    }

    // Verifica se ainda há guerreiros vivos em todas as filas de um lado
    private boolean temGuerreirosVivos(FilaDeGuerreiros[] lado) {
        for (FilaDeGuerreiros fila : lado) {
            if (fila.temGuerreiros()) {
                return true;
            }
        }
        return false;
    }
}
