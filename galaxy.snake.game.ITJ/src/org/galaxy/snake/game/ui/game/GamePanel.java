package org.galaxy.snake.game.ui.game;

import org.galaxy.snake.game.logic.Events;
import org.galaxy.snake.game.logic.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements MouseListener
{
    final private Events events;
    final public Timer timerPanel;
    final private Timer timerSnake;
    final private JButton pause;
    private boolean isPause;

    public GamePanel() 
    {
        setPreferredSize(new Dimension(1000, 600));
        setBackground(Color.BLACK);

        events = new Events();
        pause = new JButton();

        //Render game;
        timerPanel = new Timer(16, this::eventsPanel);
        timerPanel.start();

        timerSnake = new Timer(0, this::eventsSnake);
        timerSnake.start();

        pause.addMouseListener(this);

        this.addKeyListener(events);
        setDoubleBuffered(true);
        this.setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        requestFocusInWindow();
        drawBackground(g);
        drawNave(g);
        drawSnake(g);
        drawPanel(g);
        drawScore(g);
        drawVelocity(g);

        if(isPause)
        {
            System.out.println("print");
            drawPauseIcon(g);
        }

        drawButton();
        //drawAsteroids(g);
    }

    private void drawNave(Graphics g)
    {
        //Active anti-aliasing
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draw nave
        Tile food = events.getGame().getNave().getPosition();
        g2.drawImage(events.getGame().getAssets().getNaveImg(), food.x * events.getGame().getTileGame(), food.y * events.getGame().getTileGame(), events.getGame().getNave().getTileNave(), events.getGame().getNave().getTileNave(), null);
    }

    private void drawSnake(Graphics g)
    {
        //Active anti-aliasing
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //draw snake
        Tile snakeHead = events.getGame().getSnake().getHead();
        g2.setColor(Color.white);
        g2.fillRect(snakeHead.x * events.getGame().getTileGame(), snakeHead.y * events.getGame().getTileGame(), events.getGame().getSnake().getTileSnake(), events.getGame().getSnake().getTileSnake());

        for (Tile part : events.getGame().getSnake().getBody()) 
        {
            g2.setColor(Color.white);
            g2.fillRect(part.x * events.getGame().getTileGame(), part.y * events.getGame().getTileGame(), events.getGame().getSnake().getTileSnake(), events.getGame().getSnake().getTileSnake());
       }
    }

    private void drawBackground(Graphics g)
    {
        //Active anti-aliasing
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draw background
        g2.drawImage(events.getGame().getAssets().getBackgroundImageGame(), 0, 0, 1000, 600, null);
    }

    private void drawScore(Graphics g)
    {
        //Active anti-aliasing
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draw score
        g2.setFont(events.getGame().getAssets().getFont1());
        g2.drawString("Score: " + events.getGame().getSnake().getBody().size(), 430, 50);
    }

    private void drawVelocity(Graphics g)
    {
        //Active anti-aliasing
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draw velocity
        g2.setFont(events.getGame().getAssets().getFont1());
        g2.drawString("Velocity - " + events.getVelocityTimer(), 680, 50);
    }

    private void drawPanel(Graphics g)
    {
        //Active anti-aliasing
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draw panel
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, 1000, 77);

        //Draw border panel
        g2.setColor(Color.WHITE);
        g2.draw3DRect(0, 0, 999, 76, getFocusTraversalKeysEnabled());
    }

    private void drawPauseIcon(Graphics g)
    {
        //Active anti-aliasing
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Draw background
        g2.drawImage(events.getGame().getAssets().getPauseIcon(), 410, 200, 200, 200, null);
    }

    private void drawButton()
    {
        //Draw button
        if(!isPause)
        {
            events.getGame().getSounds().playLoop();
            pause.setText("Pause");
            pause.setBackground(Color.yellow);
        }
        else
        {
            pause.setText("Continue");
            pause.setBackground(new Color(10, 20,40));
        }

        pause.setBounds(10, 13, 300,50);
        pause.setFont(events.getGame().getAssets().getFont1());
        add(pause);
    }

    private void eventsPanel(ActionEvent e)
    {
        if(!isPause)
        {
            events.getGame().updateGame();
            repaint();
        }
    }

    private void eventsSnake(ActionEvent e)
    {
        if(!isPause)
        {
            events.getGame().updateSnakeMove();
            if (events.getBoost()) {
                timerSnake.setDelay(10);
            }
            if (!events.getBoost()) {
                timerSnake.setDelay(50);
            }
            repaint();
        }
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e)
    {
        System.out.println(true);
        isPause = !isPause;
        //Change pause value;
        if (isPause)
        {
            events.getGame().getSounds().stopLoop();
            pause.setText("Continue");
            pause.setBackground(new Color(10, 20, 40));
            timerPanel.stop();
            timerSnake.stop();
        }
        else
        {
            pause.setText("Pause");
            pause.setBackground(Color.YELLOW);
            timerPanel.start();
            timerSnake.start();
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

    }
}
