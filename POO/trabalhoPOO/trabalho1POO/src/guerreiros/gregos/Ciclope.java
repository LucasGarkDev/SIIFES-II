/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerreiros.gregos;

import arena.Arena;
import arena.FilaDeGuerreiros;
import arena.Guerreiro;
import arena.Guerreiro;
import arena.Guerreiro;

/**
 *
 * @author lucas
 */
public class Ciclope extends GuerreiroGrego {

    public Ciclope(String nome, int idade, double peso) {
        super(nome, idade, peso);
        this.dano = 35; // Definindo o dano padrão do Ciclope como 35
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        // Verificar se o Ciclope é o primeiro na fila
        int minhaFilaIndice = arena.encontrarFilaDeGuerreiro(this); // Encontrar a fila do Ciclope
        FilaDeGuerreiros minhaFila = arena.getFila(1, minhaFilaIndice); // O Ciclope está no lado 1 (gregos)
        Guerreiro primeiroNaFila = minhaFila.getPrimeiroGuerreiro();

        if (this == primeiroNaFila) {
            // Se o Ciclope é o primeiro na fila e seu lado começou o turno
            System.out.println(this.getNome() + " ataca com força total!");
            adversario.sofrerDano(this.dano,arena); // Causar dano

            // Se o adversário ainda está vivo, movê-lo para o fim de sua própria fila
            if (!adversario.isEstaMorto()) {
                int filaAdversarioIndice = arena.encontrarFilaDeGuerreiro(adversario); // Encontrar a fila do adversário
                FilaDeGuerreiros filaAdversaria = arena.getFila(2, filaAdversarioIndice); // O adversário está no lado 2 (atlantes e egípcios)
                filaAdversaria.moverGuerreiroParaFrente(filaAdversaria.encontrarGuerreiro(adversario), filaAdversaria.getLista().size() - 1);

                System.out.println(adversario.getNome() + " foi movido para o fim da sua fila!");
            }
        } else {
            // Caso o Ciclope não seja o primeiro, ele apenas realiza o ataque normalmente
            System.out.println(this.getNome() + " ataca normalmente.");
            adversario.sofrerDano(this.dano,arena);
        }
    }

}
