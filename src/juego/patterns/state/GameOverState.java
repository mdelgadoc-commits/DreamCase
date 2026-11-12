package juego.patterns.state;

import javax.swing.JOptionPane;

import juego.patterns.singleton.GameManager;
import juego.mvc.controller.GameController;
import juego.mvc.model.GameModel;


public class GameOverState implements GameState {

    private boolean mostrado = false;

    @Override
    public void handle(GameManager manager) {

        if (!mostrado) {
            mostrado = true;
            JOptionPane.showMessageDialog(
                null,
                "¡Has perdido!\nEl juego se reiniciará."
            );
        }


        GameController controller = manager.getController();
        GameModel model = controller.getModel();

        model.resetGame();

        manager.setState(new PlayingState());
    }
}
