package com.company;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();


        if(Gamepanel.state == Gamepanel.STATE.MAINMENU) {
            if (mx >= 1500 / 2 - 230 && mx <= 1500 / 2 + 270) {
                // for the play button
                if (my >= 300 && my <= 500) {
                    // play button pressed
                    Gamepanel.state = Gamepanel.STATE.GAME;
                }   else if (my >= 550 && my <= 750) {
                    // difficulty button pressed
                    Gamepanel.state = Gamepanel.STATE.DIFFICULTYMENU;
                }  else if (my >= 800 && my <= 1000) {
                    // quit button pressed
                    System.exit(1);
                }
            }
        } else if (Gamepanel.state == Gamepanel.STATE.DIFFICULTYMENU) {
            if (mx >= 1500 / 2 - 230 && mx <= 1500 / 2 + 270) {
                if (my >= 300 && my <= 500) {
                    // easy button pressed
                    Gamepanel.mode = Gamepanel.MODE.EASY;
                }   else if (my >= 550 && my <= 750) {
                    // medium button pressed
                    Gamepanel.mode = Gamepanel.MODE.MEDIUM;
                }  else if (my >= 800 && my <= 1000) {
                    // difficult button pressed
                    Gamepanel.mode = Gamepanel.MODE.HARD;
                }
                Gamepanel.state = Gamepanel.STATE.MAINMENU;
            }
        } else if (Gamepanel.state == Gamepanel.STATE.GAMEOVER) {
            if (mx >= 1500 / 2 - 270 && mx <= 1500 / 2 + 280) {
                if (my >= 1500/2 + 100 && my <= 1500/2 + 300) {
                    Gamepanel.state = Gamepanel.STATE.GAME;
                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
