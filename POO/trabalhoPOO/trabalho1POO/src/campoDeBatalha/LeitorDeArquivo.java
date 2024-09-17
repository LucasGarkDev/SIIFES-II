/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campoDeBatalha;

import guerreiros.atlantes.Argus;
import guerreiros.atlantes.Prometeano;
import guerreiros.atlantes.Satiro;
import guerreiros.egipcios.Anubita;
import guerreiros.egipcios.HomemEscorpiao;
import guerreiros.egipcios.Mumia;
import guerreiros.gregos.Ciclope;
import guerreiros.gregos.Hidra;
import guerreiros.gregos.Manticora;
import guerreiros.nordicos.Valquiria;
import guerreiros.nordicos.LoboDeFenris;
import guerreiros.nordicos.GiganteDePedra;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author lucas
 */
public class LeitorDeArquivo {
     public static LinkedList<Guerreiro> carregarGuerreirosDeArquivo(String caminhoArquivo, int lado) {
        LinkedList<Guerreiro> guerreiros = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Exemplo de linha: "1 NitTe 30 70"
                String[] dados = linha.split(" ");
                int tipo = Integer.parseInt(dados[0]);
                String nome = dados[1];
                int idade = Integer.parseInt(dados[2]);
                int peso = Integer.parseInt(dados[3]);

                // Cria o guerreiro com base no lado e no tipo
                Guerreiro guerreiro = criarGuerreiro(tipo, nome, idade, peso, lado);
                if (guerreiro != null) {
                    guerreiros.add(guerreiro);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return guerreiros;
    }

    private static Guerreiro criarGuerreiro(int tipo, String nome, int idade, int peso, int lado) {
        // Lado 1: Gregos e Nórdicos
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
                    System.out.println("Tipo de guerreiro inválido para o lado 1: " + tipo);
                    return null;
            }
        }
        // Lado 2: Atlantes e Egípcios
        else if (lado == 2) {
            switch (tipo) {
                case 1:
                    return new Prometeano(nome, idade, peso);
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
                    System.out.println("Tipo de guerreiro inválido para o lado 2: " + tipo);
                    return null;
            }
        }

        System.out.println("Lado inválido: " + lado);
        return null;
    }
}