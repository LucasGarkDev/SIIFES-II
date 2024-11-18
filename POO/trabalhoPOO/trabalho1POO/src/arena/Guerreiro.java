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
    protected String nome;
    private int idade;  
    protected double peso;
    protected int energia = 100;  
    protected int dano;  
    protected boolean estaMorto; 
    protected Arena arena;
    protected boolean envenenado = false; 
    
    public Guerreiro(String nome, int idade, double peso) {
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.estaMorto = false;  
    }
    
    public abstract void atacar(Arena arena, Guerreiro adversario);

    public void sofrerDano(int quantidade, Arena arena){
        if (!estaMorto) {
            this.energia -= quantidade;
            if (this.energia <= 0) {
                this.energia = 0;  
                this.estaMorto = true;  
            }
        }
    }
    
    public void envenenar() {
        this.envenenado = true;
    }

    public boolean isEnvenenado() {
        return envenenado;
    }

    public boolean isEstaMorto() {
        if (this == null) {
            return true; 
        }
        return estaMorto;
    }

    // Getters para atributos
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public double getPeso() {
        return peso;
    }

    public int getEnergia() {
        return energia;
    }

    // Setters para modificar atributos
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setEnergia(int energia) {
        if (!estaMorto) {
            this.energia = Math.max(0, energia);  
            if (this.energia == 0) {
                this.estaMorto = true;  
            }
        }
    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    @Override
    public String toString() {
        return String.format("%s: %d anos, %.2f kg, %d pontos de energia %s", 
            nome, idade, peso, energia, estaMorto ? "(Morto)" : "(Vivo)");
    }
}
