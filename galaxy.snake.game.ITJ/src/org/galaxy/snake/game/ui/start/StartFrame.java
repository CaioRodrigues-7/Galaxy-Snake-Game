package org.galaxy.snake.game.ui.start;

import javax.swing.*;

public class StartFrame extends JFrame
{
    StartMenu startMenu = new StartMenu();
    StartPanel startPanel = new StartPanel();

    public void startScreen()
    {
        //Screen
        this.setTitle("Lobby Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(startPanel);
        this.setJMenuBar(startMenu.menuOptions());

        //Settings screen
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        Timer timerFrame = new Timer(1000, e ->
        {
            if(startPanel.getStartGame())
            {
                this.dispose();
                System.out.println("Closed windows");
                ((Timer) e.getSource()).stop();
            }
        });
        timerFrame.start();
    }
}
