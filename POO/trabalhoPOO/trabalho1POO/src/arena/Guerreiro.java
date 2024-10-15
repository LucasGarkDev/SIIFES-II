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
    private int idade;  // Atributo idade privado
    protected double peso;
    protected int energia = 100;  // Vida inicial de 100 pontos
    protected int dano;  // Dano a ser definido pelas subclasses
    protected boolean estaMorto;  // Flag para indicar se o guerreiro está morto

    // Construtor
    public Guerreiro(String nome, int idade, double peso) {
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.estaMorto = false;  // Todos os guerreiros começam vivos
    }

    // Método abstrato para o ataque
    public abstract void atacar(Arena arena, Guerreiro adversario);

    // Método para sofrer dano
    public void sofrerDano(int quantidade) {
        if (!estaMorto) {
            this.energia -= quantidade;
            if (this.energia <= 0) {
                this.energia = 0;  // Energia não pode ser negativa
                this.estaMorto = true;  // Marca o guerreiro como morto
            }
        }
    }

    // Verificar se o guerreiro está morto
    public boolean isEstaMorto() {
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
            this.energia = Math.max(0, energia);  // Garante que energia não seja negativa
            if (this.energia == 0) {
                this.estaMorto = true;  // Se a energia for zero, o guerreiro está morto
            }
        }
    }

    // Método para exibir o estado do guerreiro
    @Override
    public String toString() {
        return String.format("%s: %d anos, %.2f kg, %d pontos de energia %s", 
            nome, idade, peso, energia, estaMorto ? "(Morto)" : "(Vivo)");
    }
}
