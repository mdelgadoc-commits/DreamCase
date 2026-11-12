package juego.patterns.state;

import javax.swing.JOptionPane;

import juego.patterns.singleton.GameManager;


public class PausedState implements GameState {

    private boolean mensajeMostrado = false;

    @Override
    public void handle(GameManager manager) {

        
        if (!mensajeMostrado) {
            mensajeMostrado = true;

            JOptionPane.showMessageDialog(
                null,
                "Juego pausado.\nPresiona OK para continuar."
            );
        }

        manager.setState(new PlayingState());
    }
}
