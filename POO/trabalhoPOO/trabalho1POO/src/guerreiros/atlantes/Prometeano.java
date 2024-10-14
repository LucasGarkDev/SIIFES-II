/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.atlantes;

import arena.Arena;
import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class Prometeano extends GuerreiroAtlante {

    private int indiceFila; // Variável para armazenar o índice da fila

    public Prometeano(String nome, int idade, int peso) {
        super(nome, idade, peso);
        this.indiceFila = -1; // Inicialmente não sabemos a posição da fila
    }

    @Override
    public void atacar(Arena arena, int ladoAtacante) {
        System.out.println(this.getNome() + " (Prometeano) ataca o guerreiro à frente!");

        if (this.estaVivo()) {
            // Antes de atacar, guarda o índice da fila onde está
            this.indiceFila = arena.procurarFilaDoGuerreiro(ladoAtacante, this);
        }
        
        Guerreiro adversario = arena.obterAlvoDisponivel(arena.obterDefensores(ladoAtacante), 0);
        if (adversario != null) {
            adversario.receberDano(10);
            System.out.println(adversario.getNome() + " foi atacado e recebeu 10 de dano!");
        } else {
            System.out.println("Nenhum adversário disponível para atacar.");
        }

        // Após o ataque, verificar se o Prometeano ainda está vivo e, caso contrário, executar a divisão
        divisaoMorte(arena, ladoAtacante);
    }

    // Método para divisão quando o Prometeano morre
    public void divisaoMorte(Arena arena, int ladoAtacante) {
        if (!this.estaVivo()) {
            // Dividir a energia original pela metade
            int energiaDividida = this.getEnergia() / 2;

            if (energiaDividida > 0) {
                // Criar os descendentes com 50% da energia original
                Prometeano descendente1 = new Prometeano(this.getNome() + "1", this.getIdade(), this.getPeso());
                Prometeano descendente2 = new Prometeano(this.getNome() + "2", this.getIdade(), this.getPeso());

                descendente1.setEnergia(energiaDividida);
                descendente2.setEnergia(energiaDividida);

                // Adicionar os descendentes ao final da fila
                System.out.println(this.getNome() + " se divide em " + descendente1.getNome() + " e " + descendente2.getNome());

                // Adiciona os descendentes na fila correta (com base no índice armazenado)
                arena.adicionarGuerreiroNaFila(2, this.indiceFila, descendente1);
                arena.adicionarGuerreiroNaFila(2, this.indiceFila, descendente2);
            } else {
                System.out.println(this.getNome() + " foi completamente eliminado.");
            }
        }
    }
}
