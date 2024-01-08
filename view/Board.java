package com.codegym.task.task34.task3410.view;

import com.codegym.task.task34.task3410.controller.EventListener;
import com.codegym.task.task34.task3410.model.Direction;
import com.codegym.task.task34.task3410.model.GameObject;
import com.codegym.task.task34.task3410.model.GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel {
    private View view;
    private EventListener eventListener;

    public Board(View view) {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 500, 500);
        GameObjects gameObjects = view.getGameObjects();

        for (GameObject gameObject : gameObjects.getAll()) {
            gameObject.draw(g);
        }
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT)
                eventListener.move(Direction.LEFT);
            else if (key == KeyEvent.VK_RIGHT)
                eventListener.move(Direction.RIGHT);
            else if (key == KeyEvent.VK_UP)
                eventListener.move(Direction.UP);
            else if (key == KeyEvent.VK_DOWN)
                eventListener.move(Direction.DOWN);
            else if (key == KeyEvent.VK_R)
                eventListener.restart();
        }
    }
}
