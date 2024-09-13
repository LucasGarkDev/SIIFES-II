/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package string1;

/**
 *
 * @author lucas
 */
public class Replace {
    public static void main(String[] args) {
        // retirando espaços em branco dentro de dentro da string
        String frase1 = "Mariana gosta de nana banana".replace(" ","");
        frase1 = frase1.replace("na", "NA");
        System.out.println(frase1);
    }
}
