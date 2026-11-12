package juego.patterns.factory;

public abstract class Enemy {

    protected int x, y;
    protected int width = 70;
    protected int height = 100;

    // Velocidad base
    protected int speed = 3;

    // Dirección de movimiento (NECESARIO PARA GhostEnemy)
    protected int dx = 0;
    protected int dy = 0;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void move(int maxWidth, int maxHeight);

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
