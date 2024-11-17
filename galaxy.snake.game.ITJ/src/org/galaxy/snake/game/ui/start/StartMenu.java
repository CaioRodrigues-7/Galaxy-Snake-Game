package org.galaxy.snake.game.ui.start;

import javax.swing.*;

public class StartMenu extends JPanel
{
    JMenuBar menuBar = new JMenuBar();

    //Menu options
    JMenu jOptions = new JMenu("Options");
    JMenu jSettings = new JMenu("Settings");
    JMenu jReturn = new JMenu("Return");
    JMenuItem jMiVolume = new JMenuItem("Volume");

    public JMenuBar menuOptions()
    {
        menuBar.add(jOptions);
        jOptions.add(jSettings);
        jOptions.add(jReturn);
        jSettings.add(jMiVolume);

        return menuBar;
    }
}
