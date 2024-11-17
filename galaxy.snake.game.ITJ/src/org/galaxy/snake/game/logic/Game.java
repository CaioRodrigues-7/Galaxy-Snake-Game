package org.galaxy.snake.game.logic;

import org.galaxy.snake.game.ui.Assets;

import java.util.List;

import javax.swing.*;

public class Game
{
    final private Snake snake;
    final private Nave nave;
    final private Assets assets;
    final private Sounds music;
    final private Sounds explosion;
    final private Sounds boost;
    final private Sounds sounds;

    final private int tileGame = 15;
    boolean gameOver = false;
    boolean changeImage;

    public Game()
    {
        sounds = new Sounds();
        boost = new Sounds();
        music = new Sounds();
        explosion = new Sounds();

        snake = new Snake();
        nave = new Nave();
        assets = new Assets();
    }

    public void updateSnakeMove() {
        snake.move();
    }

    public void updateGame()
    {
        changeBackgroundImage();
        checkAllCollision();
    }

    private void checkAllCollision()
    {
        if (checkCollisionNave()) {
            isCollisionNave();
        }

        if (collisionLimits() || collisionBody())
        {
            gameOver = true;
            isGameOver();
        }
    }

    //Change image when snake destroyer naves
    private void changeBackgroundImage()
    {
        if(snake.getBody().size() <= 10)
        {
            assets.setBackgroundImageGame(new ImageIcon("src/Image/backgrounds/Background_2.png").getImage());
        }
        else if(snake.getBody().size() >= 10)
        {
            assets.setBackgroundImageGame(new ImageIcon("src/Image/backgrounds/Background_4.png").getImage());
        }
        else if(snake.getBody().size() >= 25)
        {
            assets.setBackgroundImageGame(new ImageIcon("src/Image/backgrounds/Background_5.png").getImage());
        }
        else
        {
            assets.setBackgroundImageGame(new ImageIcon("src/Image/backgrounds/Background_7.png").getImage());
        }
    }

    //Check relation with nave
    private boolean checkCollisionNave()
    {
        // Set limits Nave
        int naveLeft = nave.getPosition().x * tileGame;
        int naveRight = nave.getPosition().x * tileGame + nave.getPosition().width;
        int naveTop = nave.getPosition().y * tileGame;
        int naveBottom = nave.getPosition().y * tileGame + nave.getPosition().height;

        // Set limits of head snake
        int snakeLeft = snake.getHead().x * tileGame;
        int snakeRight = snake.getHead().x * tileGame + snake.getHead().width;
        int snakeTop = snake.getHead().y * tileGame;
        int snakeBottom = snake.getHead().y * tileGame + snake.getHead().height;

        // Verify relation between rectangle
        return snakeRight >= naveLeft && snakeLeft <= naveRight && snakeBottom >= naveTop && snakeTop <= naveBottom;
    }

    private void isCollisionNave()
    {
        changeImage = true;
        playExplosionInThread();
        nave.place();
        snake.getBody().add(new Tile(snake.getHead().x, snake.getHead().y, snake.getHead().width, snake.getHead().height));
    }

    private void playExplosionInThread()
    {
        new Thread(explosion::playExplosion).start();
    }

    private boolean collisionLimits()
    {
        return snake.getHead().x * tileGame < 0 || snake.getHead().y * tileGame < 75 || snake.getHead().x * tileGame > 1000 || snake.getHead().y * tileGame > 590;
    }

    private boolean collisionBody()
    {
        List<Tile> body = snake.getBody();
        for (int i = 0; i < body.size() - 1; i++)
        {  // Ignore the last segment
            Tile snakePart = body.get(i);
            if (snake.getHead().x * tileGame == snakePart.x * tileGame &&
                    snake.getHead().y * tileGame == snakePart.y * tileGame)
            {
                System.out.println("Collision with body");
                return true;
            }
        }
        return false;
    }

    private void isGameOver()
    {
        toTryAgain();
    }

    private void toTryAgain()
    {
        int interaction = JOptionPane.showConfirmDialog(null, "YOU CAN TRY AGAIN!!! WOULD YOU LIKE TO??", "YOU LOSE", JOptionPane.YES_NO_OPTION);
        if (interaction == JOptionPane.YES_OPTION)
        {
            resetGame();
        }
        else
        {
            exitGame();
        }
    }

    private void resetGame()
    {
        snake.setHead(new Tile(30, 20, snake.getTileSnake(), snake.getTileSnake()));
        gameOver = false;
        snake.getBody().removeAll(snake.getBody());
        snake.setVelocityX(0);
        snake.setVelocityY(0);
        nave.place();
    }

    private void exitGame()
    {
        music.closeLoop();
        explosion.closeExplosion();
        boost.closeBoost();
        System.exit(0);
    }

    public Snake getSnake()
    {
        return snake;
    }
    public Nave getNave()
    {
        return nave;
    }
    public int getTileGame()
    {
        return tileGame;
    }
    public Assets getAssets()
    {
       return assets;
    }
    public Sounds getSounds()
    {
        return sounds;
    }
}
