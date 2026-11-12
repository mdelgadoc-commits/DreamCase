package juego.main;

import javax.swing.JFrame;

import juego.mvc.model.GameModel;
import juego.mvc.view.GameView;
import juego.mvc.controller.GameController;

public class GameLauncher {

    public static void main(String[] args) {
        
        GameModel model = new GameModel();
        GameView view = new GameView(model);
        GameController controller = new GameController(model, view);

        JFrame window = new JFrame("Dream Case - MVC");
        window.setSize(1000, 700);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.add(view);
        window.setVisible(true);

        Thread loop = new Thread(controller);
        loop.start();
    }
}
