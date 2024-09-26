/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package associaçãoheranca;

/**
 *
 * @author lucas
 */
public class Cliente {
    
    private String nome;
    private Prato prato;
    
    public Cliente(String nome, Prato prato) {
        this.nome = nome;
        this.prato = prato;
    }
    
    public Prato getPrato() {
        return prato;
    }
    
    public String getNome() {
        return nome;
    }
    
    
}
