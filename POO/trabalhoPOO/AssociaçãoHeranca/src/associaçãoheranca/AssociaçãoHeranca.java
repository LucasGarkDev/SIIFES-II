/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package associaçãoheranca;

/**
 *
 * @author lucas
 */
public class AssociaçãoHeranca {
    public static void main(String[] args) {
        Prato p = new Prato("Bife", "Romeu");
        Cliente c = new Cliente("Joao", p);
        System.out.println("O nome do prato de " + c.getNome() + " é " + c.getPrato().getNome());
    
    }
}
