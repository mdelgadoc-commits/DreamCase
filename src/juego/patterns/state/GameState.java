package juego.patterns.state;

import juego.patterns.singleton.GameManager;

public interface GameState {
    void handle(GameManager manager);
}
