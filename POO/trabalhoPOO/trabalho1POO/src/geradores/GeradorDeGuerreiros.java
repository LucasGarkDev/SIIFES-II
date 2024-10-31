/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package geradores;

/**
 *
 * @author lucas
 */

import java.io.*;
import java.util.Locale;
import java.util.Random;

public class GeradorDeGuerreiros {
    private static final Random random = new Random();

    // Método para gerar guerreiros aleatórios e salvar em arquivos
    public void gerarESalvarGuerreiros(String caminhoArquivo, int lado) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            int quantidadeGuerreiros = random.nextInt(5) + 1; // Entre 1 e 5 guerreiros aleatórios

            for (int i = 0; i < quantidadeGuerreiros; i++) {
                int tipo = random.nextInt(6) + 1; // Tipos de guerreiros de 1 a 6
                String nome = GeradorDeNomes.gerarNomeAleatorio(); // Gerar nome aleatório
                int idade = random.nextInt(40) + 18; // Idade entre 18 e 58 anos
                double peso = 70 + (random.nextDouble() * 100); // Peso entre 70kg e 170kg

                // Salvar no arquivo
                bw.write(tipo + " " + nome + " " + idade + " " + String.format(Locale.US, "%.2f", peso));
                bw.newLine();
            }

            System.out.println("Guerreiros gerados e salvos no arquivo: " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os guerreiros: " + e.getMessage());
        }
    }
    
    public void gerarESalvarGuerreirosParaArena(int lado) {
        // Nomes dos arquivos para cada fila de um lado específico
        String[] arquivos = new String[4];
        for (int i = 0; i < 4; i++) {
            arquivos[i] = "fila" + lado + (i + 1) + ".txt";
            gerarESalvarGuerreiros(arquivos[i], lado); // Chama o método para cada arquivo correspondente à fila
            System.out.println("Guerreiros gerados e salvos no arquivo: " + arquivos[i]);
        }
    }
}
