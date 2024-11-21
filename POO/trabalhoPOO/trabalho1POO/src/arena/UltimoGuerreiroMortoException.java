/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arena;

/**
 *
 * @author lucas
 */
public class UltimoGuerreiroMortoException extends Exception {
    private final Guerreiro ultimoMorto;
    private final Guerreiro assassino;

    public UltimoGuerreiroMortoException(Guerreiro ultimoMorto, Guerreiro assassino) {
        super("");
        this.ultimoMorto = ultimoMorto;
        this.assassino = assassino;
    }

    public Guerreiro getUltimoMorto() {
        return ultimoMorto;
    }

    public Guerreiro getAssassino() {
        return assassino;
    }
}

