/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.atlantes;

import arena.Arena;
import arena.FilaDeGuerreiros;
import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class Prometeano extends GuerreiroAtlante {

    private int maxEnergia;  // Guarda a energia máxima do Prometeano

    // Construtor
    public Prometeano(String nome, int idade, double peso, int energia) {
        super(nome, idade, peso);
        this.energia = energia;  // Define a energia inicial do Prometeano
        this.maxEnergia = energia;  // Guarda o valor máximo de energia
        this.dano = 10;  // O ataque padrão do Prometeano
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        // Ataque padrão do Prometeano
        if (adversario != null && !adversario.isEstaMorto()) {
            System.out.println(getNome() + "(Prometeano) ataca " + adversario.getNome() + " causando " + dano + " de dano.");
            adversario.sofrerDano(dano, arena);
        }
    }

    @Override
    public void sofrerDano(int quantidade, Arena arena) {
        // Define a Arena quando o Prometeano sofre dano
        this.arena = arena;

        // Verifica se o Prometeano vai morrer com este dano
        if (this.energia - quantidade <= 0) {
            // Se o dano for fatal, o Prometeano se divide antes de morrer
            dividirAntesDeMorrer();
        }

        // Aplicar o dano somente após a tentativa de divisão
        super.sofrerDano(quantidade,arena);
    }

    private void dividirAntesDeMorrer() {
        // Calcula a nova energia dos Prometeanos gerados
        int novaEnergia = this.maxEnergia / 2;

        // Verifica se a nova energia é suficiente para a divisão (precisa ser >= 5)
        if (novaEnergia >= 5) {
            System.out.println(this.getNome() + " se divide em dois!");

            // Cria dois novos Prometeanos com metade da energia máxima
            Prometeano filho1 = new Prometeano(this.getNome() + "1", this.getIdade(), this.getPeso(), novaEnergia);
            Prometeano filho2 = new Prometeano(this.getNome() + "2", this.getIdade(), this.getPeso(), novaEnergia);

            // Encontra a fila onde o Prometeano estava
            FilaDeGuerreiros minhaFila = arena.getFila(2, arena.encontrarFilaDeGuerreiro(this));

            // Adiciona os novos Prometeanos no final da fila do Prometeano original
            minhaFila.adicionarGuerreiroNoFinal(filho1);
            minhaFila.adicionarGuerreiroNoFinal(filho2);

            // Marca o Prometeano original como morto
            this.estaMorto = true;
            System.out.println(this.getNome() + " morreu, mas deixou " + filho1.getNome() + " e " + filho2.getNome() + ".");
        } else {
            System.out.println(this.getNome() + " não tem energia suficiente para se dividir.");
        }
    }
}
