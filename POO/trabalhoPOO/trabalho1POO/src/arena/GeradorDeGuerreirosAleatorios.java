/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arena;

/**
 *
 * @author lucas
 */

import java.io.*;
import java.util.Random;

public class GeradorDeGuerreirosAleatorios {
    private Random random;

    public GeradorDeGuerreirosAleatorios() {
        this.random = new Random();
    }

    // Método para gerar guerreiros aleatórios e salvar no arquivo
    public void gerarGuerreirosAleatorios(String caminhoArquivo, int lado) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            // Definir o número de guerreiros aleatórios a serem gerados
            int quantidadeDeGuerreiros = random.nextInt(5) + 1; // Gera entre 1 e 5 guerreiros aleatórios

            for (int i = 0; i < quantidadeDeGuerreiros; i++) {
                // Gerar atributos aleatórios
                int tipo = random.nextInt(6) + 1; // Tipos de 1 a 6
                String nome = "Guerreiro" + (i + 1);
                int idade = random.nextInt(40) + 18; // Idade entre 18 e 58 anos
                double peso = 60 + (random.nextDouble() * 100); // Peso entre 60kg e 160kg

                // Escrever no arquivo no formato esperado
                bw.write(tipo + " " + nome + " " + idade + " " + String.format("%.2f", peso));
                bw.newLine();
            }

            System.out.println("Guerreiros gerados e salvos no arquivo: " + caminhoArquivo);

        } catch (IOException e) {
            System.out.println("Erro ao gerar guerreiros aleatórios: " + e.getMessage());
        }
    }

    // Método para gerar guerreiros para todos os arquivos das filas de ambos os lados
    public void gerarGuerreirosParaArena() {
        // Gerar guerreiros para as 4 filas do lado 1 (Gregos e Nórdicos)
        gerarGuerreirosAleatorios("fila11.txt", 1);
        gerarGuerreirosAleatorios("fila12.txt", 1);
        gerarGuerreirosAleatorios("fila13.txt", 1);
        gerarGuerreirosAleatorios("fila14.txt", 1);

        // Gerar guerreiros para as 4 filas do lado 2 (Atlantes e Egípcios)
        gerarGuerreirosAleatorios("fila21.txt", 2);
        gerarGuerreirosAleatorios("fila22.txt", 2);
        gerarGuerreirosAleatorios("fila23.txt", 2);
        gerarGuerreirosAleatorios("fila24.txt", 2);
    }
}
