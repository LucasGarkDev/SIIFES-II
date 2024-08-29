/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author lucas
 */
public class Combate implements Acoes{
    LinkedList<Personagem> listaDeIniciativa = new LinkedList<>();
    BufferedReader dado = new BufferedReader(new InputStreamReader(System.in));
    String resposta = "";
    public boolean combateAcabou(LinkedList<Personagem> listaDeIniciativa) {
        for (Personagem inimigo : listaDeIniciativa) {
            if (inimigo instanceof Monstro) {
                return false; // Há pelo menos um inimigo, então o combate ainda não acabou
            }
        }
        return true; // Não há inimigos, o combate acabou
    }

    // Método para ordenar a lista em ordem decrescente de iniciativa
    public void ordenarLista(LinkedList<Personagem> listaDePersonagens) {
        Collections.sort(listaDePersonagens, new Comparator<Personagem>() {
            @Override
            public int compare(Personagem p1, Personagem p2) {
                return Integer.compare(p2.getIniciativa(), p1.getIniciativa());
            }
        });
    }
    
    @Override
    public int atacar(String dado, int modificador, Personagem alvo) {
        // Implementação do ataque (depende de como você deseja que seja feita)
        // Exemplo: Retornar o dano causado
        int resultado = rolagem("d20") + modificador;
        if(resultado >= alvo.classeDeArmadura){
            return rolagem()
        }
                
        return 0;
    }

    @Override
    public int magia(Magia magiaEscolhida, int modificador, Personagem alvo) {
        // Implementação do uso de magia (depende de como você deseja que seja feita)
        // Exemplo: Retornar o dano causado ou efeito aplicado
        return 0;
    }

    @Override
    public void usarHabilidade(Personagem personagem) {
        // Implementação do uso de habilidade
    }

    @Override
    public int defender(Personagem alvo) {
        // Implementação da defesa
        return 0;
    }

    @Override
    public int esquivar(Personagem personagem) {
        // Implementação da esquiva
        return 0;
    }

    @Override
    public void guardarAcao(Personagem personagem) {
        // Implementação da ação de guardar
    }

    // Método para escolher a ação do personagem
    public int escolherAcao(Personagem personagem) {
        Scanner scanner = new Scanner(System.in);
        int opcaoEscolhida = 0;

        System.out.println("Turno de: " + personagem.getNome());
        System.out.println("1 - Atacar");
        System.out.println("2 - Magia");
        System.out.println("3 - Usar Habilidade");
        System.out.println("4 - Esquivar");
        System.out.println("5 - Guardar Ação");
        System.out.print("Escolha a ação: ");

        opcaoEscolhida = scanner.nextInt();

        return opcaoEscolhida;
    }

    // Método para realizar a ação escolhida
    public void realizarAcoes(Personagem personagem, Personagem alvo) throws IOException {
        int opcaoEscolhida = escolherAcao(personagem);
        Scanner scanner = new Scanner(System.in);

        switch (opcaoEscolhida) {
            case 1:
                System.out.println("Qual deve ser o alvo do ataque?:");
                String nomeAlvoAtaque = scanner.nextLine();
                // Supondo que você tenha uma maneira de encontrar o alvo pelo nome
                Personagem alvoAtaque = encontrarPersonagemPeloNome(nomeAlvoAtaque);
                personagem.atacar("d20", personagem.calcularModificador(personagem.getForca()), alvoAtaque);
                break;

            case 2:
                System.out.println("Qual deve ser o alvo da magia?:");
                String nomeAlvoMagia = scanner.nextLine();
                Personagem alvoMagia = encontrarPersonagemPeloNome(nomeAlvoMagia);
                Magia magiaEscolhida = escolherMagia(); // Supondo que você tenha um método para escolher a magia
                personagem.magia(magiaEscolhida, personagem.calcularModificador(personagem.getInteligencia()), alvoMagia);
                break;

            case 3:
                usarHabilidade(personagem);
                break;

            case 4:
                esquivar(personagem);
                break;

            case 5:
                guardarAcao(personagem);
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    // Método auxiliar para encontrar um personagem pelo nome na lista de iniciativa
    private Personagem encontrarPersonagemPeloNome(String nome) {
        for (Personagem p : listaDeIniciativa) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null; // Ou pode lançar uma exceção se o personagem não for encontrado
    }

    // Método auxiliar para escolher magia (deve ser implementado de acordo com sua lógica)
    private Magia escolherMagia() {
        // Implementação de seleção de magia
        return null; // Substituir com a lógica apropriada
    }

    
    public void iniciarCombate(LinkedList<Personagem> listaDeIniciativa) throws IOException{
        ordenarLista(listaDeIniciativa);
        boolean encerrarCombate = false;
        String alvo;
        do{
            for (Personagem atual : listaDeIniciativa){
                System.out.println("Qual personagem sera o alvo da açao?:");
                resposta = dado.readLine();
                alvo = pesquisarNaLista(listaDeIniciativa,resposta);
                realizarAcoes(atual,alvo);
            }
            encerrarCombate = combateAcabou(listaDeIniciativa);
        }while(encerrarCombate != true);
    }

}
