/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arena;

import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author lucas
 */
public class Arena {

    private FilaDeGuerreiros[] lado1; // Gregos e Nórdicos
    private FilaDeGuerreiros[] lado2; // Atlantes e Egípcios
    private Guerreiro ultimoMorto;
    private Guerreiro ultimoAssassino;

    public Arena() {
        lado1 = new FilaDeGuerreiros[4];
        lado2 = new FilaDeGuerreiros[4];

        for (int i = 0; i < 4; i++) {
            lado1[i] = new FilaDeGuerreiros();
            lado2[i] = new FilaDeGuerreiros();
        }
    }

    // Método para acessar uma fila específica de um lado específico
    public FilaDeGuerreiros getFila(int lado, int indice) {
        if (lado == 1 && indice >= 0 && indice < 4) {
            return lado1[indice];
        } else if (lado == 2 && indice >= 0 && indice < 4) {
            return lado2[indice];
        }
        return null; // Retorna null se o lado ou o índice forem inválidos
    }

    // Método para sortear qual lado vai começar o turno
    public int sortearLado() {
        Random random = new Random();
        return random.nextInt(2) + 1; // Retorna 1 para lado1 e 2 para lado2
    }

    // Método para executar o turno de ataque de um lado
    public void executarTurno(int ladoAtacante) {
        // Determina qual lado começa atacando
        FilaDeGuerreiros[] ladoAtacanteFilas = (ladoAtacante == 1) ? lado1 : lado2;
        FilaDeGuerreiros[] ladoDefensorFilas = (ladoAtacante == 1) ? lado2 : lado1;

        // Primeiro, o lado sorteado ataca
        realizarAtaques(ladoAtacanteFilas, ladoDefensorFilas);

        // Em seguida, o lado defensor ataca
        realizarAtaques(ladoDefensorFilas, ladoAtacanteFilas);

        // Remover guerreiros mortos de ambos os lados
        removerGuerreirosMortos(ladoAtacanteFilas);
        removerGuerreirosMortos(ladoDefensorFilas);
    }

    // Novo método para realizar ataques de um lado no outro
    private void realizarAtaques(FilaDeGuerreiros[] ladoAtacante, FilaDeGuerreiros[] ladoDefensor) {
        for (int i = 0; i < 4; i++) {
            FilaDeGuerreiros filaAtacante = ladoAtacante[i];
            Guerreiro atacante = filaAtacante.getPrimeiroGuerreiro();

            // Verifica se o guerreiro atacante está vivo antes de atacar
            if (atacante != null && !atacante.isEstaMorto()) {
                // Pergunta à fila qual é o alvo prioritário a ser atacado na fila adversária
                Guerreiro defensor = null;
                for (int j = 0; j < 4; j++) {
                    FilaDeGuerreiros filaDefensora = ladoDefensor[(i + j) % 4];
                    defensor = filaAtacante.definirAlvoPrioritario(filaDefensora);
                    if (defensor != null) {
                        break;
                    }
                }

                // Executar o ataque se houver um defensor prioritário
                if (defensor != null) {
                    atacante.atacar(this, defensor);
                    if (defensor.isEstaMorto()) {
                        ultimoMorto = defensor;
                        ultimoAssassino = atacante;
                    }
                }
            }

            // Verificar novamente após o ataque se o atacante está morto e removê-lo
            if (atacante != null && atacante.isEstaMorto()) {
                filaAtacante.removerGuerreiro(0); // Remove o primeiro guerreiro morto
            } else {
                // Se o guerreiro não morreu, mover para o final da fila
                filaAtacante.moverPrimeiroParaUltimo();
            }
        }
    }

    // Método para remover guerreiros mortos de todas as filas de um lado
    private void removerGuerreirosMortos(FilaDeGuerreiros[] lado) {
        for (FilaDeGuerreiros fila : lado) {
            Iterator<Guerreiro> iterator = fila.getLista().iterator();
            while (iterator.hasNext()) {
                Guerreiro guerreiro = iterator.next();
                if (guerreiro != null && guerreiro.isEstaMorto()) {
                    iterator.remove(); // Remove guerreiros mortos da lista
                }
            }
        }
    }

    // Método para buscar um guerreiro pelo nome em todos os lados
    public Guerreiro buscarGuerreiroPorNome(String nome) {
        for (FilaDeGuerreiros fila : lado1) {
            for (Guerreiro guerreiro : fila.getLista()) {
                if (guerreiro.getNome().equals(nome)) {
                    return guerreiro;
                }
            }
        }

        for (FilaDeGuerreiros fila : lado2) {
            for (Guerreiro guerreiro : fila.getLista()) {
                if (guerreiro.getNome().equals(nome)) {
                    return guerreiro;
                }
            }
        }

        return null; // Se o guerreiro não for encontrado
    }

    // Método para exibir os guerreiros de cada lado
    public void exibirGuerreirosDeCadaLado() {
        System.out.println("-------------------------------------");
        System.out.println("Lado 1: Gregos e Nórdicos");
        for (int i = 0; i < 4; i++) {
            System.out.println("Fila " + (i + 1) + ":");
            lado1[i].listarGuerreiros();
        }
        System.out.println("-------------------------------------");
        System.out.println("Lado 2: Atlantes e Egípcios");
        for (int i = 0; i < 4; i++) {
            System.out.println("Fila " + (i + 1) + ":");
            lado2[i].listarGuerreiros();
        }
        System.out.println("-------------------------------------");
    }

