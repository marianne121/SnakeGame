package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Gamepanel extends JPanel implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 1500, HEIGHT = 1500;

    private Thread thread;

    private boolean running;

    private boolean right = true, left = false, up = false, down = false;

    private BodyPart b;
    private ArrayList<BodyPart> snake;

    private Apple apple;
    private ArrayList<Apple> apples;

    private Menu mainMenu;
    private DifficultyMenu difficultyMenu;
    private GameOverMenu gameOverMenu;

    private Random r;

    // starting size and coordinate
    private int xCoor = 10, yCoor = 10, size = 5;

    private int tileSize = 20;

    private int ticks = 0;

    private int speed;

    // not a great idea to make these public but leave it for now
    public static enum STATE {
        MAINMENU,
        DIFFICULTYMENU,
        GAME,
        GAMEOVER
    };

    public static STATE state = STATE.MAINMENU;

    public static enum MODE {
        EASY,
        MEDIUM,
        HARD
    };

    public static MODE mode = MODE.MEDIUM;

    public Gamepanel() {
        setFocusable(true);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
        this.addMouseListener(new MouseInput());

        snake = new ArrayList<BodyPart>();
        apples = new ArrayList<Apple>();

        r = new Random();

        start();
    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        if(state == STATE.GAME) {

            if(snake.size() == 0) {
                b = new BodyPart(xCoor, yCoor, tileSize);
                snake.add(b);
            }
            ticks++;
            if(mode == MODE.EASY) {
                speed = 800000;
            } else if (mode == MODE.MEDIUM) {
                speed = 500000;
            } else if (mode == MODE.HARD) {
                speed = 250000;
            }
            if(ticks > speed) {
                if (right) xCoor++;
                if (left) xCoor--;
                if (up) yCoor--;
                if (down) yCoor++;

                ticks = 0;

                b = new BodyPart(xCoor, yCoor, tileSize);
                snake.add(b);

                if (snake.size() > size) {
                    snake.remove(0);
                }
            }
            if (apples.size() ==0) {
                int xCoor = r.nextInt(WIDTH/tileSize);
                int yCoor = r.nextInt(HEIGHT/tileSize);

                apple = new Apple(xCoor, yCoor, tileSize);
                apples.add(apple);
            }

            for(int i=0; i < apples.size(); i++) {
                if(xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()) {
                    size++;
                    apples.remove(i);
                    i++;
                }
            }

            // snake hits body
            for (int i=0; i<snake.size(); i++) {
                if(xCoor == snake.get(i).getxCoor() && yCoor==snake.get(i).getyCoor()){
                    if(i != snake.size() - 1) {
                        System.out.println("Game Over!");
//                        stop();
                        resetGame();
                        state = STATE.GAMEOVER;
                    }
                }
            }

            // snake hits walls
            if(xCoor < 0 || xCoor > WIDTH/tileSize || yCoor <0 || yCoor > HEIGHT/tileSize) {
                System.out.println("Game Over!");
//                stop();
                resetGame();
                state = STATE.GAMEOVER;
            }
        }

    }

    public void paint(Graphics g) {
        g.clearRect(0,0, WIDTH, HEIGHT);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if(state == STATE.GAME) {

            for (int i=0; i<WIDTH/tileSize; i++) {
                g.drawLine(i * tileSize, 0, i*tileSize, HEIGHT);
            }
            for (int i=0; i<HEIGHT/tileSize; i++) {
                g.drawLine(0, i*tileSize, WIDTH, i*tileSize);
            }
            for (int i = 0; i< snake.size(); i++) {

                snake.get(i).draw(g);
            }
            for (int i =0; i< apples.size(); i++) {

                apples.get(i).draw(g);
            }
        } else if (state == STATE.MAINMENU) {
            mainMenu = new Menu(420, 200, 100);
            mainMenu.draw(g);
        } else if (state == STATE.DIFFICULTYMENU) {
            difficultyMenu = new DifficultyMenu(420, 200, 100);
            difficultyMenu.draw(g);
        } else if (state == STATE.GAMEOVER) {
            gameOverMenu = new GameOverMenu(420, HEIGHT/2, 100);
            gameOverMenu.draw(g);
        }
    }

    @Override
    public void run() {
        while(running) {
            tick();
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // pick up right click & snake not going towards the left
        if(state == STATE.GAME) {
            if(key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
            } else if(key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            } else if(key == KeyEvent.VK_UP && !down) {
                up = true;
                right = false;
                left = false;
            } else if(key == KeyEvent.VK_DOWN && !up) {
                down = true;
                right = false;
                left = false;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void resetGame() {
        xCoor = 10;
        yCoor = 10;
        size = 5;
        ticks = 0;
        snake = new ArrayList<BodyPart>();
        apples = new ArrayList<Apple>();
        up = false;
        right = true;
        left = false;
        down = false;
    }

}
