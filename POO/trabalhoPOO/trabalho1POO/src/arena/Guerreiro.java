/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arena;

/**
 *
 * @author lucas
 */
public abstract class Guerreiro {
    private String nome;
    private  int idade;
    private  int peso;
    protected  int energia = 100;

    public Guerreiro(String nome, int idade, int peso) {
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.energia = 100;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public boolean estaVivo() {
        return energia > 0;
    }

    public abstract void atacar(Arena arena, int ladoAtacante);

    public void receberDano(int dano) {
        this.energia -= dano;
        if (this.energia < 0){ 
            this.energia = 0;
        }
    }

    @Override
    public String toString() {
        return String.format("Guerreiro: %s, Idade: %d, Peso: %d, Energia: %d", nome, idade, peso, energia);
    }
}
