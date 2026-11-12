package juego.patterns.observer;

public class UIObserver implements GameObserver {
    @Override
    public void onCollision() {
        System.out.println("¡Colisión detectada! Mostrando efecto visual");
    }
    
    @Override
    public void onLevelComplete(int newLevel) {
        System.out.println("¡Nivel " + newLevel + " completado!");
    }
    
    @Override
    public void onScoreChange(int newScore) {
        System.out.println("Nuevo puntaje: " + newScore);
    }
}