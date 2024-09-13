/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package string1;

/**
 *
 * @author lucas
 */
public class IndexOf {
    public static void main(String[] args) {
        String texto = "Linguagem de Programeceo";
        char caracter = 'a';
        int indice = texto.indexOf(caracter);
        System.out.println(indice);
        indice++;
        indice = texto.indexOf(caracter, indice);
        System.out.println(indice);
        indice = texto.indexOf("acao", indice);
        System.out.println(indice);
    }
}