    // Método para verificar se todos os guerreiros de um lado estão mortos
    public boolean todosGuerreirosMortos(int lado) {
        FilaDeGuerreiros[] filas = (lado == 1) ? lado1 : lado2;
        for (FilaDeGuerreiros fila : filas) {
            if (!fila.estaVazia()) {
                // Verificar se a fila está vazia, ou se restam guerreiros vivos
                for (Guerreiro guerreiro : fila.getLista()) {
                    if (!guerreiro.isEstaMorto()) {
                        return false; // Ainda há guerreiros vivos neste lado
                    }
                }
            }
        }
        return true; // Todos os guerreiros deste lado estão mortos
    }

    // Método para iniciar o combate até que um lado seja derrotado
    public void iniciarCombate() {
        while (!todosGuerreirosMortos(1) && !todosGuerreirosMortos(2)) {
            int ladoSorteado = sortearLado();
            System.out.println("-------------------------------------");
            System.out.println("Lado " + ladoSorteado + " começa o turno.");
            System.out.println("-------------------------------------");

            // Executar o turno do lado sorteado
            executarTurno(ladoSorteado);

            // Exibir o estado atual das filas
            System.out.println("-------------------------------------");
            exibirGuerreirosDeCadaLado();

            // Verificar se um dos lados perdeu após o turno
            if (todosGuerreirosMortos(1)) {
                System.out.println("-------------------------------------");
                System.out.println("Lado 2 (Atlantes e Egípcios) venceu!");
                break;
            } else if (todosGuerreirosMortos(2)) {
                System.out.println("-------------------------------------");
                System.out.println("Lado 1 (Gregos e Nórdicos) venceu!");
                break;
            }
        }

        // Exibir o último guerreiro morto, se houver
        try {
            exibirUltimoGuerreiroMorto();
        } catch (Exception e) {
            System.out.println("Erro ao exibir o último guerreiro morto: " + e.getMessage());
        }
    }

    // Método com exceções para exibir o último guerreiro morto e quem o matou
    public void exibirUltimoGuerreiroMorto() throws Exception {
        if (ultimoMorto == null || ultimoAssassino == null) {
            throw new Exception("Não foi possível identificar o último guerreiro morto ou quem o matou.");
        }
        System.out.println("Último guerreiro morto: " + ultimoMorto.getNome());
        System.out.println("Guerreiro que o matou: " + ultimoAssassino.getNome());
    }

    // Método para permitir a interação entre guerreiros de diferentes filas do mesmo lado
    public void interagirEntreGuerreirosMesmoLado(int lado, int fila1, int fila2) {
        FilaDeGuerreiros f1 = getFila(lado, fila1);
        FilaDeGuerreiros f2 = getFila(lado, fila2);

        if (f1 != null && f2 != null) {
            Guerreiro guerreiro1 = f1.getPrimeiroGuerreiro();
            Guerreiro guerreiro2 = f2.getPrimeiroGuerreiro();

            if (guerreiro1 != null && guerreiro2 != null) {
                System.out.println(guerreiro1.getNome() + " está interagindo com " + guerreiro2.getNome());
            } else {
                System.out.println("Não há guerreiros disponíveis para interagir.");
            }
        }
    }

    // Método para permitir que guerreiros de filas opostas se ataquem
    public void atacarEntreFilasOpostas(int fila1, int fila2) {
        FilaDeGuerreiros f1 = getFila(1, fila1);
        FilaDeGuerreiros f2 = getFila(2, fila2);

        if (f1 != null && f2 != null) {
            Guerreiro atacante = f1.getPrimeiroGuerreiro();
            Guerreiro defensor = f2.getPrimeiroGuerreiro();

            if (atacante != null && defensor != null) {
                atacante.atacar(this, defensor);
                if (defensor.isEstaMorto()) {
                    ultimoMorto = defensor;
                    ultimoAssassino = atacante;
                    System.out.println(atacante.getNome() + " matou " + defensor.getNome());
                }
            } else {
                System.out.println("Não há guerreiros disponíveis para atacar ou defender.");
            }
        }
    }

    public int encontrarFilaDeGuerreiro(Guerreiro guerreiro) {
        // Verificar se o guerreiro está no lado 1 (gregos e nórdicos)
        for (int i = 0; i < lado1.length; i++) {
            if (lado1[i].encontrarGuerreiro(guerreiro) != -1) {
                return i; // Retorna o índice da fila no lado 1
            }
        }

        // Verificar se o guerreiro está no lado 2 (atlantes e egípcios)
        for (int i = 0; i < lado2.length; i++) {
            if (lado2[i].encontrarGuerreiro(guerreiro) != -1) {
                return i; // Retorna o índice da fila no lado 2
            }
        }

        return -1; // Retorna -1 se o guerreiro não for encontrado em nenhuma fila
    }

}
