package org.galaxy.snake.game.logic;
import java.util.Random;

public class Nave 
{
    final private Tile position;
    final private int tileNave = 35;
    final private Random random = new Random();

    public Nave() 
    {
        position = new Tile(30, 25, tileNave, tileNave);
    }

    public void place()
    {
        int maxWidth = 900;
        int maxHeight = 500;
        int tileSize = 15;

        do {
            position.x = random.nextInt(maxWidth / tileSize);
            position.y = random.nextInt(maxHeight / tileSize);
        } while (position.y <= 20);
        System.out.println(position.y);
        System.out.println(position.x);
    }

    public Tile getPosition() 
    {                                                                     
        return position;
    }

    public int getTileNave()
    {
        return tileNave;
    }
}
