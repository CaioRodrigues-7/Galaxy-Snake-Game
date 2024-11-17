package org.galaxy.snake.game.ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class Assets 
{
    private Image naveImageGame;
    private Image backgroundImageGame;
    final private Image pauseIcon;
    final private Image iconImageGame;
    final private Image snakeBoostGame;
    final private ImageIcon pauseButtonGame;

    final private Image backgroundImageLobby;

    private Font font1;
    private Font font2;
    
    public Assets()
    {
        naveImageGame = new ImageIcon("src/Image/nave's/spaceships/Spaceship_3.png").getImage();
        iconImageGame = new ImageIcon("src/Image/icons/snakeIcon_1.png").getImage();
        backgroundImageGame = new ImageIcon("src/Image/backgrounds/Background_2.png").getImage();
        snakeBoostGame = new ImageIcon("src/Image/image_boost/boost_effect#1.gif").getImage();
        pauseButtonGame = new ImageIcon("src/Image/button/22542a901669d4c-4083091355(3).png");
        pauseIcon = new ImageIcon("src/Image/icons/pauseIcon_1.png").getImage();

        backgroundImageLobby = new ImageIcon("src/Image/backgrounds/Background_7.png").getImage();

        try 
        {
            font1 = Font.createFont(Font.TRUETYPE_FONT, new File("src/Fonts/SuperLegendBoy/SuperLegendBoy.ttf"));
            font2 = Font.createFont(Font.TRUETYPE_FONT, new File("src/Fonts/SuperLegendBoy/SuperLegendBoy.ttf"));

            font1 = font1.deriveFont(26f);
            font2 = font2.deriveFont(18f);
        } 
        catch (IOException | FontFormatException e)
        {
            System.out.println("Font unavailable in the moment.");
            font1 = new Font("Serif", Font.PLAIN, 22);
        }
    }  

    public Font getFont1() {
        return font1;
    }

    public Font getFont2() {
        return font2;
    }

    public Image getNaveImg()
    {
        return naveImageGame;
    }

    public void setNaveImg(Image naveImage)
    {
        this.naveImageGame = naveImage;
    }

    public Image getBackgroundImageGame()
    {
        return backgroundImageGame;
    }

    public void setBackgroundImageGame(Image newBackground)
    {
        this.backgroundImageGame = newBackground;
    }

    public Image getIcon()
    {
        return iconImageGame;
    }

    public Image getBoost()
    {
        return snakeBoostGame;
    }

    public ImageIcon getPauseButton()
    {
        return pauseButtonGame;
    }

    public Image getBackgroundImageLobby()
    {
        return backgroundImageLobby;
    }

    public Image getPauseIcon()
    {
        return pauseIcon;
    }
}
