/*
Instancie dois robôs, peça ao usuário para entrar com a posição do alimento, e faça os dois robôs se moverem
randomicamente, um de cada vez, até que um deles encontre o alimento. Ao final, mostre quem achou o alimento
e o número de movimentos que cada robô fez.
*/

package poo.uece;

import poo.uece.standart.Robo;
import poo.uece.standart.Comida;
import poo.uece.standart.Entidade;
import poo.uece.game.DisputaRobosPainel;
import poo.uece.game.RoboPanel;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JFrame;
import static poo.uece.Main.pegarCoordenada;

public class MainBatalhaRobo {

    public static void main(String[] args) {

        Robo ciano = new Robo(Color.cyan);
        Robo magenta = new Robo(Color.magenta);

        // Posição da Comida
        int x, y;

        x = pegarCoordenada("Digite a posição da comida no eixo horizontal (0 a 79):");
        y = pegarCoordenada("Digite a posição da comida no eixo vertical (0 a 79):");

        Comida comida = new Comida(x * Entidade.SIZE, y * Entidade.SIZE);

        JFrame frame = new JFrame("Competição de Robôs");
        frame.setContentPane(new DisputaRobosPainel(ciano, magenta, comida));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // Não pode mudar o tamanho do JFrame
        frame.pack();
        frame.setPreferredSize(new Dimension(RoboPanel.WIDTH + 10, RoboPanel.WIDTH + 10));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
