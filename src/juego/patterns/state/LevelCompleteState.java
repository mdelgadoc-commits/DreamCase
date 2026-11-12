package juego.patterns.state;

import javax.swing.JOptionPane;

import juego.patterns.singleton.GameManager;
import juego.mvc.controller.GameController;
import juego.mvc.model.GameModel;

public class LevelCompleteState implements GameState {

    private boolean mostrado = false;

    @Override
    public void handle(GameManager manager) {

        if (!mostrado) {
            mostrado = true;
            JOptionPane.showMessageDialog(
                null,
                "¡Nivel completado!"
            );
        }

        GameController controller = manager.getController();
        GameModel model = controller.getModel();

        model.nextLevel();

        if (model.getLevel() > 4) {  
            manager.setState(new VictoryState());
        } else {
            manager.setState(new PlayingState());
        }
    }
}
