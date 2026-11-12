package juego.mvc.view;

import juego.mvc.model.GameModel;
import juego.Ghost.Fantasma;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Dimension;

public class GameView extends JPanel {

    private GameModel model;

    private ImageIcon fondo;
    private ImageIcon personaje;
    private ImageIcon puerta;
    private ImageIcon monstruo;

    public GameView(GameModel model) {
        this.model = model;

        fondo = new ImageIcon(getClass().getResource("/images/habitacion.png"));
        personaje = new ImageIcon(getClass().getResource("/images/Personaje.png"));
        monstruo = new ImageIcon(getClass().getResource("/images/Personaje_Monstruo.png"));
        puerta = new ImageIcon(getClass().getResource("/images/puerta.png"));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 700);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);

        g.drawImage(puerta.getImage(), 800, 250, 200, 400, null);

        g.drawImage(personaje.getImage(),
                model.getPlayerX(),
                model.getPlayerY(),
                100,
                100,
                null
        );

        // Dibujar fantasmas usando la lista de Fantasma
        for (Fantasma f : model.getGhosts()) {
            g.drawImage(monstruo.getImage(), f.x, f.y, 70, 100, null);
        }

        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString("Puntaje: " + model.getScore(), 520, 50);
        g.drawString("Nivel: " + model.getLevel(), 520, 80);

        if (model.getPlayerName() != null) {
            g.setColor(Color.WHITE);
            g.drawString("Jugador: " + model.getPlayerName(), 20, 50);
        }
    }
}
