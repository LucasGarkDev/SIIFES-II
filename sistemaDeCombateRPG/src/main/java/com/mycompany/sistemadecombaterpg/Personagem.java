/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;
/**
 *
 * @author lucas
 */
public abstract class Personagem {
    protected int forca, destreza, constituicao, inteligencia, sabedoria, carisma;
    protected int classeDeArmadura, pontosDeVida, pontosDeVidaMaximos, nivel;
    private boolean esquivando;  // Atributo para controlar o estado de esquiva

    // Construtor
    public Personagem(int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma, int nivel) {
        this.forca = forca;
        this.destreza = destreza;
        this.constituicao = constituicao;
        this.inteligencia = inteligencia;
        this.sabedoria = sabedoria;
        this.carisma = carisma;
        this.nivel = nivel;
        this.classeDeArmadura = calcularClasseDeArmadura();
        this.pontosDeVidaMaximos = calcularPontosDeVida();
        this.pontosDeVida = pontosDeVidaMaximos;
        this.esquivando = false;  // Inicialmente, o personagem não está esquivando
    }

    // Método para calcular a Classe de Armadura (CA) - Modificável conforme o personagem usa armaduras
    public int calcularClasseDeArmadura() {
        return 10 + Modificadores.calcularModificador(destreza);  // Usando a classe utilitária Modificadores
    }

    // Método abstrato para calcular os pontos de vida baseado na classe
    public abstract int calcularPontosDeVida();

    // Método para calcular a iniciativa: rolar um d20 + modificador de Destreza
    public int getIniciativa() {
        return RolagemDados.rolarD20() + Modificadores.calcularModificador(destreza);  // Usando a classe utilitária
    }

    // Método abstrato para realizar a ação no turno (subclasses devem implementar)
    public abstract void realizarAcao(Personagem alvo);

    // Método abstrato para atacar um alvo (subclasses devem implementar)
    public abstract void atacar(Personagem alvo);

    // Método sobrecarregado para usar habilidades sem alvo (afeta a si mesmo)
    public void usarHabilidade() {
        System.out.println(this.getClass().getSimpleName() + " usou uma habilidade em si mesmo.");
        // Implementação padrão ou personalizada nas subclasses
    }

    // Método sobrecarregado para usar habilidades com alvo (afeta outros personagens)
    public void usarHabilidade(Personagem alvo) {
        System.out.println(this.getClass().getSimpleName() + " usou uma habilidade em " + alvo.getClass().getSimpleName());
        // Implementação personalizada nas subclasses
    }

    // Método para receber dano
    public void receberDano(int dano) {
        this.pontosDeVida -= dano;
        if (this.pontosDeVida < 0) {
            this.pontosDeVida = 0;
        }
    }

    // Verifica se o personagem está vivo
    public boolean estaVivo() {
        return this.pontosDeVida > 0;
    }

    // Controle de esquiva: determina se o personagem está esquivando
    public boolean isEsquivando() {
        return esquivando;
    }

    public void setEsquivando(boolean esquivando) {
        this.esquivando = esquivando;
    }

    // Getters e Setters para todos os atributos
    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getConstituicao() {
        return constituicao;
    }

    public void setConstituicao(int constituicao) {
        this.constituicao = constituicao;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getSabedoria() {
        return sabedoria;
    }

    public void setSabedoria(int sabedoria) {
        this.sabedoria = sabedoria;
    }

    public int getCarisma() {
        return carisma;
    }

    public void setCarisma(int carisma) {
        this.carisma = carisma;
    }

    public int getClasseDeArmadura() {
        return classeDeArmadura;
    }

    public void setClasseDeArmadura(int classeDeArmadura) {
        this.classeDeArmadura = classeDeArmadura;
    }

    public int getPontosDeVida() {
        return pontosDeVida;
    }

    public void setPontosDeVida(int pontosDeVida) {
        this.pontosDeVida = pontosDeVida;
    }

    public int getPontosDeVidaMaximos() {
        return pontosDeVidaMaximos;
    }

    public void setPontosDeVidaMaximos(int pontosDeVidaMaximos) {
        this.pontosDeVidaMaximos = pontosDeVidaMaximos;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    // Método para calcular o bônus de proficiência baseado no nível
    public int getBonusProficiencia() {
        return Modificadores.calcularBonusProficiencia(nivel);  // Usando a classe utilitária
    }
}



