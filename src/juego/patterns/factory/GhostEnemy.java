package juego.patterns.factory;

import java.awt.Graphics;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;

public class GhostEnemy extends Enemy {

    public GhostEnemy(int x, int y) {
        super(x, y);

   
        this.speed = 2;
        this.dx = Math.random() < 0.5 ? -speed : speed;
        this.dy = Math.random() < 0.5 ? -speed : speed;

        this.width = 70;
        this.height = 100;
    }

    @Override
    public void move(int maxWidth, int maxHeight) {

        x += dx;
        y += dy;


        if (x < 0) x = maxWidth;
        if (x > maxWidth) x = 0;
        if (y < 0) y = maxHeight;
        if (y > maxHeight) y = 0;
    }

    public Area getBounds() {
        return new Area(new Ellipse2D.Double(x, y, width, height));
    }

    public void paint(Graphics g) {
        ImageIcon monstruo = new ImageIcon(
                getClass().getResource("/images/Personaje_Monstruo.png")
        );

        g.drawImage(monstruo.getImage(), x, y, width, height, null);
    }
}
