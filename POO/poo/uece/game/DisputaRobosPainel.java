package poo.uece.game;

import poo.uece.exception.MovimentoInvalido;
import poo.uece.standart.Comida;
import poo.uece.standart.Entidade;
import poo.uece.standart.Robo;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.lang.Thread;

public class DisputaRobosPainel extends JPanel implements KeyListener {

    public static final int WIDTH = 400;
    public static final int HEIGTH = 400;

    private Robo roboA;
    private Robo roboB;

    private Comida comida;

    // informações do robo A e B
    private String msgRofapeA; 
    private String msgRofapeB; 

    private void init() {
        setPreferredSize(new Dimension(WIDTH, HEIGTH));
        setFocusable(true);
        setVisible(true);
        requestFocus();
        addKeyListener(this);
    }

    public DisputaRobosPainel(Robo roboA, Robo roboB, Comida comida) {
        init();
        this.roboA = roboA;
        this.roboB = roboB;
        this.comida = comida;
        this.msgRofapeA = this.msgRofapeB = "Pressione a tecla ENTER para iniciar";

    }

    @Override
    public void paint(Graphics g) {
        // Clear All
        g.clearRect(0,0, WIDTH, HEIGTH); 

        // Imprimindo os Robôs
        paintEntidade(g, roboA);
        paintEntidade(g, roboB);

        // Imprimindo a Comida
        paintEntidade(g, comida);

        // Definindo Cores e posições
        g.setColor(Color.cyan);
        g.drawString(this.msgRofapeA, 5, HEIGTH - Entidade.SIZE - 15);
        g.setColor(Color.magenta);
        g.drawString(this.msgRofapeB, 5, HEIGTH - Entidade.SIZE);
    }

    public void paintEntidade(Graphics g, Entidade entidade) {
        g.setColor(entidade.getColor());
        g.fillRect(entidade.getX(), entidade.getY(), Entidade.SIZE, Entidade.SIZE);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int k = event.getKeyCode();

        System.out.println(k);

        if (k == KeyEvent.VK_ENTER) {
            AtomicBoolean flag = new AtomicBoolean(true);

            AtomicInteger countA = new AtomicInteger();
            AtomicInteger countB = new AtomicInteger();

            new Thread(() -> {
                while (flag.get()) {
                    try {
                        roboA.moverRandom();
                        Thread.sleep(50);
                        countA.getAndIncrement();
                        this.msgRofapeA = roboA.toString();
                    } catch (MovimentoInvalido | InterruptedException e) {
                        this.msgRofapeA = e.getMessage();
                    } if (roboA.achoComida(comida)) {
                        flag.set(false);
                        this.msgRofapeA = this.roboA + " Robô Ciano achou a Comida!";
                        this.msgRofapeB = "Movimentos: roboCiano=" + countA + ", roboMagenta=" + countB;
                        paint(this.getGraphics());
                        this.setEnabled(false);
                    } else {
                        paint(this.getGraphics());
                    }
                }
            }).start();

            new Thread(() -> {
                while (flag.get()) {
                    try {
                        roboB.moverRandom();
                        Thread.sleep(50);
                        countB.getAndIncrement();
                        this.msgRofapeB = roboB.toString();
                    } catch (MovimentoInvalido | InterruptedException e) {
                        this.msgRofapeB = e.getMessage();
                    } if (roboB.achoComida(comida)) {
                        flag.set(false);
                        this.msgRofapeB = this.roboB + " Robô Magenta achou a Comida!";
                        this.msgRofapeA = "Movimentos: roboCiano=" + countA + ", roboMagenta=" + countB;
                        paint(this.getGraphics());
                        this.setEnabled(false);
                    } else {
                        paint(this.getGraphics());
                    }
                }
            }).start();

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
