package juego.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class GameSubject {
    private List<GameObserver> observers = new ArrayList<>();
    
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }
    
    public void notifyCollision() {
        for (GameObserver observer : observers) {
            observer.onCollision();
        }
    }
    
    public void notifyLevelComplete(int level) {
        for (GameObserver observer : observers) {
            observer.onLevelComplete(level);
        }
    }
    
    public void notifyScoreChange(int score) {
        for (GameObserver observer : observers) {
            observer.onScoreChange(score);
        }
    }
}