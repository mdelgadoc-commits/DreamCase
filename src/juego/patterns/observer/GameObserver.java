package juego.patterns.observer;

public interface GameObserver {
    void onCollision();
    void onLevelComplete(int newLevel);
    void onScoreChange(int newScore);
}