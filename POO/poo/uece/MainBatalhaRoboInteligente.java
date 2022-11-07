/*
Crie uma subclasse RoboInteligente que sobrescreve o método mover de forma que se robô fez um movimento inválido,
garanta que o próximo movimento será válido. Cria uma classe Main que instancie um robô normal e outro inteligente,
peça ao usuário para entrar com a posição do alimento, e faça os dois robôs se moverem randomicamente, um de
cada vez, até que um deles encontre o alimento. Ao final, mostre quem achou o alimento e o número de movimentos
que cada robô fez.
*/


package poo.uece;

import poo.uece.standart.Robo;
import poo.uece.standart.Comida;
import poo.uece.standart.Entidade;
import poo.uece.standart.RoboInteligente;
import poo.uece.game.DisputaRobosPainel;
import poo.uece.game.RoboPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Color;

import static poo.uece.Main.pegarCoordenada;


public class MainBatalhaRoboInteligente {

    public static void main(String[] args) {
        Robo ciano = new Robo(Color.cyan); // Robo normal
        Robo magenta = new RoboInteligente(Color.magenta); // Robo Inteligente

        // Posição da Comida
        int x, y;

        x = pegarCoordenada("Digite a posição da comida no eixo horizontal (0 a 79):");
        y = pegarCoordenada("Digite a posição da comida no eixo vertical (0 a 79):");
        Comida comida = new Comida(x * Entidade.SIZE, y * Entidade.SIZE);

        JFrame frame = new JFrame("Competição de Robôs Inteligentes");
        frame.setContentPane(new DisputaRobosPainel(ciano, magenta, comida));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // Não se pode mudar o tamanho do JFrame
        frame.pack();
        frame.setPreferredSize(new Dimension(RoboPanel.WIDTH + 10, RoboPanel.WIDTH + 10));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
