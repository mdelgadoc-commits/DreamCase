package juego.mvc.view;

import juego.mvc.model.GameModel;
import juego.patterns.factory.Enemy;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class GameView extends JPanel {

    private GameModel model;

    private ImageIcon fondo;
    private ImageIcon personaje;
    private ImageIcon puerta;

    // Imagen para todos los enemigos (si quieres, luego puedes diferenciarlos)
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

        // Fondo
        g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);

        // Puerta (meta)
        g.drawImage(puerta.getImage(), 800, 250, 200, 400, null);

        // Jugador
        g.drawImage(
            personaje.getImage(),
            model.getPlayerX(),
            model.getPlayerY(),
            model.getPlayerSize(),
            model.getPlayerSize(),
            null
        );

        // Enemigos (EnemyFactory)
        for (Enemy e : model.getEnemies()) {
            g.drawImage(
                monstruo.getImage(),
                e.getX(),
                e.getY(),
                e.getWidth(),
                e.getHeight(),
                null
            );
        }

        // UI - Puntaje y nivel
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString("Puntaje: " + model.getScore(), 520, 50);
        g.drawString("Nivel: " + model.getLevel(), 520, 80);

        // Nombre del jugador
        if (model.getPlayerName() != null) {
            g.setColor(Color.WHITE);
            g.drawString("Jugador: " + model.getPlayerName(), 20, 50);
        }
    }
}
