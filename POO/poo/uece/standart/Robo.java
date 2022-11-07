package poo.uece.standart;

import poo.uece.exception.MovimentoInvalido;
import poo.uece.game.RoboPanel;
import java.awt.Color;
import java.util.Random;
/* Classe Robo que contendo dois atributos representando sua posição no eixo cartesiano e uma cor que o identifica. */
public class Robo extends Entidade {

    //Constantes
    public static final String UP = "up";
    public static final String DOWN = "down";
    public static final String RIGHT = "right";
    public static final String LEFT = "left";

    /**
     * Construtor que recebe a cor do robô e o inicialize na posição (0,0)
     *
     * @param cor
     */
    public Robo(Color cor) {
        super(0, 0, cor);
    }

    /**
     Move o robo em uma direção.
    
     @param direction Recebe como parâmetro uma String e altera a posição do robô.
     */
    public void mover(String direction) throws IllegalArgumentException, MovimentoInvalido {
        if (direction.equals(UP)) {
            up();
        } else if (direction.equals(DOWN)) {
            down();
        } else if (direction.equals(RIGHT)) {
            right();
        } else if (direction.equals(LEFT)) {
            left();
        } else {
            throw new IllegalArgumentException("Direção inválida: " + direction);
        }
    }

    /**
     * Move o robo em uma direção.
     *
     * @param direction Recebe como parâmetro um inteiro e altera a posição do robô.
     *                  1 representa “up”, 2 representa “down”, 3 representa “right” e 4 representa “left”.
     */
    public void mover(int direction) throws IllegalArgumentException, MovimentoInvalido {
        if (direction == 1) {
            up();
        } else if (direction == 2) {
            down();
        } else if (direction == 3) {
            right();
        } else if (direction == 4) {
            left();
        } else {
            throw new IllegalArgumentException("Movimento inválido para " + direction);
        }
    }

    /**
     * Move o robo para uma direção aleatória.
     */
    public void moverRandom() throws MovimentoInvalido {
        Random random = new Random();
        mover(random.nextInt(4) + 1);
    }

    /**
     * Move o robô no eixo y em uma posição acima.
     */
    private void up() throws MovimentoInvalido {
        int y = this.y - SIZE;
        if (posicaoIsValida(this.x, y)) {
            setY(y);
        } else throw new MovimentoInvalido(this.x, y, this);
    }

    /**
     * Move o robô no eixo y em uma posição abaixo.
     */
    private void down() throws MovimentoInvalido {
        int y = this.y + SIZE;
        if (posicaoIsValida(this.x, y)) {
            setY(y);
        } else throw new MovimentoInvalido(this.x, y, this);
    }

    /**
     * Move o robô no eixo x em uma posição para a direita.
     */
    private void right() throws MovimentoInvalido {
        int x = this.x + SIZE;
        if (posicaoIsValida(x, this.y)) {
            setX(x);
        } else throw new MovimentoInvalido(x, this.y, this);
    }

    /**
     * Move o robô no eixo x em uma posição para a esquerda.
     */
    private void left() throws MovimentoInvalido {
        int x = this.x - SIZE;
        if (posicaoIsValida(x, this.y)) {
            setX(x);
        } else throw new MovimentoInvalido(x, this.y, this);
    }

    /**
     * Valida se o Robo pode ir para a posição (x, y).
     * @param x x destino.
     * @param y y destino.
     * @return false se a posição é inválida, true, caso contrário.
     */
    public static boolean posicaoIsValida(int x, int y) {
        if (x < 0 || x > RoboPanel.WIDTH - SIZE || y < 0 || y > RoboPanel.HEIGTH - SIZE)
            return false;
        else
            return true;
    }

    /**
     *
     * @param comida
     * @return
     */
    public boolean achoComida(Comida comida) {
        return this.x == comida.getX() && this.y == comida.getY();
    }

    @Override
    public String toString() {
        return "Robo{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
