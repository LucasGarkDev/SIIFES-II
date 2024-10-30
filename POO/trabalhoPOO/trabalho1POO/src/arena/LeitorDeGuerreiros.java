/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arena;

import java.io.*;
import java.util.*;
// Importar todas as classes do pacote guerreiros.gregos
import guerreiros.gregos.*;

// Importar todas as classes do pacote guerreiros.nordicos
import guerreiros.nordicos.*;

// Importar todas as classes do pacote guerreiros.atlantes
import guerreiros.atlantes.*;

// Importar todas as classes do pacote guerreiros.egipcios
import guerreiros.egipcios.*;

public class LeitorDeGuerreiros {

    private Arena arena;

    public LeitorDeGuerreiros(Arena arena) {
        this.arena = arena;
    }

    // Método para ler guerreiros do arquivo e adicioná-los na arena
    public void lerArquivo(String caminhoArquivo, int lado, int fila) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ");
                int tipo = Integer.parseInt(dados[0]);
                String nome = dados[1];
                int idade = Integer.parseInt(dados[2]);
                // Substitui vírgulas por pontos antes de converter para double
                double peso = Double.parseDouble(dados[3].replace(",", "."));

                // Cria o guerreiro baseado no tipo e adiciona na fila correta
                Guerreiro guerreiro = criarGuerreiro(tipo, nome, idade, peso, lado);
                if (guerreiro != null) {
                    // Use o método correto para adicionar o guerreiro no final da fila
                    arena.getFila(lado, fila).adicionarGuerreiroNoFinal(guerreiro);
                    System.out.println("Guerreiro " + guerreiro.getNome() + " adicionado à fila " + (fila + 1) + " do lado " + lado);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao formatar número: " + e.getMessage());
        }
    }

    // Método para criar o guerreiro baseado no tipo (lado 1)
    private Guerreiro criarGuerreiro(int tipo, String nome, int idade, double peso, int lado) {
        if (lado == 1) {
            switch (tipo) {
                case 1:
                    return new Ciclope(nome, idade, peso);
                case 2:
                    return new Manticora(nome, idade, peso);
                case 3:
                    return new Hidra(nome, idade, peso);
                case 4:
                    return new Valquiria(nome, idade, peso);
                case 5:
                    return new LoboDeFenris(nome, idade, peso);
                case 6:
                    return new GiganteDePedra(nome, idade, peso);
                default:
                    throw new IllegalArgumentException("Tipo de guerreiro inválido: " + tipo);
            }
        } else if (lado == 2) {
            // Lado 2: Criando guerreiros do lado 2 (Atlantes e Egípcios)
            switch (tipo) {
                case 1:
                    return new Prometeano(nome, idade, peso, 100);
                case 2:
                    return new Satiro(nome, idade, peso);
                case 3:
                    return new Argus(nome, idade, peso);
                case 4:
                    return new Anubita(nome, idade, peso);
                case 5:
                    return new HomemEscorpiao(nome, idade, peso);
                case 6:
                    return new Mumia(nome, idade, peso);
                default:
                    throw new IllegalArgumentException("Tipo de guerreiro inválido para o lado 2: " + tipo);
            }
        }
        return null;
    }

    // Leitura dos arquivos e montagem da arena
    public void montarArena() {
        // Lado 1: Gregos e Nórdicos
        lerArquivo("fila11.txt", 1, 0);
        lerArquivo("fila12.txt", 1, 1);
        lerArquivo("fila13.txt", 1, 2);
        lerArquivo("fila14.txt", 1, 3);

        // Lado 2: Atlantes e Egípcios
        lerArquivo("fila21.txt", 2, 0);
        lerArquivo("fila22.txt", 2, 1);
        lerArquivo("fila23.txt", 2, 2);
        lerArquivo("fila24.txt", 2, 3);
    }
}
