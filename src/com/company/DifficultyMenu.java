package com.company;

import java.awt.*;

public class DifficultyMenu {
    private int xCoor, yCoor, fontSize;

    public Rectangle easyButton = new Rectangle(1500/2 - 230, 300, 500, 200);
    public Rectangle mediumButton = new Rectangle(1500/2 - 230, 550, 500, 200);
    public Rectangle hardButton = new Rectangle(1500/2 - 230, 800, 500, 200);

    public DifficultyMenu(int xCoor, int yCoor, int fontSize) {
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
        g.drawString("CHOOSE LEVEL", xCoor, yCoor);

        Font fnt1 = new Font("arial", Font.BOLD, 100);
        g.setFont(fnt1);
        g.drawString("Easy", easyButton.x + 140, easyButton.y + 130);
        g.drawString("Medium", mediumButton.x + 60, mediumButton.y + 130);
        g.drawString("Hard", hardButton.x + 140, hardButton.y + 130);
        g2d.draw(easyButton);
        g2d.draw(mediumButton);
        g2d.draw(hardButton);
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
