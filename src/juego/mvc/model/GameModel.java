package juego.mvc.model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import juego.patterns.factory.Enemy;
import juego.patterns.factory.EnemyFactory;
import juego.patterns.singleton.GameManager;

public class GameModel {

    private int playerX = 10;
    private int playerY = 400;
    private final int playerSize = 100;
    private final int playerSpeed = 4; // más suave al ser fluido

    private int score = 0;
    private int level = 1;
    private static final int MAX_LEVELS = 10;
    private boolean gameOver = false;
    private boolean win = false;

    // Teclas actualmente presionadas
    private final Set<Integer> keysPressed = new HashSet<>();

    private final List<Enemy> enemies = new ArrayList<>();
    private final int screenWidth = 1000;
    private final int screenHeight = 700;
    private final Random rnd = new Random();

    public GameModel() {
        spawnEnemiesForLevel(level);
    }

    // Llamado desde keyPressed del controller
    public void keyDown(int keyCode) {
        keysPressed.add(keyCode);
    }

    // Llamado desde keyReleased del controller
    public void keyUp(int keyCode) {
        keysPressed.remove(keyCode);
    }

    // Aplica el movimiento según las teclas actualmente presionadas
    private void applyMovement() {
        if (keysPressed.contains(KeyEvent.VK_LEFT) || keysPressed.contains(KeyEvent.VK_A)) {
            if (playerX > 0) playerX -= playerSpeed;
        }
        if (keysPressed.contains(KeyEvent.VK_RIGHT) || keysPressed.contains(KeyEvent.VK_D)) {
            if (playerX < screenWidth - playerSize) playerX += playerSpeed;
        }
        if (keysPressed.contains(KeyEvent.VK_UP) || keysPressed.contains(KeyEvent.VK_W)) {
            if (playerY > 0) playerY -= playerSpeed;
        }
        if (keysPressed.contains(KeyEvent.VK_DOWN) || keysPressed.contains(KeyEvent.VK_S)) {
            if (playerY < screenHeight - playerSize) playerY += playerSpeed;
        }
    }

    // Ya no se usa directamente, se mantiene por compatibilidad si algo lo llama
    public void movePlayer(KeyEvent e) {
        keyDown(e.getKeyCode());
    }

    public void update() {
        if (gameOver || win) return;

        applyMovement();

        for (Enemy e : enemies) {
            e.move(screenWidth, screenHeight);
        }

        for (Enemy e : enemies) {
            if (enemyCollidesWithPlayer(e)) {
                gameOver = true;
                return;
            }
        }

        if (playerReachedGoal()) {
            score += 100 * level;
            if (level >= MAX_LEVELS) {
                win = true;
            } else {
                level++;
                resetPlayer();
                spawnEnemiesForLevel(level);
            }
        }
    }

    private boolean enemyCollidesWithPlayer(Enemy e) {
        int ex = e.getX();
        int ey = e.getY();
        int ew = e.getWidth() > 0 ? e.getWidth() : 70;
        int eh = e.getHeight() > 0 ? e.getHeight() : 100;

        return playerX < ex + ew &&
               playerX + playerSize > ex &&
               playerY < ey + eh &&
               playerY + playerSize > ey;
    }

    private void spawnEnemiesForLevel(int level) {
        enemies.clear();

        // Niveles 1-3: pocos enemigos y casi todos lentos
        // Niveles 4-10: progresión normal
        int count;
        double fastChance;

        switch (level) {
            case 1:
                count = 2;
                fastChance = 0.0;  // solo lentos
                break;
            case 2:
                count = 2;
                fastChance = 0.05; // 5% rápidos
                break;
            case 3:
                count = 3;
                fastChance = 0.10; // 10% rápidos
                break;
            default:
                // Nivel 4 en adelante: progresión gradual
                count = 2 + level + (level == MAX_LEVELS ? 1 : 0);
                fastChance = Math.min(0.05 + (level - 1) * 0.077, 0.75);
                break;
        }

        for (int i = 0; i < count; i++) {
            String type = (rnd.nextDouble() < fastChance) ? "fast" : "normal";
            enemies.add(EnemyFactory.createEnemy(type, screenWidth, screenHeight));
        }
    }
    public void resetPlayer() {
        playerX = 10;
        playerY = 400;
        keysPressed.clear(); // limpia teclas al resetear para evitar movimiento fantasma
    }

    public void resetGame() {
        score = 0;
        level = 1;
        gameOver = false;
        win = false;
        resetPlayer();
        spawnEnemiesForLevel(level);
    }

    public int getPlayerX() { return playerX; }
    public int getPlayerY() { return playerY; }
    public int getPlayerSize() { return playerSize; }

    public List<Enemy> getEnemies() { return enemies; }

    public int getScore() { return score; }
    public int getLevel() { return level; }
    public int getMaxLevels() { return MAX_LEVELS; }
    public boolean isGameOver() { return gameOver; }
    public boolean isWin() { return win; }

    public String getPlayerName() {
        String n = GameManager.getInstance().getPlayerName();
        return (n != null && !n.trim().isEmpty()) ? n : "Jugador";
    }

    public boolean playerReachedGoal() {
        return playerX > 850 && playerY > 350;
    }

    public void nextLevel() {
        if (level >= MAX_LEVELS) {
            win = true;
            return;
        }
        level++;
        resetPlayer();
        spawnEnemiesForLevel(level);
    }
}