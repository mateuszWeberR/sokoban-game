package com.codegym.task.task34.task3410.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable {
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        int dx = getX() - (getWidth() / 2);
        int dy = getY() - (getHeight() / 2);
        graphics.setColor(Color.BLUE);
        graphics.drawOval(dx, dy, getWidth(), getHeight());
    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }
}
