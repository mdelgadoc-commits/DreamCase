package juego.patterns.factory;

public class FastGhostEnemy extends Enemy {

    public FastGhostEnemy(int x, int y) {
        super(x, y);
        this.speed = 7;
    }

    @Override
    public void move(int maxWidth, int maxHeight) {

        x += speed;

        // Teletransportación lateral
        if (x > maxWidth - width) {
            x = 0;
        }
    }
}
