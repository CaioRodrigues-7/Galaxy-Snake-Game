package org.galaxy.snake.game.ui.game;

import org.galaxy.snake.game.logic.Events;
import org.galaxy.snake.game.ui.Assets;

import javax.swing.*;

public class GameFrame extends JFrame {
    
    Assets assets = new Assets();
    Events events = new Events();
    GamePanel panel = new GamePanel();

    public void screen() 
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Galaxy Snake Game");
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setIconImage(assets.getIcon());
        this.setVisible(true);
        this.setResizable(false);
        events.requestFocusInWindow();
    }  
}
