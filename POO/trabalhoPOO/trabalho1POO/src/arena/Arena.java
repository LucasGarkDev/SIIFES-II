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
    private Random random;

    public Arena() {
        // Inicializando as filas para cada lado
        lado1 = new FilaDeGuerreiros[4];
        lado2 = new FilaDeGuerreiros[4];

        for (int i = 0; i < 4; i++) {
            lado1[i] = new FilaDeGuerreiros();
            lado2[i] = new FilaDeGuerreiros();
        }

        random = new Random();
    }

    // Método para executar um turno completo de ataque
    public void realizarTurno() {
        System.out.println("\n=== Iniciando o turno ===");

        boolean lado1AtacaPrimeiro = sortearLadoInicia();

        if (lado1AtacaPrimeiro) {
            System.out.println("\nLado 1 (Gregos e Nórdicos) atacando:");
            executarAtaques(1); // Lado 1 ataca
            System.out.println("\nLado 2 (Atlantes e Egípcios) atacando:");
            executarAtaques(2); // Lado 2 ataca
        } else {
            System.out.println("\nLado 2 (Atlantes e Egípcios) atacando:");
            executarAtaques(2); // Lado 2 ataca
            System.out.println("\nLado 1 (Gregos e Nórdicos) atacando:");
            executarAtaques(1); // Lado 1 ataca
        }

        // Verificar se um lado foi derrotado
        if (ladoDerrotado(1)) {
            System.out.println("\nLado 1 foi derrotado!");
        } else if (ladoDerrotado(2)) {
            System.out.println("\nLado 2 foi derrotado!");
        } else {
            System.out.println("\nO turno foi concluído. Nenhum lado foi derrotado.");
        }
    }

    // Método para obter a fila com base no lado e na posição
    public FilaDeGuerreiros obterFila(int lado, int indicadorFila) {
        if (lado == 1) {
            return lado1[indicadorFila];
        } else if (lado == 2) {
            return lado2[indicadorFila];
        }
        return null;
    }

    // Sobrecarga do método para obter o guerreiro em uma posição específica da fila
    public Guerreiro obterFila(int lado, int indicadorFila, int posicaoGuerreiro) {
        if (lado == 1) {
            return lado1[indicadorFila].obterGuerreiroNaPosicao(posicaoGuerreiro);
        } else if (lado == 2) {
            return lado2[indicadorFila].obterGuerreiroNaPosicao(posicaoGuerreiro);
        }
        return null;
    }

    // Método que define qual lado inicia o ataque
    private boolean sortearLadoInicia() {
        return random.nextBoolean();
    }

    // Executa os ataques para o lado especificado
    private void executarAtaques(int ladoAtacante) {
        FilaDeGuerreiros[] atacantes = ladoAtacante == 1 ? lado1 : lado2;
        FilaDeGuerreiros[] defensores = ladoAtacante == 1 ? lado2 : lado1;

        for (int i = 0; i < 4; i++) {
            Guerreiro atacante = atacantes[i].obterPrimeiroGuerreiro();
            Guerreiro alvo = null;

            // Verifica se há um alvo forçado (quando o Gigante de Pedra ativa sua habilidade)
            if (defensores[i].obterAlvoForcado() != null) {
                alvo = defensores[i].obterAlvoForcado(); // O alvo é o forçado pelo Gigante de Pedra
                defensores[i].limparAlvoForcado(); // Limpa o alvo forçado após o ataque
            } else {
                alvo = obterAlvoDisponivel(defensores, i); // Obtém o alvo normalmente
            }

            // Primeiro o atacante realiza o ataque
            if (atacante != null && atacante.estaVivo()) {
                if (alvo != null && alvo.estaVivo()) {
                    System.out.println(atacante.getNome() + " está atacando " + alvo.getNome());
                    atacante.atacar(this, ladoAtacante); // Ataca o adversário

                    // Se o alvo morreu após o ataque, removê-lo da fila
                    if (!alvo.estaVivo()) {
                        removerGuerreiroDaFila(defensores, i, alvo);
                    } else {
                        // Se o alvo sobreviveu, ele contra-ataca
                        System.out.println(alvo.getNome() + " está atacando " + atacante.getNome());
                        alvo.atacar(this, ladoAtacante == 1 ? 2 : 1); // Lado oposto do atacante

                        // Se o atacante morrer no contra-ataque, removê-lo da fila
                        if (!atacante.estaVivo()) {
                            removerGuerreiroDaFila(atacantes, i, atacante);
                        }
                    }
                } else {
                    System.out.println("Nenhum guerreiro disponível na fila " + (i + 1) + " para atacar.");
                }
            } else {
                System.out.println("Nenhum guerreiro disponível na fila " + (i + 1) + " para atacar.");
            }
        }

        // Após o turno, mover todos os guerreiros vivos para o final da fila
        for (int i = 0; i < 4; i++) {
            if (atacantes[i].temGuerreiros()) {
                moverGuerreirosParaFinal(atacantes[i]);
            }
        }
    }

    // Função auxiliar para mover guerreiros vivos para o final da fila
    public void moverGuerreirosParaFinal(FilaDeGuerreiros fila) {
        Guerreiro primeiro = fila.removerPrimeiroGuerreiro();
        if (primeiro != null && primeiro.estaVivo()) {
            fila.adicionarGuerreiro(primeiro); // Mover para o final se ainda estiver vivo
        }
    }

    // Remove o guerreiro da fila se sua energia chegar a zero
    public void removerGuerreiroDaFila(FilaDeGuerreiros[] defensores, int linha, Guerreiro guerreiro) {
        FilaDeGuerreiros fila = defensores[linha];
        fila.removerGuerreiro(guerreiro);
        System.out.println(guerreiro.getNome() + " foi removido da fila, pois sua energia chegou a 0.");
    }

    // Obtém o primeiro alvo disponível na fila adversária ou nas filas subsequentes
    public Guerreiro obterAlvoDisponivel(FilaDeGuerreiros[] defensores, int linhaAtacante) {
        // Primeiro tenta atacar o guerreiro na mesma fila (linhaAtacante)
        for (int i = 0; i < 4; i++) {
            int filaParaAtacar = (linhaAtacante + i) % 4; // Ordem circular
            if (defensores[filaParaAtacar].temGuerreiros()) {
                return defensores[filaParaAtacar].obterPrimeiroGuerreiro();
            }
        }
        return null; // Nenhum alvo disponível
    }

    // Verifica se o lado foi derrotado (se todos os guerreiros estiverem com energia 0)
    public boolean ladoDerrotado(int lado) {
        FilaDeGuerreiros[] filas = lado == 1 ? lado1 : lado2;
        for (FilaDeGuerreiros fila : filas) {
            for (Guerreiro guerreiro : fila.getGuerreiros()) { // Itera sobre os guerreiros da fila
                if (guerreiro.estaVivo()) { // Se encontrar um guerreiro vivo, o lado não foi derrotado
                    return false;
                }
            }
        }
        return true; // Todos os guerreiros estão mortos
    }

    // Obter o início e o fim de uma fila de guerreiros de um lado específico
    public Guerreiro obterPrimeiroGuerreiro(int lado, int fila) {
        return lado == 1 ? lado1[fila].obterPrimeiroGuerreiro() : lado2[fila].obterPrimeiroGuerreiro();
    }

    public Guerreiro obterUltimoGuerreiro(int lado, int fila) {
        return lado == 1 ? lado1[fila].obterUltimoGuerreiro() : lado2[fila].obterUltimoGuerreiro();
    }

    // Interação com os guerreiros atrás do atacante em sua própria fila
    public Guerreiro obterGuerreiroAtras(int lado, int fila, int posicao) {
        FilaDeGuerreiros[] filas = (lado == 1) ? lado1 : lado2;
        return filas[fila].obterGuerreiroNaPosicao(posicao);
    }

    // Obter as filas defensivas com base no lado do atacante
    public FilaDeGuerreiros[] obterDefensores(int ladoAtacante) {
        return ladoAtacante == 1 ? lado2 : lado1;
    }

    // Método para mover o guerreiro que atacou para o final da fila
    public void moverGuerreiroParaFinal(int lado, int fila) {
        FilaDeGuerreiros filaDeGuerreiros = (lado == 1) ? lado1[fila] : lado2[fila];
        Guerreiro guerreiro = filaDeGuerreiros.removerPrimeiroGuerreiro();
        if (guerreiro != null && guerreiro.estaVivo()) {
            filaDeGuerreiros.adicionarGuerreiro(guerreiro); // Coloca no final da fila se ainda estiver vivo
            System.out.println(guerreiro.getNome() + " foi movido para o final da fila.");
        }
    }

    // Método para executar turnos até que um dos lados seja derrotado
    public void realizarBatalhaCompleta() {
        System.out.println("=== Iniciando a batalha ===");

        while (true) { // Loop que continua até que um lado seja derrotado
            boolean lado1AtacaPrimeiro = sortearLadoInicia();

            if (lado1AtacaPrimeiro) {
                System.out.println("\nLado 1 (Gregos e Nórdicos) atacando:");
                executarAtaques(1); // Lado 1 ataca
                if (ladoDerrotado(2)) {
                    System.out.println("\nLado 2 foi derrotado! Lado 1 venceu!");
                    break; // Sai do loop se o lado 2 for derrotado
                }

                System.out.println("\nLado 2 (Atlantes e Egípcios) atacando:");
                executarAtaques(2); // Lado 2 ataca
                if (ladoDerrotado(1)) {
                    System.out.println("\nLado 1 foi derrotado! Lado 2 venceu!");
                    break; // Sai do loop se o lado 1 for derrotado
                }
            } else {
                System.out.println("\nLado 2 (Atlantes e Egípcios) atacando:");
                executarAtaques(2); // Lado 2 ataca
                if (ladoDerrotado(1)) {
                    System.out.println("\nLado 1 foi derrotado! Lado 2 venceu!");
                    break; // Sai do loop se o lado 1 for derrotado
                }

                System.out.println("\nLado 1 (Gregos e Nórdicos) atacando:");
                executarAtaques(1); // Lado 1 ataca
                if (ladoDerrotado(2)) {
                    System.out.println("\nLado 2 foi derrotado! Lado 1 venceu!");
                    break; // Sai do loop se o lado 2 for derrotado
                }
            }

            // Exibe a situação após cada turno
            System.out.println("\n--- Situação após o turno ---");
            exibirStatusLado(1); // Exibe o status dos guerreiros do lado 1
            exibirStatusLado(2); // Exibe o status dos guerreiros do lado 2
        }
    }

    // Método para exibir o status dos guerreiros de um lado
    public void exibirStatusLado(int lado) {
        FilaDeGuerreiros[] filas = lado == 1 ? lado1 : lado2;
        System.out.println("Status do lado " + lado + ":");
        for (int i = 0; i < filas.length; i++) {
            System.out.println("Fila " + (i + 1) + ":");
            filas[i].exibirGuerreiros();
        }
    }

    public int obterPosicaoFila(int lado, Guerreiro guerreiro) {
        FilaDeGuerreiros[] filas = (lado == 1) ? lado1 : lado2;

        for (int i = 0; i < filas.length; i++) {
            int posicao = filas[i].obterPosicaoGuerreiro(guerreiro);
            if (posicao != -1) {
                return i; // Retorna a fila onde o guerreiro está localizado
            }
        }
        return -1; // Retorna -1 se o guerreiro não for encontrado
    }

    public boolean ePrimeiroAtaqueDoTurno(Guerreiro atacante) {
        // Verifica se o atacante é o primeiro a atacar no turno
        return obterPrimeiroGuerreiro(1, 0) == atacante; // Supondo que a fila 0 é a primeira
    }

    public void definirAlvoForcado(Guerreiro alvo, int ladoAtacante) {
        // Definir que apenas os adversários serão forçados a atacar o Gigante de Pedra
        FilaDeGuerreiros[] defensores = obterDefensores(ladoAtacante == 1 ? 2 : 1); // Pega o lado adversário
        for (FilaDeGuerreiros fila : defensores) {
            Guerreiro primeiro = fila.obterPrimeiroGuerreiro();
            if (primeiro != null && primeiro.estaVivo()) {
                // Não forçamos o ataque aqui, mas registramos que o alvo forçado será o Gigante de Pedra
                System.out.println(primeiro.getNome() + " foi forçado a atacar " + alvo.getNome() + " no próximo turno.");
                fila.definirAlvoForcado(alvo); // Agora precisamos garantir que o alvo será o Gigante na fila
            }
        }
    }

}
