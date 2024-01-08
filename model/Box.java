package com.codegym.task.task34.task3410.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        int dx = getX() - (getWidth() / 2);
        int dy = getY() - (getHeight() / 2);
        graphics.setColor(Color.ORANGE);
        graphics.drawRect(dx, dy, getWidth(), getHeight());
        graphics.drawLine(dx, dy, dx + getWidth(), dy + getHeight());
        graphics.drawLine(dx + getWidth(), dy, dx, dy + getHeight());
    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }
}
