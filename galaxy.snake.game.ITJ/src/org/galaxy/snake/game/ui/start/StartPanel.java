package org.galaxy.snake.game.ui.start;

import org.galaxy.snake.game.ui.Assets;
import org.galaxy.snake.game.ui.game.GameFrame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StartPanel extends JPanel
{
    private final JButton buttonStart;
    private final JButton buttonExit;
    private final Border blackLine;
    private final Assets assets;
    private boolean startGame = false;
    GameFrame gameFrame = new GameFrame();

    public StartPanel()
    {
        setPreferredSize(new Dimension(1000, 500));
        setBackground(Color.LIGHT_GRAY);

        blackLine = BorderFactory.createLineBorder(Color.black);
        buttonStart = new JButton();
        buttonExit = new JButton();
        assets = new Assets();

        setDoubleBuffered(true);
        this.setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        drawBackground(g);
        drawButtons();
        drawCopy(g);
    }

    private void drawBackground(Graphics g)
    {
        //Active anti-aliasing
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.drawImage(assets.getBackgroundImageLobby(), 0, 0, 1000, 500, null);
    }

    private void drawCopy(Graphics g)
    {
        //Active anti-aliasing
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.setFont(assets.getFont2());
        g2.drawString("@Cerd-7", 440, 490);
    }

    private void drawButtons()
    {
        buttonStart.setText("Start");
        buttonStart.setFont(assets.getFont1());
        buttonStart.setBackground(Color.YELLOW);
        buttonStart.setBorder(blackLine);
        buttonStart.setBounds(350, 200, 285, 50);
        buttonStart.addActionListener(this::actionButtonStart);
        add(buttonStart);

        buttonExit.setText("Exit");
        buttonExit.setFont(assets.getFont1());
        buttonExit.setBackground(Color.YELLOW);
        buttonExit.setBorder(blackLine);
        buttonExit.setBounds(350, 260, 285, 50);
        buttonExit.addActionListener(this::actionButtonExit);
        add(buttonExit);
    }

    private void actionButtonStart(ActionEvent e)
    {
        gameFrame.screen();
        startGame = true;
    }

    private void actionButtonExit(ActionEvent e)
    {
        System.exit(0);
    }

    public boolean getStartGame()
    {
        return startGame;
    }

}
