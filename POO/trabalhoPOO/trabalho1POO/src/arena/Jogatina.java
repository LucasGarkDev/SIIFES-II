/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arena;

import arena.Arena;

// Importar todas as classes do pacote guerreiros.gregos
import guerreiros.gregos.*;

// Importar todas as classes do pacote guerreiros.nordicos
import guerreiros.nordicos.*;

// Importar todas as classes do pacote guerreiros.atlantes
import guerreiros.atlantes.*;

// Importar todas as classes do pacote guerreiros.egipcios
import guerreiros.egipcios.*;

/**
 *
 * @author lucas
 */
public class Jogatina {
    public static void main(String[] args) {
        // Criação da Arena
        Arena arena = new Arena();

        // Gerar guerreiros aleatórios e salvar nos arquivos
        System.out.println("Gerando guerreiros aleatórios e salvando nos arquivos...");
        GeradorDeGuerreirosAleatorios gerador = new GeradorDeGuerreirosAleatorios();
        gerador.gerarGuerreirosParaArena();

        // Leitura dos arquivos e adição dos guerreiros na arena
        System.out.println("Lendo guerreiros dos arquivos e adicionando à arena...");
        LeitorDeGuerreiros leitor = new LeitorDeGuerreiros(arena);
        leitor.montarArena();  // Lê todos os arquivos e insere os guerreiros na arena

        // Exibir guerreiros de cada lado para verificar
        System.out.println("Exibindo guerreiros da arena:");
        arena.exibirGuerreirosDeCadaLado();  // Exibe todos os guerreiros de ambos os lados
    }
}
