package juego.mvc.controller;

import juego.mvc.model.GameModel;
import juego.mvc.view.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class GameController implements KeyListener, Runnable {

    private GameModel model;
    private GameView view;
    private volatile boolean running = true;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;

        view.addKeyListener(this);
        view.setFocusable(true);
    }

    public GameModel getModel() {
        return model;
    }

    @Override
    public void run() {
        while (running) {
            model.update();

            if (model.isGameOver()) {
                SwingUtilities.invokeLater(() ->
                    JOptionPane.showMessageDialog(null, "¡Perdiste!")
                );
                model.resetGame();
            }

            if (model.isWin()) {
                SwingUtilities.invokeLater(() ->
                    JOptionPane.showMessageDialog(null, "¡Ganaste! Completaste los 10 niveles!")
                );
                model.resetGame();
            }

            view.repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                running = false;
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        model.keyDown(e.getKeyCode()); // tecla presionada → modelo la registra
    }

    @Override
    public void keyReleased(KeyEvent e) {
        model.keyUp(e.getKeyCode()); // tecla soltada → modelo la elimina
    }

    @Override public void keyTyped(KeyEvent e) {}

    public void stop() {
        running = false;
    }
}