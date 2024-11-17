package org.galaxy.snake.game.logic;

import javax.swing.*;
import java.awt.event.*;

public class Events extends JPanel implements KeyListener, ActionListener
{
    final public Timer timerBoost;
    final private Game game;

    private int velocityGame;
    private boolean boost;

    public Events()
    {
        game = new Game();
        boost = false;
        velocityGame = 85;

        //Timer boost
        timerBoost = new Timer(800, this::eventBoost);
        timerBoost.start();


        //Event keyboard
        addKeyListener(this);
        setFocusable(true);                       
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W:
                if (game.getSnake().getVelocityY() != 1)
                {
                    game.getSnake().setVelocityX(0);
                    game.getSnake().setVelocityY(-1);
                }
                break;
            case KeyEvent.VK_S:
                if (game.getSnake().getVelocityY() != -1)
                {
                    game.getSnake().setVelocityX(0);
                    game.getSnake().setVelocityY(1);
                }
                break;
            case KeyEvent.VK_A:
                if (game.getSnake().getVelocityX() != 1)
                {
                    game.getSnake().setVelocityX(-1);
                    game.getSnake().setVelocityY(0);
                }
                break;
            case KeyEvent.VK_D:
                if (game.getSnake().getVelocityX() != -1)
                {
                    game.getSnake().setVelocityX(1);
                    game.getSnake().setVelocityY(0);
                }
                break;
            case KeyEvent.VK_SPACE:
                if(!boost)
                {
                    boost = true;
                    velocityGame = 10;
                    game.getSounds().playBoost();
                }
            break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {}

    //boost mode
    public void eventBoost(ActionEvent e)
    {
        if(boost)
        {
            boost = false;
        }
    }

    public Game getGame()
    {
        return game;
    }

    public boolean getBoost()
    {
        return boost;
    }

    public String getVelocityTimer()
    {
        while(boost)
        {
            if (velocityGame >= 85)
            {
                return "1.0x";

            }
            else if (velocityGame < 80 && velocityGame >= 65)
            {
                return "1.5x";
            }
            else
            {
                return "2.0x";
            }
        }
        return "1.0x";
    }



}
