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
public class Teste {

    public static void main(String[] args) {
         // Criar uma nova arena
        Arena arena = new Arena();

        // Adicionar guerreiros Gregos e Nórdicos no lado 1
        GuerreiroNordico gigante = new GiganteDePedra("Lasquinha", 50, 300.0); // Manticora Grega
//        GuerreiroGrego guerreiroGregoB = new GuerreiroGrego("Guerreiro Grego B", 35, 80.0);
        GuerreiroNordico guerreiroNordicoA = new GuerreiroNordico("Guerreiro Nórdico A", 40, 90.0);
//        GuerreiroNordico guerreiroNordicoB = new GuerreiroNordico("Guerreiro Nórdico B", 38, 95.0);

        // Adicionar guerreiros Atlantes e Egípcios no lado 2
        GuerreiroAtlante guerreiroAtlanteA = new GuerreiroAtlante("Guerreiro Atlante A", 30, 75.0);
        GuerreiroAtlante guerreiroAtlanteB = new GuerreiroAtlante("Guerreiro Atlante B", 32, 70.0);
        GuerreiroEgipcio guerreiroEgipcioA = new GuerreiroEgipcio("Guerreiro Egípcio A", 45, 85.0);
        GuerreiroEgipcio guerreiroEgipcioB = new GuerreiroEgipcio("Guerreiro Egípcio B", 40, 90.0);

        // Inserir os guerreiros nas filas da arena (lado 1: Gregos e Nórdicos, lado 2: Atlantes e Egípcios)
        arena.getFila(1, 1).adicionarGuerreiroNoFinal(gigante);
        arena.getFila(1, 0).adicionarGuerreiroNoFinal(guerreiroNordicoA);
//        arena.getFila(1, 2).adicionarGuerreiroNoFinal(guerreiroNordicoA);
//        arena.getFila(1, 3).adicionarGuerreiroNoFinal(guerreiroNordicoB);

        arena.getFila(2, 0).adicionarGuerreiroNoFinal(guerreiroAtlanteA);
        arena.getFila(2, 1).adicionarGuerreiroNoFinal(guerreiroAtlanteB);
        arena.getFila(2, 1).adicionarGuerreiroNoFinal(guerreiroEgipcioA);
        arena.getFila(2, 2).adicionarGuerreiroNoFinal(guerreiroEgipcioB);

        // Exibir o estado inicial da arena
        System.out.println("Estado inicial da Arena:");
        arena.exibirGuerreirosDeCadaLado();

        // Iniciar o combate para testar a Manticora
        arena.iniciarCombate();

        // Exibir o resultado final da arena após a batalha
        System.out.println("Resultado final:");
        arena.exibirGuerreirosDeCadaLado();
    }
}
