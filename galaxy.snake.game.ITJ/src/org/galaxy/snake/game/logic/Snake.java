package org.galaxy.snake.game.logic;

import java.util.ArrayList;

public class Snake 
{
    private Tile head;
    final private ArrayList<Tile> body;
    private int velocityX;
    private int velocityY;
    final private int tileSnake = 10;

    public Snake() 
    {
        head = new Tile(10, 10, tileSnake, tileSnake);
        body = new ArrayList<>();
        velocityX = 0;
        velocityY = 0;
    }

    public void move() 
    {
        for (int i = body.size() - 1; i >= 0; i--) 
        {
            Tile part = body.get(i);
            if (i == 0) 
            {
                part.x = head.x;
                part.y = head.y;
            } 
            else 
            {
                Tile prevPart = body.get(i - 1);
                part.x = prevPart.x;
                part.y = prevPart.y;
            }
        }
        head.x += velocityX;
        head.y += velocityY;
    }

    public void setHead(Tile newHead)
    {
        this.head = newHead;
    }

    public Tile getHead() 
    {
        return head;
    }

    public ArrayList<Tile> getBody() 
    {
        return body;
    }

    public int getTileSnake() 
    {
        return tileSnake;
    }

    public int getVelocityX() 
    {
        return velocityX;
    }

    public void setVelocityX(int velocityX) 
    {
        this.velocityX = velocityX;
    }

    public int getVelocityY() 
    {
        return velocityY;
    }

    public void setVelocityY(int velocityY) 
    {
        this.velocityY = velocityY;
    }
}
