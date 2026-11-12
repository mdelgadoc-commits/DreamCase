
package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Juego extends JPanel {
    public static boolean colicion = false;
    public Ghost fantasma = new Ghost(this);  // instancia de Ghost
    public Prota yo = new Prota();

    public Juego() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                yo.KeyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ImageIcon habitacion = new ImageIcon(getClass().getResource("../images/habitacion.png"));
        g.drawImage(habitacion.getImage(), 0, 0, getWidth(), getHeight(), this);

      
        Font score = new Font("Arial", Font.BOLD, 25);
        g.setFont(score);
        g.setColor(Color.blue);
        g.drawString("Puntaje: " + fantasma.obtenerPuntos(), 520, 50);

       
        yo.paint(g);
        fantasma.paint(g);
        fantasma.mover();

        g.dispose();
    }

    public static void main(String[] args) {
        JFrame miVentana = new JFrame("Dream Case");
        Juego game = new Juego();
        miVentana.add(game);
        miVentana.setSize(1000, 700);
        miVentana.setVisible(true);
        miVentana.setResizable(false);
        miVentana.setLocationRelativeTo(null);
        miVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
          
            if (colicion || game.fantasma.nivel == 5) {
                if (game.fantasma.nivel == 5) {
                    JOptionPane.showMessageDialog(null, "¡Ganaste!");
                }
                int reiniciaJuego = JOptionPane.showConfirmDialog(null, "Reiniciamos?", "Perdiste!!", JOptionPane.YES_NO_OPTION);
                if (reiniciaJuego == 0) {
                    game.reiniciaValores();
                } else {
                    System.exit(0);
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
            game.repaint();
        }
    }

  
    public void reiniciaValores() {
        
        Prota.x = 10;
        Prota.y = 400;
        colicion = false;

        Ghost.puntos = 0;
        fantasma.nivel = 1;

        fantasma.reiniciaFantasmas();
    }
}
