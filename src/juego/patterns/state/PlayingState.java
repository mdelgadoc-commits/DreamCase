package juego.patterns.state;

import juego.patterns.singleton.GameManager;
import juego.mvc.controller.GameController;
import juego.mvc.model.GameModel;

public class PlayingState implements GameState {

    @Override
    public void handle(GameManager manager) {

        GameController controller = manager.getController();
        GameModel model = controller.getModel();

       
        model.update();

        
        if (model.hayColision()) {
            manager.setState(new GameOverState());
            return;
        }

        
        if (model.getPlayer().llegaFinal()) {
            manager.setState(new LevelCompleteState());
            return;
        }

    }
}
