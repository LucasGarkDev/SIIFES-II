/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;

/**
 *
 * @author lucas
 */
public class Monstro extends Personagem {
    private String nomeMonstro;
    private int ladosDadoAtaque;  // Define o dado de ataque do monstro, como 1d8, 1d6, etc.
    private String tipoDeAtaque;  // Pode ser "Força", "Destreza", ou outro tipo de ataque especial.

    public Monstro(String nomeMonstro, int forca, int destreza, int constituicao, int inteligencia, int sabedoria, int carisma, int nivel, int ladosDadoAtaque, String tipoDeAtaque) {
        super(forca, destreza, constituicao, inteligencia, sabedoria, carisma, nivel);
        this.nomeMonstro = nomeMonstro;
        this.ladosDadoAtaque = ladosDadoAtaque;
        this.tipoDeAtaque = tipoDeAtaque;
    }

    // Método para realizar o ataque do monstro
    @Override
    public void atacar(Personagem alvo) {
        int modificadorAtributo = 0;

        // Define o modificador com base no tipo de ataque
        if (tipoDeAtaque.equals("Força")) {
            modificadorAtributo = Modificadores.calcularModificador(this.getForca());
        } else if (tipoDeAtaque.equals("Destreza")) {
            modificadorAtributo = Modificadores.calcularModificador(this.getDestreza());
        }

        // Rola o dano com base no dado de ataque do monstro
        int dano = RolagemDados.rolarDanoArma(ladosDadoAtaque, modificadorAtributo);
        alvo.receberDano(dano);

        System.out.println(nomeMonstro + " atacou e causou " + dano + " de dano ao " + alvo.getClass().getSimpleName() + "!");
    }

    // Cálculo de pontos de vida (pode ser adaptado dependendo do tipo de monstro)
    @Override
    public int calcularPontosDeVida() {
        return 8 + Modificadores.calcularModificador(this.getConstituicao());  // Exemplo: monstros podem ter 1d8 de PV
    }

    // Método para realizar uma ação (podemos sobrescrever ou deixar genérico)
    @Override
    public void realizarAcao(Personagem alvo) {
        atacar(alvo);  // Ação principal dos monstros é atacar
    }

    // Getters e Setters
    public String getNomeMonstro() {
        return nomeMonstro;
    }

    public void setNomeMonstro(String nomeMonstro) {
        this.nomeMonstro = nomeMonstro;
    }

    public int getLadosDadoAtaque() {
        return ladosDadoAtaque;
    }

    public void setLadosDadoAtaque(int ladosDadoAtaque) {
        this.ladosDadoAtaque = ladosDadoAtaque;
    }

    public String getTipoDeAtaque() {
        return tipoDeAtaque;
    }

    public void setTipoDeAtaque(String tipoDeAtaque) {
        this.tipoDeAtaque = tipoDeAtaque;
    }
}

