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
        this.dano = 35; 
    }

    @Override
    public void atacar(Arena arena, Guerreiro adversario) {
        int minhaFilaIndice = arena.encontrarFilaDeGuerreiro(this); // Encontrar a fila do Ciclope
        FilaDeGuerreiros minhaFila = arena.getFila(1, minhaFilaIndice); 
        Guerreiro primeiroNaFila = minhaFila.getPrimeiroGuerreiro();

        boolean ladoCiclopeSorteado = (arena.getLadoSorteado() == 1); // Verifica se o lado 1 foi sorteado

        if ((this == primeiroNaFila) && (ladoCiclopeSorteado)) {
            //System.out.println(this.getNome() + " ataca com força total!");
            adversario.sofrerDano(this.dano, arena); // Causar dano

            if (!adversario.isEstaMorto() && adversario != null) {
                int filaAdversarioIndice = arena.encontrarFilaDeGuerreiro(adversario);
                FilaDeGuerreiros filaAdversaria = arena.getFila(2, filaAdversarioIndice); 
                filaAdversaria.moverGuerreiroParaFrente(filaAdversaria.encontrarGuerreiro(adversario), filaAdversaria.getLista().size() - 1);

                //System.out.println(adversario.getNome() + " foi movido para o fim da sua fila!");
            }
        } else {
            //System.out.println(this.getNome() + " ataca normalmente.");
            adversario.sofrerDano(this.dano, arena);
        }
    }

    @Override
    public String getNome() {
        return nome + " (Ciclope)";
    }
}
