/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arena;

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
        FilaDeGuerreiros[] ladoAtacanteFilas = (ladoAtacante == 1) ? lado1 : lado2;
        FilaDeGuerreiros[] ladoDefensorFilas = (ladoAtacante == 1) ? lado2 : lado1;

        for (int i = 0; i < 4; i++) {
            FilaDeGuerreiros filaAtacante = ladoAtacanteFilas[i];
            Guerreiro atacante = filaAtacante.getPrimeiroGuerreiro();

            if (atacante != null && !filaAtacante.estaVazia()) {
                // Perguntar à fila qual é o alvo prioritário a ser atacado na fila adversária
                Guerreiro defensor = null;
                for (int j = 0; j < 4; j++) {
                    FilaDeGuerreiros filaDefensora = ladoDefensorFilas[(i + j) % 4];
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

            // Mover o primeiro guerreiro para o final da fila
            filaAtacante.moverPrimeiroParaUltimo();
        }

        // Remover guerreiros mortos após o turno
        removerGuerreirosMortos(ladoAtacante == 1 ? ladoDefensorFilas : ladoAtacanteFilas);
    }

    // Método para remover guerreiros mortos de todas as filas de um lado
    private void removerGuerreirosMortos(FilaDeGuerreiros[] lado) {
        for (FilaDeGuerreiros fila : lado) {
            for (int i = 0; i < fila.getLista().size(); i++) {
                Guerreiro guerreiro = fila.getGuerreiro(i);
                if (guerreiro != null && guerreiro.isEstaMorto()) {
                    fila.removerGuerreiro(i);
                    i--; // Ajustar índice após a remoção
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
        System.out.println("Lado 1: Gregos e Nórdicos");
        for (int i = 0; i < 4; i++) {
            System.out.println("Fila " + (i + 1) + ":");
            lado1[i].listarGuerreiros();
        }

        System.out.println("Lado 2: Atlantes e Egípcios");
        for (int i = 0; i < 4; i++) {
            System.out.println("Fila " + (i + 1) + ":");
            lado2[i].listarGuerreiros();
        }
    }

    // Método para verificar se todos os guerreiros de um lado estão mortos
    public boolean todosGuerreirosMortos(int lado) {
        FilaDeGuerreiros[] filas = (lado == 1) ? lado1 : lado2;
        for (FilaDeGuerreiros fila : filas) {
            if (!fila.estaVazia()) {
                return false;
            }
        }
        return true;
    }

    // Método para iniciar o combate até que um lado seja derrotado
    public void iniciarCombate() {
        while (!todosGuerreirosMortos(1) && !todosGuerreirosMortos(2)) {
            int ladoSorteado = sortearLado();
            System.out.println("Lado " + ladoSorteado + " começa o turno.");

            // Executar o turno do lado sorteado
            executarTurno(ladoSorteado);

            // Exibir estado atual das filas
            exibirGuerreirosDeCadaLado();
        }

        // Exibir o vencedor
        if (todosGuerreirosMortos(1)) {
            System.out.println("Lado 2 (Atlantes e Egípcios) venceu!");
        } else {
            System.out.println("Lado 1 (Gregos e Nórdicos) venceu!");
        }

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
}
