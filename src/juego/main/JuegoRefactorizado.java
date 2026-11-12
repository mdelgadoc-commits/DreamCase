package juego.main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import juego.mvc.model.GameModel;
import juego.mvc.view.GameView;
import juego.mvc.controller.GameController;
import juego.patterns.singleton.GameManager;

public class JuegoRefactorizado {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            GameManager gameManager = GameManager.getInstance();

            GameModel model = new GameModel();
            GameView view = new GameView(model);
            GameController controller = new GameController(model, view);

            gameManager.setController(controller);

            JFrame ventana = new JFrame("DreamCase - Juego");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setResizable(false);

            ventana.add(view);
            ventana.pack();
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);

            // requestFocusInWindow DESPUÉS de setVisible para que funcione el teclado
            view.requestFocusInWindow();

            // Iniciar el hilo del controlador (bucle principal)
            Thread t = new Thread(controller, "GameLoop");
            t.start();
        });
    }
}