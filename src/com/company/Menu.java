package com.company;

import java.awt.*;

public class Menu {
    private int xCoor, yCoor, fontSize;

    public Rectangle playButton = new Rectangle(1500/2 - 230, 300, 500, 200);
    public Rectangle helpButton = new Rectangle(1500/2 - 230, 550, 500, 200);
    public Rectangle quitButton = new Rectangle(1500/2 - 230, 800, 500, 200);

    public Menu(int xCoor, int yCoor, int fontSize) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        this.fontSize = fontSize;
    }

    public void tick() {

    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("arial", Font.BOLD, fontSize);
        g.setFont(fnt0);
        g.setColor(Color.WHITE);
        g.drawString("SNAKE GAME", xCoor, yCoor);

        Font fnt1 = new Font("arial", Font.BOLD, 100);
        g.setFont(fnt1);
        g.drawString("Play", playButton.x + 140, playButton.y + 130);
        g.drawString("Difficulty", helpButton.x + 40, helpButton.y + 130);
        g.drawString("Quit", quitButton.x + 140, quitButton.y + 130);
        g2d.draw(playButton);
        g2d.draw(helpButton);
        g2d.draw(quitButton);
    }

    public int getxCoor() {
        return xCoor;
    }

    public void setxCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    public int getyCoor() {
        return yCoor;
    }

    public void setyCoor(int yCoor) {
        this.yCoor = yCoor;
    }
}
