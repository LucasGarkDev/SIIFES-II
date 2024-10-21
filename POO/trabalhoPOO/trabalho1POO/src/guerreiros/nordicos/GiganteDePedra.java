/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.nordicos;

import arena.Arena;
import arena.FilaDeGuerreiros;
import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class GiganteDePedra extends GuerreiroNordico {

    private Arena arena; // Referência à arena em que o Gigante está
    private int contadorDeGolpes = 0;
    private boolean habilidadeAtivada = false;
    private boolean camuflouPropriaFila = false;

    // Construtor do GiganteDePedra que recebe a arena
    public GiganteDePedra(String nome, int idade, double peso, Arena arena) {
        super(nome, idade, peso); // Chama o construtor da classe mãe
        this.energia = 300; // Energia inicial de 300 para o Gigante
        this.dano = 30; // Dano do Gigante de Pedra
        this.arena = arena; // Armazena a arena passada como parâmetro
    }

    // Método para acessar a arena
    public Arena getArena() {
        return this.arena;
    }

    // Método que será chamado sempre que o Gigante de Pedra for atacado
    @Override
    public void sofrerDano(int dano) {
        super.sofrerDano(dano); // Chama o método original para aplicar o dano
        contadorDeGolpes++; // Incrementa o contador de golpes recebidos
        System.out.println(this.getNome() + " foi atacado! Contador de golpes: " + contadorDeGolpes);

        // Verifica se ele já recebeu golpes de todos os guerreiros adversários
        int filasInimigasNaoVazias = contarFilasNaoVazias(this.arena, 2); // Conta as filas não vazias do lado inimigo

        // Se já recebeu golpes de todos os guerreiros adversários, reseta a camuflagem
        if (contadorDeGolpes >= filasInimigasNaoVazias) {
            removerCamuflagem(this.arena); // Remove a camuflagem
            habilidadeAtivada = false; // Reseta a habilidade para o próximo turno
            contadorDeGolpes = 0; // Reseta o contador de golpes
            camuflouPropriaFila = false; // Reseta a flag de camuflagem da própria fila
        }
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        // Verifica se o lado do Gigante foi sorteado para começar atacando
        if (!habilidadeAtivada) {
            // Verifica se há outro Gigante de Pedra no lado 1 (lado aliado)
            GiganteDePedra outroGigante = encontrarOutroGiganteDePedra(this.arena);

            if (outroGigante != null) {
                // Outro Gigante existe. Se este Gigante está em uma fila anterior, ele camufla a própria fila
                System.out.println(this.getNome() + " detectou outro GiganteDePedra. Camuflando a própria fila.");
                camuflarPropriaFila(this.arena);
                camuflouPropriaFila = true;
            } else {
                // Não há outro Gigante ou este é o último a ativar
                System.out.println(this.getNome() + " ativou sua habilidade e camuflou os aliados!");
                camuflarAliados(this.arena); // Camufla as outras filas aliadas
            }

            habilidadeAtivada = true; // Marca que a habilidade foi ativada
        }

        // O Gigante ataca normalmente após camuflar os aliados
        adversario.sofrerDano(this.dano);
        System.out.println(this.getNome() + " atacou " + adversario.getNome() + " causando " + this.dano + " de dano.");
    }

    // Método para camuflar a própria fila do Gigante
    private void camuflarPropriaFila(Arena arena) {
        FilaDeGuerreiros minhaFila = arena.getFila(1, encontrarMinhaFila(arena));
        if (minhaFila != null) {
            minhaFila.adicionarGuerreiro(null, 0); // Insere 'null' na primeira posição, camuflando a própria fila
        }
    }

    // Método para camuflar os guerreiros aliados, exceto a própria fila
    private void camuflarAliados(Arena arena) {
        for (int i = 0; i < 4; i++) {
            FilaDeGuerreiros filaAliada = arena.getFila(1, i); // Lado 1 é o lado aliado
            if (filaAliada != null && filaAliada.getPrimeiroGuerreiro() != null && !filaAliada.getPrimeiroGuerreiro().equals(this)) {
                filaAliada.adicionarGuerreiro(null, 0); // Insere 'null' na primeira posição, "camuflando"
            }
        }
    }

    // Método para remover os guerreiros nulos das filas aliadas
    private void removerCamuflagem(Arena arena) {
        System.out.println(this.getNome() + " está removendo a camuflagem dos aliados...");
        for (int i = 0; i < 4; i++) {
            FilaDeGuerreiros filaAliada = arena.getFila(1, i); // Lado 1 é o lado aliado
            if (filaAliada.getPrimeiroGuerreiro() == null) {
                filaAliada.removerGuerreiro(0); // Remove o 'null' da primeira posição
            } else {
                System.out.println("Nenhum guerreiro nulo encontrado para remover na fila " + (i + 1));
            }
        }
    }

    // Método para encontrar a própria fila do Gigante
    private int encontrarMinhaFila(Arena arena) {
        for (int i = 0; i < 4; i++) {
            FilaDeGuerreiros fila = arena.getFila(1, i);
            if (fila != null && fila.getPrimeiroGuerreiro() == this) {
                return i; // Retorna o índice da fila
            }
        }
        return -1; // Se não encontrar (não deveria ocorrer)
    }

    // Método para encontrar outro Gigante de Pedra nas filas aliadas
    private GiganteDePedra encontrarOutroGiganteDePedra(Arena arena) {
        for (int i = 0; i < 4; i++) {
            FilaDeGuerreiros fila = arena.getFila(1, i); // Verifica o lado 1
            if (fila != null) {
                Guerreiro guerreiro = fila.getPrimeiroGuerreiro();
                if (guerreiro instanceof GiganteDePedra && guerreiro != this) {
                    return (GiganteDePedra) guerreiro; // Retorna o outro Gigante de Pedra encontrado
                }
            }
        }
        return null; // Não encontrou outro Gigante de Pedra
    }

    // Método para contar as filas não vazias do lado adversário
    private int contarFilasNaoVazias(Arena arena, int lado) {
        int contador = 0;
        for (int i = 0; i < 4; i++) {
            FilaDeGuerreiros fila = arena.getFila(lado, i);
            if (fila != null && !fila.estaVazia()) {
                contador++;
            }
        }
        return contador;
    }
}
