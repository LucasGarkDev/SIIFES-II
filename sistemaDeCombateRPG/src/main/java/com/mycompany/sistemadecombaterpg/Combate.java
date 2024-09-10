/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;

/**
 *
 * @author lucas
 */
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Combate {
    private List<Personagem> personagens;
    private Scanner scanner = new Scanner(System.in);  // Para permitir a escolha de ações pelo usuário

    public Combate(List<Personagem> personagens) {
        this.personagens = personagens;
    }

    public void iniciarCombate() {
        // Ordena os personagens pela iniciativa
        Collections.sort(personagens, (a, b) -> b.getIniciativa() - a.getIniciativa());

        // Loop principal do combate
        while (!combateFinalizado()) {
            for (int i = 0; i < personagens.size(); i++) {
                Personagem personagem = personagens.get(i);
                if (!personagem.estaVivo()) {
                    continue;  // Ignora personagens mortos
                }
                // Exibe as opções de ação para o usuário
                System.out.println("\nTurno de " + personagem.getClass().getSimpleName() + ". O que deseja fazer?");
                int acao = escolherAcao();

                switch (acao) {
                    case 1 -> {
                        // Atacar
                        Personagem alvo = escolherAlvo(personagem);
                        // Verifica se o alvo está esquivando e aplica a lógica de desvantagem
                        if (alvo.isEsquivando()) {
                            System.out.println(alvo.getClass().getSimpleName() + " está esquivando! O ataque será feito com desvantagem.");
                            int resultado = Math.min(RolagemDados.rolarD20(), RolagemDados.rolarD20());  // Rolagem com desvantagem
                            if (resultado + Modificadores.calcularModificador(personagem.getForca()) >= alvo.getClasseDeArmadura()) {
                                personagem.atacar(alvo);  // Ataque é bem-sucedido
                            } else {
                                System.out.println(personagem.getClass().getSimpleName() + " errou o ataque.");
                            }
                        } else {
                            personagem.atacar(alvo);  // Ataque normal
                        }
                        alvo.setEsquivando(false);  // Reset esquiva depois do ataque
                    }
                    case 2 -> {
                        // Usar Habilidade
                        System.out.println("Escolha o tipo de habilidade:");
                        System.out.println("1. Usar em si mesmo\n2. Usar em um alvo");
                        int escolhaHabilidade = scanner.nextInt();

                        if (escolhaHabilidade == 1) {
                            personagem.usarHabilidade();  // Habilidade usada em si mesmo
                        } else if (escolhaHabilidade == 2) {
                            Personagem alvoHabilidade = escolherAlvo(personagem);
                            personagem.usarHabilidade(alvoHabilidade);  // Habilidade usada em outro alvo
                        } else {
                            System.out.println("Escolha inválida.");
                        }
                    }
                    case 3 -> {
                        // Esquivar
                        personagem.setEsquivando(true);  // O personagem agora está esquivando
                        System.out.println(personagem.getClass().getSimpleName() + " está esquivando e os ataques contra ele terão desvantagem até o próximo turno.");
                    }
                    case 4 -> {
                        // Guardar Ação
                        personagens.remove(personagem);
                        personagens.add(personagem);  // Coloca o personagem no final da lista de turnos
                        System.out.println(personagem.getClass().getSimpleName() + " guardou sua ação e moverá no final deste turno.");
                    }
                    default -> {
                        System.out.println("Escolha inválida. Tente novamente.");
                    }
                }
            }
        }
    }

    // Verifica se o combate foi finalizado (todos os oponentes mortos)
    private boolean combateFinalizado() {
        long vivos = personagens.stream().filter(Personagem::estaVivo).count();
        return vivos <= 1;  // Combate acaba quando sobra 1 ou nenhum personagem vivo
    }

    // Método para o usuário escolher a ação
    private int escolherAcao() {
        System.out.println("Escolha uma ação:");
        System.out.println("1. Atacar");
        System.out.println("2. Usar Habilidade");
        System.out.println("3. Esquivar");
        System.out.println("4. Guardar Ação");

        return scanner.nextInt();
    }

    // Método para o usuário escolher o alvo do ataque
    private Personagem escolherAlvo(Personagem atacante) {
        System.out.println("Escolha o alvo:");
        for (int i = 0; i < personagens.size(); i++) {
            if (personagens.get(i).estaVivo() && personagens.get(i) != atacante) {
                System.out.println((i + 1) + ". " + personagens.get(i).getClass().getSimpleName());
            }
        }
        int escolha = scanner.nextInt();
        return personagens.get(escolha - 1);
    }
}



