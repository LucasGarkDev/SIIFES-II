/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campoDeBatalha;

/**
 *
 * @author lucas
 */
public class Guerreiro {
    private String nome;
    private  int idade;
    private  int peso;
    private  int energia = 100;

    public Guerreiro(String nome, int idade, int peso) {
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
    }

    public boolean estaVivo() {
        return energia > 0;
    }

    public void atacar(Guerreiro adversario) {
        System.out.println(this.nome + " ataca " + adversario.getNome());
        adversario.receberDano(20); // Valor de dano genérico
    }

    public void receberDano(int dano) {
        this.energia -= dano;
        if (this.energia < 0){ 
            this.energia = 0;
        }
    }

    public String getNome() {
        return nome;
    }

    public int getEnergia() {
        return energia;
    }

    @Override
    public String toString() {
        return String.format("Guerreiro: %s, Idade: %d, Peso: %d, Energia: %d", nome, idade, peso, energia);
    }
}
