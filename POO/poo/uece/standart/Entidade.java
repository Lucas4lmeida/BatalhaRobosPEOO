package poo.uece.standart;

import java.awt.*;

/**
 * Classe abistrata para representar os presonagens.
 */
public abstract class Entidade {

    // Tamanho de uma entidade. Considere a área fixa de um quadrado com 5 unidades de lado.
    public static final int SIZE = 5;

    protected int x, y;

    protected Color color;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Entidade(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

}
