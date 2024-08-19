/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemadecombaterpg;
/**
 *
 * @author lucas
 */
 public class Personagem implements Modificadores{
    public String nome;
    public int forca;
    public int destresa;
    public int constituicao;
    public int inteligencia;
    public int sabedoria;
    public int carisma;
    public int classeDeArmadura;
    public int pontosDeVida;
    private int pontosDeVidaMaximo;
    public int nivel;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getForca() {
        return forca;
    }
    public void setForca(int forca) {
        this.forca = forca;
    }
    public int getDestresa() {
        return destresa;
    }
    public void setDestresa(int destresa) {
        this.destresa = destresa;
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
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    public void setPontosDeVidaMaximo(int atributoPrincipal, String dadoDeVida){
        this.pontosDeVidaMaximo = (atributoPrincipal + rolagem(dadoDeVida)) * this.nivel; 
    }

    @Override
    public int calcularModificador(int valorDeAtributo){
        if((valorDeAtributo == 15)||(valorDeAtributo == 14)){
            return 2;
        }else if((valorDeAtributo == 15)||(valorDeAtributo == 14)){
            return 1;
        }else if (valorDeAtributo == 10){
            return 0;
        }else {
            return -1;
        }      
    }
    
    public int getBonusDeProficiencia(){
        if ((this.nivel > 0) && (this.nivel < 4)){
            return 2;
        }else if ((this.nivel > 5) && (this.nivel < 8)){
            return 3;
        }else if ((this.nivel > 9) && (this.nivel < 12)){
            return 4;
        }else if ((this.nivel > 13) && (this.nivel < 16)){
            return 5;
        }else {
            return 6;
        }
    }
    
 }




