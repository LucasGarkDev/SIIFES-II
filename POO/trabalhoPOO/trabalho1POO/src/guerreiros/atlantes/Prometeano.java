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

    private int maxEnergia;  

    // Construtor
    public Prometeano(String nome, int idade, double peso, int energia) {
        super(nome, idade, peso);
        this.energia = energia;  
        this.maxEnergia = energia;  
        this.dano = 10;  
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        if (adversario != null && !adversario.isEstaMorto()) {
            System.out.println(getNome() + " ataca " + adversario.getNome() + " causando " + dano + " de dano.");
            adversario.sofrerDano(dano, arena);
        }
    }

    @Override
    public void sofrerDano(int quantidade, Arena arena) {
        this.arena = arena;
        if (this.energia - quantidade <= 0) {
            dividirAntesDeMorrer();
        }
        super.sofrerDano(quantidade,arena);
    }

    private void dividirAntesDeMorrer() {
        int novaEnergia = this.maxEnergia / 2;
        if (novaEnergia >= 1) {
            System.out.println(super.getNome() + " se divide em dois!");

            Prometeano filho1 = new Prometeano(this.getNome() + "1", this.getIdade(), this.getPeso(), novaEnergia);
            Prometeano filho2 = new Prometeano(this.getNome() + "2", this.getIdade(), this.getPeso(), novaEnergia);

            FilaDeGuerreiros minhaFila = arena.getFila(2, arena.encontrarFilaDeGuerreiro(this));

            minhaFila.adicionarGuerreiroNoFinal(filho1);
            minhaFila.adicionarGuerreiroNoFinal(filho2);

            this.estaMorto = true;
            System.out.println(this.getNome() + " morreu, mas deixou " + filho1.getNome() + " e " + filho2.getNome() + ".");
        } else {
            System.out.println(this.getNome() + " não tem energia suficiente para se dividir.");
        }
    }
    
    @Override
    public String getNome() {
        return nome;
    }
    
//    @Override
//    public String getNome() {
//        return nome + "(Prometeano)";
//    }
}
