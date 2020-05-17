package com.company;

import java.awt.*;

public class GameOverMenu {
    private int xCoor, yCoor, fontSize;

    public Rectangle playButton = new Rectangle(1500/2 - 270, 1500/2 + 100, 550, 200);

    public GameOverMenu(int xCoor, int yCoor, int fontSize) {
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
        g.drawString("GAME OVER!", xCoor, yCoor);

        Font fnt1 = new Font("arial", Font.BOLD, 100);
        g.setFont(fnt1);
        g.drawString("Play Again", playButton.x + 20, playButton.y + 130);
        g2d.draw(playButton);
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
