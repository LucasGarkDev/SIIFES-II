/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campoDeBatalha;

/**
 *
 * @author lucas
 */
public abstract class Guerreiro {
    private String nome;
    private int idade;
    private int peso;
    private int energia = 100;

    public Guerreiro(String nome, int idade, int peso) {
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
    }

    public boolean estaVivo() {
        return energia > 0;
    }

    public abstract void atacar(Guerreiro adversario);

    public void receberDano(int dano) {
        this.energia -= dano;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getPeso() {
        return peso;
    }

    public int getEnergia() {
        return energia;
    }

    @Override
    public String toString() {
        return String.format("%s: %s, %d anos, %d kilos, %d energia", this.getClass().getSimpleName(), nome, idade, peso, energia);
    }
}
