/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package string1;

/**
 *
 * @author lucas
 */
public class ValueOf {
    public static void main(String[] args) {
        String x = "";
        x = x + String.valueOf(23) + String.valueOf(true) + " - ";
        x = x + String.valueOf(Math.PI);
        System.out.println(x);
    }
}
