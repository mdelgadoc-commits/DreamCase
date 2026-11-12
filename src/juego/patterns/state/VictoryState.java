package juego.patterns.state;

import javax.swing.JOptionPane;

import juego.patterns.singleton.GameManager;
import juego.mvc.controller.GameController;
import juego.mvc.model.GameModel;
public class VictoryState implements GameState {

    private boolean mostrado = false;

    @Override
    public void handle(GameManager manager) {

        if (!mostrado) {
            mostrado = true;

            String player = manager.getPlayerName();
            String msg = (player != null)
                    ? "¡Felicidades " + player + "!\n¡Has completado el juego!"
                    : "¡Felicidades!\n¡Has completado el juego!";

            JOptionPane.showMessageDialog(
                null,
                msg,
                "Victoria",
                JOptionPane.INFORMATION_MESSAGE
            );
        }

        GameController controller = manager.getController();
        GameModel model = controller.getModel();

        model.reiniciarJuego();
        manager.resetScore();
        String nuevoNombre = JOptionPane.showInputDialog(null, "Ingresa tu nombre:");
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            manager.setPlayerName(nuevoNombre);
        }

        manager.setState(new PlayingState());
    }
}
