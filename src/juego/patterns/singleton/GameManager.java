package juego.patterns.singleton;

import juego.mvc.controller.GameController;

public class GameManager {

    private static volatile GameManager instance;

    private String playerName = "Jugador";
    private GameController controller;

    private GameManager() {}

    public static GameManager getInstance() {
        if (instance == null) {
            synchronized (GameManager.class) {
                if (instance == null) {
                    instance = new GameManager();
                }
            }
        }
        return instance;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public GameController getController() {
        return controller;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }
}