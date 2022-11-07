/* Crie uma classe Main que instancie um robô, peça ao usuário para determinar a posição do alimento,
 e peça ao usuário para ficar movendo o robô até ele encontrar o alimento – não esqueça de tratar a exceção.*/

package poo.uece;

import poo.uece.game.RoboPanel;
import poo.uece.standart.Robo;
import poo.uece.standart.Comida;
import poo.uece.standart.Entidade;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Color;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Robo robo = new Robo(Color.black);
        // Posição da Comida
        int x, y;

        x = pegarCoordenada("Digite a posição da comida no eixo horizontal (0 a 79):");
        y = pegarCoordenada("Digite a posição da comida no eixo vertical (0 a 79):");

        Comida comida = new Comida(x * Entidade.SIZE, y * Entidade.SIZE);

        JFrame quadro = new JFrame("Robô caça comida");
        quadro.setContentPane(new RoboPanel(robo, comida));
        quadro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quadro.setResizable(false); // Não pode mudar o tramano do Jquadro
        quadro.pack();
        quadro.setPreferredSize(new Dimension(RoboPanel.WIDTH + 10, RoboPanel.WIDTH + 10));
        quadro.setLocationRelativeTo(null);
        quadro.setVisible(true);
    }

    /**
    Metodo recursivo para garantir que o valor digitado pelo usuário esteja entre 0 e 79.
    @param msg
    @return valor entre 0 e 79.
    */
    public static int pegarCoordenada(String msg) {
        Scanner entrada = new Scanner(System.in);
        int valor;
        System.out.println(msg);
        try {
            valor = entrada.nextInt();
            if (valor < 0 || valor > 79) throw new IllegalArgumentException();
        } catch (InputMismatchException e) {
            System.err.println("O caractere digitado não é um número válido.");
            valor = pegarCoordenada(msg);
        } catch (IllegalArgumentException e) {
            System.err.println("O valor não se encontra no intervalo válido (entre 0 e 79).");
            valor = pegarCoordenada(msg);
        }
        return valor;
        
    }

}
