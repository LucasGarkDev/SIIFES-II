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
    private int ladoSorteado; // Armazena o lado sorteado para iniciar o turno

    // Construtor
    public Arena() {
        lado1 = new FilaDeGuerreiros[4];
        lado2 = new FilaDeGuerreiros[4];

        for (int i = 0; i < 4; i++) {
            lado1[i] = new FilaDeGuerreiros();
            lado2[i] = new FilaDeGuerreiros();
        }
    }

    // Reseta os flags de ataque de todas as filas em um lado específico
    public void resetarFlagsDeAtaque(FilaDeGuerreiros[] lado) {
        for (FilaDeGuerreiros fila : lado) {
            fila.setDeveSerAtacada(false);
        }
    }

    // Métodos auxiliares para acesso aos lados
    public FilaDeGuerreiros[] getLado1() {
        return lado1;
    }

    public FilaDeGuerreiros[] getLado2() {
        return lado2;
    }

    public int getLado(Guerreiro guerreiro) {
        for (int i = 0; i < 4; i++) {
            if (lado1[i].encontrarGuerreiro(guerreiro) != -1) {
                return 1; // Guerreiro está no lado 1
            }
        }

        for (int i = 0; i < 4; i++) {
            if (lado2[i].encontrarGuerreiro(guerreiro) != -1) {
                return 2; // Guerreiro está no lado 2
            }
        }

        return -1; // Guerreiro não encontrado (o que não deveria acontecer)
    }

    public int getLadoSorteado() {
        return ladoSorteado;
    }

    // Método para calcular a soma dos pesos dos guerreiros de ambos os lados
    public void exibirSomaDosPesosDosGuerreiros() {
        double somaPesosLado1 = calcularSomaPesos(lado1);
        double somaPesosLado2 = calcularSomaPesos(lado2);

        System.out.println("Soma dos pesos dos guerreiros do Lado 1 (Gregos e Nórdicos): " + somaPesosLado1 + " kg");
        System.out.println("Soma dos pesos dos guerreiros do Lado 2 (Atlantes e Egípcios): " + somaPesosLado2 + " kg");
    }

    private double calcularSomaPesos(FilaDeGuerreiros[] lado) {
        double soma = 0;
        for (FilaDeGuerreiros fila : lado) {
            for (Guerreiro guerreiro : fila.getLista()) {
                if (!guerreiro.isEstaMorto()) {
                    soma += guerreiro.getPeso();
                }
            }
        }
        return soma;
    }

    // Método para exibir o guerreiro mais velho de toda a arena
    public void exibirGuerreiroMaisVelhoDaArena() {
        Guerreiro guerreiroMaisVelho = encontrarGuerreiroMaisVelhoDaArena();

        if (guerreiroMaisVelho != null) {
            System.out.println("Guerreiro mais velho da arena: " + guerreiroMaisVelho.getNome()
                    + " com " + guerreiroMaisVelho.getIdade() + " anos.");
        } else {
            System.out.println("Nenhum guerreiro encontrado na arena.");
        }
    }

    private Guerreiro encontrarGuerreiroMaisVelhoDaArena() {
        Guerreiro maisVelho = null;
        FilaDeGuerreiros[][] lados = {lado1, lado2};
        for (FilaDeGuerreiros[] lado : lados) {
            for (FilaDeGuerreiros fila : lado) {
                for (Guerreiro guerreiro : fila.getLista()) {
                    if ((!guerreiro.isEstaMorto()) && ((maisVelho == null) || (guerreiro.getIdade()) > (maisVelho.getIdade()))) {
                        maisVelho = guerreiro;
                    }
                }
            }
        }

        return maisVelho;
    }

    // Método para acessar uma fila específica de um lado específico
    public FilaDeGuerreiros getFila(int lado, int indice) {
        if ((lado == 1) && (indice >= 0) && (indice < 4)) {
            return lado1[indice];
        } else if ((lado == 2) && (indice >= 0) && (indice < 4)) {
            return lado2[indice];
        }
        return null; // Retorna null se o lado ou o índice forem inválidos
    }

    // Método para sortear qual lado vai começar o turno
    public int sortearLado() {
        Random random = new Random();
        ladoSorteado = random.nextInt(2) + 1; // Sorteia 1 para lado1 e 2 para lado2
        return ladoSorteado;
    }

    // Método para executar o turno de ataque de um lado
    public void executarTurno(int ladoAtacante) throws UltimoGuerreiroMortoException {
        FilaDeGuerreiros[] ladoAtacanteFilas;
        FilaDeGuerreiros[] ladoDefensorFilas;

        if (ladoAtacante == 1) {
            ladoAtacanteFilas = lado1;
            ladoDefensorFilas = lado2;
        } else {
            ladoAtacanteFilas = lado2;
            ladoDefensorFilas = lado1;
        }

        realizarAtaques(ladoAtacanteFilas, ladoDefensorFilas);
        realizarAtaques(ladoDefensorFilas, ladoAtacanteFilas);

        removerGuerreirosMortos(ladoAtacanteFilas);
        removerGuerreirosMortos(ladoDefensorFilas);
    }

    // Método para realizar os ataques
    private void realizarAtaques(FilaDeGuerreiros[] ladoAtacante, FilaDeGuerreiros[] ladoDefensor) throws UltimoGuerreiroMortoException {
        for (int i = 0; i < 4; i++) {
            FilaDeGuerreiros filaAtacante = ladoAtacante[i];
            Guerreiro atacante = filaAtacante.getPrimeiroGuerreiro();

            if (atacante != null && !atacante.isEstaMorto()) {
                Guerreiro defensor = buscarDefensor(ladoDefensor, i);

                if (defensor != null && !defensor.isEstaMorto()) {
                    atacante.atacar(this, defensor);

                    if (defensor.isEstaMorto()) {
                        throw new UltimoGuerreiroMortoException(defensor, atacante);
                    }
                }
            }
        }
    }

    private Guerreiro buscarDefensor(FilaDeGuerreiros[] ladoDefensor, int indexInicial) {
        // Verifica se alguma fila está marcada como deve ser atacada
        for (FilaDeGuerreiros fila : ladoDefensor) {
            if (fila.isDeveSerAtacada()) {
                Guerreiro defensor = fila.getPrimeiroGuerreiro();
                if ((defensor != null) && (!defensor.isEstaMorto())) {
                    return defensor;
                }
            }
        }

        // Se nenhuma fila tiver o flag ativo, segue a busca convencional
        for (int j = 0; j < 4; j++) {
            FilaDeGuerreiros filaDefensora = ladoDefensor[(indexInicial + j) % 4];
            Guerreiro defensor = filaDefensora.getPrimeiroGuerreiro();
            if ((defensor != null) && (!defensor.isEstaMorto())) {
                return defensor;
            }
        }
        return null;
    }


    // Método para remover guerreiros mortos de todas as filas de um lado (após o turno)
    private void removerGuerreirosMortos(FilaDeGuerreiros[] lado) {
        for (FilaDeGuerreiros fila : lado) {
            Iterator<Guerreiro> iterator = fila.getLista().iterator();
            while (iterator.hasNext()) {
                Guerreiro guerreiro = iterator.next();
                if ((guerreiro != null) && (guerreiro.isEstaMorto())) {
                    iterator.remove();
                    System.out.println(guerreiro.getNome() + " foi removido da arena.");
                }
            }
        }
    }

    // Método para retornar a Arena de um Guerreiro
    public Arena getArena(Guerreiro guerreiro) {
        // Verifica se o guerreiro está em uma das filas do lado 1
        for (FilaDeGuerreiros fila : lado1) {
            if (fila.encontrarGuerreiro(guerreiro) != -1) {
                return this; // Se o guerreiro está no lado 1
            }
        }

        // Verifica se o guerreiro está em uma das filas do lado 2
        for (FilaDeGuerreiros fila : lado2) {
            if (fila.encontrarGuerreiro(guerreiro) != -1) {
                return this; // Se o guerreiro está no lado 2
            }
        }

        return null; // Se o guerreiro não está em nenhuma fila
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
        FilaDeGuerreiros[] filas;
        if (lado == 1) {
            filas = lado1;
        } else {
            filas = lado2;
        }
        for (FilaDeGuerreiros fila : filas) {
            if (!fila.estaVazia()) {
                for (Guerreiro guerreiro : fila.getLista()) {
                    if ((guerreiro != null) && (!guerreiro.isEstaMorto())) {
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

            try {
                executarTurno(ladoSorteado); 
            } catch (UltimoGuerreiroMortoException e) {
                // Captura o evento de morte
                System.out.println(e.getMessage()); // Mensagem formatada pela exceção
                this.ultimoMorto = e.getUltimoMorto();
                this.ultimoAssassino = e.getAssassino();
            }

            System.out.println("-------------------------------------");
            exibirGuerreirosDeCadaLado();

            if (todosGuerreirosMortos(1)) {
                System.out.println("-------------------------------------");
                System.out.println("d) Lado 2 (Atlantes e Egípcios) venceu!");
                break;
            } else if (todosGuerreirosMortos(2)) {
                System.out.println("-------------------------------------");
                System.out.println("d) Lado 1 (Gregos e Nórdicos) venceu!");
                break;
            }
        }

        if (ultimoMorto != null && ultimoAssassino != null) {
            System.out.println("e) O último guerreiro morto foi: " + ultimoMorto.getNome() + ", morto por: " + ultimoAssassino.getNome() + ".");
            System.out.println("f) O Guerreiro que matou o ultimo oponente foi: " + ultimoAssassino.getNome() + ", responsavel por matar: " + ultimoMorto.getNome() + ".");
        } else {
            System.out.println("Nenhum guerreiro morreu durante o combate.");
        }
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

        if ((f1 != null) && (f2 != null)) {
            Guerreiro atacante = f1.getPrimeiroGuerreiro();
            Guerreiro defensor = f2.getPrimeiroGuerreiro();

            if ((atacante != null) && (defensor != null)) {
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
